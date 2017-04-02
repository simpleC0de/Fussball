package FS.Events;

import FS.Storage.MainStorage;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

/**
 * Created by root on 02.03.2017.
 */
public class InventoryEvents implements Listener{



    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(!MainStorage.getInstance().inGame){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e){
        if(!MainStorage.getInstance().inGame){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMove(InventoryMoveItemEvent e){
        if(!MainStorage.getInstance().inGame){
            e.setCancelled(true);
        }
    }


}
