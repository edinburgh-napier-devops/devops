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
2. User provides the city name and, when needed, its country and district or unique city ID.
3. The system finds exactly one matching city and extracts its population.
4. The system provides the population figure to the organisation.

## EXTENSIONS

3. **City does not exist**:

    1. The system informs the user that no matching city exists.

3. **City name matches more than one city**:

    1. The system shows the matching city IDs and locations without reporting a population.
    2. The user specifies the country and district or a unique city ID, then retries.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
