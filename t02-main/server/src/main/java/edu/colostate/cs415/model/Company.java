package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Set;

public class Company {

	private String name;
	private Set<Worker> employees;
	private Set<Worker> available;
	private Set<Worker> assigned;
	private Set<Project> projects;
	private Set<Qualification> qualifications;



	public Company(String name) {
	{
	if (name == null || name.trim().length() == 0) {
		throw new IllegalArgumentException("A null or blank company name is not allowed.");
	}
	this.name = name;
	this.employees = new HashSet<>(); // Initialize sets
	this.available = new HashSet<>();
	this.assigned = new HashSet<>();
	this.projects = new HashSet<>();
	this.qualifications = new HashSet<>();
	}

	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof Company) {
			final Company other = (Company) o;
			if (other.name.equals(this.name)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		int numWorkers = this.getAvailableWorkers().size();
		int numProjects = this.getProjects().size();

		return this.getName() + ":" + String.valueOf(numWorkers) + ":" + String.valueOf(numProjects);
	}

	public String getName() {
		return this.name;
	}

	public Set<Worker> getEmployedWorkers() {
		HashSet<Worker> employedWorkers = new HashSet<Worker>(this.employees);
        return employedWorkers;
	}

	public Set<Worker> getAvailableWorkers() {
		HashSet<Worker> available = new HashSet<Worker>(this.available);
		return available;
	}

	public Set<Worker> getUnavailableWorkers() {
		Set<Worker> unavailableWorkers = new HashSet<>();

		for (Worker employee : this.employees) {
			if (!available.contains(employee)) {
				unavailableWorkers.add(employee);
			}
		}

		return unavailableWorkers;
	}

	public Set<Worker> getAssignedWorkers() {
		return new HashSet<Worker>(this.assigned);
	}

	public Set<Worker> getUnassignedWorkers() {
		Set<Worker> unassigedWorkers = new HashSet<>();

		for (Worker employee : this.employees) {
			if (!assigned.contains(employee)) {
				unassigedWorkers.add(employee);
			}
		}

		return unassigedWorkers;
	}

	public Set<Project> getProjects() {
		return new HashSet<Project>(this.projects);
	}

	public Set<Qualification> getQualifications() {
		return new HashSet<Qualification>(this.qualifications);
	}

	public Worker createWorker(String name, Set<Qualification> qualifications, double salary) {
		{
			if (name == null) {
			throw new IllegalArgumentException("A null name is not allowed for a worker.");
			}
			if (salary < 0) {
			throw new IllegalArgumentException("Salary for a worker cannot be negative.");
			}
			// Create a new Worker object
			if(qualifications.size()<1){
				throw new IllegalArgumentException("Worker must have atleast one qualification to be employeed.");
			}
			Worker worker = new Worker(name, qualifications, salary);
			// Add the worker to the company's employee and available worker sets
			employees.add(worker);
			available.add(worker);
			for(Qualification q : this.qualifications){
				if(qualifications.contains(q)){
					q.addWorker(worker);
				}
			
			}
			// Return the newly created worker
			return worker;
			}
			
		
	}

	public Qualification createQualification(String description) {
		if (description == null) {
		throw new IllegalArgumentException("Qualification description cannot be null.");
		}
		
		
		Qualification qualification = new Qualification(description);
		this.qualifications.add(qualification);
		return qualification;
		}

	public Project createProject(String name, Set<Qualification> qualifications, ProjectSize size) {
		Project newProject = new Project (name, qualifications, size);
		this.projects.add(newProject);
		return newProject;
	}

	public void start(Project project) {
			if (project == null) {
				throw new IllegalArgumentException("Project cannot be null.");
			}
			// Check if project is in PLANNED or SUSPENDED state and all qualifications are met
			if ((project.getStatus() == ProjectStatus.PLANNED || project.getStatus() == ProjectStatus.SUSPENDED) && project.getMissingQualifications().size() == 0) {
				project.setStatus(ProjectStatus.ACTIVE);
			}
	}

	public void finish(Project project) {
			if (project == null) {
				throw new IllegalArgumentException("Project cannot be null.");
			}
		
			if (project.getStatus() == ProjectStatus.ACTIVE) {
				project.setStatus(ProjectStatus.FINISHED); // Mark project as finished
				for (Worker w : project.getWorkers()) {
					unassign(w, project);
				}
			}
		
	}

	public void assign(Worker worker, Project project) {
			if (worker == null || project == null) {
				throw new IllegalArgumentException("Worker and project cannot be null.");
			}
			if (!(project.getStatus() == ProjectStatus.ACTIVE || project.getStatus() == ProjectStatus.FINISHED && 
			    available.contains(worker) && !assigned.contains(worker) && worker.willOverload(project) == false 
				&& project.isHelpful(worker))) {
					project.addWorker(worker);
					worker.addProject(project);
					assigned.add(worker);
					available.remove(worker);
			}
		
	}

	public void unassign(Worker worker, Project project) {
			if (worker == null || project == null) {
				throw new IllegalArgumentException("Worker and project cannot be null.");
			}
		
			if (employees.contains(worker) && project.getWorkers().contains(worker)) {
				project.removeWorker(worker);
				worker.removeProject(project);
				assigned.remove(worker);
				available.add(worker);
				if(project.getStatus() == ProjectStatus.ACTIVE && project.getMissingQualifications().size() > 0){
					project.setStatus(ProjectStatus.SUSPENDED);
				}
				
			}
			// Check if project is active if it meets requierments still
	}

	public void unassignAll(Worker worker) {
			if (worker == null) {
				throw new IllegalArgumentException("Worker cannot be null.");
			}
		
			if (assigned.contains(worker)) {
				for (Project project : this.projects) {
					
					if (project.getWorkers().contains(worker)) {
						project.removeWorker(worker);
						worker.removeProject(project);
						if(project.getStatus() == ProjectStatus.ACTIVE && project.getMissingQualifications().size() > 0){
							project.setStatus(ProjectStatus.SUSPENDED);
						}
						
					}
						
				}
				assigned.remove(worker);
				available.add(worker);
			}
		}
		
}
