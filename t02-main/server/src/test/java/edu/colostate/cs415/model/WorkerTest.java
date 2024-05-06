package edu.colostate.cs415.model;

import org.junit.Test;

import edu.colostate.cs415.dto.WorkerDTO;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.HashSet;

public class WorkerTest {
	@Test // BASE TEST
	public void testConstructorCorrect(){
		Qualification A = new Qualification("Qual A");
		Qualification B = new Qualification("B");
		HashSet<Qualification> set = new HashSet<Qualification>();
		set.add(B);
		set.add(A);

		Worker teamPlayer = new Worker("teamwork",set,100000.00);
	}

	@Test
	public void testConstructorSingleQual() {
		Qualification A = new Qualification("Qual A");
		HashSet<Qualification> set = new HashSet<Qualification>();
		set.add(A);

		Worker singleQual = new Worker("dreamwork", set, 10000.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNoQual() {
		HashSet<Qualification> set = new HashSet<Qualification>();

		Worker singleQual = new Worker("dreamwork", set, 10000.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullQual() {
		HashSet<Qualification> set = null;

		Worker singleQual = new Worker("dreamwork", set, 10000.00);
	}

	@Test 
	public void testConstructorZeroSal(){
		Qualification A = new Qualification("Qual A");
		Qualification B = new Qualification("B");
		HashSet<Qualification> set = new HashSet<Qualification>();
		set.add(B);
		set.add(A);

		Worker teamPlayer = new Worker("teamwork",set,0.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegativeSal(){
		Qualification A = new Qualification("Qual A");
		Qualification B = new Qualification("B");
		HashSet<Qualification> set = new HashSet<Qualification>();
		set.add(B);
		set.add(A);

		Worker teamPlayer = new Worker("teamwork",set,-10000.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNoName(){
		Qualification A = new Qualification("Qual A");
		Qualification B = new Qualification("B");
		HashSet<Qualification> set = new HashSet<Qualification>();
		set.add(B);
		set.add(A);

		Worker teamPlayer = new Worker("",set,-10000.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullName(){
		Qualification A = new Qualification("Qual A");
		Qualification B = new Qualification("B");
		HashSet<Qualification> set = new HashSet<Qualification>();
		set.add(B);
		set.add(A);

		Worker teamPlayer = new Worker(null,set,-10000.00);
	}

	@Test
	public void testEqualsMethod(){
		Qualification A = new Qualification("Qual A");
		HashSet<Qualification> set = new HashSet<Qualification>();
		set.add(A);

		Worker one = new Worker("dreamwork", set, 10000.00);
		Worker one_1 = new Worker("dreamwork", set, 550.00);
		Worker two = new Worker("two", set, 10000.00);

		assertTrue(one.equals(one_1));
		assertTrue(!one.equals(two));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEqualsNull(){
		Qualification A = new Qualification("Qual A");
		HashSet<Qualification> set = new HashSet<Qualification>();
		set.add(A);

		Worker one = new Worker("dreamwork", set, 10000.00);
		Worker two = null;

		one.equals(two);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEqualsObject(){
		Qualification A = new Qualification("Qual A");
		HashSet<Qualification> set = new HashSet<Qualification>();
		set.add(A);

		Worker one = new Worker("dreamwork", set, 10000.00);

		Integer integ = 1;
		one.equals(integ);
	}

	@Test
    public void testgetProjects(){
        Qualification q = new Qualification("Qual1");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

        Worker worker = new Worker("dreamwork", Qualset, 10000.00);
        assertTrue(worker.getProjects().isEmpty());
    }

	@Test
    public void testgetProjects2(){
		ProjectSize size = ProjectSize.SMALL;
        Qualification q = new Qualification("Qual1");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

		Project p = new Project("New Project", Qualset, size);

        Worker worker = new Worker("dreamwork", Qualset, 10000.00);
		worker.addProject(p);
        assertTrue(worker.getProjects().contains(p));
    }

    @Test
    public void testaddProject(){
        ProjectSize size = ProjectSize.SMALL;
        Qualification q = new Qualification("Qual1");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

        Project p = new Project("New Project", Qualset, size);
        Worker worker = new Worker("dreamwork", Qualset, 10000.00);

        worker.addProject(p);
        assertTrue(worker.getProjects().contains(p));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testaddProjectNullCase(){
        ProjectSize size = ProjectSize.SMALL;
        Qualification q = new Qualification("Qual1");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

        Project p = null;//new Project("New Project", Qualset, size);
        Worker worker = new Worker("dreamwork", Qualset, 10000.00);

        worker.addProject(p);
        // assertTrue(worker.getProjects().contains(p));
    }

    @Test
    public void testremoveProject(){
        ProjectSize size = ProjectSize.SMALL;
        Qualification q = new Qualification("Qual1");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

        Project p = new Project("New Project", Qualset, size);
        Worker worker = new Worker("dreamwork", Qualset, 10000.00);

        worker.addProject(p);
        worker.removeProject(p);
        assertTrue(worker.getProjects().isEmpty());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testremoveProjectNullCase(){
        ProjectSize size = ProjectSize.SMALL;
        Qualification q = new Qualification("Qual1");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

        Project p = new Project("New Project", Qualset, size);
        Worker worker = new Worker("dreamwork", Qualset, 10000.00);

        worker.addProject(p);
        worker.removeProject(null);
        // assertTrue(worker.getProjects().isEmpty());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testremoveProjectNotInSetCase(){
        ProjectSize size = ProjectSize.SMALL;
        Qualification q = new Qualification("Qual1");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

        Project p = new Project("New Project", Qualset, size);
        Project p2 = new Project("Second Project", Qualset, size);
        Worker worker = new Worker("dreamwork", Qualset, 10000.00);

        worker.addProject(p);
        worker.removeProject(p2);
        // assertTrue(worker.getProjects().isEmpty());
    }

    @Test
    public void testgetSalary(){
        // ProjectSize size = ProjectSize.SMALL;
        Qualification q = new Qualification("Qual1");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

        // Project p = new Project("New Project", Qualset, size);
        Worker worker = new Worker("dreamwork", Qualset, 10000.00);

        double salary = worker.getSalary();
        assertTrue(salary == 10000.00);
    }

    @Test
    public void testsetSalary(){
        // ProjectSize size = ProjectSize.SMALL;
        Qualification q = new Qualification("Qual1");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

        // Project p = new Project("New Project", Qualset, size);
        Worker worker = new Worker("dreamwork", Qualset, 10000.00);
        worker.setSalary(15000.00);
        double salary = worker.getSalary();
        assertTrue(salary == 15000.00);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testsetSalaryBelowZero(){
        // ProjectSize size = ProjectSize.SMALL;
        Qualification q = new Qualification("Qual1");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

        // Project p = new Project("New Project", Qualset, size);
        Worker worker = new Worker("dreamwork", Qualset, 10000.00);
        worker.setSalary(-15000.00);
    }

    @Test
    public void testgetQualifications(){
        Qualification q = new Qualification("Qual1");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

        Worker worker = new Worker("dreamwork", Qualset, 10000.00);
        assertTrue(worker.getQualifications().equals(Qualset));
    }

    @Test
    public void testaddQualifications(){
        Qualification q = new Qualification("Qual1");
        Qualification q2 = new Qualification("Qual2");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

        Worker worker = new Worker("dreamwork", Qualset, 10000.00);
        worker.addQualification(q2);
        assertTrue(worker.getQualifications().contains(q2));
    }   

    @Test (expected = IllegalArgumentException.class)
    public void testaddQualificationsNullCase(){
        Qualification q = new Qualification("Qual1");
        Qualification q2 = null;
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

        Worker worker = new Worker("dreamwork", Qualset, 10000.00);
        worker.addQualification(q2);
        // assertTrue(worker.getQualifications().contains(q2));
    }

    @Test
    public void testgetName(){
        Qualification q = new Qualification("Qual1");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

        Worker worker = new Worker("dreamwork", Qualset, 10000.00);
        assertTrue(worker.getName().equals("dreamwork"));
    }

	@Test
	public void testHashCodeReplacedReturnMutation () {
        Qualification q = new Qualification("Qual1");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

        Worker worker = new Worker("dreamwork", Qualset, 10000.00);
		assertTrue (worker.hashCode () == "dreamwork".hashCode());
	}
    
    @Test
    public void testtoString(){
        Qualification q = new Qualification("Qual1");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

        Worker worker = new Worker("dreamwork", Qualset, 10000.00);
        assertTrue(worker.toString().equals("dreamwork:0:1:10000"));
    }

	@Test
    public void testWorkload(){
        ProjectSize size1 = ProjectSize.SMALL;
		ProjectSize size2 = ProjectSize.MEDIUM;
		ProjectSize size3 = ProjectSize.BIG;
		ProjectStatus status = ProjectStatus.FINISHED;
        Qualification q = new Qualification("Qual1");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

        Project p = new Project("New Project", Qualset, size1);
		Project p2 = new Project("New Project2", Qualset, size2);
		Project p3 = new Project("New Project3", Qualset, size2);
		Project p4 = new Project("New Project4", Qualset, size3);
		Project p5 = new Project("New Project5", Qualset, size3);
		p5.setStatus(status);
        Worker worker = new Worker("dreamwork", Qualset, 10000.00);

        worker.addProject(p);
		worker.addProject(p2);
		worker.addProject(p3);
		worker.addProject(p4);
		worker.addProject(p5);
        assertTrue(worker.getWorkload() == 8);
    }

	@Test
    public void testWorkloadCaseWhereNoProject(){
        ProjectSize size1 = ProjectSize.SMALL;
		ProjectSize size2 = ProjectSize.MEDIUM;
		ProjectSize size3 = ProjectSize.BIG;
		ProjectStatus status = ProjectStatus.FINISHED;
        Qualification q = new Qualification("Qual1");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);
        Worker worker = new Worker("dreamwork", Qualset, 10000.00);
        assertTrue(worker.getWorkload() == 0);
    }

	@Test
    public void testWorkload2(){
        ProjectSize size1 = ProjectSize.SMALL;
		ProjectSize size2 = ProjectSize.MEDIUM;
		ProjectSize size3 = ProjectSize.BIG;
		ProjectStatus status = ProjectStatus.FINISHED;
        Qualification q = new Qualification("Qual1");
        HashSet<Qualification> Qualset = new HashSet<Qualification>();
        Qualset.add(q);

        Project p = new Project("New Project", Qualset, size1);
		Project p2 = new Project("New Project2", Qualset, size2);
		Project p3 = new Project("New Project3", Qualset, size2);
		Project p4 = new Project("New Project4", Qualset, size3);
		Project p5 = new Project("New Project5", Qualset, size3);
		p2.setStatus(status);
		p5.setStatus(status);
        Worker worker = new Worker("dreamwork", Qualset, 10000.00);

        worker.addProject(p);
		worker.addProject(p2);
		worker.addProject(p3);
		worker.addProject(p4);
		worker.addProject(p5);
        assertTrue(worker.getWorkload() == 6);
    }
  
  	//2.3.2.14 Worker.willOverload()

	@Test
	public void testWillOverloadBase () {
		HashSet<Qualification> set = new HashSet<Qualification>();
		Qualification A = new Qualification("Qual A");
		set.add(A);
		Worker worker = new Worker ("example", set, 40000.0);
		Project p = new Project ("example", set, ProjectSize.MEDIUM);
		worker.addProject (new Project ("example0", set, ProjectSize.BIG));
		worker.addProject (new Project ("example1", set, ProjectSize.BIG));
		worker.addProject (new Project ("example2", set, ProjectSize.BIG));
		worker.addProject (new Project ("example3", set, ProjectSize.MEDIUM));
		assertTrue (worker.willOverload (p));
	}
	
	@Test
	public void testWillOverloadSmall () {
		HashSet<Qualification> set = new HashSet<Qualification>();
		Qualification A = new Qualification("Qual A");
		set.add(A);
		Worker worker = new Worker ("example", set, 40000.0);
		Project p = new Project ("example", set, ProjectSize.SMALL);
		worker.addProject (new Project ("example0", set, ProjectSize.BIG));
		worker.addProject (new Project ("example1", set, ProjectSize.BIG));
		worker.addProject (new Project ("example2", set, ProjectSize.BIG));
		worker.addProject (new Project ("example3", set, ProjectSize.MEDIUM));
		assertFalse (worker.willOverload (p));
	}
	
	@Test
	public void testWillOverloadBig () {
		HashSet<Qualification> set = new HashSet<Qualification>();
		Qualification A = new Qualification("Qual A");
		set.add(A);
		Worker worker = new Worker ("example", set, 40000.0);
		Project p = new Project ("example", set, ProjectSize.BIG);
		worker.addProject (new Project ("example0", set, ProjectSize.BIG));
		worker.addProject (new Project ("example1", set, ProjectSize.BIG));
		worker.addProject (new Project ("example2", set, ProjectSize.BIG));
		worker.addProject (new Project ("example3", set, ProjectSize.MEDIUM));
		assertTrue (worker.willOverload (p));
	}

	@Test
	public void testWillOverloadAlreadyPartOfProject () {
		HashSet<Qualification> set = new HashSet<Qualification>();
		Qualification A = new Qualification("Qual A");
		set.add(A);
		Worker worker = new Worker ("example", set, 40000.0);
		Project p = new Project ("example", set, ProjectSize.MEDIUM);
		worker.addProject (new Project ("example0", set, ProjectSize.BIG));
		worker.addProject (new Project ("example1", set, ProjectSize.BIG));
		worker.addProject (new Project ("example2", set, ProjectSize.BIG));
		worker.addProject (p);
		assertTrue (worker.willOverload (p));
	}
	
	@Test
	public void testWillOverloadTwelve () {
		HashSet<Qualification> set = new HashSet<Qualification>();
		Qualification A = new Qualification("Qual A");
		set.add(A);
		Worker worker = new Worker ("example", set, 40000.0);
		Project p = new Project ("example", set, ProjectSize.MEDIUM);
		worker.addProject (new Project ("example0", set, ProjectSize.BIG));
		worker.addProject (new Project ("example1", set, ProjectSize.BIG));
		worker.addProject (new Project ("example2", set, ProjectSize.BIG));
		worker.addProject (new Project ("example3", set, ProjectSize.BIG));
		assertTrue (worker.willOverload (p));
	}
	
	@Test
	public void testWillOverloadTen () {
		HashSet<Qualification> set = new HashSet<Qualification>();
		Qualification A = new Qualification("Qual A");
		set.add(A);
		Worker worker = new Worker ("example", set, 40000.0);
		Project p = new Project ("example", set, ProjectSize.MEDIUM);
		worker.addProject (new Project ("example0", set, ProjectSize.BIG));
		worker.addProject (new Project ("example1", set, ProjectSize.BIG));
		worker.addProject (new Project ("example2", set, ProjectSize.BIG));
		worker.addProject (new Project ("example3", set, ProjectSize.SMALL));
		assertFalse (worker.willOverload (p));
	}
	
	@Test
	public void testWillOverloadNine () {
		HashSet<Qualification> set = new HashSet<Qualification>();
		Qualification A = new Qualification("Qual A");
		set.add(A);
		Worker worker = new Worker ("example", set, 40000.0);
		Project p = new Project ("example", set, ProjectSize.MEDIUM);
		worker.addProject (new Project ("example0", set, ProjectSize.BIG));
		worker.addProject (new Project ("example1", set, ProjectSize.BIG));
		worker.addProject (new Project ("example2", set, ProjectSize.SMALL));
		worker.addProject (new Project ("example3", set, ProjectSize.MEDIUM));
		assertFalse (worker.willOverload (p));
	}
  
	//2.3.2.15 Worker.isAvailable ()
	@Test
	public void testIsAvailableBase () {
		HashSet<Qualification> set = new HashSet<Qualification>();
		Qualification A = new Qualification("Qual A");
		set.add(A);
		Worker worker = new Worker ("example", set, 40000.0);
		worker.addProject (new Project ("example0", set, ProjectSize.BIG));
		worker.addProject (new Project ("example1", set, ProjectSize.BIG));
		worker.addProject (new Project ("example2", set, ProjectSize.BIG));
		worker.addProject (new Project ("example3", set, ProjectSize.MEDIUM));
		assertTrue (worker.isAvailable ());
	}

	@Test
	public void testIsAvailableTwelve () {
		HashSet<Qualification> set = new HashSet<Qualification>();
		Qualification A = new Qualification("Qual A");
		set.add(A);
		Worker worker = new Worker ("example", set, 40000.0);
		worker.addProject (new Project ("example0", set, ProjectSize.BIG));
		worker.addProject (new Project ("example1", set, ProjectSize.BIG));
		worker.addProject (new Project ("example2", set, ProjectSize.BIG));
		worker.addProject (new Project ("example3", set, ProjectSize.BIG));
		assertFalse (worker.isAvailable ());
	}
	
	@Test
	public void testIsAvailableGreaterThanTwelve () {
		HashSet<Qualification> set = new HashSet<Qualification>();
		Qualification A = new Qualification("Qual A");
		set.add(A);
		Worker worker = new Worker ("example", set, 40000.0);
		worker.addProject (new Project ("example0", set, ProjectSize.BIG));
		worker.addProject (new Project ("example1", set, ProjectSize.BIG));
		worker.addProject (new Project ("example2", set, ProjectSize.BIG));
		worker.addProject (new Project ("example3", set, ProjectSize.BIG));
		worker.addProject (new Project ("example4", set, ProjectSize.SMALL));
		assertFalse (worker.isAvailable ());
	}
  
  	//2.3.2.16 Worker.toDTO ()
	@Test
	public void testToDtoName () {
		HashSet<Qualification> set = new HashSet<Qualification>();
		Qualification A = new Qualification("Qual A");
		set.add(A);
		Worker worker = new Worker ("example", set, 40000.0);
		worker.addProject (new Project ("example0", set, ProjectSize.BIG));
		worker.addProject (new Project ("example1", set, ProjectSize.SMALL));
		WorkerDTO dto = worker.toDTO ();
		assertTrue (dto.getName ().equals ("example"));
	}

	@Test
	public void testToDtoSalary () {
		HashSet<Qualification> set = new HashSet<Qualification>();
		Qualification A = new Qualification("Qual A");
		set.add(A);
		Worker worker = new Worker ("example", set, 40000.0);
		worker.addProject (new Project ("example0", set, ProjectSize.BIG));
		worker.addProject (new Project ("example1", set, ProjectSize.SMALL));
		WorkerDTO dto = worker.toDTO ();
		assertTrue (dto.getSalary () == 40000.0);
	}

	@Test
	public void testToDtoWorkload () {
		HashSet<Qualification> set = new HashSet<Qualification>();
		Qualification A = new Qualification("Qual A");
		set.add(A);
		Worker worker = new Worker ("example", set, 40000.0);
		worker.addProject (new Project ("example0", set, ProjectSize.BIG));
		worker.addProject (new Project ("example1", set, ProjectSize.SMALL));
		WorkerDTO dto = worker.toDTO ();
		assertTrue (dto.getWorkload () == 4);
	}

	@Test
	public void testToDtoProjects () {
		HashSet<Qualification> set = new HashSet<Qualification>();
		Qualification A = new Qualification("Qual A");
		set.add(A);
		Worker worker = new Worker ("example", set, 40000.0);
		worker.addProject (new Project ("example0", set, ProjectSize.BIG));
		worker.addProject (new Project ("example1", set, ProjectSize.SMALL));
		WorkerDTO dto = worker.toDTO ();
		String [] strings0 = {"example0", "example1"};
		String [] strings1 = {"example1", "example0"};
		String [] gottenProjects = dto.getProjects ();
		assertTrue (Arrays.equals (gottenProjects, strings0) || Arrays.equals (gottenProjects, strings1));
	}

	@Test
	public void testToDtoWualifications () {
		HashSet<Qualification> set = new HashSet<Qualification>();
		Qualification A = new Qualification("Qual A");
		set.add(A);
		Worker worker = new Worker ("example", set, 40000.0);
		worker.addProject (new Project ("example0", set, ProjectSize.BIG));
		worker.addProject (new Project ("example1", set, ProjectSize.SMALL));
		WorkerDTO dto = worker.toDTO ();
		String [] strings = {"Qual A"};
		assertTrue (Arrays.equals (dto.getQualifications (), strings));
	}
}
