/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.bll;

/**
 *
 * @author Adam
 */
public class Match extends DataObject
{

    private String id;
    private Team home;
    private Team guest;
    private int homeScore;
    private int guestScore;
    private int round;
    private String group;
    private boolean played;

    public Match()
    {
        this.id = new String();
        this.homeScore = 0;
        this.guestScore = 0;
        this.played = true;
    }

    public Match(String id, Team home, Team guest, String group, int round)
    {
        this.id = id;
        this.home = home;
        this.guest = guest;
        this.group = group;
        this.round = round;
        // for testing only
        /*  if (!id.startsWith("MC"))
        {
            this.played = false;
        } else
        {
            this.played = true;
        } */
        this.played = false;
    }

    public String getId()
    {
        return id;
    }

    public Team getGuest()
    {
        return guest;
    }

    public Team getHome()
    {
        return home;
    }

    public int getHomeScore()
    {
        return homeScore;
    }

    public int getGuestScore()
    {
        return guestScore;
    }

    public int getRound()
    {
        return round;
    }

    public String getGroup()
    {
        return group;
    }

    public boolean isPlayed()
    {
        return played;
    }

    public void setPlayed(boolean played)
    {
        this.played = played;
    }

    public String getHomeName()
    {
        return home.getName();
    }

    public String getGuestName()
    {
        return guest.getName();
    }

    public void setHomeScore(int homeScore)
    {
        this.homeScore = homeScore;
    }

    public void setGuestScore(int guestScore)
    {
        this.guestScore = guestScore;
    }

    public Team getWinner()
    {
        if (homeScore >= guestScore)
        {
            return home;
        } else
        {
            return guest;
        }
    }

    public Team getLooser()
    {
        if (homeScore <= guestScore)
        {
            return home;
        } else
        {
            return guest;
        }
    }
}
