package com.tokyocodingclub.jaeyoon.sds.database;

import com.tokyocodingclub.jaeyoon.sds.Simulator;
import com.tokyocodingclub.jaeyoon.sds.Player;
import com.tokyocodingclub.jaeyoon.sds.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.UUID;
import java.util.Enumeration;

import org.bukkit.World;

public class Database
{
    private Simulator simulator;
    private int debugLevel;
    private Config config;
    private Connection connection;
    private World world;

    public Database(Simulator simulator)
    {
        this.simulator = simulator;
        debugLevel = simulator.getDebugLevel();
        config = simulator.getConfig();
        world = simulator.getWorld();
    }

    public void startDatabase()
    {
        try
        {
            if(debugLevel > 0)
            {
                simulator.getLogger().info("Starting SQLite Database: ./" + simulator.getDataFolder().getPath() + "/" + config.getDbFileName());
            }
            connection = DriverManager.getConnection("jdbc:sqlite:./" + simulator.getDataFolder().getPath() + "/" + config.getDbFileName());
            createPlayerTable();
            //createBuildingTable();
            //createNpcTable();
        }
        catch(SQLException e)
        {
            simulator.getLogger().severe(e.getMessage());
        }
    }

    public void stopDatabase()
    {
        try
        {
            saveAllData();
            if (connection != null)
            {
                connection.close();
            }
        }
        catch(SQLException e)
        {
            simulator.getLogger().severe(e.getMessage());
        }
    }

    public void createPlayerTable()
    {
        String sql = "CREATE TABLE IF NOT EXISTS players (\n"
            + "uuid TEXT PRIMARY KEY,\n"
            + "has INTEGER,\n"
            + "hasChance REAL,\n"
            + "healChance REAL,\n"
            + "lastWashTime INTEGER,\n"
            + "lastSanitizeTime INTEGER,\n"
            + "lastSanitize2Time INTEGER,\n"
            + "lastSoapTime INTEGER,\n"
            + "lastDrugTime INTEGER,\n"
            + "healCounter INTEGER,\n"
            + "infectionStage INTEGER,\n"
            + "infectionTime INTEGER,\n"
            + "lastWorldTime INTEGER,\n"
            + "lastAppleTime INTEGER,\n"
            + "lastMilkTime INTEGER\n"
            + "balance INTEGER\n"
            + ");";

        if(debugLevel > 0)
        {
            simulator.getLogger().info("Creating Table");
        }
        try
        {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            if(debugLevel > 0)
            {
                simulator.getLogger().info("Table Created");
            }
        }
        catch(SQLException e)
        {
            simulator.getLogger().severe(e.getMessage());
        }
    }

    public void resetData()
    {
        String sql = "DELETE FROM players;";

        try
        {
            Statement statement = connection.createStatement();

            statement.execute(sql);
        }
        catch(SQLException e)
        {
            simulator.getLogger().severe(e.getMessage());
        }
    }

    public void saveData(Player player)
    {
        UUID uuid = player.getSpigotPlayer().getUniqueId();
        String sql = "INSERT OR REPLACE INTO players(\n"
            + "uuid, has, hasChance, healChance, lastWashTime, lastSanitizeTime, lastSanitize2Time, lastSoapTime, \n"
            + "lastDrugTime, healCounter, infectionStage, infectionTime, lastWorldTime, lastAppleTime, lastMilkTime, balance)\n"
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, uuid.toString());

            int hasValue = 0;
            if (player.getHas())
            {
                hasValue = 1;
            }
            statement.setInt(2, hasValue);

            statement.setDouble(3, player.getHasChance());

            statement.setDouble(4, player.getHealChance());

            statement.setLong(5, player.getLastWashTime());

            statement.setLong(6, player.getLastSanitizeTime());

            statement.setLong(7, player.getLastSanitize2Time());

            statement.setLong(8, player.getLastSoapTime());

            statement.setLong(9, player.getLastDrugTime());

            statement.setInt(10, player.getHealCounter());

            statement.setInt(11, player.getInfectionStage());

            statement.setLong(12, player.getInfectionTime());

            statement.setLong(13, world.getTime());

            statement.setLong(14, player.getLastAppleTime());

            statement.setLong(15, player.getLastMilkTime());

            statement.setInt(16, player.getBalance());

