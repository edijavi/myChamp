/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mychamp.bll.Match;
import mychamp.bll.Team;
import mychamp.gui.util.StringLibrary;
import static java.util.Comparator.comparing;

/**
 *
 * @author Adam
 */
public class MatchModel
{

    /**
     * The observable lists, used for data binding the view to the model.
     */
    private final ObservableList<Match> matches;
    private int matchCounter;
    private boolean quarterFinalsReady;
    private boolean semiFinalsGenerated;
    private boolean quarterFinalsGenerated;
    private boolean semiFinalsReady;
    private boolean finalsReady;
    private boolean finalScheduleReady;

    private final List<String> groupNames;

    private final int[][] scheduleForThree =
    {
        {
            0, 1
        },
        {
            1, 2
        },
        {
            2, 0
        },
        {
            1, 0
        },
        {
            2, 1
        },
        {
            0, 2
        }
    };
    private final int[][] scheduleForFour =
    {
        {
            0, 1, 2, 3
        },
        {
            1, 2, 3, 0
        },
        {
            2, 0, 3, 1
        },
        {
            1, 0, 3, 2
        },
        {
            2, 1, 0, 3
        },
        {
            0, 2, 1, 3
        }
    };

    private final int[][] scheduleQuarterFinals =
    {
        {
            0, 1, 2, 3
        },
        {
            1, 2, 3, 0
        }
    };

    private final int[][] scheduleSemiFinals =
    {
        {
            1, 2
        },
        {
            3, 4
        }
    };

    public MatchModel()
    {
        matches = FXCollections.observableArrayList();
        matchCounter = 0;
        quarterFinalsReady = false;
        semiFinalsReady = false;
        quarterFinalsGenerated = false;
        semiFinalsGenerated = false;
        finalsReady = false;
        finalScheduleReady = false;
        groupNames = new ArrayList<>();
        groupNames.add(StringLibrary.GROUP1);
        groupNames.add(StringLibrary.GROUP2);
        groupNames.add(StringLibrary.GROUP3);
        groupNames.add(StringLibrary.GROUP4);
    }

    public ObservableList<Match> getMatches()
    {
        return matches;
    }

    public boolean areQuarterFinalsReady()
    {
        return quarterFinalsReady;
    }

    /**
     * Method finds match by its ID
     *
     * @param id
     * @return
     */
    public Match getMatchByID(String id)
    {

        for (Match match : matches)
        {
            if (match.getId().equals(id))
            {
                return match;
            }
        }
        return null;
    }

    /**
     * Method save updated data for match
     *
     * @param newMatchData
     */
    public void updateMatch(Match newMatchData)
    {
        Match oldMatchData = getMatchByID(newMatchData.getId());
        if (oldMatchData != null)
        {
            oldMatchData.setGuestScore(newMatchData.getGuestScore());
            oldMatchData.setHomeScore(newMatchData.getHomeScore());
            if (!oldMatchData.isPlayed())
            {
                oldMatchData.getHome().addMatchesPlayed();
                oldMatchData.getGuest().addMatchesPlayed();
                oldMatchData.setPlayed(true);
            }

            // update points and golas difference for teams
            recalculateTeamPointsAndGD(oldMatchData.getGuest());
            recalculateTeamPointsAndGD(oldMatchData.getHome());

            if (oldMatchData.getId() == "F0001")
            {
                generateFinalRanking();
            } else
            {
                prepareQuarterFinals();
            }
        }
    }

