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
import mychamp.gui.controller.GroupOverviewController;

/**
 *
 * @author Adam
 */
public class ViewUtilGroup extends ViewUtil
{

    /**
     *
     * @return String - filePath for view
     */
    @Override
    public String getViewPathOverview()
    {
        return "view/GroupOverview.fxml";
    }

    /**
     * Shows the team overview inside the root layout.
     * @param loader
     * @param mainApp
     * @param obsList
     */
     @Override
    public void prepareControllerOverview(FXMLLoader loader, MyChamp mainApp, ObservableList<String> obsList)
    {
        GroupOverviewController controller = loader.getController();
        controller.setMainApp(mainApp);
        controller.prepareController(obsList);
    }

    /**
     *
     * @return
     */
    @Override
    public String getViewPathDialog()
    {
        throw new UnsupportedOperationException("Not supported.");
    }

    /**
     *
     * @param mainApp
     * @param loader
     * @param dialogStage
     * @param dataObject
     * @return
     */
    @Override
    public EditDialogController prepareControllerDialog(MyChamp mainApp, FXMLLoader loader, Stage dialogStage, DataObject dataObject)
    {
        throw new UnsupportedOperationException("Not supported.");
    }
}
