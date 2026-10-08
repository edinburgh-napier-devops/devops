# USE CASE: 28 Population of a Region

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user* I want *to provide the population of a region* so that *the organisation can access population information.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the region. Database contains population data.

### Success End Condition

The population of a region is available to the organisation.

### Failed End Condition

No population figure is produced.

### Primary Actor

User.

### Trigger

The user requests the population of a region.

## MAIN SUCCESS SCENARIO

1. User requests the population of a region.
2. User captures the name of the region.
3. User extracts the population of that region.
4. User provides the population figure to the organisation.

## EXTENSIONS

3. **Region does not exist**:

    1. User informs the organisation that no such region exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
