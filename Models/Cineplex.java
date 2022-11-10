package Models;

public class Cineplex {

    private String cineplexCode;
    private String name;

    /**
     * Constructor method
     * @param cineplexCode
     * @param name
     */
    public Cineplex(String cineplexCode, String name) {
        this.cineplexCode = cineplexCode;
        this.name = name;
    }

    /**
     * Getter and Setter Methods
     * @return
     */
    public String getCineplexCode() {
        return cineplexCode;
    }

    public void setCineplexCode(String cineplexCode) {
        this.cineplexCode = cineplexCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
