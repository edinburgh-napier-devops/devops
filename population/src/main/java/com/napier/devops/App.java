package com.napier.devops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
public class App {
    private Connection con;

    public App() {
    }

    public App(Connection con) {
        this.con = con;
    }

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        App app = new App();
        app.con = dbConnection.connect();

        if (app.con != null) {
            System.out.println("Successfully connected");
            try {
                // Change this block to test a report. Call the report method, then its print method.
                // Report 1: all countries in the world.
                System.out.println("Report 1: all countries in the world");
                List<Country> countries = app.allCountriesInWorld();
                app.printCountryReport(countries);

                // Report 2: countries in a continent. Change "Europe" to the continent to test.
                System.out.println("\n \n Report 2: countries in a continent");
                List<Country> continentCountries = app.countriesInContinent("Europe");
                app.printCountryReport(continentCountries);

                // Report 3: countries in a region.
                System.out.println("\n \n Report 3: countries in a region");
                List<Country> regionCountries = app.countriesInRegion("Southern and Central Asia");
                app.printCountryReport(regionCountries);

                // Report 4: top N countries in the world. Change 10 to the N to test.
                System.out.println("\n \n Report 4: top N countries in the world");
                List<Country> topCountries = app.topCountriesInWorld(10);
                app.printCountryReport(topCountries);

                // Report 5: top N countries in the continent.
                // Change "Asia" to the continent and 10 to the N to test.
                System.out.println("\n \n Report 5: top N countries in the continent.");
                List<Country> topCountriesInContinent = app.topCountriesInContinent("Asia", 10);
                app.printCountryReport(topCountriesInContinent);

                // Report 6: top N countries in a region.
                // Change "Southern and Central Asia" to the region and 10 to the N to test.
                System.out.println("\n \n Report 6: top N countries in a region.");
                List<Country> topCountriesInRegion =
                        app.topCountriesInRegion("Southern and Central Asia", 10);
                app.printCountryReport(topCountriesInRegion);

                // Report 7: all cities in the world organised by largest population to smallest.
                System.out.println("\n \n Report 7: All the cities in the world organised by largest population to smallest.");
                List<City> allCities = app.allCitiesInWorld();
                app.printCityReport(allCities);

                // Report 8: cities in a continent. Change "Europe" to the continent to test.
                System.out.println("\n \n Report 8: cities in a continent");
                List<City> continentCities = app.citiesInContinent("Europe");
                app.printCityReport(continentCities);

                // Report 9: cities in a region. Change the region name to test.
                System.out.println("\n \n Report 9: cities in a region");
                List<City> regionCities = app.citiesInRegion("British Islands");
                app.printCityReport(regionCities);

                // Report 10: cities in a country. Change the country name to test.
                System.out.println("\n \n Report 10: cities in a country");
                List<City> countryCities = app.citiesInCountry("United Kingdom");
                app.printCityReport(countryCities);

                // Report 11: cities in a district. Change the district name to test.
                System.out.println("\n \n Report 11: cities in a district");
                List<City> districtCities = app.citiesInDistrict("England");
                app.printCityReport(districtCities);

                //Report 12: Top N cities in the world.
                System.out.println("\n \nReport 12: top N cities in the world");
                List<City> topCities = app.topCitiesInWorld(10);
                app.printCityReport(topCities);

                // Report 13: top N cities in a continent. Change the continent and N to test.
                System.out.println("\n \n Report 13: top N cities in a continent");
                List<City> topContinentCities = app.topCitiesInContinent("Asia", 10);
                app.printCityReport(topContinentCities);

                // Report 14: top N cities in a region. Change the region and N to test.
                System.out.println("\n \n Report 14: top N cities in a region");
                List<City> topRegionCities = app.topCitiesInRegion("British Islands", 10);
                app.printCityReport(topRegionCities);

                // Report 15: top N cities in a country. Change the country and N to test.
                System.out.println("\n \n Report 15: top N cities in a country");
                List<City> topCountryCities = app.topCitiesInCountry("United Kingdom", 10);
                app.printCityReport(topCountryCities);

                // Report 16: top N cities in a district. Change the district and N to test.
                System.out.println("\n \n Report 16: top N cities in a district");
                List<City> topDistrictCities = app.topCitiesInDistrict("England", 10);
                app.printCityReport(topDistrictCities);

                // Report 17: all capital cities in the world.
                System.out.println("\n \n Report 17: all capital cities in the world");
                List<City> capitals = app.allCapitalsInWorld();
                app.printCapitalReport(capitals);

                // Report 18: capital cities in a continent. Change the continent to test.
                System.out.println("\n \n Report 18: capital cities in a continent");
                List<City> continentCapitals = app.capitalsInContinent("Europe");
                app.printCapitalReport(continentCapitals);

                // Report 19: capital cities in a region. Change the region to test.
                System.out.println("\n \n Report 19: capital cities in a region");
                List<City> regionCapitals = app.capitalsInRegion("British Islands");
                app.printCapitalReport(regionCapitals);

                // Report 20: top N capital cities in the world. Change 10 to the N to test.
                System.out.println("\n \n Report 20: top N capital cities in the world");
                List<City> topCapitals = app.topCapitalsInWorld(10);
                app.printCapitalReport(topCapitals);

                // Report 21: top N capital cities in a continent. Change the continent and N to test.
                System.out.println("\n \n Report 21: top N capital cities in a continent");
                List<City> topContinentCapitals = app.topCapitalsInContinent("Asia", 10);
                app.printCapitalReport(topContinentCapitals);

                // Report 22: top N capital cities in a region. Change the region and N to test.
                System.out.println("\n \n Report 22: top N capital cities in a region");
                List<City> topRegionCapitals = app.topCapitalsInRegion("British Islands", 10);
                app.printCapitalReport(topRegionCapitals);

                // Report 23: population in cities and not in cities, for each continent.
                System.out.println("\n \n Report 23: population by continent");
                List<Population> continentPopulations = app.populationByContinent();
                app.printPopulationReport(continentPopulations);

                // Report 24: population in cities and not in cities, for each region.
                System.out.println("\n \n Report 24: population by region");
                List<Population> regionPopulations = app.populationByRegion();
                app.printPopulationReport(regionPopulations);

                // Report 25: Population by country.
                System.out.println("\n\nReport 25: Population by country");
                List<Population> populations = app.populationByCountry();
                app.printPopulationReport(populations);

                // Report 26: Population by the world
                System.out.println("\n \n Report 26: Population of the world");
                long worldPopulation = app.worldPopulation();

                if (worldPopulation > 0) {
                    System.out.printf("World Population: %,d%n", worldPopulation);
                }

                // Report 27: total population of one continent.
                String reportContinent = "Europe";
                System.out.println("\n\nReport 27: population of a continent");
                System.out.println(String.format("%-20s %s", "Continent", "Population"));
                System.out.println(String.format("%-20s %d", reportContinent,
                        app.continentPopulation(reportContinent)));

                // Report 28: Population of a region.
                System.out.println("\n Report 28: Population of a region");

                long population =
                        app.regionPopulation("Western Europe");

                if (population > 0) {
                    System.out.println(
                            "Population of western Europe: " + population);
                }

                // Report 29: Population of a country.
                String reportCountry = "United Kingdom";
                System.out.println("\n Report 29: Population of a country");

                long countryPop = app.countryPopulation(reportCountry);

                if (countryPop > 0)
                {
                    System.out.printf("Population of %s: %,d%n",
                            reportCountry, countryPop);
                }


                // Report 30: Population of a district
                System.out.println("\n\nReport 30: Population of a district");
                String district = "England";
                long districtPopulation = app.districtPopulation(district);
                if (districtPopulation > 0) {
                    System.out.printf("Total population of %s: %,d%n", district, districtPopulation);
                } else {
                    System.out.printf("No population data found for district %s%n", district);
                }


                // Report 31: identify one city by name and location.
                String reportCity = "London";
                String reportCountryCode = "GBR";
                String reportDistrict = "England";
                System.out.println("\n\nReport 31: population of a city");
                Long reportCityPopulation = app.cityPopulation(
                        reportCity, reportCountryCode, reportDistrict);
                if (reportCityPopulation != null) {
                    System.out.println(String.format("%-20s %-12s %-20s %s",
                            "City", "Country Code", "District", "Population"));
                    System.out.println(String.format("%-20s %-12s %-20s %d",
                            reportCity, reportCountryCode, reportDistrict, reportCityPopulation));

                }

                // Report 32: speakers of Chinese, English, Hindi, Spanish and Arabic.
                System.out.println("\n\nReport 32: language speakers");
                List<Language> languages = app.languageSpeakers();
                app.printLanguageReport(languages);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                dbConnection.disconnect(app.con);
            }
        }
    }

    public void printCountryReport(List<Country> countries) {
        if (countries == null) {
            System.out.println("No country report produced");
            return;
        }
        System.out.println(String.format("%-5s %-45s %-20s %-30s %-15s %s",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));
        for (Country country : countries) {
            if (country == null) continue;
            System.out.println(String.format("%-5s %-45s %-20s %-30s %-15d %s",
                    country.getCode(),
                    country.getName(),
                    country.getContinent(),
                    country.getRegion(),
                    country.getPopulation(),
                    country.getCapital()));
        }
    }

    public void printCityReport(List<City> cities) {
        if (cities == null) {
            System.out.println("No city report produced");
            return;
        }
        System.out.println(String.format("%-40s %-40s %-25s %s",
                "Name", "Country", "District", "Population"));
        for (City city : cities) {
            if (city == null) continue;
            System.out.println(String.format("%-40s %-40s %-25s %d",
                    city.getName(),
                    city.getCountryName(),
                    city.getDistrict(),
                    city.getPopulation()));
        }
    }

    public void printCapitalReport(List<City> capitals) {
        if (capitals == null) {
            System.out.println("No capital city report produced");
            return;
        }
        System.out.println(String.format("%-40s %-40s %s",
                "Name", "Country", "Population"));
        for (City city : capitals) {
            if (city == null) continue;
            System.out.println(String.format("%-40s %-40s %d",
                    city.getName(),
                    city.getCountryName(),
                    city.getPopulation()));
        }
    }

    public void printPopulationReport(List<Population> populations) {
        if (populations == null) {
            System.out.println("No population report produced");
            return;
        }

        System.out.println(String.format(
                "%-35s %18s %18s %12s %18s %12s",
                "Name",
                "Total Population",
                "In Cities",
                "% In Cities",
                "Not In Cities",
                "% Not In Cities"
        ));

        for (Population population : populations) {
            if (population == null) continue;
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

    public void printLanguageReport(List<Language> languages) {
        if (languages == null) {
            System.out.println("No language report produced");
            return;
        }
        System.out.println(String.format("%-15s %18s %18s",
                "Language", "Speakers", "% of World"));
        for (Language language : languages) {
            if (language == null) continue;
            System.out.println(String.format("%-15s %18d %17.2f%%",
                    language.getLanguage(),
                    language.getSpeakers(),
                    language.getWorldPercentage()));
        }
    }

    // 1. All countries in the world, largest population to smallest.
    public List<Country> allCountriesInWorld() {
        List<Country> countries = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, "
                            + "country.Population, city.Name AS Capital "
                            + "FROM country "
                            + "LEFT JOIN city ON country.Capital = city.ID "
                            + "ORDER BY country.Population DESC";
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()) {
                countries.add(new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getLong("Population"),
                        rset.getString("Capital")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return countries;
    }

    // 2. All countries in a continent largest population to smallest.
    public List<Country> countriesInContinent(String continent) {
        List<Country> countries = new ArrayList<>();
        try {
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
            while (rset.next()) {
                countries.add(new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getLong("Population"),
                        rset.getString("Capital")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return countries;
    }

    // 3. All countries in a region, largest population to smallest.
    // SQL: same country columns, where region = region, order by population desc.
    // return null if error, else return ArrayList of countries.
    public List<Country> countriesInRegion(String region) {
        List<Country> countries = new ArrayList<>();
        try {
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
            while (rset.next()) {
                countries.add(new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getLong("Population"),
                        rset.getString("Capital")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return countries;
    }

    // 4. Top N countries in the world.
    public List<Country> topCountriesInWorld(int n) {
        List<Country> countries = new ArrayList<>();
        try {
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
            while (rset.next()) {
                countries.add(new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getLong("Population"),
                        rset.getString("Capital")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return countries;
    }

    // 5. Top N countries in a continent.
    public List<Country> topCountriesInContinent(String continent, int n) {
        List<Country> countries = new ArrayList<>();
        try {
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
            while (rset.next()) {
                countries.add(new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getLong("Population"),
                        rset.getString("Capital")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return countries;
    }

    // 6. Top N countries in a region.
    public List<Country> topCountriesInRegion(String region, int n) {
        if (region == null || region.trim().isEmpty()) {
            System.out.println("A region must be provided");
            return null;
        }
        if (n <= 0) {
            System.out.println("N must be a positive whole number");
            return null;
        }

        List<Country> countries = new ArrayList<>();
        try {
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
            while (rset.next()) {
                countries.add(new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getLong("Population"),
                        rset.getString("Capital")));
            }
            if (countries.isEmpty()) {
                System.out.println("No countries found for region: " + region);
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return countries;
    }

    // 7. All cities in the world, largest population to smallest.
    public List<City> allCitiesInWorld()
    {
        List<City> cities = new ArrayList<>();
        try
        {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT city.ID, city.Name, city.CountryCode, country.Name AS CountryName, "
                            + "city.District, city.Population "
                            + "FROM city "
                            + "JOIN country ON city.CountryCode = country.Code "
                            + "ORDER BY city.Population DESC";
            ResultSet rset = stmt.executeQuery(sql);
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

    // 8. All cities in a continent, largest population to smallest.
    public List<City> citiesInContinent(String continent) {
        List<City> cities = new ArrayList<>();
        try {
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
            while (rset.next()) {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 9. All cities in a region, largest population to smallest.
    public List<City> citiesInRegion(String region) {
        List<City> cities = new ArrayList<>();
        try {
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
            while (rset.next()) {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 10. All cities in a country, largest population to smallest.
    public List<City> citiesInCountry(String country) {
        List<City> cities = new ArrayList<>();
        try {
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
            while (rset.next()) {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 11. All cities in a district, largest population to smallest.
    public List<City> citiesInDistrict(String district) {
        List<City> cities = new ArrayList<>();
        try {
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
            while (rset.next()) {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 12. Top N cities in the world.
    public List<City> topCitiesInWorld(int n) {
        List<City> cities = new ArrayList<>();
        try {
            String sql =
                    "SELECT city.ID, city.Name, city.CountryCode, country.Name AS CountryName, "
                            + "city.District, city.Population "
                            + "FROM city "
                            + "JOIN country ON city.CountryCode = country.Code "
                            + "ORDER BY city.Population DESC "
                            + "LIMIT ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, n);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 13. Top N cities in a continent.
    public List<City> topCitiesInContinent(String continent, int n) {
        List<City> cities = new ArrayList<>();
        try {
            String sql =
                    "SELECT city.ID, city.Name, city.CountryCode, country.Name AS CountryName, "
                            + "city.District, city.Population "
                            + "FROM city "
                            + "JOIN country ON city.CountryCode = country.Code "
                            + "WHERE country.Continent = ? "
                            + "ORDER BY city.Population DESC "
                            + "LIMIT ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, continent);
            stmt.setInt(2, n);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 14. Top N cities in a region.
    public List<City> topCitiesInRegion(String region, int n) {
        List<City> cities = new ArrayList<>();
        try {
            String sql =
                    "SELECT city.ID, city.Name, city.CountryCode, country.Name AS CountryName, "
                            + "city.District, city.Population "
                            + "FROM city "
                            + "JOIN country ON city.CountryCode = country.Code "
                            + "WHERE country.Region = ? "
                            + "ORDER BY city.Population DESC "
                            + "LIMIT ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, region);
            stmt.setInt(2, n);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 15. Top N cities in a country.
    public List<City> topCitiesInCountry(String country, int n) {
        List<City> cities = new ArrayList<>();
        try {
            String sql =
                    "SELECT city.ID, city.Name, city.CountryCode, country.Name AS CountryName, "
                            + "city.District, city.Population "
                            + "FROM city "
                            + "JOIN country ON city.CountryCode = country.Code "
                            + "WHERE country.Name = ? "
                            + "ORDER BY city.Population DESC "
                            + "LIMIT ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, country);
            stmt.setInt(2, n);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 16. Top N cities in a district.
    public List<City> topCitiesInDistrict(String district, int n) {
        List<City> cities = new ArrayList<>();
        try {
            String sql =
                    "SELECT city.ID, city.Name, city.CountryCode, country.Name AS CountryName, "
                            + "city.District, city.Population "
                            + "FROM city "
                            + "JOIN country ON city.CountryCode = country.Code "
                            + "WHERE city.District = ? "
                            + "ORDER BY city.Population DESC "
                            + "LIMIT ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, district);
            stmt.setInt(2, n);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 17. All capital cities in the world, largest population to smallest.
    public List<City> allCapitalsInWorld() {
        List<City> cities = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT city.ID, city.Name, city.CountryCode, country.Name AS CountryName, "
                            + "city.District, city.Population "
                            + "FROM country "
                            + "JOIN city ON country.Capital = city.ID "
                            + "ORDER BY city.Population DESC";
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()) {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 18. All capital cities in a continent, largest population to smallest.
    public List<City> capitalsInContinent(String continent) {
        List<City> cities = new ArrayList<>();
        try {
            String sql =
                    "SELECT city.ID, city.Name, city.CountryCode, country.Name AS CountryName, "
                            + "city.District, city.Population "
                            + "FROM country "
                            + "JOIN city ON country.Capital = city.ID "
                            + "WHERE country.Continent = ? "
                            + "ORDER BY city.Population DESC";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, continent);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 19. All capital cities in a region, largest population to smallest.
    public List<City> capitalsInRegion(String region) {
        List<City> cities = new ArrayList<>();
        try {
            String sql =
                    "SELECT city.ID, city.Name, city.CountryCode, country.Name AS CountryName, "
                            + "city.District, city.Population "
                            + "FROM country "
                            + "JOIN city ON country.Capital = city.ID "
                            + "WHERE country.Region = ? "
                            + "ORDER BY city.Population DESC";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, region);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 20. Top N capital cities in the world.
    public List<City> topCapitalsInWorld(int n) {
        List<City> cities = new ArrayList<>();
        try {
            String sql =
                    "SELECT city.ID, city.Name, city.CountryCode, country.Name AS CountryName, "
                            + "city.District, city.Population "
                            + "FROM country "
                            + "JOIN city ON country.Capital = city.ID "
                            + "ORDER BY city.Population DESC "
                            + "LIMIT ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, n);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 21. Top N capital cities in a continent.
    public List<City> topCapitalsInContinent(String continent, int n) {
        List<City> cities = new ArrayList<>();
        try {
            String sql =
                    "SELECT city.ID, city.Name, city.CountryCode, country.Name AS CountryName, "
                            + "city.District, city.Population "
                            + "FROM country "
                            + "JOIN city ON country.Capital = city.ID "
                            + "WHERE country.Continent = ? "
                            + "ORDER BY city.Population DESC "
                            + "LIMIT ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, continent);
            stmt.setInt(2, n);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 22. Top N capital cities in a region.
    public List<City> topCapitalsInRegion(String region, int n) {
        List<City> cities = new ArrayList<>();
        try {
            String sql =
                    "SELECT city.ID, city.Name, city.CountryCode, country.Name AS CountryName, "
                            + "city.District, city.Population "
                            + "FROM country "
                            + "JOIN city ON country.Capital = city.ID "
                            + "WHERE country.Region = ? "
                            + "ORDER BY city.Population DESC "
                            + "LIMIT ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, region);
            stmt.setInt(2, n);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                cities.add(new City(
                        rset.getLong("ID"),
                        rset.getString("Name"),
                        rset.getString("CountryCode"),
                        rset.getString("CountryName"),
                        rset.getString("District"),
                        rset.getLong("Population")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cities;
    }

    // 23. Population in cities and not in cities, for each continent.
    public List<Population> populationByContinent() {
        List<Population> populations = new ArrayList<>();
        String sql =
                "SELECT country.Continent AS Name, "
                        + "SUM(country.Population) AS TotalPopulation, "
                        + "COALESCE(SUM(cityPop.CityPopulation), 0) AS CityPopulation, "
                        + "SUM(country.Population) - COALESCE(SUM(cityPop.CityPopulation), 0) AS NonCityPopulation, "
                        + "CASE WHEN SUM(country.Population) > 0 THEN "
                        + "100.0 * COALESCE(SUM(cityPop.CityPopulation), 0) / SUM(country.Population) "
                        + "ELSE 0 END AS CityPercentage, "
                        + "CASE WHEN SUM(country.Population) > 0 THEN "
                        + "100.0 * (SUM(country.Population) - COALESCE(SUM(cityPop.CityPopulation), 0)) "
                        + "/ SUM(country.Population) ELSE 0 END AS NonCityPercentage "
                        + "FROM country "
                        + "LEFT JOIN (SELECT CountryCode, SUM(Population) AS CityPopulation "
                        + "FROM city GROUP BY CountryCode) cityPop "
                        + "ON cityPop.CountryCode = country.Code "
                        + "GROUP BY country.Continent "
                        + "ORDER BY country.Continent";
        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rset = stmt.executeQuery()) {
            while (rset.next()) {
                populations.add(new Population(
                        rset.getString("Name"),
                        rset.getLong("TotalPopulation"),
                        rset.getLong("CityPopulation"),
                        rset.getDouble("CityPercentage"),
                        rset.getLong("NonCityPopulation"),
                        rset.getDouble("NonCityPercentage")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return populations;
    }

    // 24. Population in cities and not in cities, for each region.
    public List<Population> populationByRegion() {
        List<Population> populations = new ArrayList<>();
        String sql =
                "SELECT country.Region AS Name, "
                        + "SUM(country.Population) AS TotalPopulation, "
                        + "COALESCE(SUM(cityPop.CityPopulation), 0) AS CityPopulation, "
                        + "SUM(country.Population) - COALESCE(SUM(cityPop.CityPopulation), 0) AS NonCityPopulation, "
                        + "CASE WHEN SUM(country.Population) > 0 THEN "
                        + "100.0 * COALESCE(SUM(cityPop.CityPopulation), 0) / SUM(country.Population) "
                        + "ELSE 0 END AS CityPercentage, "
                        + "CASE WHEN SUM(country.Population) > 0 THEN "
                        + "100.0 * (SUM(country.Population) - COALESCE(SUM(cityPop.CityPopulation), 0)) "
                        + "/ SUM(country.Population) ELSE 0 END AS NonCityPercentage "
                        + "FROM country "
                        + "LEFT JOIN (SELECT CountryCode, SUM(Population) AS CityPopulation "
                        + "FROM city GROUP BY CountryCode) cityPop "
                        + "ON cityPop.CountryCode = country.Code "
                        + "GROUP BY country.Region "
                        + "ORDER BY country.Region";
        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rset = stmt.executeQuery()) {
            while (rset.next()) {
                populations.add(new Population(
                        rset.getString("Name"),
                        rset.getLong("TotalPopulation"),
                        rset.getLong("CityPopulation"),
                        rset.getDouble("CityPercentage"),
                        rset.getLong("NonCityPopulation"),
                        rset.getDouble("NonCityPercentage")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return populations;
    }

    // 25. Population in cities and not in cities, for each country.
    public List<Population> populationByCountry() {
        List<Population> populations = new ArrayList<>();

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
             ResultSet rset = stmt.executeQuery()) {
            while (rset.next()) {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        return populations;
    }

    // 26. Population of the world.
    public long worldPopulation() {
        String sql = "SELECT SUM(Population) AS WorldPopulation FROM country";

        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rset = stmt.executeQuery()) {
            if (rset.next()) {
                long population = rset.getLong("WorldPopulation");

                if (!rset.wasNull()) {
                    return population;
                }
            }

            System.out.println("No world population data is available");
            return 0;
        } catch (Exception e) {
            System.out.println("Unable to retrieve world population: " + e.getMessage());
            return 0;
        }
    }

    // 27. Population of a continent.
    public long continentPopulation(String continent) {
        String sql = "SELECT COALESCE(SUM(Population), 0) AS Population "
                + "FROM country WHERE Continent = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, continent);
            try (ResultSet rset = stmt.executeQuery()) {
                if (rset.next()) {
                    return rset.getLong("Population");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    // 28. Population of a region.
    public long regionPopulation(String region) {
        if (region == null || region.trim().isEmpty()) {
            System.out.println("A region must be provided");
            return 0;
        }

        String sql =
                "SELECT SUM(Population) AS TotalPopulation " +
                        "FROM country WHERE Region = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, region);

            try (ResultSet rset = stmt.executeQuery()) {
                if (rset.next()) {
                    long population = rset.getLong("TotalPopulation");

                    if (!rset.wasNull()) {
                        return population;
                    }
                }

                System.out.println("Region not found: " + region);
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Could not retrieve region population: "
                    + e.getMessage());
            return 0;
        }
    }

    // 29. Population of a country.
    public long countryPopulation(String country)
    {
        if (country == null || country.trim().isEmpty())
        {
            System.out.println("A country must be provided");
            return 0;
        }

        String sql =
                "SELECT Population FROM country WHERE Name = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql))
        {
            stmt.setString(1, country.trim());

            try (ResultSet rset = stmt.executeQuery())
            {
                if (rset.next())
                {
                    long population = rset.getLong("Population");

                    if (!rset.wasNull())
                    {
                        return population;
                    }
                }

                System.out.println("Country not found: " + country);
                return 0;
            }
        }
        catch (Exception e)
        {
            System.out.println("Could not retrieve country population: "
                    + e.getMessage());
            return 0;
        }
    }


    // 30. Population of a district.
    public long districtPopulation(String district) {
        long population = 0;

        try {
            String sql =
                    "SELECT SUM(Population) AS TotalPopulation " +
                            "FROM city " +
                            "WHERE District = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, district);
            ResultSet rset = stmt.executeQuery();
            if (rset.next()) {
                population = rset.getLong("TotalPopulation");
            }

            rset.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return population;
    }


    // 31. Population of a city.
    public Long cityPopulation(String city) {
        return cityPopulation(city, null, null);
    }

    // A name may occur in several countries or districts. Only return a
    // population when the supplied details identify exactly one city.
    public Long cityPopulation(String city, String countryCode, String district) {
        StringBuilder sql = new StringBuilder(
                "SELECT ID, Name, CountryCode, District, Population FROM city WHERE Name = ?");
        if (countryCode != null) {
            sql.append(" AND CountryCode = ?");
        }
        if (district != null) {
            sql.append(" AND District = ?");
        }

        List<City> matches = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(sql.toString())) {
            int parameter = 1;
            stmt.setString(parameter++, city);
            if (countryCode != null) {
                stmt.setString(parameter++, countryCode);
            }
            if (district != null) {
                stmt.setString(parameter, district);
            }

            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    matches.add(new City(
                            rset.getLong("ID"),
                            rset.getString("Name"),
                            rset.getString("CountryCode"),
                            rset.getString("District"),
                            rset.getLong("Population")));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        if (matches.isEmpty()) {
            System.out.println("No city found for: " + city);
            return null;
        }
        if (matches.size() > 1) {
            System.out.println("Ambiguous city name: " + city
                    + ". Specify country and district, or choose a city ID:");
            for (City match : matches) {
                System.out.println(String.format("%d: %s, %s, %s",
                        match.getId(), match.getName(), match.getCountryCode(),
                        match.getDistrict()));
            }
            return null;
        }
        return matches.get(0).getPopulation();
    }

    // The city table's primary key resolves any remaining name ambiguity.
    public Long cityPopulation(long cityId) {
        String sql = "SELECT Population FROM city WHERE ID = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, cityId);
            try (ResultSet rset = stmt.executeQuery()) {
                if (rset.next()) {
                    return rset.getLong("Population");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        System.out.println("No city found for ID: " + cityId);
        return null;
    }

    // 32. Speakers of Chinese, English, Hindi, Spanish and Arabic, with world percentage.
    public List<Language> languageSpeakers() {
        List<Language> languages = new ArrayList<>();
        String sql =
                "SELECT countrylanguage.Language, "
                        + "ROUND(SUM(country.Population * countrylanguage.Percentage / 100)) AS Speakers, "
                        + "SUM(country.Population * countrylanguage.Percentage / 100) "
                        + "/ (SELECT SUM(Population) FROM country) * 100 AS WorldPercentage "
                        + "FROM countrylanguage "
                        + "JOIN country ON countrylanguage.CountryCode = country.Code "
                        + "WHERE countrylanguage.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') "
                        + "GROUP BY countrylanguage.Language "
                        + "ORDER BY Speakers DESC";
        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rset = stmt.executeQuery()) {
            while (rset.next()) {
                languages.add(new Language(
                        rset.getString("Language"),
                        rset.getLong("Speakers"),
                        rset.getDouble("WorldPercentage")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return languages;
    }
}
