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

class Report6Test
{
    private Connection connection;
    private App app;

    @BeforeEach
    void setUp() throws Exception
    {
        connection = DriverManager.getConnection("jdbc:h2:mem:report6;MODE=MySQL");
        try (Statement statement = connection.createStatement())
        {
            statement.execute("CREATE TABLE city (ID INT PRIMARY KEY, Name VARCHAR(35))");
            statement.execute("CREATE TABLE country (Code CHAR(3) PRIMARY KEY, Name VARCHAR(52), "
                    + "Continent VARCHAR(30), Region VARCHAR(30), Population BIGINT, Capital INT)");

            statement.execute("INSERT INTO city VALUES (1, 'New Delhi'), (2, 'Kabul'), (3, 'Paris')");
            statement.execute("INSERT INTO country VALUES "
                    + "('IND', 'India', 'Asia', 'Southern and Central Asia', 1013662000, 1), "
                    + "('AFG', 'Afghanistan', 'Asia', 'Southern and Central Asia', 22720000, 2), "
                    + "('FRA', 'France', 'Europe', 'Western Europe', 59225700, 3)");
        }
        app = new App(connection);
    }

    @AfterEach
    void tearDown() throws Exception
    {
        connection.close();
    }

    @Test
    void returnsCountriesFromRequestedRegion()
    {
        ArrayList<Country> countries = app.topCountriesInRegion("Southern and Central Asia", 10);

        assertNotNull(countries);
        assertEquals(2, countries.size());
        assertEquals("IND", countries.get(0).getCode());
        assertEquals("India", countries.get(0).getName());
        assertEquals("Asia", countries.get(0).getContinent());
        assertEquals("Southern and Central Asia", countries.get(0).getRegion());
        assertEquals(1013662000L, countries.get(0).getPopulation());
        assertEquals("New Delhi", countries.get(0).getCapital());
    }

    @Test
    void limitsResultsAfterOrderingByPopulation()
    {
        ArrayList<Country> countries = app.topCountriesInRegion("Southern and Central Asia", 1);

        assertNotNull(countries);
        assertEquals(1, countries.size());
        assertEquals("India", countries.get(0).getName());
        assertEquals(1013662000L, countries.get(0).getPopulation());
    }
}
