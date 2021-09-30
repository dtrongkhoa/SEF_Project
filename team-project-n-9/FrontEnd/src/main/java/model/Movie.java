package model;

public class Movie {

    public  String title;
    public  String PCo;
    public  String genre;
    public  String year;
    public  String director;
    public  String actors;
    public static int totalMovies = 0;
    public final int movieID;

    private String actorsArray[];

    public Movie(String title, String PCo, String genre, String year, String director, String actors){
        this.title = title;
        this.PCo = PCo;
        this.genre = genre;
        this.year = year;
        this.director= director;

        if(actors != null && !actors.isEmpty()){
            this.actors= formatActors(actors);
        }else{
            this.actors = "";
        }

        movieID = totalMovies;
        totalMovies++;
        title += movieID;
    }

    public String getTitle(){return title;}
    public String getPCo(){return PCo;}
    public String getGenre() {return  genre;}
    public String getYear() {return year;}
    public String getDirector() {return director;}
    public String getActors() {return actors;}
    public int getMovieID(){return movieID;}
    
    public void setTitle(String title) {this.title = title;}
    public void setPCi(String PCo) {this.PCo = PCo;}
    public void setGenre(String genre) {this.genre = genre;}
    public void setYear(String year) {this.year = year;}
    public void setDirector(String director) {this.director = director;}
    public void setActor(String actors) {this.actors = actors;}


    private String formatActors(String s){
        actorsArray = s.split("&");
        return s.replace("&",",");
    }

    public String[] getActorsArray(){
        return actorsArray;
    }

    public boolean equals(Movie m){
        return (this.title.equals(m.title) &&
                this.PCo.equals(m.PCo) &&
                this.genre.equals(m.genre) &&
                this.year.equals(m.year) &&
                this.director.equals(m.director) &&
                this.actors.equals(m.actors));
    }

    public String movieAsCsv() {
        String DELIMITER = ",";
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(getTitle());
        sb.append(DELIMITER);
        sb.append(getPCo());
        sb.append(DELIMITER);
        sb.append(getGenre());
        sb.append(DELIMITER);
        sb.append(getYear());
        sb.append(DELIMITER);
        sb.append(getDirector());
        sb.append(DELIMITER);
        sb.append(getActors());
        return sb.toString();
    }

    private static String movieAsCsv(Movie m, boolean hasExtraCol) {
        String DELIMITER = ",";
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(m.getTitle());
        sb.append(DELIMITER);
        sb.append(m.getPCo());
        sb.append(DELIMITER);
        sb.append(m.getGenre());
        sb.append(DELIMITER);
        sb.append(m.getYear());
        sb.append(DELIMITER);
        sb.append(m.getDirector());
        sb.append(DELIMITER);
        sb.append(m.getActors());
        return sb.toString();
    }

    public boolean partialMatch(Movie other){
        if((other.getTitle().length() != 0 && !other.getTitle().equals(this.getTitle())) ||
                (other.getPCo().length() != 0 && !other.getPCo().equals(this.getPCo())) ||
                (other.getGenre().length() != 0 && !other.getGenre().equals(this.getGenre())) ||
                (other.getYear().length() != 0 && !other.getYear().equals(this.getYear())) ||
                (other.getDirector().length() != 0 && !other.getDirector().equals(this.getDirector()))){
        }
        else if(other.getActors().length() != 0){

            for(String actor:this.getActorsArray()){
                if(actor.equals(other.getActors())){
                    //movies.add(movie);
                    return true;
                }
            }
        }else if(other.actors.length() == 0){
           // movies.add(movie);
            return true;
        }
        return false;
    }
}
