package controller;

import dao.MovieDao;
import dao.UserDao;
import io.javalin.http.Handler;
import model.Movie;

import java.util.*;

public class ShowMoviesController {
	
    public static Handler showMovies = ctx -> {
    	
        Iterable<Movie> movies = MovieDao.getAllMovies();
        Map<String, Object> model = new HashMap<String ,Object>();
        model.put("header","All Movies");
        model.put("movies",movies);
        //model.put("CURRENT_USER", "Logged in as: " + UserDao.getCurrentUserName());
        model.put("CURRENT_USER", UserDao.getCurrentUser());
        ctx.render("/public/movie/movies.vm",model);
    };

    public static Handler search = ctx -> {
    	Map<String, Object> model = new HashMap<String ,Object>();
    	//model.put("CURRENT_USER", "Logged in as: " + UserDao.getCurrentUserName());
        model.put("CURRENT_USER", UserDao.getCurrentUser());
        ctx.render("/public/movie/search.vm",model);
    };

    public static Handler searchResults = ctx -> {
        Movie query = new Movie(
                ctx.formParam("title"),
                ctx.formParam("PCo"),
                ctx.formParam("genre"),
                ctx.formParam("year"),
                ctx.formParam("director"),
                ctx.formParam("actor")
                );

        Iterable<Movie> results = MovieDao.search(query);
        Map<String,Object> model = new HashMap<>();
        model.put("header","Search Results");
        model.put("movies",results);
       // model.put("CURRENT_USER", "Logged in as: " + UserDao.getCurrentUserName());
        model.put("CURRENT_USER", UserDao.getCurrentUser());
        ctx.render("/public/movie/movies.vm",model);
    };
    
   

    public static Handler showOneMovie = ctx -> {
        Map<String, Object> model = new HashMap<>();
        model.put("movie", MovieDao.getMovieByID(ctx.pathParam("id")));
       // model.put("CURRENT_USER", "Logged in as: " + UserDao.getCurrentUserName());
        model.put("CURRENT_USER", UserDao.getCurrentUser());
        ctx.render("/public/movie/oneMovie.vm",model);
    };

    public static  Handler showPendingMovies = ctx -> {
        Iterable<Movie> movies = MovieDao.getPendingMovies();
        Map<String, Object> model = new HashMap<String ,Object>();
        model.put("header","Pending Movies");
        model.put("movies",movies) ;
        //model.put("CURRENT_USER", "Logged in as: " + UserDao.getCurrentUserName());
        model.put("CURRENT_USER", UserDao.getCurrentUser());
        ctx.render("/public/movie/movies.vm",model);
    };
    public static Handler addMoviePost = ctx -> {
        Movie newMovie = MovieDao.searchPending(ctx.pathParam("id"));
        MovieDao.addMovieToMain(newMovie);
        ctx.html(newMovie.title);

        Map<String, Object> model = new HashMap<String ,Object>();
        model.put("header","All Movies");
        model.put("CURRENT_USER", UserDao.getCurrentUser());
        model.put("movies",MovieDao.getAllMovies());
        ctx.render("/public/movie/movies.vm",model);

    };
    public static Handler deletePendingMoviePost = ctx -> {
        Movie deleteMovie = MovieDao.searchPending(ctx.pathParam("id"));
        MovieDao.deleteMovieFromPending(deleteMovie);
        ctx.html(deleteMovie.title);

        Map<String, Object> model = new HashMap<String ,Object>();
        model.put("header","Pending Movies");
        model.put("CURRENT_USER", UserDao.getCurrentUser());
        model.put("movies",MovieDao.getPendingMovies()) ;
        ctx.render("/public/movie/movies.vm",model);

    };
    public static Handler deleteMainMoviePost = ctx -> {
        Movie deleteMovie = MovieDao.getMovieByID(ctx.pathParam("id"));
        MovieDao.deleteMovieFromMain(deleteMovie);
        ctx.html(deleteMovie.title);

        Map<String, Object> model = new HashMap<String ,Object>();
        model.put("header","All Movies");
        model.put("CURRENT_USER", UserDao.getCurrentUser());
        model.put("movies",MovieDao.getAllMovies()) ;
        ctx.render("/public/movie/movies.vm",model);

    };
}
