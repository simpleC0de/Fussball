package FS.Storage;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by root on 02.03.2017.
 */
public class MainStorage {


    private static MainStorage instance;

    public boolean inGame = false;

    public MainStorage(){
        instance = this;
    }

    public ArrayList<Player> allPlayers = new ArrayList<>();

    public ArrayList<Player> maybeCheating = new ArrayList<>();

    public HashMap<Player, Integer> cheatAmount = new HashMap<Player, Integer>();

    public ArrayList<Player> openforCheck = new ArrayList<>();

    public HashMap<Player, Double> maxMovement = new HashMap<>();

    public static MainStorage getInstance(){
        return instance;
    }

	/*public void test(){

        World world = Bukkit.getWorld("WORLD NAME HERE");
        Location edgeMin = new Location(world, 10, 10, 10);
        Location edgeMax = new Location(world, 20, 20, 20);
//List of materials that you want removed, just copy the structure and add all block materials(types) you wish
        List<Material> removedMaterials = new ArrayList<Material>();
        removedMaterials.add(Material.DIRT);
//This runs through each block in the specified region
        for (int x = edgeMin.getBlockX(); x <= edgeMax.getBlockX(); x ++) {
            for (int y = edgeMin.getBlockX(); y <= edgeMax.getBlockX(); y ++) {
                for (int z = edgeMin.getBlockX(); z <= edgeMax.getBlockX(); z ++) {
                    Block b = world.getBlockAt(x, y, z);
//If the types of blocks to be removed contain the type of the block being inspected, set it to air(remove it).
                    if(removedMaterials.contains(b.getType())){
                        b.setType(Material.AIR);
                    }
                }
            }
        }
    }

	}
*/

}
