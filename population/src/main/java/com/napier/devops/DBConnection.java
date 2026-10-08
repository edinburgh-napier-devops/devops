package com.napier.devops;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
    public Connection connect()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                Thread.sleep(30000);
                String host = System.getenv().getOrDefault("DB_HOST", "world-db");
                String port = System.getenv().getOrDefault("DB_PORT", "3306");
                return DriverManager.getConnection(
                        "jdbc:mysql://" + host + ":" + port + "/world?useSSL=false&allowPublicKeyRetrieval=true",
                        "root",
                        "example");
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
        return null;
    }

    public void disconnect(Connection con)
    {
        if (con != null)
        {
            try
            {
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }
}