package controller;

import java.util.HashMap;
import java.util.Map;

import dao.MovieDao;
import dao.UserDao;
import io.javalin.http.Handler;
import model.Movie;
import paths.databases;

public class RequestMovieController {
	
	public static Handler handleRequestGet = ctx -> {
		Map<String, Object> model = new HashMap<String ,Object>();
    	//model.put("CURRENT_USER", "Logged in as: " + UserDao.getCurrentUserName());
        model.put("CURRENT_USER", UserDao.getCurrentUser());
        ctx.render("/public/movie/request.vm",model);
    };
	
	
	 public static Handler handleRequestPost = ctx -> {
	    	Movie movieRequest = new Movie(
	    			ctx.formParam("title"),
	                ctx.formParam("PCo"),
	                ctx.formParam("genre"),
	                ctx.formParam("year"),
	                ctx.formParam("director"),
	                ctx.formParam("actor")
	                );
	    	MovieDao.addMovie(movieRequest, databases.REQUESTS);
	    	
	    	Map<String, Object> model = new HashMap<String ,Object>();
	    	//model.put("CURRENT_USER", "Logged in as: " + UserDao.getCurrentUserName());
	        model.put("CURRENT_USER", UserDao.getCurrentUser());
	        ctx.render("/public/movie/request.vm",model);
	    };
	    
	    
}
