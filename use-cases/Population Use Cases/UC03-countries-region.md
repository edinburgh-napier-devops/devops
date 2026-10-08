# USE CASE: 3 Countries in a Region

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user* I want *to produce a report of all countries in a region organised by largest population to smallest* so that *the organisation can access population information.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the region. Database contains country population data.

### Success End Condition

A country report is available to the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

The user requests countries in a region.

## MAIN SUCCESS SCENARIO

1. User requests countries in a region.
2. User captures the name of the region.
3. User extracts country records for that region, ordered by population, largest to smallest.
4. User produces the country report with columns: code, name, continent, region, population, and capital.
5. User provides the report to the organisation.

## EXTENSIONS

3. **Region does not exist**:

    1. User informs the organisation that no such region exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
