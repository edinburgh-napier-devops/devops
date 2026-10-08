# USE CASE: 32 Speakers of Selected Languages

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user* I want *to produce a report of the number of people who speak Chinese, English, Hindi, Spanish, and Arabic, from greatest number to smallest, including the percentage of the world population* so that *the organisation can access population information.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

Database contains language and world population data.

### Success End Condition

A language report is available to the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

The user requests speaker numbers for Chinese, English, Hindi, Spanish, and Arabic.

## MAIN SUCCESS SCENARIO

1. User requests the number of speakers of Chinese, English, Hindi, Spanish, and Arabic.
2. User extracts the number of speakers of each language.
3. User calculates each language as a percentage of the world population.
4. User orders the languages from greatest number of speakers to smallest.
5. User produces the report with language, number of speakers, and percentage of world population.
6. User provides the report to the organisation.

## EXTENSIONS

2. **A requested language is not recorded**:

    1. User produces the report for the languages that are recorded.

    2. User notes which requested languages are missing.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
