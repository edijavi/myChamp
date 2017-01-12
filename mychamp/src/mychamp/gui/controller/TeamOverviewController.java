/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mychamp.bll.Team;
import mychamp.gui.util.StringLibrary;
import mychamp.gui.util.ViewUtilTeam;

/**
 * FXML OverviewController class
 *
 * @author Adam
 */
public class TeamOverviewController extends OverviewController
{

    @FXML
    private TableView<Team> tableViewTeamsOverview;

    @FXML
    private void handleAddNewTeam(ActionEvent event)
    {
        if (!mainApp.getTeamModel().areGroupsGenerated())
        {
            Team tempTeam = new Team();
            ViewUtilTeam dialog = new ViewUtilTeam();
            boolean okClicked = this.showDialog(dialog, tempTeam, "New Team");
            if (okClicked)
            {
                mainApp.getTeamModel().addTeam(tempTeam);
                updateTeamsNumber();
            }
        } else
        {
            showAlert(StringLibrary.TEAMS_NOADD_HEADER, StringLibrary.TEAMS_NOADD_TITLE, StringLibrary.TEAMS_NOADD);
        }
    }

    /**
     * set up controller
     */
    public void prepareController()
    {
        prepareTables();
        updateTeamsNumber();
    }

    /**
     * Method bind data to tableView and prepare columns, creates listener
     */
    private void prepareTables()
    {
        tableViewTeamsOverview.setItems(mainApp.getTeamModel().getTeams());

        for (String[] columnName : StringLibrary.TEAM_TABLEHEADER)
        {
            TableColumn column = new TableColumn(columnName[0]);
            column.setMinWidth(100);
            column.setCellValueFactory(new PropertyValueFactory<>(columnName[1]));
            tableViewTeamsOverview.getColumns().add(column);
        }
    }

    @FXML
    private void handleEditTeam(ActionEvent event)
    {
        Team selectedTeam = tableViewTeamsOverview.getSelectionModel().getSelectedItem();
        if (selectedTeam != null)
        {
            ViewUtilTeam dialog = new ViewUtilTeam();
            boolean okClicked = this.showDialog(dialog, selectedTeam, "Edit Team");
            if (okClicked)
            {
                tableViewTeamsOverview.refresh();
            }
        } else
        {
            // Nothing selected.
            showAlert(StringLibrary.TEAMS_DIALOG_HEADER, StringLibrary.TEAMS_DIALOG_TITLE, StringLibrary.TEAMS_DIALOG);
        }
    }

    @FXML
    private void handleRemoveTeam(ActionEvent event)
    {
        if (!mainApp.getTeamModel().areGroupsGenerated())
        {
            Team selectedTeam = tableViewTeamsOverview.getSelectionModel().getSelectedItem();
            if (selectedTeam != null)
            {
                mainApp.getTeamModel().removeTeam(selectedTeam);
                tableViewTeamsOverview.refresh();
                updateTeamsNumber();
            } else
            {
                // Nothing selected.
                showAlert(StringLibrary.TEAMS_DIALOG_HEADER, StringLibrary.TEAMS_DIALOG_TITLE, StringLibrary.TEAMS_DIALOG);
            }
        } else
        {
            showAlert(StringLibrary.TEAMS_NOADD_HEADER, StringLibrary.TEAMS_NOADD_TITLE, StringLibrary.TEAMS_NOADD);
        }
    }
}
