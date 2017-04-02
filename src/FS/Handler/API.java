package FS.Handler;

import FS.Main.Football;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 02.03.2017.
 */
public class API {


    private static API instance;

    public API(){
        instance = this;
    }

    public boolean isInLocation(Location playerLoc, Location l1, Location l2){
        int x1 = Math.min(l1.getBlockX(), l2.getBlockX());
        int y1 = Math.min(l1.getBlockY(), l2.getBlockY());
        int z1 = Math.min(l1.getBlockZ(), l2.getBlockZ());
        int x2 = Math.max(l1.getBlockX(), l2.getBlockX());
        int y2 = Math.max(l1.getBlockY(), l2.getBlockY());
        int z2 = Math.max(l1.getBlockZ(), l2.getBlockZ());
        l1 = new Location(l1.getWorld(), x1, y1, z1);
        l2 = new Location(l1.getWorld(), x2, y2, z2);

        return playerLoc.getBlockX() >= l1.getBlockX() && playerLoc.getBlockX() <= l2.getBlockX()
                && playerLoc.getBlockY() >= l1.getBlockY() && playerLoc.getBlockY() <= l2.getBlockY()
                && playerLoc.getBlockZ() >= l1.getBlockZ() && playerLoc.getBlockZ() <= l2.getBlockZ();
    }

    public void giveItemstoPlayer(Player p){

        List<String> _selLore = new ArrayList<>();

        _selLore.add("§cWähle dein Team!");

        ItemStack _selector = new ItemStack(Material.BANNER);
        ItemMeta _meta = _selector.getItemMeta();
        _meta.setDisplayName("§cTeam-Auswahl");
        _meta.setLore(_selLore);

        _selector.setItemMeta(_meta);

        //------------------------------------------------

        List<String> _exitLore = new ArrayList<>();

        _exitLore.add("§cVerlasse das Spiel!");

        ItemStack _exit = new ItemStack(Material.DARK_OAK_DOOR_ITEM);
        ItemMeta _exitMeta = _exit.getItemMeta();

        _exitMeta.setDisplayName("§4Verlassen!");
        _exitMeta.setLore(_exitLore);

        _exit.setItemMeta(_exitMeta);

        p.getInventory().clear();

        p.getInventory().setItem(0, _selector);
        p.getInventory().setItem(8, _exit);

        p.updateInventory();

        //------------------------------------------------


    }

    public void teleportPlayers(){

        ArrayList<String> locationStrings = new ArrayList<>();
        locationStrings.add("One");
        locationStrings.add("Two");
        locationStrings.add("Three");

        locationStrings.add("Four");
        locationStrings.add("Five");
        locationStrings.add("Six");

        for(int i = 0; i < Football.getInstance().teamOne.getPlayers().size(); i++){

            if(i > 4){

                String locationtoSpawn = Football.getInstance().getConfig().getString("Locations.Spawn." + locationStrings.get(i));

                Player player;
                player = Football.getInstance().teamOne.getPlayerbyIndex(i);

                player.teleport(convert(locationtoSpawn));
                player.setHealth(20);
                player.setWalkSpeed(1);
                player.setFlying(false);
                player.setAllowFlight(false);
                player.setFoodLevel(20);
                player.setGameMode(GameMode.ADVENTURE);

                player.playSound(player.getLocation(), Sound.ENTITY_ENDERPEARL_THROW, 1, 1);

            }else{

                String locationtoSpawn = Football.getInstance().getConfig().getString("Locations.Spawn." + locationStrings.get(i));

                Player player;
                player = Football.getInstance().teamTwo.getPlayerbyIndex(i);

                player.teleport(convert(locationtoSpawn));
                player.setHealth(20);
                player.setWalkSpeed(1);
                player.setFlying(false);
                player.setAllowFlight(false);
                player.setFoodLevel(20);
                player.setGameMode(GameMode.ADVENTURE);

                player.playSound(player.getLocation(), Sound.ENTITY_ENDERPEARL_THROW, 1, 1);

            }

        }

    }

    public Location convert(String location){

        String[] splitString = location.split(",");
        String xS, yS, zS, yawS, pitchS;
        xS = splitString[0];
        yS = splitString[1];
        zS = splitString[2];
        yawS = splitString[3];
        pitchS = splitString[4];
        String world = "world";

        double x, y, z;
        float yaw, pitch;
        x = Double.parseDouble(xS);
        y = Double.parseDouble(yS);
        z = Double.parseDouble(zS);
        yaw = Float.parseFloat(yawS);
        pitch = Float.parseFloat(pitchS);


        Location loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);

        return loc;

    }

    public void fill_teamSel(Inventory _teamSel){

        ItemStack teamOne, teamTwo;


        teamOne = new ItemStack(Material.APPLE);
        teamTwo = new ItemStack(Material.POTATO);


        ItemMeta _one, _two;

        _one = teamOne.getItemMeta();
        _two = teamTwo.getItemMeta();

        _one.setDisplayName("Team #01");
        _two.setDisplayName("Team #02");

        teamOne.setItemMeta(_one);
        teamTwo.setItemMeta(_two);

        _teamSel.clear();

        _teamSel.setItem(2, teamOne);
        _teamSel.setItem(5, teamTwo);
    }


    public static API getInstance(){
        return instance;
    }

}
