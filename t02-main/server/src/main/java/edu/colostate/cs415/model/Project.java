package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import edu.colostate.cs415.dto.ProjectDTO;

public class Project {

	private String name;
	private ProjectSize size;
	private ProjectStatus status;
	private Set<Worker> workers;
	private Set<Qualification> qualifications;

	public Project(String name, Set<Qualification> qualifications, ProjectSize size) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("A null/blank name is not allowed.");
		}
		else {
			this.name = name;
		}
		if (qualifications == null) {
			throw new IllegalArgumentException("A null qualifications is not allowed.");
		} else {
			this.qualifications = new HashSet<>();
			Iterator <Qualification> qualificationsIterator = qualifications.iterator();
			while (qualificationsIterator.hasNext()) {
				addQualification (qualificationsIterator.next());
			}
		}
		if (size == null) {
			throw new IllegalArgumentException("A null size is not allowed.");
		} else {
			this.size = size;
		}
		status = ProjectStatus.PLANNED;
		workers = new HashSet<Worker> ();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			throw new IllegalArgumentException("The project cannot be null.");
		} else {
			if (other instanceof Project) {
				if (((Project) other).name.equals (this.name)) {
					return true;
				}
			} else {
				throw new IllegalArgumentException("The argument must be a Project.");
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode ();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder ();
		builder.append (name);
		builder.append (":");
		builder.append (workers.size ());
		builder.append (":");
		builder.append (status.name ());
		return builder.toString ();
	}

	public String getName() {
		return name;
	}

	public ProjectSize getSize() {
		return size;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public void addWorker(Worker worker) {
		try {
		for(Qualification q: worker.getQualifications()){
				for(Qualification p: qualifications){
					if(q.equals(p)){
						this.workers.add(worker);
					}
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Worker can not be added to Project"); 
		}
			
	}
		
	

	public void removeWorker(Worker worker) {
		try{
			this.workers.remove(worker);
		}catch(Exception e){
			throw new IllegalArgumentException("Worker was not found or was null");
		}
	}

	public Set<Worker> getWorkers() {
		return new HashSet<Worker>(this.workers);
	}

	public void removeAllWorkers() {
		try {
			this.workers.clear();
		} catch (Exception e) {
			throw new IllegalArgumentException("Worker could  not be removed");
		}
	}

	public Set<Qualification> getRequiredQualifications() {
		Set<Qualification> requieredQualification = new HashSet<>(this.qualifications);
		if (requieredQualification.size() < 1) {
			return new HashSet<Qualification>();
		}
		return requieredQualification;
	}

	public void addQualification(Qualification qualification) {
		if (qualifications.contains(qualification)){
			throw new IllegalArgumentException("This qualification already exists");
		}
		this.qualifications.add(qualification);
	}

	public Set<Qualification> getMissingQualifications() {
		Set<Qualification> missingQualificationsSet = new HashSet<>(this.qualifications);
		for (Worker w : workers){
			for(Qualification q: w.getQualifications()){
				if (missingQualificationsSet.contains(q)) {
					missingQualificationsSet.remove(q);				
				}
			}
			
		}
		return missingQualificationsSet;
	
	}

	public boolean isHelpful(Worker worker) {
		Set<Qualification> missingQualificationsSet = getMissingQualifications();
		Boolean workerUsefulness = false;
		for(Qualification q: worker.getQualifications()){
			for(Qualification p: missingQualificationsSet){
				if(q.equals(p)){
					workerUsefulness = true;
				}
			}
		}	
		return workerUsefulness;
	}

	public ProjectDTO toDTO() {
		Set<Qualification> missigQualifications = getMissingQualifications();

		String[] workerStrings = new String[this.workers.size()];
		String[] qualificationStrings = new String[this.qualifications.size()];
		String[] missingQualificationStrings = new String[missigQualifications.size()];
		int i = 0;
		for (Worker w : this.workers) {
			workerStrings[i] = w.getName();
			i++;
		}
		i = 0;
		for (Qualification qualification : this.qualifications) {
			qualificationStrings[i] = qualification.toDTO().getDescription();
			i++;
		}
		i = 0;
		for (Qualification qualification : missigQualifications) {
			missingQualificationStrings[i] = qualification.toDTO().getDescription();
			i++;
		}
		return new ProjectDTO(this.name, this.size, this.status, workerStrings, qualificationStrings, missingQualificationStrings);
	}
}
