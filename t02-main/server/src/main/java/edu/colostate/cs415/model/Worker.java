package edu.colostate.cs415.model;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

import edu.colostate.cs415.dto.WorkerDTO;

public class Worker {

	public static final int MAX_WORKLOAD = 12;

	private String name;
	private double salary;
	private Set<Project> projects;
	private Set<Qualification> qualifications;

	public Worker(String name, Set<Qualification> qualifications, double salary) {
		if (name == null) {
			throw new IllegalArgumentException("INVALID WORKER CONSTRUCTION ATTEMPT");
		} else if(name.equals ("") || qualifications == null|| qualifications.size()==0 || salary <0){
			throw new IllegalArgumentException("INVALID WORKER CONSTRUCTION ATTEMPT");
		} else{
			this.name = name;
			this.qualifications = new HashSet<>();
			Iterator <Qualification> qualificationsIterator = qualifications.iterator();
			while (qualificationsIterator.hasNext()) {
				addQualification (qualificationsIterator.next());
			}
			this.salary = salary;
			this.projects = new HashSet<>();
		}
	}

	@Override
	public boolean equals(Object other) {
		if(other ==null || !(other instanceof Worker)){
			throw new IllegalArgumentException("CAN NOT COMPARE A NON WORKER OBJECT TO A WORKER OBJECT");
		}else{
			if (((Worker)other).name.equals (this.name)){
				return true;
			}
			return false;
		}
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public String toString() {
		return this.name + ":" + String.valueOf(projects.size()) + ":" + String.valueOf(qualifications.size()) + ":" + String.valueOf((int)salary);
	}

	public String getName() {
		return this.name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		if(salary < 0){
			throw new IllegalArgumentException("the salary is a negative number, invalid salary.");
		}
		else{
			this.salary = salary;
		}
	}

	public Set<Qualification> getQualifications() {
		return new HashSet<Qualification>(this.qualifications);
	}

	public void addQualification(Qualification qualification) {
		if (qualification == null){
			throw new IllegalArgumentException("the qualification is null, must have a correct qualification");
		}
		else{
			this.qualifications.add(qualification);
		}
	}

	public Set<Project> getProjects() {
		return new HashSet<Project>(this.projects);
	}

	public void addProject(Project project) {
		if (project == null){
			throw new IllegalArgumentException("the project is null, must have a correct project");
		}
		else{
			this.projects.add(project);
		}
	}

	public void removeProject(Project project) {
		if(projects.isEmpty()){
			throw new IllegalArgumentException("the projects set is empty, cannot remove this element");
		}
		else if(!(projects.contains(project))){
			throw new IllegalArgumentException("this project is not in the project set");
		}
		else{
			projects.remove(project);
		}
	}

	public int getWorkload() {
		int workload = 0;
		if(projects.size() == 0){
			return workload;
		}
		else{
			for(Project p:projects){
				if(!(p.getStatus().name().equals("FINISHED"))){
					workload += p.getSize().getValue();
				}
			}
			return workload;
		}
	}

	public boolean willOverload(Project project) {
		int addition = -1;
		if (project.getSize ().equals (ProjectSize.SMALL)) {
			addition = 1;
		} else if (project.getSize ().equals (ProjectSize.MEDIUM)) {
			addition = 2;
		} else if (project.getSize ().equals (ProjectSize.BIG)) {
			addition = 3;
		}
		if (getWorkload () + addition > 12) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isAvailable() {
		return getWorkload () < 12;
	}

	public WorkerDTO toDTO() {
		String [] projectStrings = new String [projects.size ()];
		int i = 0;
		for (Project p:projects) {
			projectStrings [i] = p.getName ();
			i++;
		}
		String [] qualificationStrings = new String [qualifications.size ()];
		i = 0;
		for (Qualification q:qualifications) {
			qualificationStrings [i] = q.toString ();
			i++;
		}
		return new WorkerDTO (name, salary, getWorkload (), projectStrings, qualificationStrings);
	}
}
