package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import models.Project;

/**
 * This class represents the Mock database to store the projects
 * */


public class MockDB {
	
	public boolean isCorrect ;
	
	
	/**
	 * Represent the association between the project ID and project 
	 * 
	 * */
	private Map<Integer, Project> projects = new HashMap<>();
	
	Project p = new Project();
	Task t = new Task();
	
	
	/**
	 * Returns the Project for the specified Project ID
	 * @param id - ID of the project to be returned
	 * @return Project object 
	 * 
	 * */
		
	public Project getProjectByID(int id) {
		
		return projects.get(id);
	}
	
	

	/**
	 * Returns the Task for the specified Task ID after updating 
	 * @param projid - ID of the project 
	 * @param taskid - ID of the Task
	 * @param updateTask - Content to modify 
	 * @return Updated Task object
	 * 
	 * */
	public Task getTaskByIDAndUpdate(int projid,int taskid, Task updateTask) {
		boolean isFound = false;
		Task tfound = null;
		p = getProjectByID(projid);
		
		Set<Task> tasks = p.getTasks();
					
		try {
			tfound=p.getTaskByID(taskid);
			isFound = true;
					
			}
		catch(Exception e) {}
			
		
		if (!isFound) {
				
				tfound=findChildTaskRecursively(tasks,taskid,isFound,tfound);
				
		}
			
		if(updateTask.getStart().before(p.getStart())) {
			
			p.setStart(updateTask.getStart());
		}	
		if(updateTask.getEnd().after(p.getEnd())) {
			
			p.setEnd(updateTask.getEnd());
		}
					
			tfound.setStart(updateTask.getStart());
			
		
			
			tfound.setEnd(updateTask.getEnd());
			
			
			try {
				int parentid = tfound.getParentID(tfound.getTaskID());
				
				if (parentid!=0) {
					getTaskByIDAndUpdate(projid,parentid,updateTask);
				}
				}catch(Exception e) {
					
					return tfound;
				}
			
		
		
		return tfound;
	}
	
	
	/**
	 * Returns the Task for the specified Task ID  
	 * @param tasks - set of tasks which are direct child of project 
	 * @param taskid - ID of the Task to be found
	 * @param isFound - boolean representing whether task found or not 
	 * @param tfound - An empty task object 
	 * 
	 * @return Task object
	 * 
	 * */
	

	public Task findChildTaskRecursively(Set<Task> tasks, int taskid, boolean isFound,Task tfound) {
		
		for (Task t : tasks) {
			
			try {
					tfound=t.getChildTaskByID(taskid);
					isFound = true;
					return tfound;
				}
			catch(Exception e) {}
				
			
			tfound=findChildTaskRecursively(t.getChildTasks(),  taskid,isFound,tfound);
			
			
			}
		
		return tfound;
		
	}
	
	
	
	/**
	 * Add the project in to Projects HashMap 
	 * @param newProject - new project object to be added 
	 * @return boolean represents task added successfully or not
	 * 
	 * */
	
	
	
	public boolean addProject(Project newProject){
		try {
			if(checkProjectRules(newProject)) {
							
				projects.put(newProject.getProjectID(),newProject);
				
				return true;
			}else {
				
				return false;
			}
		}
		catch(Exception e){
			
			return false;
		}
		
		
	}
	
	/**
	 * Returns all the project from the mock database
	 * @return Map of all the projects
	 * 
	 * */
	
	
	public Map<Integer, Project> getProjects() {
		return projects;
	}

	

	
	/**
	 * Check whether project and its direct child follows all conventions or not
	 * @return boolean represent project follows all conventions or not
	 * 
	 * */
	
	
	
	
	
	private boolean checkProjectRules(Project newProject) {
		boolean isProjectStrucureCorrect = false;
		
		
		
		
		
		isCorrect = true;
		if(!newProject.getEnd().before(newProject.getStart())) {
			isProjectStrucureCorrect= true;}
		Set<Task> tasks =  newProject.getTasks();
		
		
		t.setParentChildMap(p.getProjectID(), tasks);
		
		isProjectStrucureCorrect=checkChildTaskRecursively(newProject.getStart(),newProject.getEnd(),tasks);
		
		 
		return isProjectStrucureCorrect;
		
	}	
	
	
	/**
	 * Check whether childTask of project's direct child follows all conventions or not
	 * @return boolean represent childTasks follows all conventions or not
	 * 
	 * */
		
	private boolean checkChildTaskRecursively(Date start, Date end, Set<Task> childTasks) {
		
		for(Task childTask : childTasks){
			
				  
			  if(childTask.getEnd().before(childTask.getStart())) {
			  
				  isCorrect= false; 
										  
			  }
			 
			  if(end.before(childTask.getEnd())){
			  
				  isCorrect= false; 
				  
			  }
			 
			  if(childTask.getStart().before(start)){
				  
				  isCorrect= false;
				  
			  }
			 
	
			Set<Task> grandChilds = childTask.getChildTasks();
			if(grandChilds.size() > 0) {
					  
			checkChildTaskRecursively(childTask.getStart(),childTask.getEnd(),grandChilds);
			  
				  
			  }
			 
		
		}
				
		
		return isCorrect;
	}
	
}
