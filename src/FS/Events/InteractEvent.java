package FS.Events;

import FS.Handler.API;
import FS.Main.Football;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

/**
 * Created by root on 02.03.2017.
 */
public class InteractEvent implements Listener{


    private Inventory _teamSel = Bukkit.createInventory(null, 9);

    public InteractEvent(){
        API.getInstance().fill_teamSel(_teamSel);
    }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e){

        Player p;
        Location loc;
        p = e.getPlayer();
        loc = e.getPlayer().getLocation();

        //Locations.First.Upper // Lower

        Location l1, l2, l3, l4;

        l1 = API.getInstance().convert(Football.getInstance().getConfig().getString("Locations.First.Upper") + ",0,0");
        l2 = API.getInstance().convert(Football.getInstance().getConfig().getString("Locations.First.Lower") + ",0,0");

        l3 = API.getInstance().convert(Football.getInstance().getConfig().getString("Locations.Second.Lower") + ",0,0");
        l4 = API.getInstance().convert(Football.getInstance().getConfig().getString("Locations.Second.Lower") + ",0,0");


        if(API.getInstance().isInLocation(loc, l1, l2) || API.getInstance().isInLocation(loc, l3, l4)){

            if(e.getRightClicked() instanceof Chicken){

                Vector vec = e.getPlayer().getVelocity();

                e.getRightClicked().setVelocity(vec);

            }

        }else{

            return;

        }

    }

    @EventHandler
    public void onInteractItem(PlayerInteractEvent e){

        if(e.getItem().getType() == Material.BANNER){

            //Open Inventory for choosing of Team.

            e.getPlayer().closeInventory();
            e.getPlayer().openInventory(_teamSel);

        }

        if(e.getItem().getType() == Material.DARK_OAK_DOOR){

            //Send player back to main server.

        }

    }


}
