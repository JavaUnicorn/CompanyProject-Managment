# P2 MUTATION REPORTS

# PIT Tables:
### BEFORE:
| **Classes**            | **Line Coverage** | **Mutation Coverage** | **Test Strength** |
|------------------------|-------------------|-----------------------|-------------------|
| Overall                | 79% (244/310)     | 71% (110/155)         | 85% (110/129)     |
| **Breakdown by Class** |                   |                       |                   |
| Company                | 50% (54/108)      | 40% (25/58)           | 64% (23/36)       |
| Project                | 90% (91/101)      | 84% (32/38)           | 94% (32/34)       |
| Qualification          | 100% (27/27)      | 100% (14/14)          | 100% (14/14)      |
| Worker                 | 99% (66/67)       | 91% (40/44)           | 91% (40/44)       |

### AFTER:
| **Classes**            | **Line Coverage** | **Mutation Coverage** | **Test Strength** |
|------------------------|-------------------|-----------------------|-------------------|
| Overall                | 96% (284/296)     | 90% (144/160)         | 94% (144/153)     |
| **Breakdown by Class** |                   |                       |                   |
| Company                | 92% (87/95)       | 82% (56/68)           | 89% (56/51)       |
| Project                | 97% (87/90)       | 97% (32/33)           | 97% (32/33)       |
| Qualification          | 100% (27/27)      | 100% (14/14)          | 100% (14/14)      |
| Worker                 | 99% (66/67)       | 93% (41/44)           | 93% (41/44)       |

# TESTS ADDED:

* CompanyTest.testFinishNoSetStatusMutation() was added to ensure our tests covered the line in Company.finish(Project project) that sets project's status to FINISHED.  This killed a mutation that removed the setStatus call.
* Company.testGetAssignedWorkers_BaseCase() was added to ensure the coverage for getAssignedWorkers. This solved lack of coverage on this line.
* Company.testGetUnavailableWorkers_BaseCase() was added to ensure the coverage for getUnavailableWorkers. This solved lack of coverage on this line
* Company.testGetUnassignedWorkers_BaseCase() was added to ensure the coverage for getunassignedWorkers. This solved lack of coverage on this line
* Company.testGetProjects_BaseCase() was added to ensure the coverage for getProjects. This solved lack of coverage on this line
* Company.testCreateWorker_BadSalary() was created to deal with a negated conditional that was not covered.
* Company.testAssignWorkerToProject_ProjectAlreadyFinished() was created to deal with a negated conditional that was not covered.
* Company.testCreateWorker_ASingleQualification() was created to deal with a negated conditional that was not covered.

* ProjectTest.testGetReuqiredQualificationsChangedConditionalBoundaryMutation() was added to ensure Project.getRequiredQualifications() worked properly with exactly one required qualification.  This killed a mutant that changed the condition boundary that checks whether or not to create an empty set and return it.

* WorkerTest.testHashCodeReplacedReturnMutation() was added to ensure Worker.hashCode()'s return statement was returning the correct int.  This killed a mutation that made it return 0 instead.
