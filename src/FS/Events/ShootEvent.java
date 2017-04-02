package FS.Events;

import FS.CustomEvents.BallShootEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by root on 02.03.2017.
 */
public class ShootEvent implements Listener{

    @EventHandler
    public void onShoot(BallShootEvent e){
        System.out.println("Shooter: " + e.getShooter().getDisplayName());
    }

}
