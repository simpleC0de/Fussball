package FS.Handler;

import FS.CustomEvents.BallShootEvent;
import FS.Main.Football;
import FS.Storage.MainStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 02.03.2017.
 */
public class MainHandler {


    private static MainHandler instance;

    public MainHandler(){
        handle();
        instance = this;
    }


    public BukkitTask task;
    private HashMap<Player, Location> locs = new HashMap<>();

    private Location ballSpawn = API.getInstance().convert(Football.getInstance().getConfig().getString("Locations.Ballspawn") + ",0,0");

    private double goalOne, goalTwo;

    //Vector velocity = currentLocation.toVector().subtract(previousLocation.toVector()).normalize().multiply(speed)
    protected void handle() {

        goalOne = Double.parseDouble(Football.getInstance().getConfig().getString("Locations.Goals.First"));
        goalTwo = Double.parseDouble(Football.getInstance().getConfig().getString("Locations.Goals.Second"));

        task = new BukkitRunnable() {

            @Override
            public void run() {
                //Method to store latest and current Location then convert it to a Vector



                for(Player all : MainStorage.getInstance().allPlayers){

                    Location loc, locBefore;

                    if(locs.containsKey(all)){
                        locBefore = locs.get(all);
                    }else{
                        locs.put(all, all.getLocation());
                        continue;
                    }

                    loc = all.getLocation();

                    Vector v = loc.toVector().subtract(locBefore.toVector().normalize().multiply(1));

                    locs.put(all, loc);

                    List<Entity> entities = all.getNearbyEntities(1.5, 1, 1.5);

                    for(int i = 0; i < entities.size(); i++){

                        if(entities.get(i) instanceof Chicken){

                            Chicken c = (Chicken)entities.get(i);

                            //Update chicken upon shot
                            c.setSilent(true);
                            c.setLastDamage(0);
                            c.setBreed(false);
                            c.setAdult();
                            c.setCustomNameVisible(true);
                            c.setCustomName("§cBall");
                            c.setGravity(true);
                            c.setHealth(500000);
                            c.setNoDamageTicks(1000000);
                            //Chicken updated


                            //Shoot the Chicken

                            Bukkit.getServer().getPluginManager().callEvent(new BallShootEvent(all, all.getUniqueId().toString()));

                            c.setVelocity(v);

                            //Play a sound for shooting
                            playSound();


                            if(c.getLocation().getZ() > goalOne && c.getLocation().getZ() < goalTwo){

                                Football.getInstance().teamOne.addGoal();

                            }else{

                                Football.getInstance().teamTwo.addGoal();

                            }

                            c.getLocation().getWorld().createExplosion(2,3,5,1);

                            c.teleport(ballSpawn);







                        }else{
                            continue;
                        }

                    }
                }
            }
        }.runTaskTimer(Football.getInstance(), 0, 3);

    }

    protected void playSound(){
        for(Player all : MainStorage.getInstance().allPlayers){
            //Plays the Sound for every player
            all.playSound(all.getLocation(), Sound.ENTITY_BOBBER_THROW, 1, 1);
        }
    }

    public static MainHandler getInstance(){
        return instance;
    }


}