    // main method for managing state and scheduling during the finals
    private void prepareQuarterFinals()
    {
        boolean startQuarterFinals = true;
        boolean startSemiFinals = true;
        boolean startFinals = true;
        // tournament isn't over
        if (!finalScheduleReady)
        {
            for (Match match : matches)
            {
                if (match.getRound() < 7 && !match.isPlayed())
                {
                    startQuarterFinals = false;
                }
                if (match.getRound() == 7 && !match.isPlayed())
                {
                    startSemiFinals = false;
                }
                if (match.getRound() == 8 && !match.isPlayed())
                {
                    startFinals = false;
                }
            }

            if (quarterFinalsReady && startSemiFinals)
            {
                semiFinalsReady = true;
            }

            if (startQuarterFinals)
            {
                quarterFinalsReady = true;
            }

            if (quarterFinalsReady)
            {
                if (finalsReady && startFinals)
                {
                    prepareFinals();
                } else if (semiFinalsReady && !semiFinalsGenerated)
                {
                    prepareSemiFinals();
                } else if (!quarterFinalsGenerated)
                {
                    prepareQuarterFinals(calculateGroupRanks());
                }
            }
        }
    }

    // preparation of first round of finals (quarter-finals)
    private void prepareQuarterFinals(ArrayList<ArrayList> lists)
    {
        String id = new String();
        int i = 1;
        for (int[] is : scheduleQuarterFinals)
        {
            id = "QF" + String.format("%03d", i);
            Match match1 = new Match(id, (Team) lists.get(0).get(is[0]), (Team) lists.get(1).get(is[1]), StringLibrary.QFINAL, 7);
            matches.add(match1);
            i++;
            id = "QF" + String.format("%03d", i);
            Match match2 = new Match(id, (Team) lists.get(0).get(is[2]), (Team) lists.get(1).get(is[3]), StringLibrary.QFINAL, 7);
            matches.add(match2);
            i++;
        }
        quarterFinalsGenerated = true;
    }

    /**
     * Method process teams data and groups then, schedule is created afterwards
     *
     * @param teams
     */
    public void prepareMatches(ObservableList<Team> teams)
    {
        Map<String, ArrayList<Team>> groups = new HashMap<>();

        // sorting teams by groups
        for (String groupName : groupNames)
        {
            ArrayList<Team> groupTeams = new ArrayList<>();
            for (Team team : teams)
            {
                if (team.getGroup().equals(groupName))
                {
                    groupTeams.add(team);
                }
            }
            groups.put(groupName, groupTeams);
        }

        // matchmaking
        for (String groupsName : groupNames)
        {
            if (groups.get(groupsName).size() == 3)
            {
                matchMakingForThree(groupsName, groups.get(groupsName));
            } else if (groups.get(groupsName).size() == 4)
            {
                matchMakingForFour(groupsName, groups.get(groupsName));
            }
        }
    }

    // creating schedule for group of 3 teams by given rules
    private void matchMakingForThree(String groupsName, ArrayList<Team> teams)
    {
        String id = new String();
        int i = 1;
        for (int[] is : scheduleForThree)
        {
            id = "MC" + String.format("%03d", matchCounter);
            Match match1 = new Match(id, teams.get(is[0]), teams.get(is[1]), groupsName, i);
            matches.add(match1);
            matchCounter++;
            i++;
        }
    }

    // creating schedule for group of 4 teams by given rules
    private void matchMakingForFour(String groupsName, ArrayList<Team> teams)
    {
        String id = new String();
        int i = 1;
        for (int[] is : scheduleForFour)
        {
            id = "MC" + String.format("%03d", matchCounter);
            Match match1 = new Match(id, teams.get(is[0]), teams.get(is[1]), groupsName, i);
            matchCounter++;
            id = "MC" + String.format("%03d", matchCounter);
            Match match2 = new Match(id, teams.get(is[2]), teams.get(is[3]), groupsName, i);
            matchCounter++;
            matches.add(match1);
            matches.add(match2);
            i++;
        }
    }

    // method set current points and GD value for given team
    private void recalculateTeamPointsAndGD(Team team)
    {
        int gd = 0;
        int points = 0;
        int goals = 0;
        for (Match match : matches)
        {
            if (match.isPlayed())
            {
                if (match.getHome() == team)
                {
                    gd += match.getHomeScore() - match.getGuestScore();
                    points += calculatePoints(match.getGuestScore(), match.getHomeScore());
                    goals += match.getHomeScore();
                } else if (match.getGuest() == team)
                {
                    gd += match.getGuestScore() - match.getHomeScore();
                    points += calculatePoints(match.getHomeScore(), match.getGuestScore());
                    goals += match.getGuestScore();
                }
            }
        }
        team.setPoints(points);
        team.setGd(gd);
        team.setGoals(goals);
    }

