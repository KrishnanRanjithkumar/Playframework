package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.mvc.Http.Status.CREATED;
import static play.mvc.Http.Status.NOT_FOUND;
import static play.test.Helpers.GET;
import static play.test.Helpers.POST;

import static play.test.Helpers.route;

import org.junit.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

public class ProjectControllerTest extends WithApplication {
	
	/*
	 * @Override protected Application provideApplication() { return new
	 * GuiceApplicationBuilder().build(); }
	 */

	    @Test
	    public void testUploadProject() {
	    	ObjectNode jsonNode = Json.newObject();
	    	jsonNode.put("projectID","1");
	    	jsonNode.put("start","2018-03-21");
	    	jsonNode.put("end","2018-03-29");
	        Http.RequestBuilder request = new Http.RequestBuilder()
	                .method(POST).bodyJson(jsonNode)
	                .uri("/");

	        Result result = route(app, request);
	        assertEquals(CREATED, result.status());
	        assertTrue(result.contentType().isPresent());
	        assertEquals("application/json", result.contentType().get());
	    }
	    
	    
	    
	    
	    @Test
	    public void testGetProject() {
	    	
	        Http.RequestBuilder request = new Http.RequestBuilder()
	                .method(GET)
	                .uri("/1");

	        Result result = route(app, request);
	        assertEquals(NOT_FOUND, result.status());
	        assertTrue(result.contentType().isPresent());
	        
	    }

}
