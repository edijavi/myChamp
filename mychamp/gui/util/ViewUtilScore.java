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
import mychamp.gui.controller.ScoreStatisticsController;

/**
 *
 * @author Adam
 */
public class ViewUtilScore extends ViewUtil
{

    /**
     *
     * @return String - filePath for view
     */
    @Override
    public String getViewPathOverview()
    {
        return "view/ScoreStatistics.fxml";
    }

    /**
     * Shows the overview inside the root layout.
     * @param loader
     * @param mainApp
     * @param obsList
     */
    @Override
    public void prepareControllerOverview(FXMLLoader loader, MyChamp mainApp, ObservableList<String> obsList)
    {
        ScoreStatisticsController controller = loader.getController();
        controller.setMainApp(mainApp);
        controller.prepareController();
    }

    /**
     *
     * @return String - filePath for view
     */
    @Override
    public String getViewPathDialog()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Shows the edit dialog
     */
    @Override
    public EditDialogController prepareControllerDialog(MyChamp mainApp, FXMLLoader loader, Stage dialogStage, DataObject dataObject)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
