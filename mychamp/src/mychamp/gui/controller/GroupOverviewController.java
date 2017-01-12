/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mychamp.bll.Team;
import mychamp.gui.util.StringLibrary;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class GroupOverviewController extends OverviewController
{

    @FXML
    private TableView<Team> tableViewGroupsOverview;
    @FXML
    private ChoiceBox<String> choiceBoxGroups;

    @FXML
    private Button buttonCreateGroupsAndSchedule;

    /**
     *
     * @param obsList
     */
    public void prepareController(ObservableList<String> obsList)
    {
        prepareChoiceBox(obsList);
        prepareTables();
        updateTeamsNumber();
        buttonCreateGroupsAndSchedule.setText(StringLibrary.BUTTON_GROUPS);
    }

    /**
     * Method bind data to tableView and prepare columns, creates listener
     */
    private void prepareTables()
    {
        tableViewGroupsOverview.setItems(mainApp.getTeamModel().getTeams());

        for (String[] columnName : StringLibrary.GROUP_TABLEHEADER)
        {
            TableColumn column = new TableColumn(columnName[0]);
            column.setMinWidth(100);
            column.setCellValueFactory(new PropertyValueFactory<>(columnName[1]));
            tableViewGroupsOverview.getColumns().add(column);
        }
    }

    // set up choicebox, add listener
    private void prepareChoiceBox(ObservableList<String> obsList)
    {
        choiceBoxGroups.setItems(obsList);
        choiceBoxGroups.getSelectionModel().select(0);

        // listener for selecting group
        choiceBoxGroups.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> 
                {
                    if (newSelection != null)
                    {
                        tableViewGroupsOverview.setItems(mainApp.getTeamModel().getTeamsFilteredByGroup(newSelection));
                    }
        });
    }

    @FXML
    private void handleCreateGroupsAndSchedule(ActionEvent event)
    {
        if (!mainApp.getTeamModel().areGroupsGenerated())
        {
            if (mainApp.getTeamModel().getNumberOfTeams() <= 16 && mainApp.getTeamModel().getNumberOfTeams() >= 12)
            {
                mainApp.getTeamModel().sortTeamsToGroups();
                mainApp.getMatchModel().prepareMatches(mainApp.getTeamModel().getTeams());
                tableViewGroupsOverview.refresh();
            } else
            {
                showAlert(StringLibrary.TEAMS_OUTOFRANGE_TITLE, StringLibrary.TEAMS_OUTOFRANGE_HEADER, StringLibrary.TEAMS_OUTOFRANGE);
            }
        } else
        {
            showAlert(StringLibrary.TEAMS_GENERATED_TITLE, StringLibrary.TEAMS_GENERATED_TITLE, StringLibrary.TEAMS_GENERATED);
        }
    }
}
