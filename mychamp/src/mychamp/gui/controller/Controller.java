/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mychamp.gui.MyChamp;

/**
 *
 * @author Adam
 */
public abstract class Controller implements Initializable
{

    @FXML
    protected AnchorPane anchorPane;
    @FXML
    protected BorderPane borderPane;

    protected MyChamp mainApp;

    public void setMainApp(MyChamp mainApp)
    {
        this.mainApp = mainApp;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    /**
     * Method for showing alert for user
     *
     * @param title
     * @param header
     * @param content
     */
    protected void showAlert(String title, String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Stage stage = new Stage();
        if (anchorPane != null)
        {
            stage = (Stage) anchorPane.getScene().getWindow();
        } else
        {
            stage = (Stage) borderPane.getScene().getWindow();
        }

        alert.initOwner(stage);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
