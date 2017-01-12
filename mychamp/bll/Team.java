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
public class Team extends DataObject
{

    private String name;
    private String group;
    private int gd;
    private int points;
    private int rank;
    private int rankGroup;
    private int goals;
    private int matchesPlayed;

    public Team()
    {
        name = new String();
        group = new String();
        gd = 0;
        points = 0;
        rankGroup = 0;
        rank = 0;
        goals = 0;
        matchesPlayed = 0;
    }

    public Team(String name)
    {
        this.name = name;
        group = new String();
        gd = 0;
        points = 0;
        rankGroup = 0;
        rank = 0;
        goals = 0;
        matchesPlayed = 0;
    }

    public int getMatchesPlayed()
    {
        return matchesPlayed;
    }

    public void addMatchesPlayed()
    {
        this.matchesPlayed++;
    }

    public int getRank()
    {
        return rank;
    }

    public int getRankGroup()
    {
        return rankGroup;
    }

    public int getGoals()
    {
        return goals;
    }

    public void setRankGroup(int rankGroup)
    {
        this.rankGroup = rankGroup;
    }

    public void setGoals(int goals)
    {
        this.goals = goals;
    }

    public void setRank(int rank)
    {
        this.rank = rank;
    }

    public int getGD()
    {
        return gd;
    }

    public int getPoints()
    {
        return points;
    }

    public void setGd(int gd)
    {
        this.gd = gd;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public void setGroup(String group)
    {
        this.group = group;
    }

    public String getGroup()
    {
        return group;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public int getGd()
    {
        return gd;
    }

}
