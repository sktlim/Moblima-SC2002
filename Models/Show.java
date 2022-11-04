package Models;

public class Show {

    public enum TheatreClass {STANDARD, SILVER, GOLD, DEFAULT};

    private int showId;
    private int movieId;
    private String date;
    private String startTime;
    private String endTime;
    private int theatre;
    private TheatreClass theatreClass;
    private String cineplex;


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

    

    //getters setters
    public int getShowId(){
        return this.showId;
    }

    public int getMovieId(){
        return this.movieId;
    }

    public String getDate(){
        return this.date;
    }

    public String getStartTime(){
        return this.startTime;
    }

    public String getEndTime(){
        return this.endTime;
    }

    public int getTheatre(){
        return this.theatre;
    }

    public TheatreClass getTheaterClass(){
        return this.theatreClass;
    }

    public String getCineplex(){
        return this.cineplex;
    }

    public void setShowId(int showId){
        this.showId = showId;
    }

    public void setMovieId(int movieId){
        this.movieId = movieId;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

    public void setTheatre(int theatre){
        this.theatre = theatre;
    }

    public void setTheaterClass(TheatreClass theatreClass){
        this.theatreClass = theatreClass;
    }

    public void setCineplex(String cineplex){
        this.cineplex = cineplex;
    }



    
}
