package models;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * This class represents the Task
 * */

public class Task {
	
	private int taskID;
	private Date start;
	private Date end;
	
	/**
	 * Represent the set of childTasks
	 * */
	private Set<Task> childTasks = new HashSet<>();
	
	/**
	 * Represent the association between the parent and child task
	 * */
	private Map<Integer,Integer> parentChildMap = new HashMap();
	
	
	
	public void setParentChildMap(int taskID,Set<Task> childTasks) {
		
		
		for (Task t : childTasks) {
			this.parentChildMap.put(t.getTaskID(),taskID);
			
		}
		
		
	}
	
	public int getParentID(int taskid) {
		
		return parentChildMap.get(taskid);
	}
	
	
	
	/**
	 * This method works together with equals method 
	 * to avoid duplication of childTasks inside tasks
	 * */
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + taskID;
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
		Task other = (Task) obj;
		if (taskID != other.taskID )
			return false;
		if(!other.getChildTasks().contains(other.taskID))
			return false;
		return true;
	}
	
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
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
	public Set<Task> getChildTasks() {
		return childTasks;
	}
	
	/**
	 * Return the child task for the specified taskID
	 * @param childtaskID  ID of the child y
	 * 
	 * task 
	 * @return A Task object 
	 * */
	
	public Task getChildTaskByID(int childtaskID) {
						
		return getChildTasks().stream().filter(task -> task.getTaskID()==(childtaskID))
				.findFirst().get();
		
	}
	public void setChildTasks(Set<Task> childTasks) {
		this.childTasks = childTasks;
	}
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
