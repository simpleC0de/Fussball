package FS.Events;

import FS.Storage.MainStorage;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

/**
 * Created by root on 02.03.2017.
 */
public class MoveEvent implements Listener{

    @EventHandler
    public void onMove(PlayerMoveEvent e){

        Player p = e.getPlayer();

        Location to, from;

        to = e.getTo();
        from = e.getFrom();

        Vector vec = to.toVector();
        double i = vec.distance(from.toVector());

        if(i > 0.4D && !p.getActivePotionEffects().contains(PotionEffectType.SPEED) && ((CraftPlayer)p).getHandle().ping < 120){

            if(MainStorage.getInstance().openforCheck.contains(p)){
                return;
            }

            MainStorage.getInstance().openforCheck.add(p);

        }


    }

}
