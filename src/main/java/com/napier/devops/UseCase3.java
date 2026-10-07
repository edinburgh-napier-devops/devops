package com.napier.devops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UseCase3 {
    private Connection con;

    public UseCase3(Connection con) {
        this.con = con;
    }

    /**
     * Find the department currently managed by an employee.
     *
     * @param managerId employee number of the department manager
     * @return department number, or null if the manager has no department
     */
    public String getManagerDepartment(int managerId)
    {
        String sql =
                "SELECT dept_no " +
                        "FROM dept_manager " +
                        "WHERE emp_no = ? " +
                        "AND to_date = '9999-01-01'";

        try (PreparedStatement stmt = con.prepareStatement(sql))
        {
            stmt.setInt(1, managerId);

            try (ResultSet rset = stmt.executeQuery())
            {
                if (rset.next())
                {
                    return rset.getString("dept_no");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get manager department");
        }

        return null;
    }

    /**
     * Get the current salaries of all employees in the manager's department.
     *
     * @param managerId employee number of the department manager
     * @return list of employees and their current salaries
     */
    public ArrayList<Employee> getDepartmentSalaries(int managerId)
    {
        String sql =
                "SELECT employees.emp_no, " +
                        "employees.first_name, " +
                        "employees.last_name, " +
                        "salaries.salary " +
                        "FROM employees " +
                        "JOIN dept_emp " +
                        "ON employees.emp_no = dept_emp.emp_no " +
                        "JOIN salaries " +
                        "ON employees.emp_no = salaries.emp_no " +
                        "JOIN dept_manager " +
                        "ON dept_emp.dept_no = dept_manager.dept_no " +
                        "WHERE dept_manager.emp_no = ? " +
                        "AND dept_manager.to_date = '9999-01-01' " +
                        "AND dept_emp.to_date = '9999-01-01' " +
                        "AND salaries.to_date = '9999-01-01' " +
                        "ORDER BY employees.emp_no ASC";

        ArrayList<Employee> employees = new ArrayList<Employee>();

        try (PreparedStatement stmt = con.prepareStatement(sql))
        {
            stmt.setInt(1, managerId);

            try (ResultSet rset = stmt.executeQuery())
            {
                while (rset.next())
                {
                    Employee emp = new Employee();

                    emp.emp_no = rset.getInt("employees.emp_no");
                    emp.first_name = rset.getString("employees.first_name");
                    emp.last_name = rset.getString("employees.last_name");
                    emp.salary = rset.getInt("salaries.salary");

                    employees.add(emp);
                }
            }

            return employees;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get department salary details");
            return null;
        }
    }

    /**
     * Print the department salary report.
     *
     * @param employees employees and their current salaries
     */
    public void printDepartmentSalaryReport(ArrayList<Employee> employees)
    {
        System.out.println(
                String.format(
                        "%-10s %-15s %-20s %-10s",
                        "Emp No",
                        "First Name",
                        "Last Name",
                        "Salary"));

        for (Employee emp : employees)
        {
            System.out.println(
                    String.format(
                            "%-10s %-15s %-20s %-10s",
                            emp.emp_no,
                            emp.first_name,
                            emp.last_name,
                            emp.salary));
        }
    }
}
