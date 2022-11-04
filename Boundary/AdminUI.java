package Boundary;
import Models.*;

public class AdminUI extends UserUI {

<<<<<<< HEAD
    private Admin sessionAdmin;

    // constructor
    public AdminUI(int userId) {
        this.sessionAdmin = new Admin(userId);
    }

    // implementation of abstract method
    public void showSelections() {
        ////
=======
public class AdminUI {

    public boolean addShowtime(){
        // T if success F if failure

        return true;

>>>>>>> 48e9b211de18a237fa2cf1c7b372848ab1ec7b51
    }

    public void createMovie() {}

}
