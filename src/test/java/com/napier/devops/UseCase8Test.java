package com.napier.devops;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UseCase8Test
{
    private Connection connection;
    private UseCase8 useCase8;

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
                    "CREATE TABLE salaries ("
                            + "emp_no INT NOT NULL, "
                            + "salary INT NOT NULL, "
                            + "FOREIGN KEY (emp_no) REFERENCES employees (emp_no) "
                            + "ON DELETE CASCADE)");
            statement.execute(
                    "INSERT INTO employees (emp_no, first_name, last_name) "
                            + "VALUES (10001, 'Georgi', 'Facello')");
            statement.execute(
                    "INSERT INTO salaries (emp_no, salary) VALUES (10001, 60117)");
        }

        useCase8 = new UseCase8(connection);
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
    void deletesEmployee() throws Exception
    {
        UseCase8.DeleteResult result = useCase8.deleteEmployee(10001);

        assertEquals(UseCase8.DeleteResult.DELETED, result);

        assertEquals(0, countRows("SELECT COUNT(*) FROM employees WHERE emp_no = 10001"));
    }

    @Test
    void deletesAssociatedRecordsByCascade() throws Exception
    {
        useCase8.deleteEmployee(10001);

        assertEquals(0, countRows("SELECT COUNT(*) FROM salaries WHERE emp_no = 10001"));
    }

    @Test
    void reportsWhenEmployeeDoesNotExist()
    {
        UseCase8.DeleteResult result = useCase8.deleteEmployee(99999);

        assertEquals(UseCase8.DeleteResult.NOT_FOUND, result);
    }

    @Test
    void rejectsInvalidEmployeeNumber() throws Exception
    {
        assertEquals(UseCase8.DeleteResult.INVALID_INPUT, useCase8.deleteEmployee(0));
        assertEquals(UseCase8.DeleteResult.INVALID_INPUT, useCase8.deleteEmployee(-1));

        assertEquals(1, countRows("SELECT COUNT(*) FROM employees WHERE emp_no = 10001"));
    }

    @Test
    void reportsDatabaseErrors() throws Exception
    {
        connection.close();

        UseCase8.DeleteResult result = useCase8.deleteEmployee(10001);

        assertEquals(UseCase8.DeleteResult.DATABASE_ERROR, result);
    }

    private int countRows(String sql) throws Exception
    {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql))
        {
            resultSet.next();
            return resultSet.getInt(1);
        }
    }
}