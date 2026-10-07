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
                else if (args.length > 0 && "--salary-by-department".equals(args[0]))
                {
                    runUseCase3(connection, args);
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

    private static void runUseCase3(Connection connection, String[] args)
    {
        if (args.length != 2)
        {
            System.out.println(
                    "Usage: --salary-by-department <manager-employee-number>");
            return;
        }

        int managerId;

        try
        {
            managerId = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Manager employee number must be a whole number");
            return;
        }

        UseCase3 useCase3 = new UseCase3(connection);

        String department = useCase3.getManagerDepartment(managerId);

        if (department == null)
        {
            System.out.println(
                    "No department is assigned to manager " + managerId);
            System.out.println("Please refer to HR.");
            return;
        }

        System.out.println(
                "Department salary report for department " + department);

        ArrayList<Employee> employees =
                useCase3.getDepartmentSalaries(managerId);

        if (employees == null)
        {
            System.out.println("Failed to produce salary report.");
            return;
        }

        if (employees.isEmpty())
        {
            System.out.println("The department has no employees.");
            return;
        }

        useCase3.printDepartmentSalaryReport(employees);

        System.out.println(
                employees.size() + " employees found in department " + department);
    }
}
