/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mychamp.bll.Team;

/**
 *
 * @author Meanmichael
 */
public class FileSystem
{

    /**
     *
     * @param teamsList
     * @param teamsFilePath
     */
    public static void saveTeamsToFile(ObservableList<Team> teamsList, String teamsFilePath)
    {
        try
        {
            OutputStream fos = new FileOutputStream(teamsFilePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<Team>(teamsList));
            oos.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param teamsFilePath
     * @return
     */
    public static ObservableList<Team> readTeamsFromFile(String teamsFilePath)
    {
        File teamsFile = new File(teamsFilePath);
        if (teamsFile.exists())
        {
            try
            {
                InputStream in = new FileInputStream(teamsFilePath);
                ObjectInputStream ois = new ObjectInputStream(in);
                List<Team> list = (List<Team>) ois.readObject();
                return FXCollections.observableList(list);
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return FXCollections.emptyObservableList();
    }
}
