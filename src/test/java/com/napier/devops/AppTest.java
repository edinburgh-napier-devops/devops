package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }
    /*
      Test printSalaries
     */
    @Test
    void printSalaries()
    {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        Employee emp = new Employee();
        emp.emp_no = 1;
        emp.first_name = "Kevin";
        emp.last_name = "Chalmers";
        emp.title = "Engineer";
        emp.salary = 55000;
        employees.add(emp);
        app.printSalaries(employees);
    }

    /*
      Test displayEmployee
     */
    // Test for nothing
    @Test
    void displayEmployeeNull()
    {
        app.displayEmployee(null);
    }
    // Test output display
    @Test
    void displayEmployee()
    {
        Employee emp = new Employee();
        emp.emp_no = 10001;
        emp.first_name = "Georgi";
        emp.last_name = "Facello";
        emp.title = "Senior Engineer";
        emp.salary = 88958;
        emp.dept_name = "Development";
        emp.manager = "Leon DasSarma";
        app.displayEmployee(emp);
    }
    // Test for blank text fields, not the word null
    @Test
    void displayEmployeeMissingFields()
    {
        Employee emp = new Employee();
        emp.emp_no = 2;
        emp.salary = 0;
        app.displayEmployee(emp);
    }
}

