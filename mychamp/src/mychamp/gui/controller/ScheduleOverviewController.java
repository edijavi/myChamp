/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mychamp.bll.Match;
import mychamp.gui.util.StringLibrary;
import mychamp.gui.util.ViewUtilSchedule;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class ScheduleOverviewController extends OverviewController
{

    @FXML
    private TableView<Match> tableViewScheduleOverview;
    @FXML
    private ChoiceBox<String> choiceBoxSchedule;

    /** set up controller
     *
     * @param obsList
     */
    public void prepareController(ObservableList<String> obsList)
    {
        prepareChoiceBox(obsList);
        prepareTables();
    }

    // set up choice box
    private void prepareChoiceBox(ObservableList<String> obsList)
    {
        choiceBoxSchedule.setItems(obsList);
        choiceBoxSchedule.getSelectionModel().select(0);

        // listener for selecting 
        choiceBoxSchedule.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> 
                {
                    if (newSelection != null)
                    {
                        // will work, there must be atleast 12 teams for creating groups and schedule
                        if (obsList.size() > 12)
                        {
                            tableViewScheduleOverview.setItems(mainApp.getMatchModel().getMatchesFilteredByTeam(newSelection));
                        } else
                        {
                            tableViewScheduleOverview.setItems(mainApp.getMatchModel().getMatchesFilteredByGroup(newSelection));
                        }
                    }
        });

    }

     // set up tables
    private void prepareTables()
    {
        tableViewScheduleOverview.setItems(mainApp.getMatchModel().getMatches());

        for (String[] columnName : StringLibrary.SCHEDULE_TABLE_HEADER)
        {
            TableColumn column = new TableColumn(columnName[0]);
            column.setMinWidth(90);
            column.setCellValueFactory(new PropertyValueFactory<>(columnName[1]));
            tableViewScheduleOverview.getColumns().add(column);
        }
    }

    @FXML
    private void handleSetMatchResut(ActionEvent event)
    {
        if (mainApp.getTeamModel().areGroupsGenerated())
        {
            if (!mainApp.getMatchModel().areQuarterFinalsReady())
            {
                Match selectedMatch = tableViewScheduleOverview.getSelectionModel().getSelectedItem();
                if (selectedMatch == null)
                {
                    selectedMatch = new Match();
                }
                ViewUtilSchedule dialog = new ViewUtilSchedule();
                boolean okClicked = this.showDialog(dialog, selectedMatch, StringLibrary.TITLE_SETMATCH);
                if (okClicked)
                {
                    mainApp.getMatchModel().updateMatch(selectedMatch);
                    tableViewScheduleOverview.refresh();
                }
            } else
            {
                showAlert(StringLibrary.FINALS_GENERATED_HEADER, StringLibrary.FINALS_GENERATED_TITLE, StringLibrary.FINALS_GENERATED);

            }
        } else
        {
            showAlert(StringLibrary.GROUPS_NOTGENERATED_HEADER, StringLibrary.GROUPS_NOTGENERATED_TITLE, StringLibrary.GROUPS_NOTGENERATED);
        }
    }
}
