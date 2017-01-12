/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mychamp.bll.Team;
import mychamp.gui.util.StringLibrary;

/**
 *
 * @author Adam
 */
public class TeamModel
{

    /**
     * The observable lists, used for data binding the view to the model.
     */
    private final ObservableList<Team> teams;

    private boolean groupsGenerated;

    public TeamModel()
    {
        teams = FXCollections.observableArrayList();
        this.groupsGenerated = false;
    }

    public boolean areGroupsGenerated()
    {
        return groupsGenerated;
    }

    /**
     * Gets the observable list of teams.
     *
     * @return
     */
    public ObservableList<Team> getTeams()
    {
        return teams;
    }

    /**
     * Adds new team into the application
     *
     * @param team
     */
    public void addTeam(Team team)
    {
        teams.add(team);
    }

    public int getNumberOfTeams()
    {
        return teams.size();
    }

    /**
     * Array of string containing name of every team
     *
     * @return
     */
    public ArrayList<String> getTeamNames()
    {
        ArrayList<String> teamNames = new ArrayList<>();

        for (Team team : teams)
        {
            teamNames.add(team.getName());
        }

        return teamNames;
    }

    /**
     * team grouping
     *
     */
    public void sortTeamsToGroups()
    {
        if (!groupsGenerated)
        {

            List<Integer> unassignedTeams = new ArrayList<>();
            for (int i = 0; i < teams.size(); i++)
            {
                unassignedTeams.add(i);
            }

            Random randomnes = new Random();
            int index = randomnes.nextInt(unassignedTeams.size());

            String[] groups =
            {
                StringLibrary.GROUP1, StringLibrary.GROUP2, StringLibrary.GROUP3, StringLibrary.GROUP4
            };

            while (true)
            {
                // add random team to every group
                for (String group : groups)
                {
                    // add random team to group
                    index = randomnes.nextInt(unassignedTeams.size());
                    teams.get(unassignedTeams.get(index)).setGroup(group);
                    // and remove it from unassigned list
                    unassignedTeams.remove(index);
                    if (unassignedTeams.isEmpty())
                    {
                        break;
                    }
                }
                // no more teams without group >> end of cycle
                if (unassignedTeams.isEmpty())
                {
                    groupsGenerated = true;
                    break;
                }
            }
        }
    }

    /**
     * deleting team from application
     *
     * @param team
     */
    public void removeTeam(Team team)
    {
        teams.remove(team);
    }

    /**
     * filtering teams by given group
     *
     * @param newSelection
     * @return
     */
    public ObservableList<Team> getTeamsFilteredByGroup(String newSelection)
    {
        ObservableList<Team> teamsFiltered = FXCollections.observableArrayList();
        if (newSelection == StringLibrary.GROUP1 || newSelection == StringLibrary.GROUP2 || newSelection == StringLibrary.GROUP3 || newSelection == StringLibrary.GROUP4)
        {
            for (Team team : teams)
            {
                if (team.getGroup() == newSelection)
                {
                    teamsFiltered.add(team);
                }
            }
            return teamsFiltered;
        } else
        {
            return teams;
        }
    }

}
