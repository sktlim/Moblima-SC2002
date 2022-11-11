package Models;

/**
 * Represents a MovieGoer in our application.
 * This is for MovieGoers that are registered in our application.
 * Subclass of the base User class.
 */

public class MovieGoer extends User {

    /**
     * This class inherits the following attributes from the base User class:
     * username
     * password
     * age
     */

    /**
     * This is the uniqueId for the MovieGoer
     */
    private int movieGoerId;

    /**
     * This is the age of the MovieGoer
     * This field is necessary in determining ticket prices
     */
    private int age;

    /**
     * Constructor for MovieGoer to instantiate MovieGoer attribute in MovieGoerUI
     * @param movieGoerId
     */
    public MovieGoer(int movieGoerId) {
        this.movieGoerId = movieGoerId;
    }

    /**
     * Overloaded method for constructor of MovieGoer
     * @param username
     * @param password
     * @param age
     * @param movieGoerId
     */
    public MovieGoer(String username, String password, int age, int movieGoerId){
        super(username, password);
        this.age = age;
        this.movieGoerId = movieGoerId;
    }

    /**
     * Getter and Setter Methods
     */

    /**
     * Gets this MovieGoer's unique ID
     * @return this MovieGoer's ID
     */
    public int getMovieGoerId(){
        return this.movieGoerId;
    }

    /**
     * Gets this MovieGoer's age
     * @return this MovieGoer's age
     */
    public int getAge(){
        return this.age;
    }

    /**
     * Modifies this MovieGoer's unique ID
     * SHOULD NOT BE CALLED UNNECESSARILY
     * @param movieGoerId
     */
    public void setMovieGoerId(int movieGoerId){
        this.movieGoerId = movieGoerId;
    }

    /**
     * Modifies this MovieGoer's age
     * @param age
     */
    public void setAge(int age){
        this.age = age;
    }
    
}
