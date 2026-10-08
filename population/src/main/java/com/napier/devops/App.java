package com.napier.devops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Population reports. Each report method has a comment where its SQL should go.
 */
public class App
{
    private Connection con;

    public App() {
    }

    public static void main(String[] args)
    {
        DBConnection dbConnection = new DBConnection();
        App app = new App();
        app.con = dbConnection.connect();

        if (app.con != null)
        {
            System.out.println("Successfully connected");
            try
            {
                // Change this block to test a report. Call the report method, then its print method.
                // Report 1: all countries in the world.
                ArrayList<Country> countries = app.allCountriesInWorld();
                app.printCountryReport(countries);

                // Report 2: countries in a continent. Change "Europe" to the continent to test.
                ArrayList<Country> continentCountries = app.countriesInContinent("Europe");
                app.printCountryReport(continentCountries);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            finally
            {
                dbConnection.disconnect(app.con);
            }
        }
    }

    public void printCountryReport(ArrayList<Country> countries) {
        if (countries == null)
        {
            System.out.println("No country report produced");
            return;
        }
        System.out.println(String.format("%-5s %-45s %-20s %-30s %-15s %s",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));
        for (Country country : countries)
        {
            System.out.println(String.format("%-5s %-45s %-20s %-30s %-15d %s",
                    country.getCode(),
                    country.getName(),
                    country.getContinent(),
                    country.getRegion(),
                    country.getPopulation(),
                    country.getCapital()));
        }
    }

    public void printCityReport(ArrayList<City> cities) {
        // Name, country, district, population.
    }

    public void printCapitalReport(ArrayList<City> capitals) {
        // Name, country, population.
    }

    public void printPopulationReport(ArrayList<Population> populations) {
        // Name, total population, population in cities and percentage, population not in cities and percentage.
    }

    // 1. All countries in the world, largest population to smallest.
    public ArrayList<Country> allCountriesInWorld()
    {
        ArrayList<Country> countries = new ArrayList<>();
        try
        {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, "
                            + "country.Population, city.Name AS Capital "
                            + "FROM country "
                            + "LEFT JOIN city ON country.Capital = city.ID "
                            + "ORDER BY country.Population DESC";
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next())
            {
                countries.add(new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getLong("Population"),
                        rset.getString("Capital")));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        return countries;
    }

    // 2. All countries in a continent largest population to smallest.
    public ArrayList<Country> countriesInContinent(String continent)
    {
        ArrayList<Country> countries = new ArrayList<>();
        try
        {
            String sql =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, "
                            + "country.Population, city.Name AS Capital "
                            + "FROM country "
                            + "LEFT JOIN city ON country.Capital = city.ID "
                            + "WHERE country.Continent = ? "
                            + "ORDER BY country.Population DESC";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, continent);
            ResultSet rset = stmt.executeQuery();
            while (rset.next())
            {
                countries.add(new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getLong("Population"),
                        rset.getString("Capital")));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        return countries;
    }

    // 3. All countries in a region, largest population to smallest.
    public ArrayList<Country> countriesInRegion(String region)
    {
        // SQL: same country columns, where region = region, order by population desc.
        return null;
    }

    // 4. Top N countries in the world.
    public ArrayList<Country> topCountriesInWorld(int n)
    {
        // SQL: same country columns, order by population desc limit n.
        return null;
    }

    // 5. Top N countries in a continent.
    public ArrayList<Country> topCountriesInContinent(String continent, int n)
    {
        // SQL: same country columns, where continent = continent, order by population desc limit n.
        return null;
    }

    // 6. Top N countries in a region.
    public ArrayList<Country> topCountriesInRegion(String region, int n)
    {
        // SQL: same country columns, where region = region, order by population desc limit n.
        return null;
    }

    // 7. All cities in the world, largest population to smallest.
    public ArrayList<City> allCitiesInWorld()
    {
        // SQL: select city name, country name, district, population order by population desc.
        return null;
    }

    // 8. All cities in a continent, largest population to smallest.
    public ArrayList<City> citiesInContinent(String continent)
    {
        // SQL: cities joined to country, where continent = continent, order by population desc.
        return null;
    }

    // 9. All cities in a region, largest population to smallest.
    public ArrayList<City> citiesInRegion(String region)
    {
        // SQL: cities joined to country, where region = region, order by population desc.
        return null;
    }

    // 10. All cities in a country, largest population to smallest.
    public ArrayList<City> citiesInCountry(String country)
    {
        // SQL: cities for the given country, order by population desc.
        return null;
    }

    // 11. All cities in a district, largest population to smallest.
    public ArrayList<City> citiesInDistrict(String district)
    {
        // SQL: cities where district = district, order by population desc.
        return null;
    }

    // 12. Top N cities in the world.
    public ArrayList<City> topCitiesInWorld(int n)
    {
        // SQL: cities order by population desc limit n.
        return null;
    }

    // 13. Top N cities in a continent.
    public ArrayList<City> topCitiesInContinent(String continent, int n)
    {
        // SQL: cities in continent, order by population desc limit n.
        return null;
    }

    // 14. Top N cities in a region.
    public ArrayList<City> topCitiesInRegion(String region, int n)
    {
        // SQL: cities in region, order by population desc limit n.
        return null;
    }

    // 15. Top N cities in a country.
    public ArrayList<City> topCitiesInCountry(String country, int n)
    {
        // SQL: cities in country, order by population desc limit n.
        return null;
    }

    // 16. Top N cities in a district.
    public ArrayList<City> topCitiesInDistrict(String district, int n)
    {
        // SQL: cities in district, order by population desc limit n.
        return null;
    }

    // 17. All capital cities in the world, largest population to smallest.
    public ArrayList<City> allCapitalsInWorld()
    {
        // SQL: cities whose id equals country.capital, order by population desc.
        return null;
    }

    // 18. All capital cities in a continent, largest population to smallest.
    public ArrayList<City> capitalsInContinent(String continent)
    {
        // SQL: capital cities where continent = continent, order by population desc.
        return null;
    }

    // 19. All capital cities in a region, largest population to smallest.
    public ArrayList<City> capitalsInRegion(String region)
    {
        // SQL: capital cities where region = region, order by population desc.
        return null;
    }

    // 20. Top N capital cities in the world.
    public ArrayList<City> topCapitalsInWorld(int n)
    {
        // SQL: capital cities order by population desc limit n.
        return null;
    }

    // 21. Top N capital cities in a continent.
    public ArrayList<City> topCapitalsInContinent(String continent, int n)
    {
        // SQL: capital cities in continent, order by population desc limit n.
        return null;
    }

    // 22. Top N capital cities in a region.
    public ArrayList<City> topCapitalsInRegion(String region, int n)
    {
        // SQL: capital cities in region, order by population desc limit n.
        return null;
    }

    // 23. Population in cities and not in cities, for each continent.
    public ArrayList<Population> populationByContinent()
    {
        // SQL: continent name, country population, sum of city population, non-city population and percentages.
        return null;
    }

    // 24. Population in cities and not in cities, for each region.
    public ArrayList<Population> populationByRegion()
    {
        // SQL: region name, country population, sum of city population, non-city population and percentages.
        return null;
    }

    // 25. Population in cities and not in cities, for each country.
    public ArrayList<Population> populationByCountry()
    {
        // SQL: country name, country population, sum of city population, non-city population and percentages.
        return null;
    }

    // 26. Population of the world.
    public long worldPopulation()
    {
        // SQL: sum of country.population.
        return 0;
    }

    // 27. Population of a continent.
    public long continentPopulation(String continent)
    {
        // SQL: sum of country.population where continent = continent.
        return 0;
    }

    // 28. Population of a region.
    public long regionPopulation(String region)
    {
        // SQL: sum of country.population where region = region.
        return 0;
    }

    // 29. Population of a country.
    public long countryPopulation(String country)
    {
        // SQL: country.population where name = country.
        return 0;
    }

    // 30. Population of a district.
    public long districtPopulation(String district)
    {
        // SQL: sum of city.population where district = district.
        return 0;
    }

    // 31. Population of a city.
    public long cityPopulation(String city)
    {
        // SQL: city.population where name = city.
        return 0;
    }

    // 32. Speakers of Chinese, English, Hindi, Spanish and Arabic, with world percentage.
    public ArrayList<Language> languageSpeakers()
    {
        // SQL: language, speakers, speakers / world population, for those five languages, order by speakers desc.
        return null;
    }
}
