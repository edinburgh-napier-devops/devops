package com.napier.devops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UseCase4 {
    private static final String CURRENT_ROLE_SALARIES_SQL =
            "SELECT e.emp_no, e.first_name, e.last_name, t.title, s.salary "
                    + "FROM employees AS e "
                    + "INNER JOIN salaries AS s "
                    + "ON e.emp_no = s.emp_no AND s.to_date = '9999-01-01' "
                    + "INNER JOIN titles AS t "
                    + "ON e.emp_no = t.emp_no AND t.to_date = '9999-01-01' "
                    + "WHERE t.title = ? "
                    + "ORDER BY e.emp_no ASC";

    private static final String ROW_FORMAT = "%-10s %-15s %-20s %-25s %-8s";

    private final Connection con;

    public UseCase4(Connection con)
    {
        this.con = con;
    }

    /**
     * Gets the current salary of every employee holding the given role.
     *
     * @param roleName role (title) to include in the report
     * @return employees of the role, or null if the query fails
     */
    public ArrayList<Employee> getSalariesByRole(String roleName)
    {
        try (PreparedStatement stmt = con.prepareStatement(CURRENT_ROLE_SALARIES_SQL))
        {
            stmt.setString(1, roleName);

            try (ResultSet rset = stmt.executeQuery())
            {
                ArrayList<Employee> employees = new ArrayList<>();
                while (rset.next())
                {
                    employees.add(toEmployee(rset));
                }
                return employees;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details for role " + roleName);
            return null;
        }
    }

    /**
     * Prints a salary report for a role.
     *
     * @param employees employees returned by {@link #getSalariesByRole(String)}
     * @param roleName role name
     */
    public void printSalaries(ArrayList<Employee> employees, String roleName)
    {
        if (employees == null || employees.isEmpty())
        {
            System.out.println("No employees found for role " + roleName);
            return;
        }

        System.out.println("Salary report for role: " + roleName);
        System.out.println(String.format(ROW_FORMAT,
                "Emp No", "First Name", "Last Name", "Role", "Salary"));

        for (Employee emp : employees)
        {
            System.out.println(String.format(ROW_FORMAT,
                    emp.emp_no, emp.first_name, emp.last_name,
                    emp.title, emp.salary));
        }
    }

    // Convert data to Employee object
    private Employee toEmployee(ResultSet rset) throws SQLException
    {
        Employee emp = new Employee();
        emp.emp_no = rset.getInt("emp_no");
        emp.first_name = rset.getString("first_name");
        emp.last_name = rset.getString("last_name");
        emp.title = rset.getString("title");
        emp.salary = rset.getInt("salary");
        return emp;
    }
}
