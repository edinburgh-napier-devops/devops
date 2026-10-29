package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the methods that check inputs and return early.
 * These don't need a database.
 */
public class ValidationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    // topCountriesInRegion - should return null if the region is missing or N is bad

    // no region given
    @Test
    void topCountriesInRegionNull()
    {
        assertNull(app.topCountriesInRegion(null, 5));
    }

    // empty region
    @Test
    void topCountriesInRegionEmpty()
    {
        assertNull(app.topCountriesInRegion("", 5));
    }

    // just spaces
    @Test
    void topCountriesInRegionBlank()
    {
        assertNull(app.topCountriesInRegion("   ", 5));
    }

    // N is 0
    @Test
    void topCountriesInRegionZeroN()
    {
        assertNull(app.topCountriesInRegion("Western Europe", 0));
    }

    // N is negative
    @Test
    void topCountriesInRegionNegativeN()
    {
        assertNull(app.topCountriesInRegion("Western Europe", -10));
    }

    // regionPopulation - should return 0 if the region is missing

    // no region
    @Test
    void regionPopulationNull()
    {
        assertEquals(0, app.regionPopulation(null));
    }

    // empty
    @Test
    void regionPopulationEmpty()
    {
        assertEquals(0, app.regionPopulation(""));
    }

    // spaces
    @Test
    void regionPopulationBlank()
    {
        assertEquals(0, app.regionPopulation("   "));
    }

    // countryPopulation - same idea, return 0 if country is missing

    // no country
    @Test
    void countryPopulationNull()
    {
        assertEquals(0, app.countryPopulation(null));
    }

    // empty
    @Test
    void countryPopulationEmpty()
    {
        assertEquals(0, app.countryPopulation(""));
    }

    // spaces
    @Test
    void countryPopulationBlank()
    {
        assertEquals(0, app.countryPopulation("   "));
    }
}
