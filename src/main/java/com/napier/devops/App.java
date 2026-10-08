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

                if (args.length > 0 && "--update-employee".equals(args[0]))
                {
                    updateEmployee(connection, args);
                }
                else if (args.length > 0 && "--delete-employee".equals(args[0]))
                {
                    deleteEmployee(connection, args);
                }
                else
                {
                    // Use Case 4, Salary report by role
                    UseCase4 useCase4 = new UseCase4(connection);
                    String roleName = args.length > 0 ? args[0] : "Technique Leader";

                    ArrayList<Employee> roleEmployees =
                            useCase4.getSalariesByRole(roleName);
                    useCase4.printSalaries(roleEmployees, roleName);
                    if (roleEmployees != null)
                    {
                        System.out.println(roleEmployees.size()
                                + " employees found in " + roleName);
                    }
                }

                dbConnection.disconnect(connection);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void updateEmployee(Connection connection, String[] args)
    {
        if (args.length != 4)
        {
            System.out.println(
                    "Usage: --update-employee <employee-number> <first-name> <last-name>");
            return;
        }

        int employeeNumber;
        try
        {
            employeeNumber = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Employee number must be a whole number");
            return;
        }

        UseCase7 useCase7 = new UseCase7(connection);
        UseCase7.UpdateResult result =
                useCase7.updateEmployeeDetails(employeeNumber, args[2], args[3]);

        switch (result)
        {
            case UPDATED:
                System.out.println("Employee " + employeeNumber + " updated successfully");
                break;
            case NOT_FOUND:
                System.out.println("No employee found with number " + employeeNumber);
                break;
            case INVALID_INPUT:
                System.out.println("A positive employee number and valid names are required");
                break;
            case DATABASE_ERROR:
                System.out.println("Employee details were not updated because of a database error");
                break;
        }
    }

    private static void deleteEmployee(Connection connection, String[] args)
    {
        if (args.length != 2)
        {
            System.out.println("Usage: --delete-employee <employee-number>");
            return;
        }

        int employeeNumber;
        try
        {
            employeeNumber = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Employee number must be a whole number");
            return;
        }

        UseCase8 useCase8 = new UseCase8(connection);
        UseCase8.DeleteResult result = useCase8.deleteEmployee(employeeNumber);

        switch (result)
        {
            case DELETED:
                System.out.println("Employee " + employeeNumber + " deleted successfully");
                break;
            case NOT_FOUND:
                System.out.println("No employee found with number " + employeeNumber);
                break;
            case INVALID_INPUT:
                System.out.println("A positive employee number is required");
                break;
            case DATABASE_ERROR:
                System.out.println("Employee was not deleted because of a database error");
                break;
        }
    }
}