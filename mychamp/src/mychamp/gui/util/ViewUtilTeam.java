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
import mychamp.bll.Team;
import mychamp.gui.MyChamp;
import mychamp.gui.controller.EditDialogController;
import mychamp.gui.controller.TeamEditDialogController;
import mychamp.gui.controller.TeamOverviewController;

/**
 *
 * @author Adam
 */
public class ViewUtilTeam extends ViewUtil
{

    /**
     *
     * @return String - filePath for view
     */
    @Override
    public String getViewPathDialog()
    {
        return "view/TeamEditDialog.fxml";
    }

    /**
     *
     * @return String - filePath for view
     */
    @Override
    public String getViewPathOverview()
    {
        return "view/TeamOverview.fxml";
    }

     /**
     * Shows the team edit dialog
     */
    @Override
    public EditDialogController prepareControllerDialog(MyChamp mainApp, FXMLLoader loader, Stage dialogStage, DataObject team)
    {
        TeamEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        ((TeamEditDialogController) controller).setTeam((Team) team);
        return controller;
    }

    /**
     * Shows the team overview inside the root layout.
     */
     @Override
    public void prepareControllerOverview(FXMLLoader loader, MyChamp mainApp, ObservableList<String> obsList)
    {
        TeamOverviewController controller = loader.getController();
        controller.setMainApp(mainApp);
        controller.prepareController();
    }
}
