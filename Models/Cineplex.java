package Models;

public class Cineplex {

    private String cineplexCode;
    private String name;

    public Cineplex() {}

    public Cineplex(String cineplexCode, String name) {
        this.cineplexCode = cineplexCode;
        this.name = name;
    }

    public String getCineplexCode() {
        return cineplexCode;
    }

    public String getName() {
        return name;
    }

    public void setCineplexCode(String cineplexCode) {
        this.cineplexCode = cineplexCode;
    }

    public void setName(String name) {
        this.name = name;
    }

}
