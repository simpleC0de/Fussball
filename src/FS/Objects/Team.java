package FS.Objects;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by root on 02.03.2017.
 */
public class Team {



    private ArrayList<Player> spieler = new ArrayList<>();

    private ChatColor teamColor;

    private String teamName;

    private int goals;



    public Team(ArrayList<Player> spielerR, ChatColor teamCoolor, String name){
        spieler = spielerR;
        teamColor = teamCoolor;
        teamName = name;
        goals = 0;
    }

    public void addPlayer(Player p){
        if(!spieler.contains(p)){
            spieler.add(p);
        }
    }

    public ArrayList<Player> getPlayers(){
        return spieler;
    }

    public Player getPlayerbyIndex(int index){
        return spieler.get(index);
    }

    public boolean isInTeam(Player p){
        if(spieler.contains(p)){
            return true;
        }
        return false;
    }

    public void removePlayer(Player p){
        if(spieler.contains(p)){
            spieler.remove(p);
        }
    }

    public String getTeamName(){
        return teamName;
    }

    public ChatColor getTeamColor(){
        return teamColor;
    }

    public void setTeamColor(ChatColor color){
        teamColor = color;
    }

    public void setTeamName(String name){
        teamName = name;
    }

    public void addGoal(){

        goals ++;

    }

    public Integer getGoals(){

        return goals;

    }


}
