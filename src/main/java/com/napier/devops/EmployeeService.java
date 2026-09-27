package com.napier.devops;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeService {

    private Connection _connection;

    public EmployeeService(Connection connection){
        _connection = connection;
    }

    /**
     Retrieve the employee data from DB
     */
    public Employee getEmployee(int ID)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = _connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT e.emp_no, e.first_name, e.last_name, t.title, s.salary, d.dept_name, "
                            + "CONCAT(m.first_name, ' ', m.last_name) AS manager "
                            + "FROM employees AS e "
                            + "INNER JOIN titles AS t "
                            + "ON e.emp_no = t.emp_no AND t.to_date = '9999-01-01' "
                            + "INNER JOIN salaries s "
                            + "ON e.emp_no = s.emp_no AND s.to_date = '9999-01-01' "
                            + "INNER JOIN dept_emp de "
                            + "ON e.emp_no = de.emp_no AND de.to_date = '9999-01-01' "
                            + "INNER JOIN departments AS d "
                            + "ON de.dept_no = d.dept_no "
                            + "INNER JOIN dept_manager AS dm "
                            + "ON de.dept_no = dm.dept_no AND dm.to_date = '9999-01-01' "
                            + "INNER JOIN employees AS m "
                            + "ON m.emp_no = dm.emp_no "
                            + "WHERE e.emp_no = " + ID;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.title = rset.getString("title");
                emp.salary = rset.getInt("salary");
                emp.dept_name = rset.getString("dept_name");
                emp.manager = rset.getString("manager");
                return emp;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public void displayEmployee(Employee emp)
    {
        if (emp != null)
        {
            System.out.println(
                    emp.emp_no + " "
                            + emp.first_name + " "
                            + emp.last_name + "\n"
                            + emp.title + "\n"
                            + "Salary:" + emp.salary + "\n"
                            + emp.dept_name + "\n"
                            + "Manager: " + emp.manager + "\n");
        }
    }
}
