/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mychamp.gui.MyChamp;
import mychamp.bll.DataObject;
import mychamp.gui.util.ViewUtil;

/**
 *
 * @author Adam
 */
public abstract class OverviewController extends Controller
{

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label labelTeamsNumber;

    protected void updateTeamsNumber()
    {
        labelTeamsNumber.setText(Integer.toString(mainApp.getTeamModel().getNumberOfTeams()));
    }

    /**
     * Shows dialog window to user
     *
     * @param dialogWindow
     * @param dataObject
     * @param title
     * @return
     */
    protected boolean showDialog(ViewUtil dialogWindow, DataObject dataObject, String title)
    {
        try
        {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MyChamp.class.getResource(dialogWindow.getViewPathDialog()));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);

            Stage stage = (Stage) anchorPane.getScene().getWindow();
            dialogStage.initOwner(stage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EditDialogController controller = dialogWindow.prepareControllerDialog(mainApp, loader, dialogStage, dataObject);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

// showing alert for 
    protected void showAlert(String title, String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        alert.initOwner(stage);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
