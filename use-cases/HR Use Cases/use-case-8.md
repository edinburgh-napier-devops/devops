# USE CASE: 8 Delete an Employee's Details

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to delete an employee's details* so that *the company is compliant with data retention legislation.*

### Scope

Company.

### Level

Primary task.

### Preconditions

The employee record exists. The applicable data retention period has expired, or a lawful erasure request has been received. HR advisor is authorised to remove employee records.

### Success End Condition

The employee's details are deleted and the company remains compliant with data retention legislation.

### Failed End Condition

The employee's details are not deleted.

### Primary Actor

HR Advisor.

### Trigger

An employee record reaches the end of its retention period, or a valid erasure request is received.

## MAIN SUCCESS SCENARIO

1. HR advisor identifies the employee whose details are to be deleted.
2. HR advisor retrieves the employee's current details and retention status.
3. HR advisor confirms that the record is eligible for deletion under data retention legislation.
4. HR advisor deletes the employee's details.
5. System removes the employee record.
6. HR advisor confirms the employee's details have been deleted.

## EXTENSIONS

2. **Employee does not exist**:
    1. HR advisor is informed that no matching employee record exists.
    2. Use case ends.
3. **Retention period has not expired and no lawful erasure request exists**:
    1. System informs the HR advisor that the record must be retained.
    2. HR advisor does not delete the record.
4. **Record is still required for an active legal or payroll obligation**:
    1. System informs the HR advisor that deletion is blocked.
    2. HR advisor records the reason and does not delete the record.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: v0.1.0.10
