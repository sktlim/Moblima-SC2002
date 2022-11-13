package Models;

/**
 * Represents a Movie in our application. This is the generic Movie in production, and NOT a particular screening of the Movie.
 * One Movie has multiple different Shows.
 */

public class Movie {

    /**
     * Custom type representing the current Showing Status of the Movie
     */
    public enum ShowingStatus{
        /**
         * Movie is coming soon
         */
        COMING_SOON,
        /**
         * Movie is in preview status
         */
        PREVIEW,
        /**
         * Movie is now showing
         */
        NOW_SHOWING,
        /**
         * Movie showing has ended
         */
        SHOWING_ENDED,
        /**
         * Default placeholder value
         */
        DEFAULT}

    /**
     * Custom type representing the rating categories for the Movie
     */
    public enum MovieRating{
        /**
         * Movie is for general audience
         */
        G,
        /**
         * Parental Guidance is advised for movie
         */
        PG13,
        /**
         * No children under 16 for this movie
         */
        NC16,
        /**
         * 18 year old mature rating
         */
        M18,
        /**
         * Movie restricted for those above 21 years old
         */
        R21,
        /**
         * Default placeholder value
         */
        DEFAULT}

    /**
     *Custom type representing the type of the Movie
     */
    public enum MovieType{
        /**
         * Movie is available is 2D
         */
        TWO_D,
        /**
         * Movie is available in 3D
         */
        THREE_D,
        /**
         * Movie is classified as blockbuster
         */
        BLOCKBUSTER,
        /**
         * Default placeholder value
         */
        DEFAULT}

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
     * The End Date for screening for this Movie
     * Must be of format DD/MM/YYYY
     */
    private String endOfShowingDate;

    /**
     * Constructor for Movie
     * Allows Admins to create a new Movie with the following params
     * @param movieId This Movie's unique ID. ID will be incremented with each new entry in the Movies Database.
     * @param movieTitle This Movie's Title
     * @param showingStatus This Movie's current showing status. Enumerated variable consisting of COMING_SOON, PREVIEW, NOW_SHOWING, SHOWING_ENDED, DEFAULT
     * @param synopsis This Movie's synopsis
     * @param director This Movie's Director
     * @param cast This Movie's Cast
     * @param movieRuntime This Movie's Runtime, represented in minutes
     * @param movieRating This Movie's Rating. Enumerated variable consisting of G, PG13, NC16, M18, R21, DEFAULT
     * @param movieType This Movie's Type. Enumerated variable consisting of TWO_D,THREE_D, BLOCKBUSTER, DEFAULT
     * @param endOfShowingDate This Movie's End Date of Screening. Once this date has passed, the Admin will set showingStatus to SHOWING_ENDED and this Movie will not be available for booking by movieGoers.
     */
    public Movie(int movieId, String movieTitle, ShowingStatus showingStatus, String synopsis, String director, String cast, int movieRuntime, MovieRating movieRating, MovieType movieType, String endOfShowingDate){
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.showingStatus = showingStatus;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.movieRuntime = movieRuntime;
        this.movieRating = movieRating;
        this.movieType = movieType;
        this.endOfShowingDate = endOfShowingDate;
    }

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
     * Get the End Date of screening for this Movie
     * @return this Movie's end of showing date in format DD/MM/YYYY
     */
    public String getEndOfShowingDate() {
        return endOfShowingDate;
    }

    /**
     * Modifies the End Date of screening for this Movie
     * @param endOfShowingDate this Movie's updated end of showing date in format DD/MM/YYYY
     */
    public void setEndOfShowingDate(String endOfShowingDate) {
        this.endOfShowingDate = endOfShowingDate;
    }

    /**
     * Modifies this Movie's unique ID
     * SHOULD NOT BE CALLED UNNECESSARILY
     * @param movieId This Movie's unique ID.
     */
    public void setMovieId(int movieId){
        this.movieId = movieId;
    }

    /**
     * Modifies this Movie's Title
     * @param movieTitle This Movie's Title.
     */
    public void setMovieTitle(String movieTitle){
        this.movieTitle = movieTitle;
    }

    /**
     * Modifies this Movie's ShowingStatus
     * @param showingStatus This Movie's showing status. Enumerated variable consisting of COMING_SOON, PREVIEW, NOW_SHOWING, DEFAULT
     */
    public void setShowingStatus(ShowingStatus showingStatus){
        this.showingStatus = showingStatus;
    }

    /**
     * Modifies this Movie's Synopsis
     * @param synopsis This Movie's synopsis.
     */
    public void setSynopsis(String synopsis){
        this.synopsis = synopsis;
    }

    /**
     * Modifies this Movie's Director
     * @param director This Movie's Director.
     */
    public void setDirector(String director){
        this.director = director;
    }

    /**
     * Modifies this Movie's Cast
     * @param cast This Movie's Cast.
     */
    public void setCast(String cast){
        this.cast = cast;
    }

    /**
     * Modifies this Movie's RunTime
     * @param movieRuntime This Movie's RunTime, represented in minutes.
     */
    public void setMovieRuntime(int movieRuntime){
        this.movieRuntime = movieRuntime;
    }

    /**
     * Modifies this Movie's Rating
     * @param movieRating This Movie's Rating. Enumerated variable consisting of G, PG13, NC16, M18, R21, DEFAULT
     */
    public void setMovieRating(MovieRating movieRating){
        this.movieRating = movieRating;
    }

    /**
     * Modifies this Movie's Type
     * @param movieType This Movie's Type. Enumerated variable consisting of TWO_D,THREE_D, BLOCKBUSTER, DEFAULT
     */
    public void setMovieType(MovieType movieType){
        this.movieType = movieType;
    }




}
