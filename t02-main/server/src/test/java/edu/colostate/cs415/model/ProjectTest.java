package edu.colostate.cs415.model;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.colostate.cs415.dto.ProjectDTO;

import java.util.Collections;
import java.util.HashSet;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ProjectTest {
	//Global Project sets

	static Project largeproject_One;
	static Project largeproject_Two;
	static Project largeproject_Three;
	static Project largeproject_Four;

	//Global Qualification sets

	static HashSet <Qualification> basicQualifications;
	static HashSet <Qualification> someQualifications;
	static HashSet <Qualification> decenQualifications;
	static HashSet <Qualification> emptyQualifications;


	@BeforeClass
	public static void setup(){
	basicQualifications = new HashSet <> ();
	basicQualifications.add (new Qualification ("Angular"));
	basicQualifications.add(new Qualification ("Cyber Security"));
	basicQualifications.add (new Qualification ("Java"));
	basicQualifications.add (new Qualification ("JavaScript"));
	basicQualifications.add (new Qualification ("MongoDB"));
	basicQualifications.add (new Qualification ("Python"));
	basicQualifications.add (new Qualification ("React"));
	basicQualifications.add (new Qualification ("Spark"));
	basicQualifications.add (new Qualification ("Spring"));
	basicQualifications.add (new Qualification ("Sql"));
	basicQualifications.add (new Qualification ("Tensorflow"));
	basicQualifications.add (new Qualification ("TypeScript"));

	someQualifications = new HashSet <> ();
	someQualifications.add (new Qualification ("Angular"));
	someQualifications.add(new Qualification ("Cyber Security"));
	someQualifications.add (new Qualification ("Java"));
	someQualifications.add (new Qualification ("JavaScript"));
	someQualifications.add (new Qualification ("MongoDB"));
	someQualifications.add (new Qualification ("Python"));

	decenQualifications = new HashSet <> ();
	decenQualifications.add (new Qualification ("JavaScript"));
	decenQualifications.add (new Qualification ("MongoDB"));
	decenQualifications.add (new Qualification ("Python"));

	emptyQualifications = new HashSet <> ();


	largeproject_One = new Project ("Testifying", basicQualifications, ProjectSize.BIG);
	largeproject_Two = new Project ("Homelessness", someQualifications, ProjectSize.BIG);
	largeproject_Three = new Project ("Project STEMM", basicQualifications, ProjectSize.BIG);
	largeproject_Four = new Project ("Applications", decenQualifications, ProjectSize.BIG);


	}
	
	//2.3.3.1

	@Test // Base test.
	public void testConstructorBase () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("Project Seven Staplers", qualificationSet, ProjectSize.MEDIUM);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorNullName () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project (null, qualificationSet, ProjectSize.MEDIUM);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorWhitespaceName() {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project("    ", qualificationSet, ProjectSize.MEDIUM);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorNullQualifications () {
		Project testProject = new Project ("Project Seven Staplers", null, ProjectSize.MEDIUM);
	}

	@Test
	public void testConstructorNoQualifications () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		Project testProject = new Project ("Project Seven Staplers", qualificationSet, ProjectSize.MEDIUM);
	}

	@Test
	public void testConstructorManyQualifications () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		qualificationSet.add (new Qualification ("Cyber Security"));
		qualificationSet.add (new Qualification ("Java"));
		qualificationSet.add (new Qualification ("JavaScript"));
		qualificationSet.add (new Qualification ("MongoDB"));
		qualificationSet.add (new Qualification ("Python"));
		qualificationSet.add (new Qualification ("React"));
		qualificationSet.add (new Qualification ("Spark"));
		qualificationSet.add (new Qualification ("Spring"));
		qualificationSet.add (new Qualification ("Sql"));
		qualificationSet.add (new Qualification ("Tensorflow"));
		qualificationSet.add (new Qualification ("TypeScript"));
		Project testProject = new Project ("Project Seven Staplers", qualificationSet, ProjectSize.MEDIUM);
	}

	@Test
	public void testConstructorSmall () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("Project Seven Staplers", qualificationSet, ProjectSize.SMALL);
	}

	@Test
	public void testConstructorBig () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("Project Seven Staplers", qualificationSet, ProjectSize.BIG);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorNullSize () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("Project Seven Staplers", qualificationSet, null);
	}

	@Test
	public void testEqualsBase () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		Project testProject = new Project ("Project Seven Staplers", qualificationSet, ProjectSize.MEDIUM);
		Project equalProject = new Project ("Project Seven Staplers", qualificationSet, ProjectSize.MEDIUM);
		assertTrue (testProject.equals (equalProject));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testEqualsNullProject () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		Project testProject = new Project ("Project Seven Staplers", qualificationSet, ProjectSize.MEDIUM);
		Project nullProject = null;
		testProject.equals (nullProject);
	}

	@Test
	public void testEqualsDifferentName () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		Project testProject = new Project ("Project Seven Staplers", qualificationSet, ProjectSize.MEDIUM);
		Project equalProject = new Project ("Project Backup Meeting", qualificationSet, ProjectSize.MEDIUM);
		assertFalse (testProject.equals (equalProject));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testEqualsDifferentType () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		Project testProject = new Project ("Project Seven Staplers", qualificationSet, ProjectSize.MEDIUM);
		testProject.equals("Project");
	}

	//2.3.3.3 Project.hashCode()
	@Test
	public void testHashCodeBase () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("Project Money Structure", qualificationSet, ProjectSize.MEDIUM);
		String string = "Project Money Structure";
		assertTrue (testProject.hashCode () == string.hashCode ());
	}

	@Test (expected = IllegalArgumentException.class)
	public void testHashCodeNameLengthZero () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("", qualificationSet, ProjectSize.MEDIUM);
		String string = "";
		assertTrue (testProject.hashCode () == string.hashCode ());
	}
  
	//2.3.3.4 Project.toString()

	@Test
	public void testToStringBase () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("Project Ultimate Value, Maybe", qualificationSet, ProjectSize.MEDIUM);
		Worker jeff = new Worker ("Jeff", qualificationSet, 40.0);
		testProject.addWorker (jeff);
		String string = testProject.toString ();
		assertTrue (string.equals ("Project Ultimate Value, Maybe:1:PLANNED"));
	}

	@Test
	public void testToStringZeroWorkers () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("Project Ultimate Value, Maybe", qualificationSet, ProjectSize.MEDIUM);
		String string = testProject.toString ();
		assertTrue (string.equals ("Project Ultimate Value, Maybe:0:PLANNED"));
	}

	@Test
	public void testToStringSuspended () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("Project Ultimate Value, Maybe", qualificationSet, ProjectSize.MEDIUM);
		Worker jeff = new Worker ("Jeff", qualificationSet, 40.0);
		testProject.addWorker (jeff);
		testProject.setStatus (ProjectStatus.SUSPENDED);
		String string = testProject.toString ();
		assertTrue (string.equals ("Project Ultimate Value, Maybe:1:SUSPENDED"));
	}

	@Test
	public void testToStringActive () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("Project Ultimate Value, Maybe", qualificationSet, ProjectSize.MEDIUM);
		Worker jeff = new Worker ("Jeff", qualificationSet, 40.0);
		testProject.addWorker (jeff);
		testProject.setStatus (ProjectStatus.ACTIVE);
		String string = testProject.toString ();
		assertTrue (string.equals ("Project Ultimate Value, Maybe:1:ACTIVE"));
	}

	@Test
	public void testToStringFinished () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("Project Ultimate Value, Maybe", qualificationSet, ProjectSize.MEDIUM);
		Worker jeff = new Worker ("Jeff", qualificationSet, 40.0);
		testProject.addWorker (jeff);
		testProject.setStatus (ProjectStatus.FINISHED);
		String string = testProject.toString ();
		assertTrue (string.equals ("Project Ultimate Value, Maybe:1:FINISHED"));
	}

	@Test
	public void testToStringNullStatus () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("Project Ultimate Value, Maybe", qualificationSet, ProjectSize.MEDIUM);
		Worker jeff = new Worker ("Jeff", qualificationSet, 40.0);
		testProject.addWorker (jeff);
		String string = testProject.toString ();
		assertTrue (string.equals ("Project Ultimate Value, Maybe:1:PLANNED"));
	}

	//2.3.3.5 Project.getName ()
	@Test
	public void testGetNameBase () {
  	HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("Project Seven Staplers", qualificationSet, ProjectSize.MEDIUM);
		assertTrue (testProject.getName ().equals ("Project Seven Staplers"));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testGetNameZeroCharacters () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("", qualificationSet, ProjectSize.MEDIUM);
		assertTrue (testProject.getName().equals (""));
	}
  
	@Test
	public void testGetNameWithQuotationMarks () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
	 	Project testProject = new Project ("A\"B", qualificationSet, ProjectSize.MEDIUM);
		assertTrue (testProject.getName ().equals ("A\"B"));
	}
  
	//2.3.3.6 Project.getSize ()
	@Test
	public void testGetSizeBase () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("Project Seven Staplers", qualificationSet, ProjectSize.MEDIUM);
		assertTrue (testProject.getSize ().equals (ProjectSize.MEDIUM));
	}

	@Test
	public void testGetSizeSmall () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("Project Seven Staplers", qualificationSet, ProjectSize.SMALL);
		assertTrue (testProject.getSize ().equals (ProjectSize.SMALL));
	}
  
	@Test
	public void testGetSizeBig () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("Project Seven Staplers", qualificationSet, ProjectSize.BIG);
		assertTrue (testProject.getSize ().equals (ProjectSize.BIG));
	}

	//2.3.3.7 Project.getStatus ()
	@Test
	public void testGetStatusBase () {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Angular"));
		Project testProject = new Project ("Project Seven Staplers", qualificationSet, ProjectSize.MEDIUM);
		assertTrue (testProject.getStatus () == ProjectStatus.PLANNED);
  	}

  	//2.3.3.8 Project.setStatus()
  	@Test
	public void testSetStatus(){
		HashSet <Qualification> set_of_qualifications = new HashSet<>();
		set_of_qualifications.add(new Qualification ("Accounting"));
		set_of_qualifications.add(new Qualification ("Soctware Development"));
		set_of_qualifications.add(new Qualification ("Scrum"));
		Project testProjectSize = new Project("AccountingProgram", set_of_qualifications,  ProjectSize.MEDIUM);
		testProjectSize.setStatus(ProjectStatus.ACTIVE);
		assertTrue(testProjectSize.getStatus().equals(ProjectStatus.ACTIVE));
	}

    //2.3.3.9 and 2.3.3.11 Project.AddWorker() and Project.getWorkers()

	@Test //(expected = IllegalArgumentException.class)
	public void testAddWorker_BaseCase() {

		HashSet <Qualification> qualifictionSet = new HashSet <> ();
		qualifictionSet.add (new Qualification ("Python"));
		qualifictionSet.add (new Qualification ("React"));
		qualifictionSet.add (new Qualification ("Spark"));
		qualifictionSet.add (new Qualification ("Spring"));

		HashSet <Qualification> workerQualifictionSet = new HashSet <> ();
		workerQualifictionSet.add (new Qualification ("Python"));
		workerQualifictionSet.add (new Qualification ("Spring"));

		Project testProject = new Project ("Project TestSubjects", qualifictionSet, ProjectSize.SMALL);
		Worker Jack = new Worker("Jack John", workerQualifictionSet, 30.45);

		testProject.addWorker(Jack);
		assertTrue(testProject.getWorkers().contains(Jack));
	}

	
	@Test 
	public void testAddWorker_NoMatchQualifications() {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Python"));
		qualificationSet.add (new Qualification ("React"));
		qualificationSet.add (new Qualification ("Spark"));
		qualificationSet.add (new Qualification ("Spring"));
		HashSet <Qualification> workerqualificationSet = new HashSet <> ();
		workerqualificationSet.add (new Qualification ("Student"));
		workerqualificationSet.add (new Qualification ("Docker"));
		Project testProject = new Project ("Project TestSubjects", qualificationSet, ProjectSize.SMALL);
		Worker Jack = new Worker("Jack John", workerqualificationSet, 30.45);
		testProject.addWorker(Jack);
		assertFalse(testProject.getWorkers().contains(Jack));
	}

	@Test 
	public void testAddWorker_NoMatch() {
		HashSet <Qualification> qualificationSet = new HashSet <> ();
		qualificationSet.add (new Qualification ("Python"));
		qualificationSet.add (new Qualification ("React"));
		qualificationSet.add (new Qualification ("Spark"));
		qualificationSet.add (new Qualification ("Spring"));
		HashSet <Qualification> workerqualificationSet = new HashSet <> ();
		workerqualificationSet.add (new Qualification ("Student"));
		workerqualificationSet.add (new Qualification ("Docker"));
		Project testProject = new Project ("Project TestSubjects", qualificationSet, ProjectSize.SMALL);
		Worker Jack = new Worker("Jack John", qualificationSet, 30.45);
		Worker Jones = new Worker("John Jones", qualificationSet, 30.45);
		testProject.addWorker(Jack);
		assertFalse(testProject.getWorkers().contains(Jones));
	}

	// @Test (expected = IllegalArgumentException.class)
	// public void testAddWorker_Overworked() {
	// 	Worker Jack = new Worker("Jack John", decenQualifications, 30.45);
	// 	largeproject_One.addWorker(Jack);
	// 	largeproject_Two.addWorker(Jack);
	// 	largeproject_Three.addWorker(Jack);
	// 	largeproject_Four.addWorker(Jack);
	// 	Project testProjectOverworked = new Project("AccountingProgram", decenQualifications,  ProjectSize.MEDIUM);
	// 	assertTrue(testProjectOverworked.getWorkers().isEmpty());
	// }

	@Test 
	public void testAddWorker_NoWorkers() {
		Project testProjectOverworked = new Project("AccountingProgram", decenQualifications,  ProjectSize.MEDIUM);
		assertTrue(testProjectOverworked.getWorkers().isEmpty());
	}
	
	//2.3.3.10 Project.RemoveWorker
	@Test 
	public void testRemoveWorker_BaseCase() {
		Project testProject = new Project ("Project TestSubjects", decenQualifications, ProjectSize.SMALL);
		Worker Jack = new Worker("Jack John", decenQualifications, 30.45);
		testProject.addWorker(Jack);
		testProject.removeWorker(Jack);
		assertTrue(testProject.getWorkers().isEmpty());

	}

	//2.3.3.13 Project.Set<Qualification> getRequiredQualifications()
	@Test
	public void testQualifications(){
		HashSet <Qualification> set_of_qualifications = new HashSet<>();
		set_of_qualifications.add(new Qualification ("Accounting"));
		set_of_qualifications.add(new Qualification ("Soctware Development"));
		set_of_qualifications.add(new Qualification ("Scrum"));
		Project testProjectSize = new Project("AccountingProgram", set_of_qualifications,  ProjectSize.MEDIUM);
		assertTrue(testProjectSize.getRequiredQualifications().equals(set_of_qualifications));
	}
	@Test
	public void testGetReuqiredQualificationsChangedConditionalBoundaryMutation () {
		HashSet <Qualification> set_of_qualifications = new HashSet<>();
		set_of_qualifications.add(new Qualification ("Accounting"));
		Project testProjectSize = new Project("AccountingProgram", set_of_qualifications,  ProjectSize.MEDIUM);
		assertTrue(testProjectSize.getRequiredQualifications().equals(set_of_qualifications));
	}

	@Test
	public void testEmptyQualifications(){
		HashSet <Qualification> set_of_qualifications = new HashSet<>();
		Project testProjectSize = new Project("AccountingProgram", set_of_qualifications,  ProjectSize.MEDIUM);
		assertTrue(testProjectSize.getRequiredQualifications().equals(set_of_qualifications));
	}




	@Test (expected = IllegalArgumentException.class)
	public void testAddQualification_Duplicate(){
		HashSet <Qualification> set_of_qualifications = new HashSet<>();
		set_of_qualifications.add(new Qualification ("Accounting"));
		set_of_qualifications.add(new Qualification ("Soctware Development"));
		set_of_qualifications.add(new Qualification ("Scrum"));
		Project testProjectSize = new Project("AccountingProgram", set_of_qualifications,  ProjectSize.MEDIUM);
		Qualification Scrum = new Qualification("Scrum");
		testProjectSize.addQualification(Scrum);
		testProjectSize.getRequiredQualifications().contains(Scrum);
	}
	
	//2.3.3.14 Project.addQualification(Qualification qualification)
	@Test
	public void testAddQualification_BaseTest(){
		HashSet <Qualification> set_of_qualifications = new HashSet<>();
		set_of_qualifications.add(new Qualification ("Accounting"));
		set_of_qualifications.add(new Qualification ("Soctware Development"));
		set_of_qualifications.add(new Qualification ("Scrum"));
		Project testProjectSize = new Project("AccountingProgram", set_of_qualifications,  ProjectSize.MEDIUM);
		Qualification Student = new Qualification("Student");
		testProjectSize.addQualification(Student);
		assertTrue(testProjectSize.getRequiredQualifications().contains(Student));
	}

	//2.3.3.10 RemoveAllWorkers()
	@Test //(expected = IllegalArgumentException.class)
	public void testRemoveWorkersSet_BaseCase(){
		Project testProjectSize = new Project("AccountingProgram", decenQualifications,  ProjectSize.MEDIUM);
		Worker John = new Worker("John", decenQualifications, 2030);
		Worker Jack = new Worker("Jack", decenQualifications, 2030);
		Worker Jonah = new Worker("Jonah", decenQualifications, 2030);
		Worker April = new Worker("April", decenQualifications, 2030);

		testProjectSize.addWorker(April);
		testProjectSize.addWorker(Jonah);
		testProjectSize.addWorker(Jack);
		testProjectSize.addWorker(John);

		testProjectSize.removeAllWorkers();
		assertTrue(testProjectSize.getWorkers().isEmpty());
	}

	@Test 
	public void testRemoveWorkersSet_EmptyCase(){
		Project testProjectSize = new Project("AccountingProgram", decenQualifications,  ProjectSize.MEDIUM);

		testProjectSize.removeAllWorkers();
		assertTrue(testProjectSize.getWorkers().isEmpty());
	}

 //2.3.3.14 Project.Set<Qualification> getMissingQualifications()
	 @Test
	 public void testMissingQual_BaseTest(){
		 HashSet <Qualification> set_of_qualifications = new HashSet<>();
		 set_of_qualifications.add(new Qualification ("Accounting"));
		 set_of_qualifications.add(new Qualification ("Soctware Development"));
		 set_of_qualifications.add(new Qualification ("Scrum"));
		 Project testProjectSize = new Project("AccountingProgram", set_of_qualifications,  ProjectSize.MEDIUM);
		 Worker Jack = new Worker("Jack", set_of_qualifications, 12340);
		 Worker John = new Worker("John", set_of_qualifications, 12340);
		 testProjectSize.addWorker(Jack);
		 testProjectSize.addWorker(John);
		 assertTrue(testProjectSize.getMissingQualifications().equals(Collections.emptySet()));
	 }
	 @Test
	 public void testMissingQual_MissingOne(){
		 HashSet <Qualification> qualifications = new HashSet<>();
		 qualifications.add(new Qualification ("Accounting"));
		 qualifications.add(new Qualification ("Soctware Development"));
		 qualifications.add(new Qualification ("Scrum"));
		 qualifications.add(new Qualification ("Student"));
		 Qualification student = new Qualification ("Student");
		 Project testProjectSize = new Project("AccountingProgram", qualifications,  ProjectSize.MEDIUM);
		 HashSet <Qualification> set_of_qualifications = new HashSet<>();
		 set_of_qualifications.add(new Qualification ("Accounting"));
		 set_of_qualifications.add(new Qualification ("Soctware Development"));
		 set_of_qualifications.add(new Qualification ("Scrum"));
		 Worker Jack = new Worker("Jack", set_of_qualifications, 2340);
		 Worker John = new Worker("John", set_of_qualifications, 12340);
		 testProjectSize.addWorker(Jack);
		 testProjectSize.addWorker(John);
		 assertTrue(testProjectSize.getMissingQualifications().contains(student));
	 }
	 @Test
	 public void testMissingQual_MissingTwo(){
		 HashSet <Qualification> qualifications = new HashSet<>();
		 qualifications.add(new Qualification ("Accounting"));
		 qualifications.add(new Qualification ("Soctware Development"));
		 qualifications.add(new Qualification ("Scrum"));
		 qualifications.add(new Qualification ("Student"));

		 Qualification student = new Qualification ("Student");
		 Qualification software_Development = new Qualification ("Soctware Development");
		 HashSet <Qualification> mssingHashSet = new HashSet<>();
		 mssingHashSet.add(software_Development);
		 mssingHashSet.add(student);

		 Project testProjectSize = new Project("AccountingProgram", qualifications,  ProjectSize.MEDIUM);
		 HashSet <Qualification> set_of_qualifications = new HashSet<>();
		 set_of_qualifications.add(new Qualification ("Accounting"));
		 set_of_qualifications.add(new Qualification ("Scrum"));
		 Worker Jack = new Worker("Jack", set_of_qualifications, 2340);
		 Worker John = new Worker("John", set_of_qualifications, 12340);
		 testProjectSize.addWorker(Jack);
		 testProjectSize.addWorker(John);
		 assertTrue(testProjectSize.getMissingQualifications().equals(mssingHashSet));
	 }


	 //2.3.3.16 Project.isHelpful()
	 @Test
	 public void testisHelpful_BaseTest(){
		HashSet <Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification ("Accounting"));
		qualifications.add(new Qualification ("Soctware Development"));
		qualifications.add(new Qualification ("Scrum"));
		qualifications.add(new Qualification ("Student"));

		Qualification student = new Qualification ("Student");
		Qualification software_Development = new Qualification ("Soctware Development");
		HashSet <Qualification> mssingHashSet = new HashSet<>();
		mssingHashSet.add(software_Development);
		mssingHashSet.add(student);

		Project testProjectSize = new Project("AccountingProgram", qualifications,  ProjectSize.MEDIUM);

		HashSet <Qualification> set_of_qualifications = new HashSet<>();
		set_of_qualifications.add(new Qualification ("Accounting"));
		set_of_qualifications.add(new Qualification ("Scrum"));
		Worker Jack = new Worker("Jack", set_of_qualifications, 2340);
		Worker John = new Worker("John", set_of_qualifications, 12340);

		testProjectSize.addWorker(Jack);
		testProjectSize.addWorker(John);
		
		Worker Lola  = new Worker("Lola", mssingHashSet, 12340);

		 assertTrue(testProjectSize.isHelpful(Lola));
	 }

	 @Test
	 public void testisHelpful_NotHelpful(){
		HashSet <Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification ("Accounting"));
		qualifications.add(new Qualification ("Soctware Development"));
		qualifications.add(new Qualification ("Scrum"));
		qualifications.add(new Qualification ("Student"));


		Project testProjectSize = new Project("AccountingProgram", qualifications,  ProjectSize.MEDIUM);

		HashSet <Qualification> set_of_qualifications = new HashSet<>();
		set_of_qualifications.add(new Qualification ("Accounting"));
		set_of_qualifications.add(new Qualification ("Scrum"));
		Worker Jack = new Worker("Jack", set_of_qualifications, 2340);
		Worker John = new Worker("John", set_of_qualifications, 12340);

		testProjectSize.addWorker(Jack);
		testProjectSize.addWorker(John);
		
		Worker Lola  = new Worker("Lola", set_of_qualifications, 12340);

		 assertFalse(testProjectSize.isHelpful(Lola));
	 }


	 	//2.3.3.18
	@Test
	public void testToDTO(){
		HashSet <Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification ("Accounting"));
		qualifications.add(new Qualification ("Soctware Development"));
		qualifications.add(new Qualification ("Scrum"));
		qualifications.add(new Qualification ("Student"));

		Project testProjectSize = new Project("AccountingProgram", qualifications,  ProjectSize.MEDIUM);

		HashSet <Qualification> set_of_qualifications = new HashSet<>();
		set_of_qualifications.add(new Qualification ("Accounting"));
		set_of_qualifications.add(new Qualification ("Scrum"));
		Worker Jack = new Worker("Jack", set_of_qualifications, 2340);
		Worker John = new Worker("John", set_of_qualifications, 12340);

		testProjectSize.addWorker(Jack);
		testProjectSize.addWorker(John);

		ProjectDTO results = testProjectSize.toDTO();
		String Tname = results.getName();
		assertTrue(testProjectSize.getName().equals(Tname));

		ProjectSize sizeT = results.getSize();
		assertTrue(testProjectSize.getSize().equals(sizeT));

		ProjectStatus statusT = results.getStatus();
		assertTrue(testProjectSize.getStatus().equals(statusT));
	}

}
