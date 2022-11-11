package Models;

/**
 * Show Class with related variables such as Theatre Class, show ID, movie ID, date, start time, end time, theatre identifier, and Cineplex
 * A Show is a specific screening of a Movie
 */
public class Show {
    /**
     * Enumerated variables for Theatre Classes. Consists of STANDARD, PLATINUM and DEFAULT
     */
    public enum TheatreClass {STANDARD, PLATINUM, DEFAULT}

    /**
     * show ID of associated Show instance
     */
    private int showId;
    /**
     * movie ID of movie that is screened
     */
    private int movieId;
    /**
     * Date of Show in YYYY-MM-DD format
     */
    private String date;
    /**
     * Start time of show in HH:MM format
     */
    private String startTime;
    /**
     * End time of show in HH:MM format
     */
    private String endTime;
    /**
     * Theatre identifier in Integer format
     */
    private int theatre;
    /**
     * Enumerated variables for Theatre Classes. Consists of STANDARD, PLATINUM and DEFAULT
     */
    private TheatreClass theatreClass;
    /**
     * Cineplex code in String format
     */
    private String cineplex;

    /**
     * Constructor for Show class
     * @param showId show ID of associated Show instance
     * @param movieId movie ID of movie that is screened
     * @param date Date of Show in YYYY-MM-DD format
     * @param startTime Start time of show in HH:MM format
     * @param endTime End time of show in HH:MM format
     * @param theatre Theatre identifier in Integer format
     * @param theatreClass Enumerated variables for Theatre Classes. Consists of STANDARD, PLATINUM and DEFAULT
     * @param cineplex Cineplex code in String format
     */
    public Show(int showId, int movieId, String date, String startTime, String endTime, int theatre, TheatreClass theatreClass, String cineplex){
        this.showId = showId;
        this.movieId = movieId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.theatre = theatre;
        this.theatreClass = theatreClass;
        this.cineplex = cineplex;
    }


    /**
     * Getter method for show ID
     * @return Returns show ID in Integer format
     */
    public int getShowId(){
        return this.showId;
    }

    /**
     * Getter method for movie ID
     * @return Returns movie ID in Integer format
     */
    public int getMovieId(){
        return this.movieId;
    }

    /**
     * Getter method for date
     * @return Returns date in String with format YYYY-MM-DD
     */
    public String getDate(){
        return this.date;
    }

    /**
     * Getter method for start time
     * @return Returns start time in String in HH:MM format
     */
    public String getStartTime(){
        return this.startTime;
    }

    /**
     * Getter method for end time
     * @return Returns end time in String in HH:MM format
     */
    public String getEndTime(){
        return this.endTime;
    }

    /**
     * Getter method for theatre identifier
     * @return Returns Theatre identifier in Integer format
     */
    public int getTheatre(){
        return this.theatre;
    }

    /**
     * Getter method for theatre class
     * @return Returns theatre class in enumerated formats of STANDARD, PLATINUM and DEFAULT
     */
    public TheatreClass getTheatreClass(){
        return this.theatreClass;
    }

    /**
     * Getter method for Cineplex code
     * @return Returns Cineplex code in Integer format
     */
    public String getCineplex(){
        return this.cineplex;
    }

    /**
     * Setter method for show ID
     * @param showId show ID of associated Show instance in Integer format
     */
    public void setShowId(int showId){
        this.showId = showId;
    }

    /**
     * Setter method for movie ID
     * @param movieId movie ID of associated Movie in Integer format
     */
    public void setMovieId(int movieId){
        this.movieId = movieId;
    }

    /**
     * Setter method for date
     * @param date Date of Show in YYYY-MM-DD format
     */
    public void setDate(String date){
        this.date = date;
    }

    /**
     * Setter method for start time
     * @param startTime Start time in String in HH:MM format
     */
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    /**
     * Setter method for end time
     * @param endTime End time in String in HH:MM format
     */
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

    /**
     * Setter method for theatre
     * @param theatre Theatre identifier in Integer format
     */
    public void setTheatre(int theatre){
        this.theatre = theatre;
    }

    /**
     * Setter method for theater class
     * @param theatreClass Theatre class in enumerated format. Consists of STANDARD, PLATINUM and DEFAULT
     */
    public void setTheatreClass(TheatreClass theatreClass){
        this.theatreClass = theatreClass;
    }

    /**
     * Setter method for cineplex
     * @param cineplex Cineplex code in Integer format
     */
    public void setCineplex(String cineplex){
        this.cineplex = cineplex;
    }



    
}
