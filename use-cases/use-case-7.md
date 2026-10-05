# USE CASE: 7 Update an Employee's Details

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to update an employee's details* so that *the employee's details are kept up-to-date.*

### Scope

Company.

### Level

Primary task.

### Preconditions

The HR advisor knows the employee number and the corrected first and last names. The employee database is available.

### Success End Condition

The selected employee's first and last names are updated. No other employee record is changed.

### Failed End Condition

The employee's existing details remain unchanged.

### Primary Actor

HR Advisor.

### Trigger

The HR advisor receives a request to correct an employee's personal details.

## MAIN SUCCESS SCENARIO

1. The HR advisor enters the employee number and corrected first and last names.
2. The system validates the supplied values.
3. The system finds the employee record.
4. The system updates the employee's first and last names.
5. The system confirms that the employee was updated.

## EXTENSIONS

2a. **An employee number or name is invalid**:
   1. The system rejects the update and explains that valid values are required.

3a. **The employee does not exist**:
   1. The system reports that no matching employee was found.

4a. **The database update fails**:
   1. The system reports the failure.
   2. The employee's existing details remain unchanged.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: v0.1.0.7
