package com.napier.devops;

/**
 * A population breakdown report row.
 * Columns: name, total population, population in cities and percentage,
 * population not in cities and percentage.
 */
public class Population
{
    private String name;
    private long totalPopulation;
    private long populationInCities;
    private double percentageInCities;
    private long populationNotInCities;
    private double percentageNotInCities;

    public Population(String name, long totalPopulation, long populationInCities,
                      double percentageInCities, long populationNotInCities,
                      double percentageNotInCities)
    {
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.populationInCities = populationInCities;
        this.percentageInCities = percentageInCities;
        this.populationNotInCities = populationNotInCities;
        this.percentageNotInCities = percentageNotInCities;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getTotalPopulation()
    {
        return totalPopulation;
    }

    public void setTotalPopulation(long totalPopulation)
    {
        this.totalPopulation = totalPopulation;
    }

    public long getPopulationInCities()
    {
        return populationInCities;
    }

    public void setPopulationInCities(long populationInCities)
    {
        this.populationInCities = populationInCities;
    }

    public double getPercentageInCities()
    {
        return percentageInCities;
    }

    public void setPercentageInCities(double percentageInCities)
    {
        this.percentageInCities = percentageInCities;
    }

    public long getPopulationNotInCities()
    {
        return populationNotInCities;
    }

    public void setPopulationNotInCities(long populationNotInCities)
    {
        this.populationNotInCities = populationNotInCities;
    }

    public double getPercentageNotInCities()
    {
        return percentageNotInCities;
    }

    public void setPercentageNotInCities(double percentageNotInCities)
    {
        this.percentageNotInCities = percentageNotInCities;
    }

    @Override
    public String toString()
    {
        return "Population{"
                + "name='" + name + '\''
                + ", totalPopulation=" + totalPopulation
                + ", populationInCities=" + populationInCities
                + ", percentageInCities=" + percentageInCities
                + ", populationNotInCities=" + populationNotInCities
                + ", percentageNotInCities=" + percentageNotInCities
                + '}';
    }
}
