# USE CASE: 11 Cities in a District

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user* I want *to produce a report of all cities in a district organised by largest population to smallest* so that *the organisation can access population information.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the district. Database contains city and country population data.

### Success End Condition

A cities report is available to the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

The user requests all cities in a district.

## MAIN SUCCESS SCENARIO

1. User requests all cities in a district.
2. User captures the name of the district.
3. User extracts all cities for that district, ordered by population, largest to smallest.
4. User produces the report with columns: name, country, district, and population.
5. User provides the report to the organisation.

## EXTENSIONS

3. **District does not exist**:
    1. User informs the organisation that no such district exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
