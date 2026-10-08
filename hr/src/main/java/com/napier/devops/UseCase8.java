package com.napier.devops;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Deletes employee records from the database.
 */
public class UseCase8
{
    private final Connection con;

    public UseCase8(Connection con)
    {
        this.con = con;
    }

    /**
     * Outcome of an attempt to delete an employee.
     */
    public enum DeleteResult
    {
        DELETED,
        NOT_FOUND,
        INVALID_INPUT,
        DATABASE_ERROR
    }

    /**
     * Deletes an employee and, by cascade, their salary, title and department
     * records.
     *
     * @param empNo employee number to delete
     * @return the outcome of the delete
     */
    public DeleteResult deleteEmployee(int empNo)
    {
        if (empNo <= 0)
        {
            return DeleteResult.INVALID_INPUT;
        }

        String strDelete = "DELETE FROM employees WHERE emp_no = ?";

        try (PreparedStatement stmt = con.prepareStatement(strDelete))
        {
            stmt.setInt(1, empNo);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1 ? DeleteResult.DELETED : DeleteResult.NOT_FOUND;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return DeleteResult.DATABASE_ERROR;
        }
    }
}