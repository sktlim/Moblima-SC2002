package Models;

public class SeatingPlan {

    private String cineplex;
    private int theatre;
    private int noOfRows;
    private int noOfColumns;

    public SeatingPlan(String cineplex, int theatre, int noOfRows, int noOfColumns){
        this.cineplex = cineplex;
        this.theatre = theatre;
        this.noOfRows = noOfRows;
        this.noOfColumns = noOfColumns;
    }

    // getters setters
    public String getCineplex(){
        return this.cineplex;
    }

    public int getTheatre(){
        return this.theatre;
    }

    public int getNoOfRows(){
        return this.noOfRows;
    }

    public int getNoOfColumns(){
        return this.noOfColumns;
    }


    public void setCineplex(String cineplex){
        this.cineplex = cineplex;
    }

    public void setTheatre(int theatre){
        this.theatre = theatre;
    }

    public void setNoOfRows(int noOfRows){
        this.noOfRows = noOfRows;
    }

    public void setNoOfColumns(int noOfColumns){
        this.noOfColumns = noOfColumns;
    }
    
}
