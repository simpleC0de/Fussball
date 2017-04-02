package FS.Handler;

import FS.Main.Football;
import FS.Storage.MainStorage;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

/**
 * Created by root on 02.03.2017.
 */
public class CheatHandler {

    public CheatHandler(){
        handleCheaters();
    }

    protected void handleCheaters(){

        new BukkitRunnable(){

            @Override
            public void run(){

                for(Player p : MainStorage.getInstance().openforCheck){

                HashMap<Player, Integer> cheatAmount = MainStorage.getInstance().cheatAmount;

                if(MainStorage.getInstance().maybeCheating.contains(p)){

                    //Do a closer check-up.



                    return;
                }

                if(MainStorage.getInstance().maxMovement.containsKey(p)){

                double speed = MainStorage.getInstance().maxMovement.get(p);

                if(speed > 0.5D) {

                    p.kickPlayer("§cDu wurdest wegen §4-§cHacks§4-§c gekickt!");
                    Football.getInstance().getSQL().addWarning(p.getUniqueId().toString());

                    if(cheatAmount.get(p) > 2){

                        if(p.isOnline()){

                            p.kickPlayer("§cDu wurdest wegen §4-§cHacks§4-§c gekickt!");
                            Football.getInstance().getSQL().addWarning(p.getUniqueId().toString());

                        }

                    }

                }else{
                    if(speed > 0.3D && speed < 0.5D){
                        if(MainStorage.getInstance().maybeCheating.contains(p)){return;}
                        MainStorage.getInstance().maybeCheating.add(p);

                    }
                    return;
                }

                try{MainStorage.getInstance().maybeCheating.remove(p);MainStorage.getInstance().allPlayers.remove(p);MainStorage.getInstance().maxMovement.remove(p);MainStorage.getInstance().cheatAmount.remove(p);MainStorage.getInstance().openforCheck.remove(p);}catch(Exception ex){}
                //Removing player from every list.



                }

                }

            }

        }.runTaskTimerAsynchronously(Football.getInstance(), 0, 60);

    }

}
