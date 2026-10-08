# USE CASE: 5 Top N Countries in a Continent

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user* I want *to produce a report of the top N populated countries in a continent* so that *the organisation can access population information.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the continent. N is provided by the user. Database contains country population data.

### Success End Condition

A country report is available to the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

The user requests the top N countries in a continent.

## MAIN SUCCESS SCENARIO

1. User requests the top N populated countries in a continent.
2. User captures the continent name and N.
3. User extracts the N country records in that continent with the largest population.
4. User produces the country report with columns: code, name, continent, region, population, and capital.
5. User provides the report to the organisation.

## EXTENSIONS

3. **Continent does not exist**:

    1. User informs the organisation that no such continent exists.

2. **N is missing or not a positive whole number**:

    1. User informs the organisation that N must be a positive whole number.

    2. Use case ends.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
