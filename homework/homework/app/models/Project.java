package models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * This class represents the project 
 * 
 * */
public class Project {
	
	private int projectID;
	private Date start;
	private Date end;
	
	/**Represents set of tasks associated with the project
	 * 
	 * */
	private Set<Task> tasks = new HashSet<>();
	
	
	
	
	
	
	/**
	 * This method works together with equals method \
	 * to avoid duplication of tasks inside project
	 * */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (tasks == null) {
			if (other.tasks != null)
				return false;
		} else if (!tasks.equals(other.tasks))
			return false;
		return true;
	}

	/**
	 * Return the task for the specified taskID
	 * @param taskID  ID of the task 
	 * @return A Task object 
	 * */
	
	public Task getTaskByID(int taskID) {
			
		
			return getTasks().stream().filter(task -> task.getTaskID()==(taskID))
					.findFirst().get();
			
		}
	
	
	
	public Set<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
	public int getProjectID() {
		return projectID;
	}
	
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	
	public Date getStart() {
		return start;
	}
	
	public void setStart(Date start) {
		this.start = start;
	}
	
	public Date getEnd() {
		return end;
	}
	
	public void setEnd(Date end) {
		this.end = end;
	}
	
	
	
	 
	

}
