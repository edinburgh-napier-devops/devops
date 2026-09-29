package com.napier.devops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Produces salary reports for employees in a selected department.
 */
public class UseCase2
{
    private final Connection con;

    public UseCase2(Connection con)
    {
        this.con = con;
    }

    /**
     * Gets the current salary of every current employee in a department.
     *
     * @param departmentName department to include in the report
     * @return employees in the department, or {@code null} if the query fails
     */
    public ArrayList<Employee> getSalariesByDepartment(String departmentName)
    {
        String strSelect =
                "SELECT e.emp_no, e.first_name, e.last_name, s.salary, d.dept_name "
                        + "FROM employees AS e "
                        + "INNER JOIN salaries AS s "
                        + "ON e.emp_no = s.emp_no AND s.to_date = '9999-01-01' "
                        + "INNER JOIN dept_emp AS de "
                        + "ON e.emp_no = de.emp_no AND de.to_date = '9999-01-01' "
                        + "INNER JOIN departments AS d "
                        + "ON de.dept_no = d.dept_no "
                        + "WHERE d.dept_name = ? "
                        + "ORDER BY e.emp_no ASC";

        try (PreparedStatement stmt = con.prepareStatement(strSelect))
        {
            stmt.setString(1, departmentName);

            try (ResultSet rset = stmt.executeQuery())
            {
                ArrayList<Employee> employees = new ArrayList<Employee>();
                while (rset.next())
                {
                    Employee emp = new Employee();
                    emp.emp_no = rset.getInt("emp_no");
                    emp.first_name = rset.getString("first_name");
                    emp.last_name = rset.getString("last_name");
                    emp.salary = rset.getInt("salary");
                    emp.dept_name = rset.getString("dept_name");
                    employees.add(emp);
                }
                return employees;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details for department " + departmentName);
            return null;
        }
    }

    /**
     * Prints a salary report for a department.
     *
     * @param employees employees returned by {@link #getSalariesByDepartment(String)}
     * @param departmentName department included in the report
     */
    public void printSalaries(ArrayList<Employee> employees, String departmentName)
    {
        if (employees == null || employees.isEmpty())
        {
            System.out.println("No employees found for department " + departmentName);
            return;
        }

        System.out.println("Salary report for department: " + departmentName);
        System.out.println(String.format("%-10s %-15s %-20s %-25s %-8s",
                "Emp No", "First Name", "Last Name", "Department", "Salary"));

        for (Employee emp : employees)
        {
            String employeeRow =
                    String.format("%-10s %-15s %-20s %-25s %-8s",
                            emp.emp_no, emp.first_name, emp.last_name,
                            emp.dept_name, emp.salary);
            System.out.println(employeeRow);
        }
    }
}
