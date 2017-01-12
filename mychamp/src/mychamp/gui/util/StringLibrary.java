/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.util;

/**
 *
 * @author Adam
 */
public abstract class StringLibrary
{

    // Text constants for global usage
    public static final String CHOOSE = "CHOOSE";
    public static final String ALL = "ALL";
    public static final String GROUP1 = "GROUP A";
    public static final String GROUP2 = "GROUP B";
    public static final String GROUP3 = "GROUP C";
    public static final String GROUP4 = "GROUP D";
    public static final String FINAL = "FINAL";
    public static final String SFINAL = "S-FINALS";
    public static final String QFINAL = "Q-FINALS";

    public static final String BUTTON_GROUPS = "Create Groups \n and \n Schedule";

    public static final String TITLE_SETMATCH = "Set Match Result";

    public static final String ERROR = "ERROR";
    public static final String MATCH_IDNOTFOUND = "Match with this ID does not exists.";
    public static final String ERROR_INPUT = "INPUT ERROR";
    public static final String ERROR_INPUT_SCORE = "Not valid score format!";
    public static final String ERROR_INPUT_TEAMNAME = "Not valid Team name format! Team name must be unique in application.";
    public static final String TEAMS_DIALOG = "Please select a team in the table.";
    public static final String TEAMS_DIALOG_HEADER = "No Team Selected";
    public static final String TEAMS_DIALOG_TITLE = "No Selection";
    public static final String TEAMS_OUTOFRANGE = "Number of teams required for matchmaking must be between 12 and 16.";
    public static final String TEAMS_OUTOFRANGE_TITLE = "Number of Teams out of range for Matchmaking";
    public static final String TEAMS_OUTOFRANGE_HEADER = "Please manage number of teams for matchmaking.";
    public static final String TEAMS_GENERATED = "All team was already devided into the groups and schedule was generated.";
    public static final String TEAMS_GENERATED_TITLE = "Groups and Schedule were already created.";
    public static final String TEAMS_GENERATED_HEADER = "Groups and Schedule were already created.";
    public static final String TEAMS_NOADD = "All team was already devided into the groups and schedule was generated, it is not possible add or removenew team.";
    public static final String TEAMS_NOADD_TITLE = "Groups and Schedule were already created.";
    public static final String TEAMS_NOADD_HEADER = "Not possible add or remove new team after groups are created.";
    public static final String GROUPS_NOTGENERATED = "All team are not yet devided into the groups and schedule is not generated.";
    public static final String GROUPS_NOTGENERATED_TITLE = "Groups and Schedule are not created.";
    public static final String GROUPS_NOTGENERATED_HEADER = "Groups and Schedule are not created.";
    public static final String FINALS_GENERATED_HEADER = "All matched in groups are closed.";
    public static final String FINALS_GENERATED_TITLE = "Groups are closed.";
    public static final String FINALS_GENERATED = "Not possible to change score for cloused groups. \n For seting score of finals matches use special view.";
    public static final String FINALS_NOTGENERATED_HEADER = "Final schedule is not generated yet.";
    public static final String FINALS_NOTGENERATED_TITLE = "Final schedule is not generated yet.";
    public static final String FINALS_NOTGENERATED = "Set score for finals will be possible after all matches in grops are played.";
    public static final String ERROR_INPUT_SCORE_FINALS = "Match in finals must finish with victorious team, draw is no allowed.";
    public static final String ABOUT = "About";
    public static final String BY = "Made by";
    public static final String ABOUT_US = "Adam Strasak\nEdison Javier Lamar\nLinda Braarup\nMichal Adam Izdebski";
    public static final String[][] SCHEDULE_TABLE_HEADER =
    {
        {
            "ID", "id"
        },
        {
            "Group", "group"
        },
        {
            "Round", "round"
        },
        {
            "Home Team", "homeName"
        },
        {
            "Guest Team", "guestName"
        },
        {
            "Home Score", "homeScore"
        },
        {
            "Guest Score", "guestScore"
        }
    };

    public static final String[][] GROUP_TABLEHEADER =
    {
        {
            "Group rank", "rankGroup"
        },
        {
            "Name", "name"
        },
        {
            "Group", "group"
        },
        {
            "Goals", "goals"
        },
        {
            "GD", "gd"
        },
        {
            "Points", "points"
        }
    };

    public static final String[][] TEAM_TABLEHEADER =
    {
        {
            "Global rank", "rank"
        },
        {
            "Name", "name"
        },
        {
            "Group", "group"
        },
        {
            "Matches", "matchesPlayed"
        },
        {
            "Goals", "goals"
        },
        {
            "GD", "gd"
        },
        {
            "Points", "points"
        }
    };
}
