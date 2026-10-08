# USE CASE: 30 Population of a District

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user* I want *to provide the population of a district* so that *the organisation can access population information.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the district. Database contains population data.

### Success End Condition

The population of a district is available to the organisation.

### Failed End Condition

No population figure is produced.

### Primary Actor

User.

### Trigger

The user requests the population of a district.

## MAIN SUCCESS SCENARIO

1. User requests the population of a district.
2. User captures the name of the district.
3. User extracts the population of that district.
4. User provides the population figure to the organisation.

## EXTENSIONS

3. **District does not exist**:

    1. User informs the organisation that no such district exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
