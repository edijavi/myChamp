/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import mychamp.gui.MyChamp;
import mychamp.gui.util.StringLibrary;
import mychamp.gui.util.ViewUtil;
import mychamp.gui.util.ViewUtilGroup;
import mychamp.gui.util.ViewUtilSchedule;
import mychamp.gui.util.ViewUtilScheduleFinals;
import mychamp.gui.util.ViewUtilScore;
import mychamp.gui.util.ViewUtilTeam;

/**
 * FXML OverviewController class
 *
 * @author Adam
 */
public class RootLayoutController extends Controller
{

    private final ObservableList<String> observList;

    public RootLayoutController()
    {
        observList = FXCollections.observableArrayList();
    }

    /**
     * Shows the overview inside the root layout.
     */
    private void showOverview(ViewUtil view, ObservableList<String> obsList)
    {
        try
        {
            // Load overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MyChamp.class.getResource(view.getViewPathOverview()));
            AnchorPane overview = (AnchorPane) loader.load();

            // Set overview into the center of root layout.
            borderPane.setCenter(overview);

            view.prepareControllerOverview(loader, mainApp, obsList);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleShowGroupOverview(ActionEvent event)
    {
        ViewUtilGroup overview = new ViewUtilGroup();
        ObservableList<String> obsList = FXCollections.observableArrayList();
        obsList.addAll(StringLibrary.ALL, StringLibrary.GROUP1, StringLibrary.GROUP2, StringLibrary.GROUP3, StringLibrary.GROUP4);
        showOverview(overview, obsList);
    }

    @FXML
    private void handleShowFinalSchedule(ActionEvent event)
    {
        ViewUtilScheduleFinals overview = new ViewUtilScheduleFinals();
        ObservableList<String> obsList = FXCollections.observableArrayList();
        showOverview(overview, observList);
    }

    @FXML
    private void handleShowTeamManagement(ActionEvent event)
    {
        ViewUtilTeam overview = new ViewUtilTeam();
        showOverview(overview, observList);
    }

    @FXML
    private void handleShowTeamSchedule(ActionEvent event)
    {
        ViewUtilSchedule overview = new ViewUtilSchedule();
        ObservableList<String> teamNames = FXCollections.observableArrayList();
        teamNames.add(StringLibrary.ALL);
        teamNames.addAll(mainApp.getTeamModel().getTeamNames());
        showOverview(overview, teamNames);
    }

    @FXML
    private void handleShowGroupSchedule(ActionEvent event)
    {
        ViewUtilSchedule overview = new ViewUtilSchedule();
        ObservableList<String> obsList = FXCollections.observableArrayList();
        obsList.addAll(StringLibrary.ALL, StringLibrary.GROUP1, StringLibrary.GROUP2, StringLibrary.GROUP3, StringLibrary.GROUP4);
        showOverview(overview, obsList);
    }

    @FXML
    private void handleShowScoreStatistics(ActionEvent event)
    {
        ViewUtilScore overview = new ViewUtilScore();
        showOverview(overview, observList);
    }

    @FXML
    private void handleShowAbout(ActionEvent event)
    {
        showAlert(StringLibrary.ABOUT, StringLibrary.BY, StringLibrary.ABOUT_US);
    }
}
