# USE CASE: 8 Delete an Employee's Details

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to delete an employee's details* so that *the company is compliant with data retention legislation.*

### Scope

Company.

### Level

Primary task.

### Preconditions

The HR advisor knows the employee number of the record to be removed and has confirmed that the retention period for that record has elapsed. The employee database is available.

### Success End Condition

The selected employee's record is removed, together with their salary history, job titles and department records. No other employee record is changed.

### Failed End Condition

The employee's record and all associated records remain in the database.

### Primary Actor

HR Advisor.

### Trigger

The HR advisor receives a request to remove an employee's record, or a retention review identifies a record that must no longer be held.

## MAIN SUCCESS SCENARIO

1. The HR advisor enters the employee number of the record to be deleted.
2. The system validates the supplied employee number.
3. The system finds the employee record.
4. The system deletes the employee record and the associated salary, title and department records.
5. The system confirms that the employee was deleted.

## EXTENSIONS

2a. **The employee number is invalid**:
1. The system rejects the deletion and explains that a positive employee number is required.

3a. **The employee does not exist**:
1. The system reports that no matching employee was found.

4a. **The database deletion fails**:
1. The system reports the failure.
2. The employee's record and associated records remain unchanged.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: v0.1.0.10