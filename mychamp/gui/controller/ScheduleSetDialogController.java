/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import mychamp.bll.Match;
import mychamp.gui.util.StringLibrary;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class ScheduleSetDialogController extends EditDialogController
{

    private Match match;
    private boolean finals;
    private final ObservableList<String> teamNames;

    @FXML
    private TextField txtFieldMatchID;
    @FXML
    private ChoiceBox<String> choiceBoxHome;
    @FXML
    private ChoiceBox<String> choiceBoxGuest;
    @FXML
    private TextField txtFieldHomeScore;
    @FXML
    private TextField txtFieldGuestScore;

    @FXML
    private void handleOk(ActionEvent event)
    {
        if (isInputValid())
        {
            match.setGuestScore(Integer.parseInt(txtFieldGuestScore.getText().trim()));
            match.setHomeScore(Integer.parseInt(txtFieldHomeScore.getText().trim()));
            okClicked = true;
            dialogStage.close();
        }
    }

    public ScheduleSetDialogController()
    {
        teamNames = FXCollections.observableArrayList();
    }

    public void prepareChoiceBoxes()
    {
        teamNames.add(StringLibrary.CHOOSE);
        teamNames.addAll(mainApp.getTeamModel().getTeamNames());
        choiceBoxHome.setItems(teamNames);
        choiceBoxGuest.setItems(teamNames);

        // listener for match ID value change 
        txtFieldMatchID.textProperty().addListener((obs, oldID, newID)
                -> 
                {
                    match = mainApp.getMatchModel().getMatchByID(newID);
                    prepareComponents();
        });

        // listeners for selecting 
        choiceBoxHome.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> 
                {
                    if (newSelection != null)
                    {

                        if (newSelection != StringLibrary.CHOOSE && choiceBoxGuest.getSelectionModel().getSelectedItem() != StringLibrary.CHOOSE)
                        {
                            txtFieldMatchID.setText(mainApp.getMatchModel().getMatchIdByTeams(choiceBoxHome.getSelectionModel().getSelectedItem(), choiceBoxGuest.getSelectionModel().getSelectedItem()));
                        }
                    }
        });
        choiceBoxGuest.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> 
                {
                    if (newSelection != null)
                    {

                        if (newSelection != StringLibrary.CHOOSE && choiceBoxHome.getSelectionModel().getSelectedItem() != StringLibrary.CHOOSE)
                        {
                            txtFieldMatchID.setText(mainApp.getMatchModel().getMatchIdByTeams(choiceBoxHome.getSelectionModel().getSelectedItem(), choiceBoxGuest.getSelectionModel().getSelectedItem()));
                        }
                    }
        });
    }

    /**
     * Sets the match to be edited in the dialog.
     *
     * @param match
     */
    public void setMatch(Match match)
    {
        this.match = match;
        prepareComponents();
    }

    private void prepareComponents()
    {
        finals = false;
        if (match != null && !match.getId().isEmpty())
        {
            txtFieldMatchID.setText(match.getId());
            choiceBoxHome.getSelectionModel().select(match.getHomeName());
            choiceBoxGuest.getSelectionModel().select(match.getGuestName());
            txtFieldHomeScore.setText(Integer.toString(match.getHomeScore()));
            txtFieldGuestScore.setText(Integer.toString(match.getGuestScore()));

            if (!match.getId().startsWith("MC"))
            {
                choiceBoxHome.setDisable(true);
                choiceBoxGuest.setDisable(true);
                txtFieldMatchID.setDisable(true);
                finals = true;
            }

        } else
        {
            choiceBoxHome.getSelectionModel().select(StringLibrary.CHOOSE);
            choiceBoxGuest.getSelectionModel().select(StringLibrary.CHOOSE);
            txtFieldHomeScore.setText(Integer.toString(0));
            txtFieldGuestScore.setText(Integer.toString(0));
        }
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid()
    {
        Match match = mainApp.getMatchModel().getMatchByID(txtFieldMatchID.getText());
        if (match != null)
        {
            try
            {
                int home = Integer.parseInt(txtFieldHomeScore.getText().trim());
                int guest = Integer.parseInt(txtFieldGuestScore.getText().trim());

                if (finals && home == guest)
                {
                    showAlert(StringLibrary.ERROR, StringLibrary.ERROR_INPUT, StringLibrary.ERROR_INPUT_SCORE_FINALS);
                } else
                {
                    return true;
                }
            } catch (Exception e)
            {
                showAlert(StringLibrary.ERROR, StringLibrary.ERROR_INPUT, StringLibrary.ERROR_INPUT_SCORE);
            }
        } else
        {
            showAlert(StringLibrary.ERROR, StringLibrary.ERROR_INPUT, StringLibrary.MATCH_IDNOTFOUND);
        }

        return false;
    }

}
