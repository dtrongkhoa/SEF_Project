package dao;

import model.Movie;

import paths.databases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class MovieDao {

    public static List<Movie> movies = readMoviesFromDatabase();
    public static List<Movie> pending = readPendingMovies();

    public static Iterable<Movie> getAllMovies() {

        return movies;
    }
    public static Iterable<Movie> getPendingMovies(){
        Movie.totalMovies -= pending.size();
        pending = readPendingMovies();
        return pending;
    }

    private static List readMoviesFromDatabase() {
        return readMoviesAtPath(databases.MOVIES);
    }

    private static List<Movie> readMoviesAtPath(String path) {
        List<Movie> movies = new ArrayList<>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArray = data.split(",");
                if(dataArray.length>2) {
                    //if(path.equals(databases.MOVIES))
                    //movies.add(new Movie(dataArray[0], dataArray[2], dataArray[1], dataArray[7], dataArray[8], dataArray[9]));
                    //else
                    {

                        if(!dataArray[0].equals("null"))

                        {

                            movies.add(new Movie(dataArray[0], dataArray[1], dataArray[2], dataArray[3], dataArray[4], dataArray[5]));
                        }


                    }
                }


            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return movies;
    }

    public static Set<Movie> search(Movie m){

        Set<Movie> movies = new HashSet<>();

        for(Movie movie:MovieDao.getAllMovies()){

            if(movie.partialMatch(m)) {
                movies.add(movie);
            }
        }
        return movies;
    }

    public static Movie getMovieByID(String id){

        try {
            int iid = Integer.parseInt(id);
            for(Movie m:movies){
                if(m.getMovieID() == iid){
                    return m;
                }
            }
//            for(Movie m: readMoviesAtPath(databases.REQUESTS)){
//                if(m.getMovieID() == iid){
//                    return m;
//                }
//            }
        }catch (Exception e){

        }
        return null;
    }

    //requests methods below this line

    public static void updateTable() {
        Movie.totalMovies = 0;
        movies = readMoviesFromDatabase(); //this refreshes the movies list
    }


    public static void addMovie(Movie m, String database) {
        try {
            String movieCsv = movieAsCsv(m,false);
            Files.write(Paths.get(database), movieCsv.getBytes(), StandardOpenOption.APPEND);
            updateTable();
        }catch (IOException e) {
            System.err.println("Unable to submit request");
        }
    }

    private static String movieAsCsv(Movie m, boolean hasExtraCol) {
        String DELIMITER = ",";
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(m.getTitle());
        sb.append(DELIMITER);
        if(hasExtraCol){
            //sb.append(m.getGenre());
//            sb.append(DELIMITER);
//            sb.append(m.getPCo());
//            sb.append(DELIMITER);

//            sb.append(DELIMITER);
//            sb.append(DELIMITER);
//            sb.append(DELIMITER);
//            sb.append(DELIMITER);
        }
        //else{
        sb.append(m.getPCo());
        sb.append(DELIMITER);
        sb.append(m.getGenre());
        sb.append(DELIMITER);
        //}
        sb.append(m.getYear());
        sb.append(DELIMITER);
        sb.append(m.getDirector());
        sb.append(DELIMITER);
        sb.append(m.getActors());
        return sb.toString();
    }

    public static void addMovieToMain(Movie m) {
        try {
            String movieCsv = movieAsCsv(m,true);
            Files.write(Paths.get(databases.MOVIES), movieCsv.getBytes(), StandardOpenOption.APPEND);
            deleteMovieFromPending(m);
            updateTable();
        }catch (IOException e) {
            System.err.println("Unable to submit request");
        }
    }
    // IMPLEMENT ME
    public static void deleteMovieFromPending(Movie m) {

        pending.remove(m);

        try {
            PrintWriter pw = new PrintWriter(databases.REQUESTS);
//				pw.print("\n");
            pw.close();
            for (Movie mov: pending) {
                addMovie(mov, databases.REQUESTS);
            }
            updateTable();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public static void deleteMovieFromMain(Movie m) throws IOException {


        movies.remove(m);

        try {
            PrintWriter pw = new PrintWriter(databases.MOVIES);
//			pw.print("\n");
            pw.close();
            for (Movie mov: movies) {

                String movieCsv = movieAsCsv(mov,false);
                Files.write(Paths.get(databases.MOVIES), movieCsv.getBytes(), StandardOpenOption.APPEND);


                //addMovie(mov, databases.MOVIES);
            }
            //updateTable();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static List readPendingMovies(){
        return readMoviesAtPath(databases.REQUESTS);
    }

    public static Movie searchPending(String id) {

        int iid = Integer.parseInt(id);
        for(Movie m: pending){
            if(m.getMovieID() == iid){
                return m;
            }
        }

        return null;
    }
}