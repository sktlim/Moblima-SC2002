package Models;
/**
 * Represents a Cineplex in our Application.
 * One Cineplex has multiple theatres.
 * */

public class Cineplex {

    /**
     * The unique code for the Cineplex
     * Represented with a 3-Character String
     */
    private String cineplexCode;

    /**
     * Represents the name of the Cineplex for ease of reading and documenting
     */
    private String name;

    /**
     * Constructor method
     * Allows us to create new Cineplexes if our application scales in the future
     * @param cineplexCode This Cineplex's Code.Represented with a 3-Character String
     * @param name This Cineplex's name
     */
    public Cineplex(String cineplexCode, String name) {
        this.cineplexCode = cineplexCode;
        this.name = name;
    }

    /**
     * Getter and Setter Methods
     */

    /**
     * Gets the unique cineplexCode
     * @return cineplexCode as a 3-Character String
     */
    public String getCineplexCode() {
        return cineplexCode;
    }

    /**
     * Modifies the unique cineplexCode
     * @param cineplexCode This Cineplex's unique Code
     */
    public void setCineplexCode(String cineplexCode) {
        this.cineplexCode = cineplexCode;
    }

    /**
     * Gets the name of this Cineplex
     * @return this Cineplex's name
     */
    public String getName() {
        return name;
    }

    /**
     * Modifies the name of this Cineplex
     * @param name This Cineplex's name
     */
    public void setName(String name) {
        this.name = name;
    }
}
