package FS.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;

/**
 * Created by root on 03.03.2017.
 */
public class Explosion implements Listener{

    @EventHandler
    public void onExplode(BlockExplodeEvent e){

        e.blockList().clear();

    }

}
