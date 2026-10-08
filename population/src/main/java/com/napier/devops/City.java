package com.napier.devops;

/**
 * A city. countryName is the country report column, filled from the country table.
 */
public class City
{
    private long id;
    private String name;
    private String countryCode;
    private String countryName;
    private String district;
    private long population;

    public City(long id, String name, String countryCode, String district, long population)
    {
        this(id, name, countryCode, null, district, population);
    }

    public City(long id, String name, String countryCode, String countryName, String district, long population)
    {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.district = district;
        this.population = population;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getCountryName()
    {
        return countryName;
    }

    public void setCountryName(String countryName)
    {
        this.countryName = countryName;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public long getPopulation()
    {
        return population;
    }

    public void setPopulation(long population)
    {
        this.population = population;
    }

    @Override
    public String toString()
    {
        return "City{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", countryCode='" + countryCode + '\''
                + ", countryName='" + countryName + '\''
                + ", district='" + district + '\''
                + ", population=" + population
                + '}';
    }
}