/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import mychamp.bll.Team;
import mychamp.gui.util.StringLibrary;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class TeamEditDialogController extends EditDialogController
{

    private Team team;

    @FXML
    private TextField txtFieldName;

    @FXML
    private void handleOk(ActionEvent event)
    {
        if (isInputValid())
        {
            team.setName(txtFieldName.getText().trim());
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Sets the team to be edited in the dialog.
     *
     * @param team
     */
    public void setTeam(Team team)
    {
        this.team = team;
        txtFieldName.setText(team.getName());
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid()
    {
        if (txtFieldName.getText() == null || txtFieldName.getText().trim().length() == 0)
        {
            showAlert(StringLibrary.ERROR, StringLibrary.ERROR_INPUT, StringLibrary.ERROR_INPUT_TEAMNAME);
            return false;
        } else
        {
            return true;
        }
    }

}
