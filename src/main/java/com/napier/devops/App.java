package com.napier.devops;

import java.sql.Connection;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args)
    {
        App a = new App();
        DBConnection dbConnection = new DBConnection();

        try
        {
            Connection connection = dbConnection.connect();
            if (connection != null)
            {
                System.out.println("Successfully connected");

                UseCase1 useCase1 = new UseCase1(connection);
                ArrayList<Employee> employees = useCase1.getAllSalaries();
                useCase1.printSalaries(employees);

                if (employees != null)
                {
                    System.out.println(employees.size());
                }

                dbConnection.disconnect(connection);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}