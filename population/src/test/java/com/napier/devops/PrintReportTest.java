package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Tests for the print methods. Lab 06 style: null, empty, null inside the list, and a normal list.
 */
public class PrintReportTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    // country report

    // should not throw if the list is null
    @Test
    void printCountryReportNull()
    {
        app.printCountryReport(null);
    }

    // empty list should just print the header
    @Test
    void printCountryReportEmpty()
    {
        app.printCountryReport(new ArrayList<>());
    }

    // list with a null country in it should not throw
    @Test
    void printCountryReportContainsNull()
    {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null);
        app.printCountryReport(countries);
    }

    // one normal country
    @Test
    void printCountryReportNormal()
    {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("GBR", "United Kingdom", "Europe",
                "British Islands", 59623400L, "London"));
        app.printCountryReport(countries);
    }

    // more than one country
    @Test
    void printCountryReportMultiple()
    {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("CHN", "China", "Asia", "Eastern Asia", 1277558000L, "Peking"));
        countries.add(new Country("IND", "India", "Asia", "Southern and Central Asia", 1013662000L, "New Delhi"));
        countries.add(new Country("USA", "United States", "North America", "North America", 278357000L, "Washington"));
        app.printCountryReport(countries);
    }

    // mix of real countries and nulls
    @Test
    void printCountryReportMixedNull()
    {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("GBR", "United Kingdom", "Europe", "British Islands", 59623400L, "London"));
        countries.add(null);
        countries.add(new Country("FRA", "France", "Europe", "Western Europe", 59225700L, "Paris"));
        app.printCountryReport(countries);
    }

    // country with 0 population and no capital
    @Test
    void printCountryReportZeroPopulation()
    {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("ATA", "Antarctica", "Antarctica", "Antarctica", 0L, null));
        app.printCountryReport(countries);
    }

    // city report

    // null list
    @Test
    void printCityReportNull()
    {
        app.printCityReport(null);
    }

    // empty list
    @Test
    void printCityReportEmpty()
    {
        app.printCityReport(new ArrayList<>());
    }

    // null city in the list
    @Test
    void printCityReportContainsNull()
    {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(null);
        app.printCityReport(cities);
    }

    // one city
    @Test
    void printCityReportNormal()
    {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City(1L, "London", "GBR", "United Kingdom", "England", 7285000L));
        app.printCityReport(cities);
    }

    // a few cities
    @Test
    void printCityReportMultiple()
    {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City(1L, "Mumbai (Bombay)", "IND", "India", "Maharashtra", 10500000L));
        cities.add(new City(2L, "Seoul", "KOR", "South Korea", "Seoul", 9981619L));
        cities.add(new City(3L, "Sao Paulo", "BRA", "Brazil", "Sao Paulo", 9968485L));
        app.printCityReport(cities);
    }

    // nulls mixed in with real cities
    @Test
    void printCityReportMixedNull()
    {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(null);
        cities.add(new City(1L, "London", "GBR", "United Kingdom", "England", 7285000L));
        cities.add(null);
        app.printCityReport(cities);
    }

    // capital city report

    // null list
    @Test
    void printCapitalReportNull()
    {
        app.printCapitalReport(null);
    }

    // empty list
    @Test
    void printCapitalReportEmpty()
    {
        app.printCapitalReport(new ArrayList<>());
    }

    // null capital in the list
    @Test
    void printCapitalReportContainsNull()
    {
        ArrayList<City> capitals = new ArrayList<>();
        capitals.add(null);
        app.printCapitalReport(capitals);
    }

    // one capital
    @Test
    void printCapitalReportNormal()
    {
        ArrayList<City> capitals = new ArrayList<>();
        capitals.add(new City(1L, "London", "GBR", "United Kingdom", "England", 7285000L));
        app.printCapitalReport(capitals);
    }

    // a couple of capitals
    @Test
    void printCapitalReportMultiple()
    {
        ArrayList<City> capitals = new ArrayList<>();
        capitals.add(new City(1L, "Seoul", "KOR", "South Korea", "Seoul", 9981619L));
        capitals.add(new City(2L, "Jakarta", "IDN", "Indonesia", "Jakarta Raya", 9604900L));
        app.printCapitalReport(capitals);
    }

    // null mixed in
    @Test
    void printCapitalReportMixedNull()
    {
        ArrayList<City> capitals = new ArrayList<>();
        capitals.add(new City(1L, "London", "GBR", "United Kingdom", "England", 7285000L));
        capitals.add(null);
        app.printCapitalReport(capitals);
    }

    // population report

    // null list
    @Test
    void printPopulationReportNull()
    {
        app.printPopulationReport(null);
    }

    // empty list
    @Test
    void printPopulationReportEmpty()
    {
        app.printPopulationReport(new ArrayList<>());
    }

    // null entry in the list
    @Test
    void printPopulationReportContainsNull()
    {
        ArrayList<Population> populations = new ArrayList<>();
        populations.add(null);
        app.printPopulationReport(populations);
    }

    // one continent
    @Test
    void printPopulationReportNormal()
    {
        ArrayList<Population> populations = new ArrayList<>();
        populations.add(new Population("Europe", 730074600L, 200000000L, 27.4,
                530074600L, 72.6));
        app.printPopulationReport(populations);
    }

    // a few continents
    @Test
    void printPopulationReportMultiple()
    {
        ArrayList<Population> populations = new ArrayList<>();
        populations.add(new Population("Asia", 3705025700L, 1500000000L, 40.5, 2205025700L, 59.5));
        populations.add(new Population("Europe", 730074600L, 200000000L, 27.4, 530074600L, 72.6));
        populations.add(new Population("Africa", 784475000L, 100000000L, 12.7, 684475000L, 87.3));
        app.printPopulationReport(populations);
    }

    // nulls mixed in
    @Test
    void printPopulationReportMixedNull()
    {
        ArrayList<Population> populations = new ArrayList<>();
        populations.add(null);
        populations.add(new Population("Europe", 730074600L, 200000000L, 27.4, 530074600L, 72.6));
        populations.add(null);
        app.printPopulationReport(populations);
    }

    // zero population
    @Test
    void printPopulationReportZero()
    {
        ArrayList<Population> populations = new ArrayList<>();
        populations.add(new Population("Antarctica", 0L, 0L, 0.0, 0L, 0.0));
        app.printPopulationReport(populations);
    }

    // language report

    // null list
    @Test
    void printLanguageReportNull()
    {
        app.printLanguageReport(null);
    }

    // empty list
    @Test
    void printLanguageReportEmpty()
    {
        app.printLanguageReport(new ArrayList<>());
    }

    // null language in the list
    @Test
    void printLanguageReportContainsNull()
    {
        ArrayList<Language> languages = new ArrayList<>();
        languages.add(null);
        app.printLanguageReport(languages);
    }

    // one language
    @Test
    void printLanguageReportNormal()
    {
        ArrayList<Language> languages = new ArrayList<>();
        languages.add(new Language("English", 347077867L, 5.71));
        app.printLanguageReport(languages);
    }

    // all five languages from the spec
    @Test
    void printLanguageReportMultiple()
    {
        ArrayList<Language> languages = new ArrayList<>();
        languages.add(new Language("Chinese", 1191843539L, 19.61));
        languages.add(new Language("Hindi", 405633070L, 6.67));
        languages.add(new Language("Spanish", 355029462L, 5.84));
        languages.add(new Language("English", 347077867L, 5.71));
        languages.add(new Language("Arabic", 233839239L, 3.85));
        app.printLanguageReport(languages);
    }

    // null mixed in
    @Test
    void printLanguageReportMixedNull()
    {
        ArrayList<Language> languages = new ArrayList<>();
        languages.add(new Language("English", 347077867L, 5.71));
        languages.add(null);
        languages.add(new Language("Spanish", 355029462L, 5.84));
        app.printLanguageReport(languages);
    }
}
