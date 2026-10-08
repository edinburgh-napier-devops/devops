# USE CASE: 19 Capital Cities in a Region

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user* I want *to produce a report of all capital cities in a region organised by largest population to smallest* so that *the organisation can access population information.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the region. Database contains city and country population data.

### Success End Condition

A capital cities report is available to the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

The user requests all capital cities in a region.

## MAIN SUCCESS SCENARIO

1. User requests all capital cities in a region.
2. User captures the name of the region.
3. User extracts all capital cities for that region, ordered by population, largest to smallest.
4. User produces the report with columns: name, country, and population.
5. User provides the report to the organisation.

## EXTENSIONS

3. **Region does not exist**:
    1. User informs the organisation that no such region exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
