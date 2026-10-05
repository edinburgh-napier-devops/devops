# USE CASE: 6 View an Employee's Details

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to view an employee's details* so that *the employee's promotion request can be supported.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know which employee is to be viewed. Database contains the employee's current details.

### Success End Condition

The employee's details are displayed to the HR advisor.

### Failed End Condition

The employee's details are not viewed.

### Primary Actor

HR Advisor.

### Trigger

A promotion request is received that requires the employee's current details.

## MAIN SUCCESS SCENARIO

1. HR advisor receives a promotion request for an employee.
2. HR advisor captures the identity of the employee whose details are required.
3. HR advisor retrieves the employee's current details.
4. HR advisor reviews the details to support the promotion request.

## EXTENSIONS

3. **Employee does not exist**:
    1. HR advisor is informed that no matching employee record exists.
    2. HR advisor confirms the employee identity and retries, or stops.

## SUB-VARIATIONS

2. **Employee is identified by name rather than employee number**:
    1. HR advisor searches by name.
    2. If more than one match is returned, HR advisor selects the correct employee.

## SCHEDULE

**DUE DATE**: v0.1.0.3
