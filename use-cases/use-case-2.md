# USE CASE: 2 Produce a Report on the Salary of Employees in a Department

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to produce a report on the salary of employees in a department* so that *I can support financial reporting of the organisation.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the department. Database contains current employee salary data, including department assignment.

### Success End Condition

A report of salaries for employees in the given department is available for HR to provide to finance.

### Failed End Condition

No report is produced.

### Primary Actor

HR Advisor.

### Trigger

A request for department salary information is sent to HR.

## MAIN SUCCESS SCENARIO

1. Finance request salary information for a given department.
2. HR advisor captures the name of the department to get salary information for.
3. HR advisor extracts current salary information of all employees in the given department.
4. HR advisor provides report to finance.

## EXTENSIONS

3. **Department does not exist**:
    1. HR advisor informs finance that no such department exists.
3. **Department has no employees**:
    1. HR advisor informs finance that the department has no employees.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: v0.1.0.4
