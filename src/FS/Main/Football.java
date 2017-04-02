package FS.Main;

import FS.Commands.SetSpawn;
import FS.Events.*;
import FS.Handler.API;
import FS.Handler.TimeHandler;
import FS.Objects.Team;
import FS.Storage.MainStorage;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * Created by root on 02.03.2017.
 */
public class Football extends JavaPlugin {


    private static Football instance;

    public Team teamOne = new Team(new ArrayList<>(), ChatColor.BLUE, "§2Team");

    public Team teamTwo = new Team(new ArrayList<>(), ChatColor.RED, "§5Team");

    public void onEnable(){
        instance = this;
        loadConfig();
        loadMySQL();
        loadCommands();
        loadEvents();

        new TimeHandler();

    }

    static MySQL sql;
    static API api;

    public static MySQL getSQL(){
        return sql;
    }

    public static API getAPI(){
        return api;
    }

    public static Football getInstance(){
        return instance;
    }

    public void onDisable(){
        unloadAll();
    }

    public void loadConfig(){
        if(!getDataFolder().exists()){

            getConfig().set("MySQL.Host", "localhost");
            getConfig().set("MySQL.Port", 3306);
            getConfig().set("MySQL.User", "root");
            getConfig().set("MySQL.Database", "fb");

            getConfig().set("Locations.First.Upper", "x,y,z");
            getConfig().set("Locations.First.Lower", "x,y,z");

            getConfig().set("Locations.Second.Upper", "x,y,z");
            getConfig().set("Locations.Second.Lower", "x,y,z");

            getConfig().set("Locations.Spawn.One", "x,y,z,float,pitch");
            getConfig().set("Locations.Spawn.Two", "x,y,z,float,pitch");
            getConfig().set("Locations.Spawn.Three", "x,y,z,float,pitch");

            getConfig().set("Locations.Spawn.Four", "x,y,z,float,pitch");
            getConfig().set("Locations.Spawn.Five", "x,y,z,float,pitch");
            getConfig().set("Locations.Spawn.Six", "x,y,z,float,pitch");

            getConfig().set("Locations.Goals.First", "coordinate");
            getConfig().set("Locations.Goals.Second", "coordinate");

            getConfig().set("Locations.Ballspawn", "x,y,z");


            saveConfig();
        }
    }

    public void loadMySQL(){

        new MainStorage();


        try {
            sql = new MySQL();
            api = new API();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Failed to load MySQL, error: \n " + e.getMessage());
        }
    }

    public void loadEvents(){
        PluginManager pm = getServer().getPluginManager();


        pm.registerEvents(new Join(), this);
        //------------------------------------------------
        pm.registerEvents(new ShootEvent(), this);
        //------------------------------------------------
        pm.registerEvents(new InventoryEvents(), this);
        //------------------------------------------------
        pm.registerEvents(new MoveEvent(), this);
        //------------------------------------------------
        pm.registerEvents(new Explosion(), this);
        //------------------------------------------------
        //Call constructor because of setting purposes
        new InteractEvent();
        pm.registerEvents(new InteractEvent(), this);
        //------------------------------------------------


    }

    public void loadCommands(){
        getCommand("setspawn").setExecutor(new SetSpawn());
    }

    public void unloadAll(){
        sql.closeConnection();
        sql = null;
        System.gc();
    }


}
