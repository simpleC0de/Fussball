package FS.Main;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.*;

/**
 * Created by root on 02.03.2017.
 */
public class MySQL {


    private Connection conn;
    private String hostname;
    private String user;
    private String password;
    private String database;
    private int port;
    public MySQL() throws Exception
    {

        this.hostname = Football.getInstance().getConfig().getString("MySQL.Hostname");
        this.port = Football.getInstance().getConfig().getInt("MySQL.Port");
        this.database =  Football.getInstance().getConfig().getString("MySQL.Database");
        this.user = Football.getInstance().getConfig().getString("MySQL.User");
        this.password = Football.getInstance().getConfig().getString("MySQL.Password");
        this.openConnection();

    }
    public Connection openConnection()
    {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://"+hostname + ":" + port + "/" + database + "?user=" + user + "&password=" + password + "&useUnicode=true&characterEncoding=UTF-8");
            this.conn = conn;
            //UUID, GAMES, GOALS

            queryUpdate("CREATE TABLE IF NOT EXISTS stats(UUID varchar(64), GAMES int, GOALS int)");
            queryUpdate("CREATE TABLE IF NOT EXISTS warning(UUID varchar(64), WARNINGS int);");

            return conn;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Bukkit.shutdown();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Bukkit.shutdown();
        }
        return conn;
    }



    public Connection getConnection()
    {
        return this.conn;
    }
    public boolean hasConnection()
    {
        try {
            return this.conn != null || this.conn.isValid(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public void closeRessources(ResultSet rs, PreparedStatement st)
    {
        if(rs != null)
        {
            try {
                rs.close();
            } catch (SQLException e) {

            }
        }
        if(st != null)
        {
            try {
                st.close();
            } catch (SQLException e) {

            }
        }
    }


    public void closeConnection()
    {
        try {
            this.conn.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }finally
        {
            this.conn = null;
        }

    }
    public void queryUpdate(final String query)
    {
        new BukkitRunnable() {

            public void run() {
                try {
                    if(getConnection().isValid(2000))
                    {
                        PreparedStatement st = null;

                        Connection conn = getConnection();
                        try {
                            st = conn.prepareStatement(query);
                            st.executeUpdate();
                        } catch (SQLException e) {
                            System.err.println("Failed to send update '" + query + "'.");
                            e.printStackTrace();
                        }finally
                        {
                            closeRessources(null, st);
                        }
                    }
                    else
                    {
                        try {
                            openConnection();
                            PreparedStatement st = null;

                            Connection conn = getConnection();
                            try {
                                st = conn.prepareStatement(query);
                                st.executeUpdate();
                            } catch (SQLException e) {
                                System.err.println("Failed to send update '" + query + "'.");
                                e.printStackTrace();
                            }finally
                            {
                                closeRessources(null, st);
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }


            }
        }.runTaskAsynchronously(Football.getInstance());

    }


    //UUID, GAMES, GOALS

    public boolean playerExists(String uuid){
        try {
            if(!getConnection().isValid(2000)){
                openConnection();
            }

            Connection conn = getConnection();
            PreparedStatement st = conn.prepareStatement("SEELCT * FROM stats WHERE UUID = '" + uuid + "';");
            ResultSet rs = st.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public void addPlayer(String uuid){
        try {
            if(!getConnection().isValid(2000)){
                openConnection();
            }

            queryUpdate("INSERT INTO stats(`UUID`, `GAMES`, `GOALS`) VALUES('" + uuid + "', 0, 0)");
            queryUpdate("INSERT INTO warning(`UUID`, `WARNINGS`) VALUES ('" + uuid + "', 0)");


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateGoals(String uuid, int goals){
        try {
            if(!getConnection().isValid(2000)){
                openConnection();
            }

            queryUpdate("UPDATE stats SET GOALS = GOALS + " + goals + " WHERE UUID = '" + uuid + "';");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateGames(String uuid){
        try {
            if(!getConnection().isValid(2000)){
                openConnection();
            }

            queryUpdate("UPDATE stats SET GAMES = GAMES + 1 WHERE UUID = '" + uuid + "';");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addWarning(String uuid){

        queryUpdate("UPDATE warning SET WARNINGS = WARNINGS + 1 WHERE UUID = '" + uuid + "';");

    }

    public void removeWarning(String uuid){

        queryUpdate("UPDATE warning SET WARNINGS = WARNINGS - 1 WHERE UUID = '" + uuid + "';");

    }

}
