# USE CASE: 5 Add a New Employee's Details

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to add a new employee's details* so that *I can ensure the new employee is paid.*

### Scope

Company.

### Level

Primary task.

### Preconditions

HR advisor is authorised to maintain employee records. The new employee's details are available, including the information required for payroll.

### Success End Condition

The new employee's details are stored and available for payroll.

### Failed End Condition

The new employee's details are not added.

### Primary Actor

HR Advisor.

### Trigger

A new employee is to join the organisation and must be set up for payment.

## MAIN SUCCESS SCENARIO

1. HR advisor initiates the addition of a new employee.
2. HR advisor enters the employee's details, including identity, role, department, start date, and salary.
3. System validates the entered details.
4. System stores the new employee record.
5. HR advisor confirms the employee has been added and is available for payroll.

## EXTENSIONS

3. **Required details are missing or invalid**:
    1. System reports the validation errors.
    2. HR advisor corrects the details.
    3. Use case resumes at step 3.
3. **Employee already exists**:
    1. System informs the HR advisor that a matching employee record already exists.
    2. HR advisor stops the addition or updates the existing record (see Use Case 7).

## SUB-VARIATIONS

2. **Salary is not yet agreed**:
    1. HR advisor records the employee with salary marked as pending.
    2. Employee is not released to payroll until salary is completed.

## SCHEDULE

**DUE DATE**: v0.1.0.8
