/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 *
 * @author Adam
 */
public abstract class EditDialogController extends Controller
{

    protected Stage dialogStage;
    protected boolean okClicked = false;

  
    /**
     * Closes application
     * @param event
     */
    @FXML
    protected void handleCancel(ActionEvent event)
    {
        dialogStage.close();
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked()
    {
        return okClicked;
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage)
    {
        this.dialogStage = dialogStage;
    }

    
}
