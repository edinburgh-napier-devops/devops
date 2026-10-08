package com.napier.devops;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Demo App
 */
public class App
{
    public App() {
    }

    public static void main(String[] args)
    {
        DBConnection dbConnection = new DBConnection();
        Connection con = dbConnection.connect();

        if (con != null)
        {
            System.out.println("Successfully connected");
            try
            {
                Statement stmt = con.createStatement();
                String strSelect = "SELECT * FROM city";
                ResultSet rset = stmt.executeQuery(strSelect);
                while (rset.next())
                {
                    int ID = rset.getInt("ID");
                    String name = rset.getString("Name");
                    System.out.println(name);
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            finally
            {
                dbConnection.disconnect(con);
            }
        }
    }

    public void printCityReport(City city) {
        System.out.println(city);
    }

    public void printCityReport(ArrayList<City> cities) {

    }
}
