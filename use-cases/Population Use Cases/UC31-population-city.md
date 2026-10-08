# USE CASE: 31 Population of a City

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user* I want *to provide the population of a city* so that *the organisation can access population information.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the city. Database contains population data.

### Success End Condition

The population of a city is available to the organisation.

### Failed End Condition

No population figure is produced.

### Primary Actor

User.

### Trigger

The user requests the population of a city.

## MAIN SUCCESS SCENARIO

1. User requests the population of a city.
2. User captures the name of the city.
3. User extracts the population of that city.
4. User provides the population figure to the organisation.

## EXTENSIONS

3. **City does not exist**:

    1. User informs the organisation that no such city exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
