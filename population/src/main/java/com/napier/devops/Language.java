package com.napier.devops;

/**
 * A language report row.
 * Columns: language, number of speakers, percentage of world population.
 */
public class Language
{
    private String language;
    private long speakers;
    private double worldPercentage;

    public Language(String language, long speakers, double worldPercentage)
    {
        this.language = language;
        this.speakers = speakers;
        this.worldPercentage = worldPercentage;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public long getSpeakers()
    {
        return speakers;
    }

    public void setSpeakers(long speakers)
    {
        this.speakers = speakers;
    }

    public double getWorldPercentage()
    {
        return worldPercentage;
    }

    public void setWorldPercentage(double worldPercentage)
    {
        this.worldPercentage = worldPercentage;
    }

    @Override
    public String toString()
    {
        return "Language{"
                + "language='" + language + '\''
                + ", speakers=" + speakers
                + ", worldPercentage=" + worldPercentage
                + '}';
    }
}