    // get points based on match result
    private int calculatePoints(int goalsIn, int goalsOut)
    {
        if (goalsIn < goalsOut)
        {
            return 3;
        } else if (goalsIn == goalsOut)
        {
            return 1;
        } else
        {
            return 0;
        }
    }

    /** return all matches in given group
     *
     * @param newSelection
     * @return
     */
    public ObservableList<Match> getMatchesFilteredByGroup(String newSelection)
    {
        ObservableList<Match> matchesFiltered = FXCollections.observableArrayList();
        if (groupNames.contains(newSelection))
        {
            for (Match match : matches)
            {
                if (match.getGroup() == newSelection)
                {
                    matchesFiltered.add(match);
                }
            }
            return matchesFiltered;
        } else
        {
            return matches;
        }
    }

    /** return all matches played by given team
     *
     * @param newSelection
     * @return
     */
    public ObservableList<Match> getMatchesFilteredByTeam(String newSelection)
    {
        ObservableList<Match> matchesFiltered = FXCollections.observableArrayList();

        for (Match match : matches)
        {
            if (match.getHome().getName() == newSelection || match.getGuest().getName() == newSelection)
            {
                matchesFiltered.add(match);
            }
        }

        if (matchesFiltered.size() > 0)
        {
            return matchesFiltered;
        } else
        {
            return matches;
        }
    }

    /** return ID of match played by given teams
     *
     * @param home
     * @param guest
     * @return
     */
    public String getMatchIdByTeams(String home, String guest)
    {
        String result = new String();

        for (Match match : matches)
        {

            if (match.getHomeName() == home && match.getGuestName() == guest)
            {
                return match.getId();
            }
        }
        return result;
    }

    // calculate and set ranking for every team in all teams
    private ArrayList<ArrayList> calculateGroupRanks()
    {
        ArrayList<ArrayList> result = new ArrayList<>();
        ArrayList<Team> finalTeams = new ArrayList<>();
        ArrayList<Team> finalTeamsSecond = new ArrayList<>();
        for (String group : groupNames)
        {
            List<Team> groupTeams = new ArrayList<>();
            for (Match match : matches)
            {
                if (match.getGroup().equals(group) && !groupTeams.contains(match.getHome()))
                {
                    groupTeams.add(match.getHome());
                }
            }
            // sort by given rules, accessor methods, TODO compare by match between 
            Collections.sort(groupTeams, comparing(Team::getPoints)
                    .thenComparing(Team::getGd).thenComparingInt(Team::getGoals).reversed());

            for (Team groupTeam : groupTeams)
            {
                groupTeam.setRankGroup(groupTeams.indexOf(groupTeam) + 1);
            }
            finalTeams.add(groupTeams.get(0));
            finalTeamsSecond.add(groupTeams.get(1));
        }
        result.add(finalTeams);
        result.add(finalTeamsSecond);
        return result;

    }

    /** return specifically formated data of final scheduled matches for showing them in the view (tree of matches)
     *
     * @return
     */
    public ArrayList<String[]> getFinalData()
    {
        ArrayList<String[]> result = new ArrayList<String[]>();

        ArrayList<Match> quarterFinalMatches = new ArrayList<>();
        ArrayList<Match> semiFinalMatches = new ArrayList<>();
        ArrayList<Match> finalMatches = new ArrayList<>();

        for (Match match : matches)
        {
            if (match.getGroup().equals(StringLibrary.QFINAL))
            {
                quarterFinalMatches.add(match);
            } else if (match.getGroup().equals(StringLibrary.SFINAL))
            {
                semiFinalMatches.add(match);
            } else if (match.getGroup().equals(StringLibrary.FINAL))
            {
                finalMatches.add(match);
            }
        }

        for (Match match : quarterFinalMatches)
        {
            result.add(getFinalsMatchAsStringArray(match));
        }
        for (Match match : semiFinalMatches)
        {
            result.add(getFinalsMatchAsStringArray(match));
        }
        for (Match match : finalMatches)
        {
            result.add(getFinalsMatchAsStringArray(match));
        }
        return result;
    }

