package Models;

/**
 * Represents a Movie in our application.
 * This is the generic Movie in production, and NOT a particular screening of the Movie
 */

public class Movie {

    /**
     * Custom type representing the current Showing Status of the Movie
     */
    public enum ShowingStatus{COMING_SOON, PREVIEW, NOW_SHOWING, DEFAULT}

    /**
     * Custom type representing the rating categories for the Movie
     */
    public enum MovieRating{G, PG13, NC16, M18, R21, DEFAULT}

    /**
     *Custom type representing the type of the Movie
     */
    public enum MovieType{TWO_D,THREE_D, BLOCKBUSTER, DEFAULT}

    /**
     * The unique ID for this Movie
     */
    private int movieId;

    /**
     * The title of this Movie
     */
    private String movieTitle;

    /**
     * The current showing status of this Movie (ENUM)
     */
    private ShowingStatus showingStatus;

    /**
     * The synopsis for this Movie
     */
    private String synopsis;

    /**
     * The director for this Movie
     */
    private String director;

    /**
     * The cast for this Movie
     */
    private String cast;

    /**
     * The runtime for this Movie represented in minutes
     */
    private int movieRuntime;

    /**
     * The rating category for this Movie (ENUM)
     */
    private MovieRating movieRating;

    /**
     * The type of this Movie (ENUM)
     */
    private MovieType movieType;

    /**
     * Constructor for Movie
     * Allows Admins to create a new Movie with the following params
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
     */

    /**
     * Get this Movie's unique ID
     * @return the Movie's ID
     */
    public int getMovieId(){
        return this.movieId;
    }

    /**
     * Get this Movie's Title
     * @return This Movie's Title
     */
    public String getMovieTitle(){
        return this.movieTitle;
    }

    /**
     * Get this Movie's current showing status
     * @return This Movie's ShowingStatus
     */
    public ShowingStatus getShowingStatus(){
        return this.showingStatus;
    }

    /**
     * Get this Movie's synopsis
     * @return This Movie's synopsis
     */
    public String getSynopsis(){
        return this.synopsis;
    }

    /**
     * Get this Movie's Director
     * @return this Movie's Director
     */
    public String getDirector(){
        return this.director;
    }

    /**
     * Get this Movie's Cast
     * @return this Movie's Cast
     */
    public String getCast(){
        return this.cast;
    }

    /**
     * Get this Movie's runtime
     * @return This Movie's RunTime
     */
    public int getMovieRuntime(){
        return this.movieRuntime;
    }

    /**
     * Get this Movie's Rating category
     * @return this Movie's MovieRating
     */
    public MovieRating getMovieRating(){
        return movieRating;
    }

    /**
     * Get this Movie's Type
     * @return this Movie's MovieTYpe
     */
    public MovieType getMovieType(){
        return this.movieType;
    }

    /**
     * Modifies this Movie's unique ID
     * SHOULD NOT BE CALLED UNNECESSARILY
     * @param movieId
     */
    public void setmovieId(int movieId){
        this.movieId = movieId;
    }

    /**
     * Modifies this Movie's Title
     * @param movieTitle
     */
    public void setMovieTitle(String movieTitle){
        this.movieTitle = movieTitle;
    }

    /**
     * Modifies this Movie's ShowingStatus
     * @param showingStatus
     */
    public void setShowingStatus(ShowingStatus showingStatus){
        this.showingStatus = showingStatus;
    }

    /**
     * Modifies this Movie's Synopsis
     * @param synopsis
     */
    public void setSynopsis(String synopsis){
        this.synopsis = synopsis;
    }

    /**
     * Modifies this Movie's Director
     * @param director
     */
    public void setDirector(String director){
        this.director = director;
    }

    /**
     * Modifies this Movie's Cast
     * @param cast
     */
    public void setCast(String cast){
        this.cast = cast;
    }

    /**
     * Modifies this Movie's RunTime
     * @param movieRuntime
     */
    public void setMovieRuntime(int movieRuntime){
        this.movieRuntime = movieRuntime;
    }

    /**
     * Modifies this Movie's Rating
     * @param movieRating
     */
    public void setMovieRating(MovieRating movieRating){
        this.movieRating = movieRating;
    }

    /**
     * Modifies this Movie's Type
     * @param movieType
     */
    public void setMovieType(MovieType movieType){
        this.movieType = movieType;
    }




}
