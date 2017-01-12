/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.util;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import mychamp.bll.DataObject;
import mychamp.bll.Match;
import mychamp.gui.MyChamp;
import mychamp.gui.controller.EditDialogController;
import mychamp.gui.controller.ScheduleOverviewController;
import mychamp.gui.controller.ScheduleSetDialogController;

/**
 *
 * @author Adam
 */
public class ViewUtilSchedule extends ViewUtil
{

    /**
     *
     * @return String - filePath for view
     */
    @Override
    public String getViewPathOverview()
    {
        return "view/ScheduleOverview.fxml";
    }

    /**
     * Shows the team overview inside the root layout.
     *
     * @param loader
     * @param mainApp
     */
    @Override
    public void prepareControllerOverview(FXMLLoader loader, MyChamp mainApp, ObservableList<String> obsList)
    {
        ScheduleOverviewController controller = loader.getController();
        controller.setMainApp(mainApp);
        controller.prepareController(obsList);
    }

    /**
     *
     * @return String - filePath for view
     */
    @Override
    public String getViewPathDialog()
    {
        return "view/ScheduleSetDialog.fxml";
    }

    /**
     * Shows the edit dialog
     *
     * @param mainApp
     * @param loader
     * @param dialogStage
     * @param match
     * @return
     */
    @Override
    public EditDialogController prepareControllerDialog(MyChamp mainApp, FXMLLoader loader, Stage dialogStage, DataObject match)
    {
        ScheduleSetDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        ((ScheduleSetDialogController) controller).setMatch((Match) match);
        controller.setMainApp(mainApp);
        controller.prepareChoiceBoxes();
        return controller;
    }
}