    // helper - formating match into the string array
    private String[] getFinalsMatchAsStringArray(Match match)
    {
        String[] result = new String[4];
        result[0] = match.getHomeName();
        result[1] = match.getGuestName();
        if (match.isPlayed())
        {
            result[2] = Integer.toString(match.getHomeScore());
            result[3] = Integer.toString(match.getGuestScore());
        }
        return result;
    }

     // prepare matches for semi-final round
    private void prepareSemiFinals()
    {
        String id = new String();
        int i = 1;
        for (int[] is : scheduleSemiFinals)
        {
            Match matchQF1 = getMatchByID("QF00" + is[0]);
            Match matchQF2 = getMatchByID("QF00" + is[1]);
            id = "SF" + String.format("%03d", i);
            Match match = new Match(id, matchQF1.getWinner(), matchQF2.getWinner(), StringLibrary.SFINAL, 8);
            matches.add(match);
            i++;
        }
        semiFinalsGenerated = true;
        finalsReady = true;
    }

    // prepare matches for final round
    private void prepareFinals()
    {
        Match matchSF1 = getMatchByID("SF001");
        Match matchSF2 = getMatchByID("SF002");
        Match match = new Match("F0001", matchSF1.getWinner(), matchSF2.getWinner(), StringLibrary.FINAL, 9);
        matches.add(match);
        finalScheduleReady = true;
    }

    // calculate and set global ranking for evry team in competition 
    private void generateFinalRanking()
    {
        ArrayList<Team> rankedTeamList = new ArrayList<>();
        ArrayList<Team> tmpTeamList = new ArrayList<>();

        Team team = getMatchByID("F0001").getWinner();
        team.setRank(1);
        rankedTeamList.add(team);
        team = getMatchByID("F0001").getLooser();
        team.setRank(2);
        rankedTeamList.add(team);

        for (int i = 1; i < 3; i++)
        {
            tmpTeamList.add(getMatchByID("SF00" + i).getLooser());
        }

        Collections.sort(tmpTeamList, comparing(Team::getPoints)
                .thenComparing(Team::getGd).thenComparingInt(Team::getGoals).reversed());

        for (int i = 0; i < 2; i++)
        {
            tmpTeamList.get(i).setRank(3 + i);
            rankedTeamList.add(tmpTeamList.get(i));
        }
        tmpTeamList.clear();

        for (int i = 1; i < 5; i++)
        {
            tmpTeamList.add(getMatchByID("QF00" + i).getLooser());
        }

        Collections.sort(tmpTeamList, comparing(Team::getPoints)
                .thenComparing(Team::getGd).thenComparingInt(Team::getGoals).reversed());

        for (int i = 0; i < 4; i++)
        {
            tmpTeamList.get(i).setRank(5 + i);
            rankedTeamList.add(tmpTeamList.get(i));
        }
        tmpTeamList.clear();

        for (Match match : matches)
        {
            if (groupNames.contains(match.getGroup()) && !rankedTeamList.contains(match.getHome()) && !tmpTeamList.contains(match.getHome()))
            {
                tmpTeamList.add(match.getHome());
            }
        }

        Collections.sort(tmpTeamList, comparing(Team::getPoints)
                .thenComparing(Team::getGd).thenComparingInt(Team::getGoals).reversed());

        int i = 0;
        for (Team tmpTeam : tmpTeamList)
        {
            tmpTeam.setRank(9 + i);
            i++;
        }
    }
}
