package com.napier.devops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Updates the directly editable personal details of an employee.
 */
public class UseCase7
{
    private static final int MAX_FIRST_NAME_LENGTH = 14;
    private static final int MAX_LAST_NAME_LENGTH = 16;

    private final Connection con;

    public UseCase7(Connection con)
    {
        this.con = con;
    }

    /**
     * Possible outcomes from an employee update.
     */
    public enum UpdateResult
    {
        UPDATED,
        NOT_FOUND,
        INVALID_INPUT,
        DATABASE_ERROR
    }

    /**
     * Updates an employee's first and last names.
     *
     * @param employeeNumber employee number to update
     * @param firstName corrected first name
     * @param lastName corrected last name
     * @return the outcome of the update
     */
    public UpdateResult updateEmployeeDetails(
            int employeeNumber, String firstName, String lastName)
    {
        if (employeeNumber <= 0
                || !isValidName(firstName, MAX_FIRST_NAME_LENGTH)
                || !isValidName(lastName, MAX_LAST_NAME_LENGTH))
        {
            return UpdateResult.INVALID_INPUT;
        }

        String updateEmployee =
                "UPDATE employees "
                        + "SET first_name = ?, last_name = ? "
                        + "WHERE emp_no = ?";

        try (PreparedStatement stmt = con.prepareStatement(updateEmployee))
        {
            stmt.setString(1, firstName.trim());
            stmt.setString(2, lastName.trim());
            stmt.setInt(3, employeeNumber);

            int updatedRows = stmt.executeUpdate();
            return updatedRows == 1
                    ? UpdateResult.UPDATED
                    : UpdateResult.NOT_FOUND;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to update employee details");
            return UpdateResult.DATABASE_ERROR;
        }
    }

    private boolean isValidName(String name, int maximumLength)
    {
        if (name == null)
        {
            return false;
        }

        String trimmedName = name.trim();
        return !trimmedName.isEmpty() && trimmedName.length() <= maximumLength;
    }
}
