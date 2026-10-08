# USE CASE: 23 City and Non-City Population by Continent

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user* I want *to produce a report of the population of people, people living in cities, and people not living in cities in each continent* so that *the organisation can access population information.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

Database contains country and city population data.

### Success End Condition

A population report for each continent is available to the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

The user requests the city and non-city population of each continent.

## MAIN SUCCESS SCENARIO

1. User requests the population living in cities and not living in cities for each continent.
2. User extracts, for each continent, the total population, the population living in cities, and the population not living in cities.
3. User calculates the percentage of the population living in cities and not living in cities.
4. User produces the population report with columns: name, total population, population living in cities (with percentage), and population not living in cities (with percentage).
5. User provides the report to the organisation.

## EXTENSIONS

2. **No continent population data is recorded**:

    1. User informs the organisation that no population data is available.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
