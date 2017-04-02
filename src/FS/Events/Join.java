package FS.Events;

import FS.Main.Football;
import FS.Storage.MainStorage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by root on 02.03.2017.
 */
public class Join implements Listener{



    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();

        String uuid = p.getUniqueId().toString();

        if(!Football.getSQL().playerExists(uuid)){
            Football.getSQL().addPlayer(uuid);
        }

        MainStorage.getInstance().allPlayers.add(e.getPlayer());

        e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.AQUA + p.getDisplayName());

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){

        Player p = e.getPlayer();

        e.setQuitMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "-" + ChatColor.DARK_GRAY + "] " + ChatColor.LIGHT_PURPLE + p.getDisplayName() );

        MainStorage.getInstance().allPlayers.remove(e.getPlayer());

    }

    @EventHandler
    public void onKick(PlayerKickEvent e){

        MainStorage.getInstance().allPlayers.remove(e.getPlayer());

    }


}
