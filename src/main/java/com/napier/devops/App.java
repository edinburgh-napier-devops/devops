package com.napier.devops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class App
{

    private static final String ALL_SALARIES_SQL =
            "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary "
                    + "FROM employees, salaries "
                    + "WHERE employees.emp_no = salaries.emp_no AND salaries.to_date = '9999-01-01' "
                    + "ORDER BY employees.emp_no ASC";

    private static final String DEPARTMENT_SALARIES_SQL =
            "SELECT e.emp_no, e.first_name, e.last_name, s.salary "
                    + "FROM employees AS e "
                    + "INNER JOIN salaries AS s "
                    + "ON e.emp_no = s.emp_no AND s.to_date = '9999-01-01' "
                    + "INNER JOIN dept_emp AS de "
                    + "ON e.emp_no = de.emp_no AND de.to_date = '9999-01-01' "
                    + "INNER JOIN departments AS d "
                    + "ON de.dept_no = d.dept_no "
                    + "WHERE d.dept_name = ? "
                    + "ORDER BY e.emp_no ASC";

    private static final String ROLE_SALARIES_SQL =
            "SELECT e.emp_no, e.first_name, e.last_name, t.title, s.salary "
                    + "FROM employees AS e "
                    + "INNER JOIN salaries AS s "
                    + "ON e.emp_no = s.emp_no AND s.to_date = '9999-01-01' "
                    + "INNER JOIN titles AS t "
                    + "ON e.emp_no = t.emp_no AND t.to_date = '9999-01-01' "
                    + "WHERE t.title = ? "
                    + "ORDER BY e.emp_no ASC";

    private static final String EMPLOYEE_DETAILS_SQL =
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
                    + "WHERE e.emp_no = ?";

    private static final String UPDATE_EMPLOYEE_SQL =
            "UPDATE employees SET first_name = ?, last_name = ? WHERE emp_no = ?";

    private static final String SALARY_ROW = "%-10s %-15s %-20s %-8s";
    private static final String ROLE_ROW = "%-10s %-15s %-20s %-25s %-8s";

    private Connection con;
    private final DBConnection dbConnection = new DBConnection();

    public static void main(String[] args)
    {
        App app = new App();

        try
        {
            app.connect();
            if (app.con != null)
            {
                System.out.println("Successfully connected");
                app.run(args);
                app.disconnect();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Opens the database connection through DBConnection.
     */
    public void connect()
    {
        con = dbConnection.connect();
    }

    /**
     * Closes the connection held by this app.
     */
    public void disconnect()
    {
        if (con != null)
        {
            dbConnection.disconnect(con);
            con = null;
        }
    }

    private void run(String[] args)
    {

        /* Use Case 1 : Get Salaries */
        if (args.length > 0 && "--all-salaries".equals(args[0]))
        {
            printSalaries(getSalaries());
            return;
        }

        /* Use Case 2 : Get Salaries */

        if (args.length > 0 && "--update-employee".equals(args[0]))
        {
            updateEmployee(Integer.parseInt(args[1]), args[2], args[3]);
            return;
        }
        if (args.length > 0 && "--view-employee".equals(args[0]))
        {
            displayEmployee(getEmployeeDetails(Integer.parseInt(args[1])));
            return;
        }
        if (args.length > 0 && "--salary-by-department".equals(args[0]))
        {
            String departmentName = args.length > 1 ? args[1] : "Development";
            printSalariesByDepartment(getSalariesByDepartment(departmentName), departmentName);
            return;
        }
        if (args.length > 0 && "--all".equals(args[0]))
        {
            printSalaries(getSalaries());
            return;
        }

        String roleName = args.length > 0 ? args[0] : "Technique Leader";
        ArrayList<Employee> roleEmployees = getSalariesByRole(roleName);
        printSalariesByRole(roleEmployees, roleName);
        if (roleEmployees != null)
        {
            System.out.println(roleEmployees.size() + " employees found in " + roleName);
        }
    }

    /**
     * Gets the current salary of every employee.
     *
     * @return current employee salaries, or null if the query fails
     */
    public ArrayList<Employee> getSalaries()
    {
        try (Statement stmt = con.createStatement();
             ResultSet rset = stmt.executeQuery(ALL_SALARIES_SQL))
        {
            ArrayList<Employee> employees = new ArrayList<Employee>();
            while (rset.next())
            {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.salary = rset.getInt("salary");
                employees.add(emp);
            }
            return employees;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    /**
     * Prints a list of employee salaries.
     *
     * @param employees employees returned by {@link #getSalaries()}
     */
    public void printSalaries(ArrayList<Employee> employees)
    {
        if (employees == null)
        {
            System.out.println("No employees");
            return;
        }

        System.out.println(String.format(SALARY_ROW,
                "Emp No", "First Name", "Last Name", "Salary"));

        for (Employee emp : employees)
        {
            System.out.println(String.format(SALARY_ROW,
                    emp.emp_no, emp.first_name, emp.last_name, emp.salary));
        }
    }

    /**
     * Gets the current salary of every current employee in a department.
     *
     * @param departmentName department to include in the report
     * @return employees in the department, or null if the query fails
     */
    public ArrayList<Employee> getSalariesByDepartment(String departmentName)
    {
        try (PreparedStatement stmt = con.prepareStatement(DEPARTMENT_SALARIES_SQL))
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
    public void printSalariesByDepartment(ArrayList<Employee> employees, String departmentName)
    {
        if (employees == null || employees.isEmpty())
        {
            System.out.println("No employees found for department " + departmentName);
            return;
        }

        System.out.println("Salary report for department: " + departmentName);
        System.out.println(String.format(SALARY_ROW,
                "Emp No", "First Name", "Last Name", "Salary"));

        for (Employee emp : employees)
        {
            System.out.println(String.format(SALARY_ROW,
                    emp.emp_no, emp.first_name, emp.last_name, emp.salary));
        }
    }

    /**
     * Gets the current salary of every employee holding the given role.
     *
     * @param roleName role (title) to include in the report
     * @return employees of the role, or null if the query fails
     */
    public ArrayList<Employee> getSalariesByRole(String roleName)
    {
        try (PreparedStatement stmt = con.prepareStatement(ROLE_SALARIES_SQL))
        {
            stmt.setString(1, roleName);

            try (ResultSet rset = stmt.executeQuery())
            {
                ArrayList<Employee> employees = new ArrayList<Employee>();
                while (rset.next())
                {
                    Employee emp = new Employee();
                    emp.emp_no = rset.getInt("emp_no");
                    emp.first_name = rset.getString("first_name");
                    emp.last_name = rset.getString("last_name");
                    emp.title = rset.getString("title");
                    emp.salary = rset.getInt("salary");
                    employees.add(emp);
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
    public void printSalariesByRole(ArrayList<Employee> employees, String roleName)
    {
        if (employees == null || employees.isEmpty())
        {
            System.out.println("No employees found for role " + roleName);
            return;
        }

        System.out.println("Salary report for role: " + roleName);
        System.out.println(String.format(ROLE_ROW,
                "Emp No", "First Name", "Last Name", "Role", "Salary"));

        for (Employee emp : employees)
        {
            System.out.println(String.format(ROLE_ROW,
                    emp.emp_no, emp.first_name, emp.last_name, emp.title, emp.salary));
        }
    }

    /**
     * Retrieves one employee's current details.
     *
     * @param employeeNumber employee number to look up
     * @return the employee, or null if not found or the query fails
     */
    public Employee getEmployeeDetails(int employeeNumber)
    {
        try (PreparedStatement stmt = con.prepareStatement(EMPLOYEE_DETAILS_SQL))
        {
            stmt.setInt(1, employeeNumber);

            try (ResultSet rset = stmt.executeQuery())
            {
                if (!rset.next())
                {
                    return null;
                }

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
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    /**
     * Displays the supplied employee.
     *
     * @param emp employee returned by {@link #getEmployeeDetails(int)}
     */
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

    /**
     * Updates an employee's first and last names.
     *
     * @param employeeNumber employee number to update
     * @param firstName corrected first name
     * @param lastName corrected last name
     */
    public void updateEmployee(int employeeNumber, String firstName, String lastName)
    {
        try (PreparedStatement stmt = con.prepareStatement(UPDATE_EMPLOYEE_SQL))
        {
            stmt.setString(1, firstName.trim());
            stmt.setString(2, lastName.trim());
            stmt.setInt(3, employeeNumber);
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to update employee details");
        }
    }
}
