package Boundary;
import Controllers.*;

public abstract class UserUI {

    public abstract void showSelections();

    public void listMovies() {
        MovieManagerAdmin.printMovieList();
    };


}
