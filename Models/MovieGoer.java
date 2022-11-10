package Models;

public class MovieGoer extends User {

    // inherits username, password, age from the user class

    private int movieGoerId;
    private int age;

    /**
     * constructor for MovieGoer
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
     * @return
     */
    public int getMovieGoerId(){
        return this.movieGoerId;
    }

    public int getAge(){
        return this.age;
    }

    public void setMovieGoerId(int movieGoerId){
        this.movieGoerId = movieGoerId;
    }

    public void setAge(int age){
        this.age = age;
    }
    
}
