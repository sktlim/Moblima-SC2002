package Boundary;
import Models.*;
import Controllers.*;
import java.util.Scanner;


public class AdminUI extends UserUI {

    private Admin sessionAdmin;

    // constructor
    public AdminUI(int userId) {
        this.sessionAdmin = new Admin(userId);
    }

    /* implementation of abstract method
       this method should be called repeatedly in the main While loop */
    public void showSelections() {
        System.out.println("Welcome to the admin page.");
        System.out.println("SELECT ONE OF THE FOLLOWING OPTIONS");
        System.out.println("========================================");
        System.out.println("MOVIES");
        System.out.println("1: Create a New Movie");
        System.out.println("2: Update an Existing Movie");
        System.out.println("3: Delete an Existing Movie");
        System.out.println("SHOWS");
        System.out.println("4: Create a New Show");
        System.out.println("5: Update an existing Show");
        System.out.println("6: Delete an existing Show");
        System.out.println("ADMINS");
        System.out.println("7: Print Admin List");
        System.out.println("8: Create a New Admin");
        System.out.println("9: Update an Existing Admin");
        System.out.println("10: Delete an Existing Admin");
    }

    public void createMovie(Scanner sc) {
        MovieManagerAdmin.createMovie(sc); // invokes the MovieManagerAdmin static method
    }

    public void updateMovie(int movieId, Scanner sc) {
        MovieManagerAdmin.updateMovies(movieId, sc);
    }

    public void deleteMovie(int movieId) {
        MovieManagerAdmin.deleteMovie(movieId);
    }

    public int createShow(Scanner sc) {
        return 1; // Placeholder
    }

    public boolean updateShow(int showId, Scanner sc) {

        return true; // place holder
    }

    public void deleteShow(int showId) {
        ShowManager.deleteShow(showId);
    }

    public void createAdmin(Scanner sc) {
        AdminManager.createAdmin(sc);
    }

    public void printAdminList() {
        AdminManager.printAdminList();
    }

    public void updateAdmin(int adminId, Scanner sc) {
        AdminManager.updateAdmin(adminId, sc);
    }

    public void deleteAdmin(int adminId) {
        AdminManager.deleteAdmin(adminId);
    }

}
