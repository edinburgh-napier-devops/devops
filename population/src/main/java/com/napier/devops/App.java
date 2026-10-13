package com.napier.devops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Population reports. Each report method has a comment where its SQL should go.
 * 1. Assign a report from the current Zube sprint to yourself first.
 * 2. Work on the report, test and commit as a new feature.
 * 3. Promote feature up through dev then release ensuring build is successful in GitHub.
 * 4. Create a new release number.
 * 5. Merge into master.
 * 6. Close GitHub issue.
 * 7. Move complete ticket in Zube to done.
 */
public class App
{
    private Connection con;

    public App() {
    }

    public App(Connection con)
    {
        this.con = con;
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
                System.out.println("Report 1: all countries in the world");
                ArrayList<Country> countries = app.allCountriesInWorld();
                app.printCountryReport(countries);

                // Report 2: countries in a continent. Change "Europe" to the continent to test.
                System.out.println("\n \n Report 2: countries in a continent");
                ArrayList<Country> continentCountries = app.countriesInContinent("Europe");
                app.printCountryReport(continentCountries);

                // Report 3: countries in a region.
                System.out.println("\n \n Report 3: countries in a region");
                ArrayList<Country> regionCountries = app.countriesInRegion("Southern and Central Asia");
                app.printCountryReport(regionCountries);

                // Report 4: top N countries in the world. Change 10 to the N to test.
                System.out.println("\n \n Report 4: top N countries in the world");
                ArrayList<Country> topCountries = app.topCountriesInWorld(10);
                app.printCountryReport(topCountries);

                // Report 5: top N countries in the continent.
                // Change "Asia" to the continent and 10 to the N to test.
                System.out.println("\n \n Report 5: top N countries in the continent.");
                ArrayList<Country> topCountriesInContinent = app.topCountriesInContinent("Asia",10);
                app.printCountryReport(topCountriesInContinent);

                // Report 6: top N countries in a region.
                // Change "Southern and Central Asia" to the region and 10 to the N to test.
                System.out.println("\n \n Report 6: top N countries in a region.");
                ArrayList<Country> topCountriesInRegion =
                        app.topCountriesInRegion("Southern and Central Asia", 10);
                app.printCountryReport(topCountriesInRegion);
              
                // Report 8: cities in a continent. Change "Europe" to the continent to test.
                System.out.println("\n \n Report 8: cities in a continent");
                ArrayList<City> continentCities = app.citiesInContinent("Europe");
                app.printCityReport(continentCities);

                // Report 9: cities in a region. Change the region name to test.
                System.out.println("\n \n Report 9: cities in a region");
                ArrayList<City> regionCities = app.citiesInRegion("British Islands");
                app.printCityReport(regionCities);

                // Report 10: cities in a country. Change the country name to test.
                System.out.println("\n \n Report 10: cities in a country");
                ArrayList<City> countryCities = app.citiesInCountry("United Kingdom");
                app.printCityReport(countryCities);

                // Report 11: cities in a district. Change the district name to test.
                System.out.println("\n \n Report 11: cities in a district");
                ArrayList<City> districtCities = app.citiesInDistrict("England");
                app.printCityReport(districtCities);
              
                // Report 25: Population by country.
                System.out.println("\n\nReport 25: Population by country");
                ArrayList<Population> populations = app.populationByCountry();
                app.printPopulationReport(populations);
              
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
        if (cities == null)
        {
            System.out.println("No city report produced");
            return;
        }
        System.out.println(String.format("%-40s %-40s %-25s %s",
                "Name", "Country", "District", "Population"));
        for (City city : cities)
        {
            System.out.println(String.format("%-40s %-40s %-25s %d",
                    city.getName(),
                    city.getCountryName(),
                    city.getDistrict(),
                    city.getPopulation()));
        }
    }

    public void printCapitalReport(ArrayList<City> capitals) {
        // Name, country, population.
    }

