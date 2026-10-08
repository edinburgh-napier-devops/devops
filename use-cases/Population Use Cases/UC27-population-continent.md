# USE CASE: 27 Population of a Continent

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user* I want *to provide the population of a continent* so that *the organisation can access population information.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the continent. Database contains population data.

### Success End Condition

The population of a continent is available to the organisation.

### Failed End Condition

No population figure is produced.

### Primary Actor

User.

### Trigger

The user requests the population of a continent.

## MAIN SUCCESS SCENARIO

1. User requests the population of a continent.
2. User captures the name of the continent.
3. User extracts the population of that continent.
4. User provides the population figure to the organisation.

## EXTENSIONS

3. **Continent does not exist**:

    1. User informs the organisation that no such continent exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
