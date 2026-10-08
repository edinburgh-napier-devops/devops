# USE CASE: 15 Top N Cities in a Country

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user* I want *to produce a report of the top N populated cities in a country organised by largest population to smallest* so that *the organisation can access population information.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

N is provided by the user. We know the country. Database contains city and country population data.

### Success End Condition

A cities report is available to the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

The user requests the top N populated cities in a country.

## MAIN SUCCESS SCENARIO

1. User requests the top N populated cities in a country.
2. User captures the country name and N.
3. User extracts the N cities for that country, ordered by population, largest to smallest.
4. User produces the report with columns: name, country, district, and population.
5. User provides the report to the organisation.

## EXTENSIONS

3. **Country does not exist**:
    1. User informs the organisation that no such country exists.

2. **N is missing or not a positive whole number**:
    1. User informs the organisation that N must be a positive whole number.
    2. Use case ends.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
