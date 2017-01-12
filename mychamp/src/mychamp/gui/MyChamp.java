/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mychamp.bll.Team;
import mychamp.gui.controller.RootLayoutController;
import mychamp.model.MatchModel;
import mychamp.model.TeamModel;

/**
 *
 * @author Adam
 */
public class MyChamp extends Application
{

    private Stage primaryStage;
    private BorderPane rootLayout;
    private TeamModel teamModel;
    private MatchModel matchModel;

    @Override
    public void start(Stage stage) throws Exception
    {

        this.primaryStage = stage;
        this.primaryStage.setTitle("myChamp");

        teamModel = new TeamModel();
        matchModel = new MatchModel();

        // FOR TESTING PURPOSES ONLY!!
      
        teamModel.addTeam(new Team("Team Jedna"));
        teamModel.addTeam(new Team("Team Dva"));
        teamModel.addTeam(new Team("Team Tri"));
        teamModel.addTeam(new Team("Team Ctyri"));
        teamModel.addTeam(new Team("Team Pet"));
        teamModel.addTeam(new Team("Team Sest"));
        teamModel.addTeam(new Team("Team Sedm"));
        teamModel.addTeam(new Team("Team Osm"));
        teamModel.addTeam(new Team("Team Devet"));
        teamModel.addTeam(new Team("Team Deset"));
        teamModel.addTeam(new Team("Team Jedenact"));
        teamModel.addTeam(new Team("Team Dvanact"));
   /*     teamModel.addTeam(new Team("Team Trinact"));
        teamModel.addTeam(new Team("Team Ctrnact"));
        teamModel.addTeam(new Team("Team Patnact"));
         */
        initRootLayout();
    }

    public MatchModel getMatchModel()
    {
        return matchModel;
    }

    public TeamModel getTeamModel()
    {
        return teamModel;
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout()
    {
        try
        {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MyChamp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
