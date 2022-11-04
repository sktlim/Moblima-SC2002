package Boundary;
import Models.*;

public class MovieGoerUI extends UserUI {

    private MovieGoer sessionMovieGoer;

    // constructor
    public MovieGoerUI(int userId) {
        this.sessionMovieGoer = new MovieGoer(userId);
    }

    // implementation of abstract method
    public void showSelections() {}
}
