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

1. Finance requests salary information for a given department.
2. The HR advisor enters the name of the department.
3. The system confirms that the department exists.
4. The system retrieves the current salary information for employees in the department.
5. The system produces a report identified by the department name, with each employee's number, name, and salary.
6. The HR advisor provides the report to finance.

## EXTENSIONS

3a. **Department does not exist**:
   1. The system informs the HR advisor that no matching department exists.
   2. The HR advisor informs finance that no such department exists.

4a. **Department has no employees**:
   1. The system informs the HR advisor that the department has no current employees.
   2. The HR advisor informs finance that the department has no employees.

4b. **Salary information cannot be retrieved**:
   1. The system reports that the salary report could not be produced.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: v0.1.0.6
