package Models;

public class Theatre {

    private int theatreId;
    private String cineplexCode;
    private Show.TheatreClass theatreClass;

    private int isBusy;

    /**
     * Constructor for Theatre
     * @param theatreId
     * @param cineplexCode
     * @param theatreClass
     * @param isBusy
     */
    public Theatre(int theatreId, String cineplexCode, Show.TheatreClass theatreClass, int isBusy) {
        this.theatreId = theatreId;
        this.cineplexCode = cineplexCode;
        this.theatreClass = theatreClass;
        this.isBusy = isBusy;
    }

    /**
     * Getter and Setter Methods
     * @return
     */
    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getCineplexCode() {
        return cineplexCode;
    }

    public int getStatus(){return isBusy;}

    public void setCineplexCode(String cineplexCode) {
        this.cineplexCode = cineplexCode;
    }

    public Show.TheatreClass getTheatreClass() {
        return theatreClass;
    }

    public void setTheatreClass(Show.TheatreClass theatreClass) {
        this.theatreClass = theatreClass;
    }

    public void setStatus(int isBusy){this.isBusy = isBusy;}
}
