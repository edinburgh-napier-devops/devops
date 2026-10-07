# USE CASE: 1 Produce a Report on the Salary of All Employees

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to produce a report on the salary of all employees* so that *I can support financial reporting of the organisation.*

### Scope

Company.

### Level

Primary task.

### Preconditions

Database contains current employee salary data.

### Success End Condition

A report of all employee salaries is available for HR to provide to finance.

### Failed End Condition

No report is produced.

### Primary Actor

HR Advisor.

### Trigger

A request for organisation-wide salary information is sent to HR.

## MAIN SUCCESS SCENARIO

1. Finance request salary information for all employees.
2. HR advisor requests a salary report covering every current employee.
3. HR advisor extracts current salary information of all employees.
4. HR advisor provides report to finance.

## EXTENSIONS

3. **No employees are recorded**:
    1. HR advisor informs finance that no employee salary data is available.
3. **Salary data is incomplete**:
    1. HR advisor produces the report for employees with salary data.
    2. HR advisor notes which employee records are missing salary information.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: v0.1.0.4
