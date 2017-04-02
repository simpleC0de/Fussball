package FS.Commands;

import FS.Main.Football;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by root on 02.03.2017.
 */
public class SetSpawn implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        // TODO Auto-generated method stub

        if(cs instanceof Player){

            if(args.length != 1){
                cs.sendMessage("§c/setspawn 1-6");

                return true;
            }

            String number = args[0];


            try{
                Location loc = ((Player) cs).getLocation();

                int parsed = Integer.parseInt(number);
                String numberString = "";
                switch (parsed) {
                    case 1:
                        numberString = "One";


                        saveToConfig(numberString, loc);
                        cs.sendMessage("§5Erfolgreich Spawn #" + args[0] + " gespeichert!");
                        break;

                    case 2:
                        numberString = "Two";

                        saveToConfig(numberString, loc);
                        cs.sendMessage("§5Erfolgreich Spawn #" + args[0] + " gespeichert!");
                        break;
                    case 3:
                        numberString = "Three";

                        saveToConfig(numberString, loc);
                        cs.sendMessage("§5Erfolgreich Spawn #" + args[0] + " gespeichert!");
                        break;
                    case 4:
                        numberString = "Four";

                        saveToConfig(numberString, loc);
                        cs.sendMessage("§5Erfolgreich Spawn #" + args[0] + " gespeichert!");
                        break;
                    case 5:
                        numberString = "Five";

                        saveToConfig(numberString, loc);
                        cs.sendMessage("§5Erfolgreich Spawn #" + args[0] + " gespeichert!");
                        break;
                    case 6:
                        numberString = "Six";

                        saveToConfig(numberString, loc);
                        cs.sendMessage("§5Erfolgreich Spawn #" + args[0] + " gespeichert!");
                        break;
                    case 12:

                        Football.getInstance().getConfig().set("Locations.Ballspawn", convert(loc));

                        Football.getInstance().saveConfig();

                        cs.sendMessage("§5Erfolgreich Ballspawn gespeichert!");

                        break;

                    case 20:

                        Football.getInstance().getConfig().set("Locations.Goals.First", "" + loc.getZ());

                        Football.getInstance().saveConfig();

                        cs.sendMessage("§5Erfolgreich Tor #01, Z-Koordinate gespeichert!");

                        break;

                    case 22:

                        Football.getInstance().getConfig().set("Locations.Goals.Second", "" + loc.getZ());

                        Football.getInstance().saveConfig();

                        cs.sendMessage("§5Erfolgreich Tor #01, Z-Koordinate gespeichert!");

                        break;
                    default:

                        cs.sendMessage("Goals: 1-6 \n" +
                                "Ballspawn: 12 \n" +
                                "Goals: 20 & 22");

                        break;
                }


            }catch(ArithmeticException e){
                cs.sendMessage("§c" + args[0] + " ist keine Zahl!");
            }


        }

        return true;
    }

    protected String convert(Location loc){
        double x, y, z;
        float yaw, pitch;

        String finalString = "";
        x = loc.getX();
        y = loc.getY();
        z = loc.getZ();
        yaw = loc.getYaw();
        pitch = loc.getPitch();

        finalString = x + "," + z + "," + y + "," + yaw + "," + pitch;

        return finalString;
    }

    protected void saveToConfig(String number, Location loc){

        String _save = convert(loc);

        //Locations.Spawn.One

        Football.getInstance().getConfig().set("Locations.Spawn." + number, _save);

        Football.getInstance().saveConfig();

    }

}
