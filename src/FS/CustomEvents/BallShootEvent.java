package FS.CustomEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by root on 02.03.2017.
 */
public class BallShootEvent extends Event{

    private String uuid;
    private Player shooter;
    private static final HandlerList handlers = new HandlerList();

    public BallShootEvent(Player shooterR, String uuidD) {

        uuid = uuidD;
        shooter = shooterR;

    }


    public Player getShooter(){
        return shooter;
    }

    public String getShooterUUID(){
        return uuid;
    }












    public static HandlerList getHandlerList() {
        return handlers;
    }


    public HandlerList getHandlers() {
        // TODO Auto-generated method stub
        return handlers;
    }

}
