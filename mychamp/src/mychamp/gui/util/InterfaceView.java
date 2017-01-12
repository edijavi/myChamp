/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.util;

import javafx.collections.ObservableList;
import mychamp.bll.DataObject;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import mychamp.gui.MyChamp;
import mychamp.gui.controller.EditDialogController;

/**
 *
 * @author Adam
 */
public interface InterfaceView
{

    public String getViewPathDialog();

    public String getViewPathOverview();

    public EditDialogController prepareControllerDialog(MyChamp mainApp, FXMLLoader loader, Stage dialogStage, DataObject dataObject);

    public void prepareControllerOverview(FXMLLoader loader, MyChamp mainApp, ObservableList<String> obsList);

}
