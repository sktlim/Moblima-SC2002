package Models;

public class Movie {

    public enum ShowingStatus{COMING_SOON, PREVIEW, NOW_SHOWING, DEFAULT}
    public enum MovieRating{G, PG13, NC16, M18, R21, DEFAULT}
    public enum MovieType{TWO_D,THREE_D, BLOCKBUSTER, DEFAULT}
    
    private int movieId;
    private String movieTitle;
    private ShowingStatus showingStatus;
    private String synopsis;
    private String director;
    private String cast;
    private int movieRuntime;
    private MovieRating movieRating;
    private MovieType movieType;

    /**
     * Constructor Method for Movie
     * @param movieId
     * @param movieTitle
     * @param showingStatus
     * @param synopsis
     * @param director
     * @param cast
     * @param movieRuntime
     * @param movieRating
     * @param movieType
     */
    public Movie(int movieId, String movieTitle, ShowingStatus showingStatus, String synopsis, String director, String cast, int movieRuntime, MovieRating movieRating, MovieType movieType){
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.showingStatus = showingStatus;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.movieRuntime = movieRuntime;
        this.movieRating = movieRating;
        this.movieType = movieType;
    }


    /**
     * Getter and Setter Methods
     * @return
     */
    public int getMovieId(){
        return this.movieId;
    }

    public String getMovieTitle(){
        return this.movieTitle;
    }

    public ShowingStatus getShowingStatus(){
        return this.showingStatus;
    }

    public String getSynopsis(){
        return this.synopsis;
    }

    public String getDirector(){
        return this.director;
    }

    public String getCast(){
        return this.cast;
    }

    public int getMovieRuntime(){
        return this.movieRuntime;
    }

    public MovieRating getMovieRating(){
        return movieRating;
    }

    public MovieType getMovieType(){
        return this.movieType;
    }


    public void setmovieId(int movieId){
        this.movieId = movieId;
    }

    public void setMovieTitle(String movieTitle){
        this.movieTitle = movieTitle;
    }

    public void setShowingStatus(ShowingStatus showingStatus){
        this.showingStatus = showingStatus;
    }

    public void setSynopsis(String synopsis){
        this.synopsis = synopsis;
    }

    public void setDirector(String director){
        this.director = director;
    }

    public void setCast(String cast){
        this.cast = cast;
    }

    public void setMovieRuntime(int movieRuntime){
        this.movieRuntime = movieRuntime;
    }

    public void setMovieRating(MovieRating movieRating){
        this.movieRating = movieRating;
    }

    public void setMovieType(MovieType movieType){
        this.movieType = movieType;
    }




}
