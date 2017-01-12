/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mychamp.bll.Match;
import mychamp.gui.util.StringLibrary;
import mychamp.gui.util.ViewUtilSchedule;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class ScheduleFinalsOverviewController extends OverviewController
{

    @FXML
    private Label labelTeamFA;
    @FXML
    private Label labelTeamFB;
    @FXML
    private Label labelTeamSF2A;
    @FXML
    private Label labelTeamSF2B;
    @FXML
    private Label labelTeamSF1A;
    @FXML
    private Label labelTeamSF1B;
    @FXML
    private Label labelTeamQF4A;
    @FXML
    private Label labelTeamQF4B;
    @FXML
    private Label labelTeamQF3A;
    @FXML
    private Label labelTeamQF3B;
    @FXML
    private Label labelTeamQF2A;
    @FXML
    private Label labelTeamQF2B;
    @FXML
    private Label labelTeamQF1A;
    @FXML
    private Label labelTeamQF1B;
    @FXML
    private Button buttonFinal;
    @FXML
    private Button buttonSFinal2;
    @FXML
    private Button buttonSFinal1;
    @FXML
    private Button buttonQFinal4;
    @FXML
    private Button buttonQFinal3;
    @FXML
    private Button buttonQFinal2;
    @FXML
    private Button buttonQFinal1;

    private Label[][] labelQuarterFinals;

    private Label[][] labelSemiFinals;

    private Label[][] labelFinals;

    private Button[] buttonQuarterFinals;

    private Button[] buttonSemiFinals;

    private Button[] buttonFinals;

    @FXML
    private void handleSetScoreF(ActionEvent event)
    {
         handleSetFinalMatchScore("F0001");
    }

    @FXML
    private void handleSetScoreSF2(ActionEvent event)
    {
         handleSetFinalMatchScore("SF002");
    }

    @FXML
    private void handleSetScoreSF1(ActionEvent event)
    {
         handleSetFinalMatchScore("SF001");
    }

    @FXML
    private void handleSetScoreQF4(ActionEvent event)
    {
        handleSetFinalMatchScore("QF004");
    }

    @FXML
    private void handleSetScoreQF3(ActionEvent event)
    {
        handleSetFinalMatchScore("QF003");
    }

    @FXML
    private void handleSetScoreQF2(ActionEvent event)
    {
        handleSetFinalMatchScore("QF002");
    }

    @FXML
    private void handleSetScoreQF1(ActionEvent event)
    {
        handleSetFinalMatchScore("QF001");
    }

    
    // helper - method serves dialog window for editing score of given match (by ID) 
    private void handleSetFinalMatchScore(String id)
    {
        if (mainApp.getMatchModel().areQuarterFinalsReady())
        {
            Match selectedMatch = mainApp.getMatchModel().getMatchByID(id);
            if (selectedMatch != null)
            {
                ViewUtilSchedule dialog = new ViewUtilSchedule();
                boolean okClicked = this.showDialog(dialog, selectedMatch, StringLibrary.TITLE_SETMATCH);
                if (okClicked)
                {
                    mainApp.getMatchModel().updateMatch(selectedMatch);
                    prepareController();
                }
            }
        } else
        {
            showAlert(StringLibrary.FINALS_NOTGENERATED_HEADER, StringLibrary.FINALS_NOTGENERATED_TITLE, StringLibrary.FINALS_NOTGENERATED);

        }
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        labelQuarterFinals = new Label[][]
        {
            {
                labelTeamQF1A, labelTeamQF1B
            },
            {
                labelTeamQF2A, labelTeamQF2B
            },
            {
                labelTeamQF3A, labelTeamQF3B
            },
            {
                labelTeamQF4A, labelTeamQF4B
            }
        };

        labelSemiFinals = new Label[][]
        {
            {
                labelTeamSF1A, labelTeamSF1B
            },
            {
                labelTeamSF2A, labelTeamSF2B
            }
        };

        labelFinals = new Label[][]
        {
            {
                labelTeamFA, labelTeamFB
            }
        };

        buttonQuarterFinals = new Button[]
        {
            buttonQFinal1,
            buttonQFinal2,
            buttonQFinal3,
            buttonQFinal4
        };

        buttonSemiFinals = new Button[]
        {
            buttonSFinal1,
            buttonSFinal2
        };

        buttonFinals = new Button[]
        {
            buttonFinal
        };
    }

    // show matches in visual components
    private void printMatches(ArrayList<String[]> finalData, Label[][] labels, Button[] buttons, int firstIndex)
    {
        int i = 0;
        int index = firstIndex;
        for (Button button : buttons)
        {
            labels[i][0].setText(finalData.get(index)[0]);
            labels[i][1].setText(finalData.get(index)[1]);
            if (finalData.get(index)[2] != null)
            {
                button.setText(finalData.get(index)[2] + " : " + finalData.get(index)[3]);
                button.setDisable(true);
            }
            i++;
            index++;
        }
    }

    /**
     * set up controller
     */
    public void prepareController()
    {
        ArrayList<String[]> finalData = mainApp.getMatchModel().getFinalData();

        if (!finalData.isEmpty())
        {

            printMatches(finalData, labelQuarterFinals, buttonQuarterFinals, 0);
            // semi-finals
            if (finalData.size() > 4)
            {
                printMatches(finalData, labelSemiFinals, buttonSemiFinals, 4);
                // finals
                if (finalData.size() > 6)
                {
                    printMatches(finalData, labelFinals, buttonFinals, 6);
                }
            }
        }
    }
}
