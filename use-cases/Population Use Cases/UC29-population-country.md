# USE CASE: 29 Population of a Country

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user* I want *to provide the population of a country* so that *the organisation can access population information.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the country. Database contains population data.

### Success End Condition

The population of a country is available to the organisation.

### Failed End Condition

No population figure is produced.

### Primary Actor

User.

### Trigger

The user requests the population of a country.

## MAIN SUCCESS SCENARIO

1. User requests the population of a country.
2. User captures the name of the country.
3. User extracts the population of that country.
4. User provides the population figure to the organisation.

## EXTENSIONS

3. **Country does not exist**:

    1. User informs the organisation that no such country exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
