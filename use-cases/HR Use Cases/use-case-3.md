# USE CASE: 3 Produce a Report on the Salary of Employees in My Department

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *department manager* I want *to produce a report on the salary of employees in my department* so that *I can support financial reporting for my department.*

### Scope

Department.

### Level

Primary task.

### Preconditions

The department manager is known and is assigned to a department. Database contains current employee salary data for that department.

### Success End Condition

A report of salaries for employees in the manager's department is available for departmental financial reporting.

### Failed End Condition

No report is produced.

### Primary Actor

Department Manager.

### Supporting Actors

HR Advisor.

### Trigger

The department manager needs salary information for departmental financial reporting.

## MAIN SUCCESS SCENARIO

1. Department manager requests a salary report for their own department.
2. System identifies the department assigned to the manager.
3. System extracts current salary information of all employees in that department.
4. Department manager receives the report.

## EXTENSIONS

2. **Manager is not assigned to a department**:
    1. System informs the manager that no department is assigned.
    2. Manager refers the request to HR.
3. **Department has no employees**:
    1. System informs the manager that the department has no employees.
3. **Manager is not authorised to view salary data**:
    1. System denies the report.
    2. Manager refers the request to HR.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: v0.1.0.7
