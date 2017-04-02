package FS.Handler;

import FS.Main.Football;
import FS.Storage.MainStorage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by root on 02.03.2017.
 */
public class TimeHandler {


    public TimeHandler(){
        handle();
    }

    protected void handle(){

        new BukkitRunnable() {
            int counter = 60;
            @Override
            public void run() {
                int onlinePlayer = MainStorage.getInstance().allPlayers.size();
                counter--;
                if(!(onlinePlayer % 2 == 0)){
                    if(!(counter >= 60)){
                        counter += 5;
                    }
                }if(onlinePlayer <= 3){
                    Bukkit.broadcastMessage("§4Es fehlen " + (4 - onlinePlayer) + " Spieler bis zum Start!");
                    if(!(counter >= 60)){
                        counter += 5;
                    }
                }

                for(Player all : MainStorage.getInstance().allPlayers){
                    all.setLevel(counter);
                }

                if(counter == 30){
                    Bukkit.broadcastMessage("§cDas Spiel beginnt in " + counter + " Sekunden!");
                }
                if(counter == 15){
                    Bukkit.broadcastMessage("§cDas Spiel beginnt in " + counter + " Sekunden!");
                }
                if(counter == 10){
                    Bukkit.broadcastMessage("§cDas Spiel beginnt in " + counter + " Sekunden!");
                }
                if(counter > 5){
                    Bukkit.broadcastMessage("§cDas Spiel beginnt in " + counter + " Sekunden!");
                }
                if(counter == 0){
                    new MainHandler();
                    API.getInstance().teleportPlayers();
                    cancel();
                }


            }
        }.runTaskTimer(Football.getInstance(), 0, 20);
    }

    protected void gameTimer(){

        new BukkitRunnable(){

            int counter = 300;

            @Override
            public void run(){
            --counter;

            if(counter == 180){

                Bukkit.broadcastMessage("§cNoch 3 Minuten zu spielen!");

            }

            if(counter == 120){

                Bukkit.broadcastMessage("§cNoch 2 Minuten zu spielen!");

            }

            if(counter == 60){

                Bukkit.broadcastMessage("§cNoch 1 Minute zu spielen!");

            }

            if(counter == 30){

                Bukkit.broadcastMessage("§cNoch 30 Sekunden zu spielen!");

            }

            if(counter < 10 && counter > 0){

                Bukkit.broadcastMessage("§cNoch " + counter + " Sekunden zu spielen!");

            }

            if(counter == 0){



                Bukkit.broadcastMessage("§cVorbei!");


                int teamOneGoals, teamTwoGoals;


                teamOneGoals = Football.getInstance().teamOne.getGoals();
                teamTwoGoals = Football.getInstance().teamTwo.getGoals();

                if(teamOneGoals > teamTwoGoals){

                    Bukkit.broadcastMessage("Team #01 hat gewonnen!");

                }

                if(teamTwoGoals > teamOneGoals){

                    Bukkit.broadcastMessage("Team #02 hat gewonnen!");

                }

                if(teamTwoGoals == teamOneGoals){

                    Bukkit.broadcastMessage("Unentschieden! May add vote for overtime");

                }


                //Teleport players back in the lobby, let 'em run in the arena.

                if(counter == -10){

                    MainHandler.getInstance().task.cancel();

                    cancel();

                }

            }


            }

        }.runTaskTimerAsynchronously(Football.getInstance(), 0, 20);

    }

}
