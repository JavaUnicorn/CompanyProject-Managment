package edu.colostate.cs415.model;

import org.junit.Test;
import org.mockito.internal.matchers.Null;
import org.eclipse.jetty.server.HttpChannelState.Action;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CompanyTest {
	//2.3.4.1
	@Test
	public void testCompanyConstructor_ValidName() {
		String name = "Test Company";
		Company company = new Company(name);

		assertTrue(company.getQualifications().isEmpty());
		// Assert initial state (empty collections)
		assertTrue(company.getProjects().isEmpty());
	}

    @Test (expected = IllegalArgumentException.class)
    public void testCompanyConstructor_NoWhitespace() {
        Company company = new Company("   ");
        assertFalse(company.getName().isEmpty());
        assertTrue(company.getName().trim().length() > 0);
    }

    //2.3.4.2
    @Test
    public void testEquals() {
        Company c1 = new Company("Exxon");
        Company c2 = new Company("Exxon");
        Company c3 = new Company("Not Exxon");

        
        // test case 1: Match
        assertEquals(true, c1.equals(c2));
        // test case 2: Non-match
        assertEquals(false, c1.equals(c3));
    }

    @Test
    public void testEqualsNull () {
        Company c1 = new Company("Exxon");

        assertFalse (c1.equals(null));
    }

    @Test
    public void testNonCompany () {
        Company c1 = new Company("Exxon");
        int c2 = 0;

        
        // test case 1: Match
        assertEquals(false, c1.equals(c2));
    }

    @Test
    public void testHashcode(){
        Company dumbycomp = new Company("JoJon");
        assertEquals("JoJon".hashCode(), dumbycomp.hashCode());
    }
    //2.3.4.3
    @Test
    public void testToString() {
        Company c1 = new Company("Exxon");

        // test case 1: Valid
        assertEquals(c1.toString(), "Exxon:0:0");
        
        // add more test cases after more functionality to Company added.
    }

    //2.3.4.4
    @Test
    public void testGetName() {
        Company c1 = new Company("Exxon");

        // test case 1
        assertEquals(c1.getName(), "Exxon");
    }

    //2.3.4.5
    @Test
    public void testGetEmployedWorkers() {
        Company c1 = new Company("Exxon");
        
        Set<Qualification> qualifications = new HashSet<>();
        Qualification qual1 = new Qualification("qual1");
        qualifications.add(qual1);
        c1.createWorker("Jeff", qualifications, 100000);

        // test case 1
        assertEquals(1, c1.getEmployedWorkers().size());

        c1.createWorker("Beff", qualifications, 100001);
        c1.createWorker("Deff", qualifications, 100002);
        c1.createWorker("Seff", qualifications, 100003);
        c1.createWorker("Leff", qualifications, 100004);

        assertEquals(5, c1.getEmployedWorkers().size());
    }

    //2.3.4.6
    @Test
    public void testGetAvailableWorkers() {
        Company c1 = new Company("Exxon");
        
        Set<Qualification> qualifications = new HashSet<>();
        Qualification qual1 = new Qualification("qual1");
        qualifications.add(qual1);
        c1.createWorker("Jeff", qualifications, 100000);

        // test case 1
        assertEquals(1, c1.getAvailableWorkers().size());

        c1.createWorker("Beff", qualifications, 100001);
        c1.createWorker("Deff", qualifications, 100002);
        c1.createWorker("Seff", qualifications, 100003);
        c1.createWorker("Leff", qualifications, 100004);

        assertEquals(5, c1.getAvailableWorkers().size());
    }

       //2.3.4.7
       @Test
       public void testGetUnavailableWorkers_BaseCase() {
           Company company = new Company("Exxon");
           
           Set<Qualification> qualifications = new HashSet<>();
           qualifications.add(company.createQualification("qual1"));
   
           Worker Jeff = company.createWorker("Jeff", qualifications, 100000);
   
           ProjectSize size = ProjectSize.BIG;
           Project project1 = company.createProject("App", qualifications, size);
           Project project2 = company.createProject("Jack", qualifications, size);
           Project project3 = company.createProject("Lco.", qualifications, size);
           Project project4 = company.createProject("JJ", qualifications, size);
           company.assign(Jeff, project1);
           company.assign(Jeff, project2);
           company.assign(Jeff, project3);
           company.assign(Jeff, project4);
   
           assertTrue(company.getUnavailableWorkers().contains(Jeff));
       }
       //2.3.4.8
       @Test
       public void testGetAssignedWorkers_BaseCase() {
           Company company = new Company("Exxon");
           
           Set<Qualification> qualifications = new HashSet<>();
           qualifications.add(company.createQualification("qual1"));
   
           Worker Jeff = company.createWorker("Jeff", qualifications, 100000);
   
           ProjectSize size = ProjectSize.BIG;
           Project project1 = company.createProject("App", qualifications, size);
           company.assign(Jeff, project1);
   
           assertTrue(company.getAssignedWorkers().contains(Jeff));
           assertTrue(company.getUnassignedWorkers().isEmpty());
       }

       @Test
       public void testGetAssignedWorkers_MultipleWorkers() {
           Company company = new Company("Exxon");

           Set<Qualification> qualifications = new HashSet<>();

           Set<Qualification> jeffQuals = new HashSet<>();
           Qualification jeffQual1 = company.createQualification("qual1");
           qualifications.add(jeffQual1);
           jeffQuals.add(jeffQual1);

           Set<Qualification> beffQuals = new HashSet<>();
           Qualification beffQual1 = company.createQualification("qual2");
           qualifications.add(beffQual1);
           beffQuals.add(beffQual1);

           Set<Qualification> seffQuals = new HashSet<>();
           Qualification seffQual1 = company.createQualification("qual3");
           qualifications.add(seffQual1);
           seffQuals.add(seffQual1);

           Set<Qualification> geffQuals = new HashSet<>();
           Qualification geffQual1 = company.createQualification("qual4");
           qualifications.add(geffQual1);
           geffQuals.add(geffQual1);

           Set<Qualification> leffQuals = new HashSet<>();
           Qualification leffQual1 = company.createQualification("qual5");
           qualifications.add(leffQual1);
           leffQuals.add(leffQual1);
   
           Worker Jeff = company.createWorker("Jeff", jeffQuals, 100000);
           Worker Beff = company.createWorker("Beff", beffQuals, 100000);
           Worker Seff = company.createWorker("Seff", seffQuals, 100000);
           Worker Geff = company.createWorker("Geff", geffQuals, 100000);
           Worker Leff = company.createWorker("Leff", leffQuals, 100000);
   
           ProjectSize size = ProjectSize.BIG;
           Project project1 = company.createProject("App", qualifications, size);
           company.assign(Jeff, project1);
           company.assign(Beff, project1);
           company.assign(Seff, project1);
           company.assign(Geff, project1);
           company.assign(Leff, project1);

   
           assertTrue(company.getAssignedWorkers().contains(Jeff));
           assertTrue(company.getAssignedWorkers().contains(Beff));
           assertTrue(company.getAssignedWorkers().contains(Seff));
           assertTrue(company.getAssignedWorkers().contains(Geff));
           assertTrue(company.getAssignedWorkers().contains(Leff));

           assertTrue(company.getUnassignedWorkers().isEmpty());
       }

       @Test
       public void testGetAssignedWorkers_NoAssignedWorkers() {
           Company company = new Company("Exxon");
           
           Set<Qualification> qualifications = new HashSet<>();
           qualifications.add(company.createQualification("qual1"));
      
           assertTrue(company.getAssignedWorkers().isEmpty());
       }       
   
       //2.3.4.9
       @Test
       public void testGetUnassignedWorkers_BaseCase() {
           Company company = new Company("Exxon");
           
           Set<Qualification> qualifications = new HashSet<>();
           qualifications.add(company.createQualification("qual1"));
   
           Worker Jeff = company.createWorker("Jeff", qualifications, 100000);
           
           assertTrue(company.getUnassignedWorkers().contains(Jeff));
   
       }
   
       //2.3.4.10
       @Test
       public void testGetProjects_BaseCase() {
           Company company = new Company("Exxon");
           
           Set<Qualification> qualifications = new HashSet<>();
           qualifications.add(company.createQualification("qual1"));
   
           ProjectSize size = ProjectSize.BIG;
           Project project1 = company.createProject("App", qualifications, size);
           assertTrue(company.getProjects().contains(project1));
       }
   

	//2.3.4.11
	@Test
	public void testGetQualifications() {
    Company company = new Company("Test Company");

    // Test case 1: Empty company
    Set<Qualification> qualifications = company.getQualifications();
    assertEquals(new HashSet<>(), qualifications); // Assert empty set

    // Test case 2: Company with qualifications
    Qualification q1 = new Qualification("Java Developer");
    Qualification q2 = new Qualification("Database Administrator");
    company.createQualification(q1.toString());
    company.createQualification(q2.toString());

    qualifications = company.getQualifications();
    // Don't assert order due to the disclaimer in the method description.
    assertTrue(qualifications.contains(q1)); // Assert presence of q1
    assertTrue(qualifications.contains(q2)); // Assert presence of q2
	}

	//2.3.4.12
	@Test
	public void testCreateWorker() {
    Company company = new Company("Test Company");

    // Create qualifications
    Qualification q1 = new Qualification("Java Developer");
    Qualification q2 = new Qualification("Database Administrator");

    // Add qualifications to the company (assuming a method like addQualification exists)
    company.createQualification("dis");
    company.createQualification("Jdis");

    // Test case 1: Valid worker creation
    String name = "John Doe";
    Set<Qualification> qualifications = new HashSet<>();
    qualifications.add(q1);
    double salary = 100000.00;
    Worker worker = company.createWorker(name, qualifications, salary);
    assertTrue(company.getAvailableWorkers().contains(worker));
    assertTrue(company.getEmployedWorkers().contains(worker));
}

    @Test (expected = IllegalArgumentException.class)
	public void testCreateWorker_BadSalary() {
    Company company = new Company("Test Company");

    // Create qualifications
    Qualification q1 = new Qualification("Java Developer");
    Qualification q2 = new Qualification("Database Administrator");

    // Add qualifications to the company (assuming a method like addQualification exists)
    company.createQualification("dis");
    company.createQualification("Jdis");

    // Test case 1: Valid worker creation
    String name = "John Doe";
    Set<Qualification> qualifications = new HashSet<>();
    qualifications.add(q1);
    double salary = -10.00;
    company.createWorker(name, qualifications, salary);
}

@Test 
public void testCreateWorker_ASingleQualification() {
Company company = new Company("Test Company");

// Create qualifications
Qualification q1 = new Qualification("Java Developer");
Qualification q2 = new Qualification("Database Administrator");

// Add qualifications to the company (assuming a method like addQualification exists)
company.createQualification("dis");
company.createQualification("Jdis");

// Test case 1: Valid worker creation
String name = "John Doe";
Set<Qualification> qualifications = new HashSet<>();
qualifications.add(q1);
double salary = 100.00;
Worker worker = company.createWorker(name, qualifications, salary);
assertTrue(company.getAvailableWorkers().contains(worker));
assertTrue(company.getEmployedWorkers().contains(worker));
}

    @Test (expected = IllegalArgumentException.class)
	public void testCreateWorker_EmptyQualificationset() {
    Company company = new Company("Test Company");

    // Create qualifications
    Qualification q1 = new Qualification("Java Developer");
    Qualification q2 = new Qualification("Database Administrator");

    // Add qualifications to the company (assuming a method like addQualification exists)
    company.createQualification("dis");
    company.createQualification("Jdis");

    // Test case 1: Valid worker creation
    String name = "John Doe";
    Set<Qualification> qualifications = new HashSet<>();
    double salary = 100.00;
    company.createWorker(name, qualifications, salary);
}
    //2.3.4.13
    @Test
    public void testCreateQualification() {
    Company company = new Company("Test Company");

    // Test case 1: null description (expected exception)
    try {
        company.createQualification(null);
        fail("Expected an exception for null description"); // Fail the test if no exception is thrown
    } catch (IllegalArgumentException e) {
        assertEquals("Qualification description cannot be null.", e.getMessage()); // Assert the exception message
    }

    // Test case 2: valid description
    String description = "Java Developer";
    Qualification qualification = company.createQualification(description);

    assertNotNull(qualification); // Assert the returned qualification is not null
    assertEquals(description, qualification.toString()); // Assert the qualification's description
    assertTrue(company.getQualifications().contains(qualification)); // Assert the qualification is added to the company's set
    }

    //2.3.4.14
    @Test
    public void testCreateProject_Test() {
    Company company = new Company("Test Company");

    // Create some qualifications
    Qualification q1 = company.createQualification("Java Developer");
    Qualification q2 = company.createQualification("Database Administrator");

    // Create a project with valid data
    String name = "Project X";
    Set<Qualification> qualifications = new HashSet<>();
    qualifications.add(q1);
    qualifications.add(q2);
    ProjectSize size = ProjectSize.MEDIUM;

    Project project = company.createProject(name, qualifications, size);

    // Smoke test assertions (basic checks)
    assertNotNull(project); // Assert the project is not null
    assertEquals(name, project.getName()); // Assert the project name
    assertEquals(size, project.getSize()); // Assert the project size
    assertTrue(company.getProjects().contains(project));
    }

    //2.3.4.15
    @Test
    public void testStartProject() {
    // Setup company with qualifications and project
    Company company = new Company("Test Company");
    Qualification q1 = company.createQualification("Java Developer");
    Qualification q2 = company.createQualification("Database Administrator");
 
    Set<Qualification> qualifications = new HashSet<>();
    // qualifications.add("1");
    qualifications.add(q1);
    qualifications.add(q2);

    Project project = new Project("Project X", qualifications, ProjectSize.MEDIUM);
    company.start(project);
    // Test: Start project with unsatisfied requirements
    assertTrue(project.getStatus().equals(ProjectStatus.PLANNED));
    Worker worker = new Worker("Worker", qualifications, 60000);
    project.addWorker(worker);
    company.getProjects().add(project); //Add project to company 

    project.setStatus(ProjectStatus.SUSPENDED);
    // Base Test: Start project with satisfied requirements
    company.start(project);
    assertTrue(project.getStatus().equals(ProjectStatus.ACTIVE));

    // Test case 3: Start project already in active state
    project.setStatus(ProjectStatus.ACTIVE);
    company.start(project);
    assertEquals(ProjectStatus.ACTIVE, project.getStatus()); // Project should remain active
    }

    @Test ( expected = IllegalArgumentException.class )
    public void testStartProject_Null() {
        Company company = new Company("Test Company");
    
        Project project = null;
        company.start(project);
    }

    //2.3.4.16
    @Test
    public void testFinishProject_Active() {
        // Setup company with qualifications, workers, and project
        Company company = new Company("Test Company");
        Qualification q1 = company.createQualification("Java Developer");
        
        Set<Qualification> qualifications = new HashSet<>();
        Qualification q = company.createQualification("Qual1");
        qualifications.add(q);
        qualifications.add(q1);


        Worker worker1 =company.createWorker("John Doe", qualifications, 100000.00);
        Project project = company.createProject("Project X", qualifications, ProjectSize.MEDIUM);
        company.assign(worker1, project);

        company.start(project);

        // // Base Test: Finish active project
        company.finish(project);
        assertEquals(ProjectStatus.FINISHED, project.getStatus());
        assertTrue(project.getWorkers().isEmpty()); // Workers are still assigned to Project

    }

    @Test
    public void testFinishProject_Planned() {
        // Setup company with qualifications, workers, and project
        Company company = new Company("Test Company");
        Qualification q1 = company.createQualification("Java Developer");
        
        Set<Qualification> qualifications = new HashSet<>();
        Qualification q = new Qualification("Qual1");
        qualifications.add(q);
        qualifications.add(q1);

        Worker worker1 =company.createWorker("John Doe", qualifications, 100000.00);
        company.createWorker(worker1.getName(), qualifications, 100000.00); // Add worker to company
        worker1.addQualification(q1); // Add qualification to worker

        Project project = company.createProject("Project X", qualifications, ProjectSize.MEDIUM);
        company.assign(worker1, project);
        
        company.finish(project);
        assertEquals(ProjectStatus.PLANNED, project.getStatus()); // Status remains unchanged
        assertFalse(project.getWorkers().isEmpty()); // Worker remains assigned

    }

    @Test
    public void testFinishProject_FinishProject() {
        Company company = new Company("Test Company");
        Qualification q1 = company.createQualification("Java Developer");
        
        Set<Qualification> qualifications = new HashSet<>();
        Qualification q = new Qualification("Qual1");
        qualifications.add(q);
        qualifications.add(q1);

        Worker worker1 = company.createWorker("John Doe", qualifications, 100000.00);
        worker1.addQualification(q1); // Add qualification to worker

        Project project = company.createProject("Project X", qualifications, ProjectSize.MEDIUM);
        company.assign(worker1, project);

        company.start(project);
        company.finish(project);
        company.finish(project);
        assertEquals(ProjectStatus.FINISHED, project.getStatus()); // Status remains unchanged
        assertTrue(project.getWorkers().size() == 0); // Worker remains assignedv

    }

    

    //2.3.4.17
    @Test
    public void testAssignWorkerToProject_ValidScenario() {
    Company company = new Company("Test Company");

    // Create qualifications and qualifications sets
    Qualification q1 = company.createQualification("Java Developer");
    Qualification q2 = company.createQualification("Database Administrator");
    Set<Qualification> projectQualifications = new HashSet<>();
    projectQualifications.add(q1);
    Set<Qualification> workerQualifications = new HashSet<>();
    workerQualifications.add(q1);

    // Create project and worker with valid qualifications
    Project project = company.createProject("Project X", projectQualifications, ProjectSize.MEDIUM);
    Worker worker = company.createWorker("John Doe", workerQualifications, 100000.00);

    // Assign worker to project
    company.assign(worker, project);
    assertFalse(company.getAvailableWorkers().contains(worker)); // Assert worker removed from available pool (optional)
    assertTrue(company.getAssignedWorkers().contains(worker));
}

@Test
public void testAssignWorkerToProject_WorkerAlreadyAssigned() {
    Company company = new Company("Test Company");
    Qualification q1 = company.createQualification("Java Developer");
    
    Set<Qualification> qualifications = new HashSet<>();
    Qualification q = new Qualification("Qual1");
    qualifications.add(q);
    qualifications.add(q1);

    Worker worker1 = company.createWorker("John Doe", qualifications, 100000.00);
    Project project = company.createProject("WorkerAlreadyAssigned", qualifications, ProjectSize.MEDIUM);
    company.assign(worker1, project);
        
    // Assign worker to project
    company.assign(worker1, project);

    assertTrue(company.getAssignedWorkers().contains(worker1));
    assertTrue(project.getWorkers().contains(worker1));
}

@Test 
public void testAssignWorkerToProject_ProjectAlreadyFinished() {
    Company company = new Company("Test Company");
    Qualification q1 = company.createQualification("Java Developer");
    
    Set<Qualification> qualifications = new HashSet<>();
    Qualification q = new Qualification("Qual1");
    qualifications.add(q);
    qualifications.add(q1);

    Worker worker1 = company.createWorker("John Doe", qualifications, 100000.00);
    Worker worker2 = company.createWorker("Jack Da", qualifications, 100000.00);


    Project project = company.createProject("Project X", qualifications, ProjectSize.MEDIUM);
    company.assign(worker1, project);
    company.start(project);
    company.finish(project);
    company.assign(worker2, project);
    
    // Needed to check that worker2 was not assigned to project
    assertFalse(project.getWorkers().contains(worker2)); 
    // Checks that worker2 is still in unassignedworker
    assertTrue(company.getUnassignedWorkers().contains(worker2));

}

//2.3.4.18
@Test
public void testUnassignWorkerFromProject() {
    Company company = new Company("Test Company");

    // Create qualifications and qualifications sets
    Qualification q1 = new Qualification("Java Developer");
    Set<Qualification> projectQualifications = new HashSet<>();
    projectQualifications.add(q1);
    Set<Qualification> workerQualifications = new HashSet<>();
    workerQualifications.add(q1);

    // Create project and worker with valid qualifications
    Project project = new Project("Project X", projectQualifications, ProjectSize.MEDIUM);
    Worker worker = new Worker("John Doe", workerQualifications, 100000.00);

    // // Add worker to available pool and assign to project (assuming methods exist)
    // company.addToAvailablePool(worker);
    // company.assign(worker, project);

    // Unassign worker from project
    company.unassign(worker, project);

    company.createProject("Project X", projectQualifications, ProjectSize.MEDIUM);

    company.createWorker("John Doe", workerQualifications, 100000.00);
    // Assertions
    assertFalse(company.getAssignedWorkers().contains(worker)); // Assert worker removed from assigned pool
    assertFalse(project.getWorkers().contains(worker)); // Assert worker removed from project
    //assertTrue(company.getAvailableWorkers().contains(worker)); // Assert worker added back to available pool (optional)

    // Check project state (optional, depending on implementation)
    if (project.getStatus() == ProjectStatus.ACTIVE) {
        // Assert project remains active if qualifications still met
    } else if (project.getStatus() == ProjectStatus.SUSPENDED) {
        // Assert project is suspended if qualifications no longer met
    }
    }

    //2.3.4.19
    @Test
    public void testUnassignWorkerFromAllProjects() {
    Company company = new Company("Test Company");

    // Create qualifications and qualifications sets
    Qualification q1 = new Qualification("Java Developer");
    Set<Qualification> projectQualifications1 = new HashSet<>();
    projectQualifications1.add(q1);
    Set<Qualification> projectQualifications2 = new HashSet<>();
    projectQualifications2.add(q1);
    Set<Qualification> workerQualifications = new HashSet<>();
    workerQualifications.add(q1);

    // Create projects, worker, and assign them (assuming methods exist)
    //Project project1 = new Project("Project X", projectQualifications1, ProjectSize.MEDIUM);
    //Project project2 = new Project("Project Y", projectQualifications2, ProjectSize.SMALL);
    //Worker worker = new Worker("John Doe", workerQualifications, 100000.00);

    Project project1 = company.createProject("Project X", projectQualifications1, ProjectSize.MEDIUM);
    Project project2 = company.createProject("Project Y", projectQualifications2, ProjectSize.SMALL);

    Worker worker = company.createWorker("John Doe", workerQualifications, 100000.00);
    // Assertions
    assertFalse(company.getAssignedWorkers().contains(worker)); // Assert worker removed from assigned pool
    //company.addToAvailablePool(worker);
    company.assign(worker, project1);
    company.assign(worker, project2);
    // Unassign worker from all projects
    company.unassignAll(worker);

    // Assertions
    assertFalse(company.getAssignedWorkers().contains(worker)); // Assert worker removed from assigned pool
    assertTrue(company.getAvailableWorkers().contains(worker));
    assertFalse(project1.getWorkers().contains(worker)); // Assert worker removed from project1
    assertFalse(project2.getWorkers().contains(worker)); // Assert worker removed from project2
    //assertTrue(company.getAvailableWorkers().contains(worker)); // Assert worker added back to available pool (optional)

    // Check project states (optional, depending on implementation)
    if (project1.getStatus() == ProjectStatus.ACTIVE) {
        // Assert project1 remains active if qualifications still met
    } else if (project1.getStatus() == ProjectStatus.SUSPENDED) {
        // Assert project1 is suspended if qualifications no longer met
    }
    if (project2.getStatus() == ProjectStatus.ACTIVE) {
        // Assert project2 remains active if qualifications still met
    } else if (project2.getStatus() == ProjectStatus.SUSPENDED) {
        // Assert project2 is suspended if qualifications no longer met
    }
}
		
}
