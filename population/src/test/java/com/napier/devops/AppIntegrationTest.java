package com.napier.devops;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests. Needs the world database running on localhost:33061.
 */
public class AppIntegrationTest
{
    static App app;
    static DBConnection db;
    static Connection con;

    @BeforeAll
    static void init()
    {
        db = new DBConnection();
        con = db.connect("localhost:33061", 5000);
        assertNotNull(con, "Could not connect to world database");
        app = new App(con);
    }

    @AfterAll
    static void cleanup()
    {
        db.disconnect(con);
    }

    // world population from the world database
    @Test
    void testWorldPopulation()
    {
        assertEquals(6078749450L, app.worldPopulation());
    }

    // United Kingdom population
    @Test
    void testCountryPopulation()
    {
        assertEquals(59623400L, app.countryPopulation("United Kingdom"));
    }

    // Europe continent population
    @Test
    void testContinentPopulation()
    {
        assertTrue(app.continentPopulation("Europe") > 0);
    }

    // top countries returns something
    @Test
    void testTopCountriesInWorld()
    {
        assertNotNull(app.topCountriesInWorld(5));
        assertEquals(5, app.topCountriesInWorld(5).size());
    }

    // language speakers returns the five languages
    @Test
    void testLanguageSpeakers()
    {
        assertNotNull(app.languageSpeakers());
        assertEquals(5, app.languageSpeakers().size());
    }
}
