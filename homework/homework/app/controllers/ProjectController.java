package controllers;



import com.fasterxml.jackson.databind.JsonNode;
import models.MockDB;
import models.Project;
import models.Task;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import javax.inject.Inject;


/**
 * Represents the controller Project class
 * 
 * Takes HTTP request and produce JSON
 * 
 * */

public class ProjectController extends Controller{


	private MockDB projectStore;



	@Inject 
	public ProjectController(HttpExecutionContext ec, MockDB	 projectStore)
	{ 
		this.projectStore = projectStore; 
	}

	/**
     * An action method that produces JSON Response.
     * This method will be called when the application receives a
     * POST request with a path of /
     */
		
	public Result create(Http.Request request) {
		JsonNode json = request.body().asJson();
		if(json!=null) {
			Project newProject = Json.fromJson(json, Project.class);
			boolean addproject = projectStore.addProject(newProject);
			if (addproject) {
				return created(Json.toJson(projectStore.getProjects()));
			}else {
				return badRequest("Project structure not correct");
			}
		}else {
			return badRequest("Request Body is empty");
		}   

	}
	
	
	/**
     * An action method that produces JSON Response.
     * This method will be called when the application receives a
     * GET request with a path of /:id
     */

	public Result retrieve(int id) {

		if(id!=0) {

			Project project = projectStore.getProjectByID(id);
			if (project!=null) {
				return ok(Json.toJson(project));
			}else {
				return notFound("Project structure not correct");
			}
		}else {
			return badRequest("Request Body is empt");
		}   

	}
	
	/**
     * An action method that produces JSON Response.
     * This method will be called when the application receives a
     * PUT request with a path of /:projectID/task/:taskid
     */

	public Result update(int projid, int taskid,Http.Request request) {
		System.out.println("update method called with :" + taskid);

		JsonNode json = request.body().asJson();
		if(json!=null) {
			Task updateTask = Json.fromJson(json, Task.class);


			Task t = projectStore.getTaskByIDAndUpdate(projid,taskid,updateTask);


			if(t!=null) {
				return ok(Json.toJson(t));
			}else {
				return ok("Task is not present");
			}
		}else {
			return ok("Task is not present");
		}


	}





}
