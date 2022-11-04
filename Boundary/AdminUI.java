package Boundary;
import Models.*;

public class AdminUI extends UserUI {

    private Admin sessionAdmin;

    // constructor
    public AdminUI(int userId) {
        this.sessionAdmin = new Admin(userId);
    }

    // implementation of abstract method
    public void showSelections() {
    }

    public void createMovie() {
    }

}
