package com.napier.devops;

import java.sql.*;

public class App
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /** Main method */
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Create a database connection object
        DBConnection dbConnection = new DBConnection();

        try {
            // Connect to the database
            Connection connection = dbConnection.connect();
            if (connection != null){
                System.out.println("Successfully connected");

                // Get Employee
                EmployeeService empService = new EmployeeService(connection);
                Employee emp = empService.getEmployee(255530);

                // Display results
                empService.displayEmployee(emp);

                // Disconnect from database
                dbConnection.disconnect(connection);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}