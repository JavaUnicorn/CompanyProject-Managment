package edu.colostate.cs415.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import edu.colostate.cs415.dto.QualificationDTO;


import org.junit.Assert;
import org.junit.Test;

public class QualificationTest {
	
	//2.3.1.1
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorEmpty() {
		Qualification empty = new Qualification("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNull() {
		Qualification NullNull = new Qualification(null);
	}

	@Test 
	public void testConstructorCorrect(){
		Qualification teamwork = new Qualification("teamwork");
	}

	//2.3.1.2
	@Test
	public void testEqualsMethod(){
		Qualification one = new Qualification("Qual 1");
		Qualification one_1 = new Qualification("Qual 1");
		Qualification two = new Qualification("Qual 2");
		assertTrue(one.equals(one_1));
		assertTrue(!one.equals(two));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEqualsNull(){
		Qualification one = new Qualification("Qual 1");
		Qualification two = null;
		one.equals(two);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEqualsObject(){
		Qualification one = new Qualification("Qual 1");
		Integer integ = 1;
		one.equals(integ);
	}

	//2.3.1.3
	@Test
	public void testHashCode(){
		Qualification one = new Qualification("abc123");
		Qualification two = new Qualification("12345678xyz");
		String string_one = "abc123";
		String string_two= "12345678xyz";
		assertTrue(string_one.hashCode()== one.hashCode());
		assertTrue(string_two.hashCode()==two.hashCode());
	}

    @Test
	public void testToString(){
		Qualification one = new Qualification("one");
		String string = "one";
		assertTrue(one.toString()==string);
	}

	//2.3.1.5, 2.3.1.6, and 2.3.1.7
	@Test
	public void testAddGetRemoveWorker(){
		Qualification A = new Qualification("Qual A");
		Qualification B = new Qualification("Qual B");
		HashSet<Qualification> set = new HashSet<Qualification>();
		set.add(B);
		set.add(A);

		Worker Matthew = new Worker("Matthew",set,100000.00);
		Worker Mark = new Worker("Mark",set,100000.00);
		Worker Luke = new Worker("Luke",set,100000.00);
		Worker John = new Worker("John",set,100000.00);

		A.addWorker(Matthew);
		A.addWorker(Mark);
		A.addWorker(Luke);
		A.addWorker(John);

		assertTrue(A.getWorkers().contains(John));
		assertTrue(A.getWorkers().contains(Matthew));
		assertTrue(A.getWorkers().contains(Mark));
		assertTrue(A.getWorkers().contains(Luke));

		A.removeWorker(Matthew);
		A.removeWorker(Mark);
		A.removeWorker(Luke);
		A.removeWorker(John);

		assertFalse(A.getWorkers().contains(John));
		assertFalse(A.getWorkers().contains(Matthew));
		assertFalse(A.getWorkers().contains(Mark));
		assertFalse(A.getWorkers().contains(Luke));

	}
	
	//2.3.17
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveWorker(){
		Qualification A = new Qualification("Qual A");
		A.removeWorker(null);
	}

	//2.3.1.8
	@Test
	public void testToDTO(){
		Qualification one = new Qualification("one");
		HashSet<Qualification> set = new HashSet<Qualification>();
		set.add(one);

		Worker Matthew = new Worker("Matthew",set,100000.00);
		Worker Mark = new Worker("Mark",set,100000.00);
		Worker Luke = new Worker("Luke",set,100000.00);

		one.addWorker(Luke);
		one.addWorker(Mark);
		one.addWorker(Matthew);

		QualificationDTO dto = new QualificationDTO();
		
		dto.setDescription("one");
		assertTrue(dto.equals(one.toDTO()));

		dto.setDescription("length");
		assertFalse(dto.equals(one.toDTO()));

	}
	
}
