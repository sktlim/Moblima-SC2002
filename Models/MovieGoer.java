package Models;

public class MovieGoer extends User {

    // inherits username, password, age from the user class

    private int movieGoerId;
    private int age;

    // constructor to instantiate MovieGoer attribute in MovieGoerUI
    public MovieGoer(int movieGoerId) {
        this.movieGoerId = movieGoerId;
    }

    public MovieGoer(String username, String password, int age, int movieGoerId){
        super(username, password);
        this.age = age;
        this.movieGoerId = movieGoerId;
    }

    //getters setters
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