            statement.executeUpdate();
        }
        catch(SQLException e)
        {
            simulator.getLogger().severe(e.getMessage());
        }
    }

    public void saveAllData()
    {
        for (Enumeration<UUID> keys = simulator.getPlayerListKeys(); keys.hasMoreElements();)
        {
            saveData(simulator.getSdsPlayer(keys.nextElement()));
        }
    }

    public boolean loadData(Player player)
    {
        org.bukkit.entity.Player spigotPlayer = player.getSpigotPlayer();
        UUID uuid = spigotPlayer.getUniqueId();
        String sql = "SELECT * FROM players WHERE players.uuid = ?;";
        long time = world.getTime();

        try
        {
            if(debugLevel > 0)
            {
                simulator.getLogger().info("Getting Data for Player: " + spigotPlayer.getDisplayName());
            }
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, uuid.toString());

            ResultSet data = statement.executeQuery();

            if (!data.isBeforeFirst())
            {
                if(debugLevel > 0)
                {
                    simulator.getLogger().info("Data is not before first row, setting defaults");
                }
                player.setHas(false);
                player.setHasChance(config.getBaseHasChance());
                player.setHealChance(config.getBaseHealChance());
                player.setLastWashTime(-config.getWashEffectTime());
                player.setLastSanitizeTime(-config.getSanitizeEffectTime());
                player.setLastSanitize2Time(-config.getSanitize2EffectTime());
                player.setLastSoapTime(-config.getSoapEffectTime());
                player.setLastDrugTime(-config.getDrugEffectTime());
                player.setLastAppleTime(-config.getAppleEffectTime());
                player.setLastMilkTime(-config.getMilkEffectTime());
                player.setHealChance(0);
                player.setInfectionStage(0);
                player.setInfectionTime(0L);
                return false;
            }
            else
            {
                while(data.next())
                {
                    if(debugLevel > 0)
                    {
                        simulator.getLogger().info("Data has rows, setting values");
                    }
                    boolean hasValue = false;
                    if (data.getInt("has") == 1)
                    {
                        hasValue = true;
                    }
                    player.setHas(hasValue);

                    player.setHasChance(data.getDouble("hasChance"));

                    player.setHealChance(data.getDouble("healChance"));

                    long lastWorldTime = data.getLong("lastWorldTime");

                    long timeDifference = time - lastWorldTime;

                    long washTime = data.getLong("lastWashTime") + timeDifference;

                    long sanitizeTime = data.getLong("lastSanitizeTime") + timeDifference;

                    long soapTime = data.getLong("lastSoapTime") + timeDifference;

                    long sanitize2Time = data.getLong("lastSanitize2Time") + timeDifference;

                    long drugTime = data.getLong("lastDrugTime") + timeDifference;
                    
                    long infectTime = data.getLong("infectionTime") + timeDifference;

                    long appleTime = data.getLong("lastAppleTime") + timeDifference;

                    long milkTime = data.getLong("lastMilkTime") + timeDifference;

                    int balance = data.getInt("balance");
                    
                    player.setHealCounter(data.getInt("healCounter"));

                    player.setInfectionStage(data.getInt("infectionStage"));

                    player.setLastWashTime(washTime);
                    
                    player.setLastSanitizeTime(sanitizeTime);

                    player.setLastSanitize2Time(sanitize2Time);

                    player.setLastSoapTime(soapTime);

                    player.setLastDrugTime(drugTime);

                    player.setInfectionTime(infectTime);

                    player.setLastAppleTime(appleTime);

                    player.setLastMilkTime(milkTime);

                    player.setBalance(balance);

                    return true;
                }
            }
        }
        catch(SQLException e)
        {
            simulator.getLogger().severe(e.getMessage());
        }
        return false;
    }

}

/*
public void createNpcTable()
{
    //creates the npc table
}

public void createBuildingsTable()
{
    //creates the buildings table
}

public void saveBuilding(GeneratedBuilding building)
{
    //gets the data for the specified building, then adds it to the database.
}

public GeneratedBuilding loadBuilding(int id)
{
    //returns the generatedbuilding for the id
}

public void createNpcTable()
    {
        String sql = "CREATE TABLE IF NOT EXISTS npcs (\n"
            + "id INTEGER PRIMARY KEY,\n"
            + "type INTEGER,\n"
            + "x INTEGER,\n"
            + "y INTEGER,\n"
            + "z INTEGER,\n"
            + "buildingId INTEGER\n"
            + "hasUI INTEGER\n"
            + "uiID INTEGER\n"
            + "name TEXT"
            + ");";

        if(debugLevel > 0)
        {
            getLogger().info("Creating Table");
        }
        try
        {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            if(debugLevel > 0)
            {
                getLogger().info("Table Created");
            }
        }
        catch(SQLException e)
        {
            getLogger().severe(e.getMessage());
        }
    }

public void createBuilingTable()
{
    String sql = "CREATE TABLE IF NOT EXISTS buildings (\n"
        + "id INTEGER PRIMARY KEY,\n"
        + "type INTEGER,\n"
        + "x INTEGER,\n"
        + "y INTEGER,\n"
        + "z INTEGER,\n"
        + ");";

    if(debugLevel > 0)
    {
        getLogger().info("Creating Table");
    }
    try
    {
        Statement statement = connection.createStatement();
        statement.execute(sql);
        if(debugLevel > 0)
        {
            getLogger().info("Table Created");
        }
    }
    catch(SQLException e)
    {
        getLogger().severe(e.getMessage());
    }
}

*/