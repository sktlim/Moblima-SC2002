package Models;

/**
 * Seating plan Class for a particular cineplex and theatre, with the associated number of rows and columns.
 */
public class SeatingPlan {
    /**
     * Cineplex code in String format
     */
    private String cineplex;
    /**
     * Theatre identifier in Integer format
     */
    private int theatre;
    /**
     * Number of rows in associated Cineplex and Theatre
     */
    private int noOfRows;
    /**
     * Number of columns in associated Cineplex and Theatre
     */
    private int noOfColumns;

    /**
     * Constructor for Seating Plan
     * @param cineplex Cineplex code in String format
     * @param theatre Theatre identifier in Integer format
     * @param noOfRows Number of rows in associated Cineplex and Theatre
     * @param noOfColumns Number of columns in associated Cineplex and Theatre
     */
    public SeatingPlan(String cineplex, int theatre, int noOfRows, int noOfColumns){
        this.cineplex = cineplex;
        this.theatre = theatre;
        this.noOfRows = noOfRows;
        this.noOfColumns = noOfColumns;
    }

    /**
     * Getter method for Cineplex
     * @return returns Cineplex code in String format
     */
    public String getCineplex(){
        return this.cineplex;
    }

    /**
     * Getter method for theatre
     * @return returns Theatre identifier in Integer format
     */
    public int getTheatre(){
        return this.theatre;
    }

    /**
     * Getter method for number of rows
     * @return returns number of rows in associated Cineplex and Theatre
     */
    public int getNoOfRows(){
        return this.noOfRows;
    }

    /**
     * Getter method for number of columns
     * @return returns number of columns in associated Cineplex and Theatre
     */
    public int getNoOfColumns(){
        return this.noOfColumns;
    }

    /**
     * Setter method for Cineplex. Input in String format
     * @param cineplex Cineplex code
     */
    public void setCineplex(String cineplex){
        this.cineplex = cineplex;
    }

    /**
     * Setter method for Theatre. Input in Integer format
     * @param theatre theatre ID
     */
    public void setTheatre(int theatre){
        this.theatre = theatre;
    }

    /**
     * Setter method for number of rows. Input in Integer format
     * @param noOfRows number of rows in seating plan
     */
    public void setNoOfRows(int noOfRows){
        this.noOfRows = noOfRows;
    }

    /**
     * Setter method for number of columns. Input in Integer format
     * @param noOfColumns number of columns in seating plan
     */
    public void setNoOfColumns(int noOfColumns){
        this.noOfColumns = noOfColumns;
    }
    
}
