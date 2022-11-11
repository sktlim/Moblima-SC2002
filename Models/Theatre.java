package Models;

/**
 * Theatre associated to Cineplex. Contains theatre ID, Cineplex code and Theatre Class
 */
public class Theatre {
    /**
     * Theatre ID of particular theatre
     */
    private int theatreId;
    /**
     * Cineplex code of associated Theatre
     */
    private String cineplexCode;
    /**
     * Enumerated variables for Theatre Classes. Consists of STANDARD, PLATINUM and DEFAULT
     */
    private Show.TheatreClass theatreClass;
    /**
     * Status of whether the theatre i.e. whether it is showing movie currently
     */
    private int isBusy;

    /**
     * Constructor for Theatre
     * @param theatreId Theatre ID of particular theatre
     * @param cineplexCode Cineplex code of associated Theatre
     * @param theatreClass Enumerated variables for Theatre Classes. Consists of STANDARD, PLATINUM and DEFAULT
     * @param isBusy Status of whether the theatre i.e. whether it is showing movie currently
     */
    public Theatre(int theatreId, String cineplexCode, Show.TheatreClass theatreClass, int isBusy) {
        this.theatreId = theatreId;
        this.cineplexCode = cineplexCode;
        this.theatreClass = theatreClass;
        this.isBusy = isBusy;
    }

    /**
     * Getter method for theatre ID
     * @return returns Theatre ID of particular theatre
     */
    public int getTheatreId() {
        return theatreId;
    }

    /**
     * Setter method for theatre ID
     * @param theatreId  New Theatre ID of particular theatre
     */
    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    /**
     * Getter method for Cineplex code
     * @return returns Cineplex code of theatre
     */
    public String getCineplexCode() {
        return cineplexCode;
    }

    /**
     * Getter method for cinema status
     * @return return status of cinema. 1 represents busy and 0 represents not busy
     */
    public int getStatus(){return isBusy;}

    /**
     * Setter method for Cineplex code
     * @param cineplexCode Cineplex code in Integer format
     */
    public void setCineplexCode(String cineplexCode) {
        this.cineplexCode = cineplexCode;
    }

    /**
     * Getter method for Cineplex code
     * @return returns theatre class in enumerated format. Consists of STANDARD, PLATINUM and DEFAULT
     */
    public Show.TheatreClass getTheatreClass() {
        return theatreClass;
    }

    /**
     * Setter method for theatre class code
     * @param theatreClass theatre class in enumerated format. Consists of STANDARD, PLATINUM and DEFAULT
     */
    public void setTheatreClass(Show.TheatreClass theatreClass) {
        this.theatreClass = theatreClass;
    }

    /**
     * Setter method for status of theatre
     * @param isBusy status of cinema. 1 represents busy and 0 represents not busy
     */
    public void setStatus(int isBusy){this.isBusy = isBusy;}
}
