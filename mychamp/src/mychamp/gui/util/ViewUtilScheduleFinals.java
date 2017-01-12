/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.util;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import mychamp.gui.MyChamp;
import mychamp.gui.controller.ScheduleFinalsOverviewController;

/**
 *
 * @author Adam
 */
public class ViewUtilScheduleFinals extends ViewUtilSchedule
{

    /**
     * Return view path
     * @return 
     */
    @Override
    public String getViewPathOverview()
    {
        return "view/ScheduleFinalsOverview.fxml";
    }

    /**
     * Shows the team overview inside the root layout.
     * @param obsList
     */
    @Override
    public void prepareControllerOverview(FXMLLoader loader, MyChamp mainApp, ObservableList<String> obsList)
    {
        ScheduleFinalsOverviewController controller = loader.getController();
        controller.setMainApp(mainApp);
        controller.prepareController();
    }
}
