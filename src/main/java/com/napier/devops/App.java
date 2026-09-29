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

                String departmentName = args.length > 0 ? args[0] : "Development";
                UseCase2 useCase2 = new UseCase2(connection);
                ArrayList<Employee> departmentEmployees =
                        useCase2.getSalariesByDepartment(departmentName);
                useCase2.printSalaries(departmentEmployees, departmentName);
                if (departmentEmployees != null)
                {
                    System.out.println(departmentEmployees.size()
                            + " employees found in " + departmentName);
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
