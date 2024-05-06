package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Set;

import edu.colostate.cs415.dto.QualificationDTO;

public class Qualification {

	private String description;
	private Set<Worker> workers;

	public Qualification(String description) {
		if (description == null) {
			throw new IllegalArgumentException("CAN NOT CONSTRUCT A QUALIFICATION OBJECT WITH NULL");
		} else if(description.equals ("") ){
			throw new IllegalArgumentException("CAN NOT CONSTRUCT A QUALIFICATION OBJECT WITH THE EMPTY STRING");
		} else {
			this.description= description;
		}
		workers = new HashSet<Worker>();
	}

	@Override
	public boolean equals(Object other) {
		if(other ==null || !(other instanceof Qualification)){
			throw new IllegalArgumentException("CAN NOT COMPARE A NON QUALIFICATION OBJECT TO A QUALIFICATION OBJECT");
		}else{
			if (((Qualification)other).description.equals (this.description)) {
				return true;
			}
			return false;
		}
	}

	@Override
	public int hashCode() {
		return description.hashCode();
	}

	@Override
	public String toString() {
		return description;
	}

	public Set<Worker> getWorkers() {
		return new HashSet<Worker>(this.workers);
	}

	public void addWorker(Worker worker) {
		workers.add(worker);
	}

	public void removeWorker(Worker worker) {
		if(!workers.contains(worker)){
			throw new IllegalArgumentException("CANNOT REMOVE WORKER THAT DOES NOT ALREADY EXIST IN QUALIFICATION SET.");
		} else{
			workers.remove(worker);
		}
	}

	public QualificationDTO toDTO() {
		String[] workerStrings = new String[workers.size()];
		int count=0;
		for (Worker worker : workers) {
			workerStrings[count]=worker.getName();
			count++;
		}
		return new QualificationDTO(description,workerStrings);
	}
}