    public void printPopulationReport(ArrayList<Population> populations) {
        if (populations == null)
        {
            System.out.println("No population report produced");
            return;
        }

        System.out.println(String.format(
                "%-35s %18s %18s %12s %18s %12s",
                "Country",
                "Total Population",
                "In Cities",
                "% In Cities",
                "Not In Cities",
                "% Not In Cities"
        ));

        for (Population population : populations)
        {
            System.out.println(String.format(
                    "%-35s %18d %18d %11.2f%% %18d %11.2f%%",
                    population.getName(),
                    population.getTotalPopulation(),
                    population.getPopulationInCities(),
                    population.getPercentageInCities(),
                    population.getPopulationNotInCities(),
                    population.getPercentageNotInCities()
            ));
        }
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
    // SQL: same country columns, where region = region, order by population desc.
    // return null if error, else return ArrayList of countries.
    public ArrayList<Country> countriesInRegion(String region)
    {
        ArrayList<Country> countries = new ArrayList<>();
        try
        {
            // retrive data from Region, population in descending order
            String sql =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, "
                            + "country.Population, city.Name AS Capital "
                            + "FROM country "
                            + "LEFT JOIN city ON country.Capital = city.ID "
                            + "WHERE country.Region = ? "
                            + "ORDER BY country.Population DESC";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, region);
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

    // 4. Top N countries in the world.
    public ArrayList<Country> topCountriesInWorld(int n)
    {
        ArrayList<Country> countries = new ArrayList<>();
        try
        {
            String sql =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, "
                            + "country.Population, city.Name AS Capital "
                            + "FROM country "
                            + "LEFT JOIN city ON country.Capital = city.ID "
                            + "ORDER BY country.Population DESC "
                            + "LIMIT ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, n);
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

    // 5. Top N countries in a continent.
    public ArrayList<Country> topCountriesInContinent(String continent, int n)
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
                            + "ORDER BY country.Population DESC "
                            + "LIMIT ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, continent);
            stmt.setInt(2, n);
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

    // 6. Top N countries in a region.
    public ArrayList<Country> topCountriesInRegion(String region, int n)
    {
        if (region == null || region.trim().isEmpty())
        {
            System.out.println("A region must be provided");
            return null;
        }
        if (n <= 0)
        {
            System.out.println("N must be a positive whole number");
            return null;
        }

        ArrayList<Country> countries = new ArrayList<>();
        try
        {
            String sql =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, "
                            + "country.Population, city.Name AS Capital "
                            + "FROM country "
                            + "LEFT JOIN city ON country.Capital = city.ID "
                            + "WHERE country.Region = ? "
                            + "ORDER BY country.Population DESC "
                            + "LIMIT ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, region);
            stmt.setInt(2, n);
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
            if (countries.isEmpty())
            {
                System.out.println("No countries found for region: " + region);
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        return countries;
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
        ArrayList<City> cities = new ArrayList<>();
        try
        {
            String sql =
                    "SELECT city.ID, city.Name, city.CountryCode, country.Name AS CountryName, "
                            + "city.District, city.Population "
                            + "FROM city "
                            + "JOIN country ON city.CountryCode = country.Code "
                            + "WHERE country.Continent = ? "
                            + "ORDER BY city.Population DESC";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, continent);
            ResultSet rset = stmt.executeQuery();
            while (rset.next())
            {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 9. All cities in a region, largest population to smallest.
    public ArrayList<City> citiesInRegion(String region)
    {
        ArrayList<City> cities = new ArrayList<>();
        try
        {
            String sql =
                    "SELECT city.ID, city.Name, city.CountryCode, country.Name AS CountryName, "
                            + "city.District, city.Population "
                            + "FROM city "
                            + "JOIN country ON city.CountryCode = country.Code "
                            + "WHERE country.Region = ? "
                            + "ORDER BY city.Population DESC";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, region);
            ResultSet rset = stmt.executeQuery();
            while (rset.next())
            {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 10. All cities in a country, largest population to smallest.
    public ArrayList<City> citiesInCountry(String country)
    {
        ArrayList<City> cities = new ArrayList<>();
        try
        {
            String sql =
                    "SELECT city.ID, city.Name, city.CountryCode, country.Name AS CountryName, "
                            + "city.District, city.Population "
                            + "FROM city "
                            + "JOIN country ON city.CountryCode = country.Code "
                            + "WHERE country.Name = ? "
                            + "ORDER BY city.Population DESC";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, country);
            ResultSet rset = stmt.executeQuery();
            while (rset.next())
            {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 11. All cities in a district, largest population to smallest.
    public ArrayList<City> citiesInDistrict(String district)
    {
        ArrayList<City> cities = new ArrayList<>();
        try
        {
            String sql =
                    "SELECT city.ID, city.Name, city.CountryCode, country.Name AS CountryName, "
                            + "city.District, city.Population "
                            + "FROM city "
                            + "JOIN country ON city.CountryCode = country.Code "
                            + "WHERE city.District = ? "
                            + "ORDER BY city.Population DESC";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, district);
            ResultSet rset = stmt.executeQuery();
            while (rset.next())
            {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
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
        ArrayList<Population> populations = new ArrayList<>();

        String sql =
                "SELECT country.Name AS CountryName, " +
                        "country.Population AS TotalPopulation, " +
                        "COALESCE(SUM(city.Population), 0) AS CityPopulation, " +
                        "country.Population - COALESCE(SUM(city.Population), 0) " +
                        "    AS NonCityPopulation, " +
                        "CASE WHEN country.Population > 0 THEN " +
                        "    100.0 * COALESCE(SUM(city.Population), 0) " +
                        "    / country.Population " +
                        "    ELSE 0 END AS CityPercentage, " +
                        "CASE WHEN country.Population > 0 THEN " +
                        "    100.0 * (country.Population " +
                        "    - COALESCE(SUM(city.Population), 0)) " +
                        "    / country.Population " +
                        "    ELSE 0 END AS NonCityPercentage " +
                        "FROM country " +
                        "LEFT JOIN city ON city.CountryCode = country.Code " +
                        "GROUP BY country.Code, country.Name, country.Population " +
                        "ORDER BY country.Name";

        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rset = stmt.executeQuery())
        {
            while (rset.next())
            {
                Population population = new Population(
                        rset.getString("CountryName"),
                        rset.getLong("TotalPopulation"),
                        rset.getLong("CityPopulation"),
                        rset.getDouble("CityPercentage"),
                        rset.getLong("NonCityPopulation"),
                        rset.getDouble("NonCityPercentage")
                );

                populations.add(population);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }

        return populations;
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
