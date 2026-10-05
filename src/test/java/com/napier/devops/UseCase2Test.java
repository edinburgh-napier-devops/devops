package com.napier.devops;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UseCase2Test
{
    private Connection connection;
    private UseCase2 useCase2;

    @BeforeEach
    void setUp() throws Exception
    {
        connection = DriverManager.getConnection("jdbc:h2:mem:department-report");

        try (Statement statement = connection.createStatement())
        {
            statement.execute(
                    "CREATE TABLE employees ("
                            + "emp_no INT PRIMARY KEY, "
                            + "first_name VARCHAR(14) NOT NULL, "
                            + "last_name VARCHAR(16) NOT NULL)");
            statement.execute(
                    "CREATE TABLE salaries ("
                            + "emp_no INT NOT NULL, salary INT NOT NULL, to_date DATE NOT NULL)");
            statement.execute(
                    "CREATE TABLE departments ("
                            + "dept_no VARCHAR(4) PRIMARY KEY, dept_name VARCHAR(40) NOT NULL)");
            statement.execute(
                    "CREATE TABLE dept_emp ("
                            + "emp_no INT NOT NULL, dept_no VARCHAR(4) NOT NULL, to_date DATE NOT NULL)");

            statement.execute(
                    "INSERT INTO employees VALUES "
                            + "(10001, 'Mary', 'Smith'), "
                            + "(10002, 'John', 'Doe'), "
                            + "(10003, 'Alex', 'Jones')");
            statement.execute(
                    "INSERT INTO salaries VALUES "
                            + "(10001, 60000, DATE '2020-01-01'), "
                            + "(10001, 70000, DATE '9999-01-01'), "
                            + "(10002, 65000, DATE '9999-01-01'), "
                            + "(10003, 62000, DATE '9999-01-01')");
            statement.execute(
                    "INSERT INTO departments VALUES "
                            + "('d001', 'Development'), ('d002', 'Sales')");
            statement.execute(
                    "INSERT INTO dept_emp VALUES "
                            + "(10001, 'd001', DATE '9999-01-01'), "
                            + "(10002, 'd002', DATE '9999-01-01'), "
                            + "(10003, 'd001', DATE '2020-01-01')");
        }

        useCase2 = new UseCase2(connection);
    }

    @AfterEach
    void tearDown() throws Exception
    {
        connection.close();
    }

    @Test
    void returnsCurrentEmployeesAndSalariesForDepartment()
    {
        ArrayList<Employee> employees =
                useCase2.getSalariesByDepartment("Development");

        assertNotNull(employees);
        assertEquals(1, employees.size());
        assertEquals(10001, employees.getFirst().emp_no);
        assertEquals("Mary", employees.getFirst().first_name);
        assertEquals("Smith", employees.getFirst().last_name);
        assertEquals(70000, employees.getFirst().salary);
    }

    @Test
    void returnsEmptyListForUnknownDepartment()
    {
        ArrayList<Employee> employees =
                useCase2.getSalariesByDepartment("Unknown Department");

        assertNotNull(employees);
        assertTrue(employees.isEmpty());
    }
}
