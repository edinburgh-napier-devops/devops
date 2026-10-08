package com.napier.devops;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UseCase7Test
{
    private Connection connection;
    private UseCase7 useCase7;

    @BeforeEach
    void setUp() throws Exception
    {
        connection = DriverManager.getConnection("jdbc:h2:mem:employees");

        try (Statement statement = connection.createStatement())
        {
            statement.execute(
                    "CREATE TABLE employees ("
                            + "emp_no INT PRIMARY KEY, "
                            + "first_name VARCHAR(14) NOT NULL, "
                            + "last_name VARCHAR(16) NOT NULL)");
            statement.execute(
                    "INSERT INTO employees (emp_no, first_name, last_name) "
                            + "VALUES (10001, 'Georgi', 'Facello')");
        }

        useCase7 = new UseCase7(connection);
    }

    @AfterEach
    void tearDown() throws Exception
    {
        if (connection != null && !connection.isClosed())
        {
            connection.close();
        }
    }

    @Test
    void updatesEmployeeNames() throws Exception
    {
        UseCase7.UpdateResult result =
                useCase7.updateEmployeeDetails(10001, " Mary ", "Smith");

        assertEquals(UseCase7.UpdateResult.UPDATED, result);

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT first_name, last_name FROM employees WHERE emp_no = 10001"))
        {
            resultSet.next();
            assertEquals("Mary", resultSet.getString("first_name"));
            assertEquals("Smith", resultSet.getString("last_name"));
        }
    }

    @Test
    void reportsWhenEmployeeDoesNotExist()
    {
        UseCase7.UpdateResult result =
                useCase7.updateEmployeeDetails(99999, "Mary", "Smith");

        assertEquals(UseCase7.UpdateResult.NOT_FOUND, result);
    }

    @Test
    void rejectsInvalidEmployeeDetails() throws Exception
    {
        assertEquals(
                UseCase7.UpdateResult.INVALID_INPUT,
                useCase7.updateEmployeeDetails(0, "Mary", "Smith"));
        assertEquals(
                UseCase7.UpdateResult.INVALID_INPUT,
                useCase7.updateEmployeeDetails(10001, " ", "Smith"));
        assertEquals(
                UseCase7.UpdateResult.INVALID_INPUT,
                useCase7.updateEmployeeDetails(10001, "Mary", "NameThatIsFarTooLong"));

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT first_name, last_name FROM employees WHERE emp_no = 10001"))
        {
            resultSet.next();
            assertEquals("Georgi", resultSet.getString("first_name"));
            assertEquals("Facello", resultSet.getString("last_name"));
        }
    }

    @Test
    void reportsDatabaseErrors() throws Exception
    {
        connection.close();

        UseCase7.UpdateResult result =
                useCase7.updateEmployeeDetails(10001, "Mary", "Smith");

        assertEquals(UseCase7.UpdateResult.DATABASE_ERROR, result);
    }
}
