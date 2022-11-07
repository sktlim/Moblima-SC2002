package Models;

public class Theatre {

    private int theatreId;
    private String cineplexCode;
    private Show.TheatreClass theatreClass;

    public Theatre(int theatreId, String cineplexCode, Show.TheatreClass theatreClass) {
        this.theatreId = theatreId;
        this.cineplexCode = cineplexCode;
        this.theatreClass = theatreClass;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getCineplexCode() {
        return cineplexCode;
    }

    public void setCineplexCode(String cineplexCode) {
        this.cineplexCode = cineplexCode;
    }

    public Show.TheatreClass getTheatreClass() {
        return theatreClass;
    }

    public void setTheatreClass(Show.TheatreClass theatreClass) {
        this.theatreClass = theatreClass;
    }
}
