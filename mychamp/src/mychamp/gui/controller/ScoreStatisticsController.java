/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class ScoreStatisticsController extends OverviewController
{

    @FXML
    private BarChart<String, Integer> barChart;

    /**
     * set up controller
     */
    public void prepareController()
    {

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Create a XYChart.Data object for each team. Add it to the series.
        for (int i = 0; i < mainApp.getTeamModel().getTeams().size(); i++)
        {
            series.getData().add(new XYChart.Data<>(mainApp.getTeamModel().getTeams().get(i).getName(), mainApp.getTeamModel().getTeams().get(i).getPoints()));
        }

        barChart.getData().add(series);
    }
}
