package Boundary;
import Models.*;
import Controllers.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This Class represents the UI interface for Admins. It extends the abstract UserUI class and provides the implementation for the showSelections and showUI methods.
 * This Class contains 2 key methods: showSelections and showUI to display the list of actions available for Admins and to handle inputs.
 * The other methods below are helper functions that call the static methods of Controllers.
 */

public class AdminUI extends UserUI{

    /**
     * Upon successful authentication, we store the AdminID of the current Admin as an attribute that will be passed as a param to the various functions below.
     */
    private Admin sessionAdmin;

    /**
     * Constructor to initialize the current session AdminId
     * @param userId The adminId of the current authenticated Admin
     */
    public AdminUI(int userId) {
        this.sessionAdmin = new Admin(userId);
    }

    /* implementation of abstract method
       this method should be called repeatedly in the main While loop */

    /**
     * Implementation of abstract method in UserUI.
     * Displays the list of possible actions to the current authenticated Admin.
     * This method should be called repeatedly in the main While loop with each iteration.
     */
    public void showSelections() {
        System.out.println("\nWelcome to the admin page.");
        System.out.println("SELECT ONE OF THE FOLLOWING OPTIONS");
        System.out.println("========================================");
        System.out.println("MOVIES");
        System.out.println("1: Create a New Movie");
        System.out.println("2: Update an Existing Movie");
        System.out.println("3: Delete an Existing Movie");
        System.out.println("4: Display all Movies");
        System.out.println("SHOWS");
        System.out.println("5: Create a New Show");
        System.out.println("6: Update an existing Show");
        System.out.println("7: Delete an existing Show");
        System.out.println("PRICES");
        System.out.println("8: Print Price List");
        System.out.println("9: Update Prices");
        System.out.println("REVIEWS");
        System.out.println("10: Print Reviews and Ratings List");
        System.out.println("11: Create Review");
        System.out.println("12: Update Review");
        System.out.println("13: Delete Review");
        System.out.println("ADMINS");
        System.out.println("14: Print Admin List");
        System.out.println("15: Create a New Admin");
        System.out.println("16: Update an Existing Admin");
        System.out.println("17: Delete an Existing Admin");
        System.out.println("========================================");
        System.out.println("18: Back to main menu");
    }

    /**
     * Implementation of abstract method in UserUI.
     * This method operates in sync with the showSelections method above to accept input from the current authenticated Admin.
     * @param sc Scanner object to allow Admins to make their selections.
     */
    public void showUI (Scanner sc) {
        while (true) {
            try {
                String input = null;
                int option = -1;

                showSelections();
                input = sc.nextLine();
                option = checkInput(input);
                switch(option) {
                    case 1:
                        createMovie(sc);
                        break;
                    case 2:
                        System.out.println("Please select the movie id you would like to update.");
                        printMovieId();
                        input = sc.nextLine();
                        int id = checkInput(input);
                        updateMovie(id, sc);
                        break;
                    case 3:
                        System.out.println("Please select the movie id you would like to delete.");
                        printMovieId();
                        input = sc.nextLine();
                        id = checkInput(input);
                        deleteMovie(id);
                        break;
                    case 4:
                        displayAllMovies();
                        break;
                    case 5:
                        printMovieId();
                        createShow(sc);
                        break;
                    case 6:
                        System.out.println("Please select the show id you would like to update.");
                        printShowsId();
                        input = sc.nextLine();
                        id = checkInput(input);
                        updateShow(id, sc);
                        break;
                    case 7:
                        System.out.println("Please select the show id you would like to delete.");
                        printShowsId();
                        input = sc.nextLine();
                        id = checkInput(input);
                        deleteShow(id);
                        break;
                    case 8:
                        TicketPriceManager.printPriceList();
                        break;
                    case 9:
                        TicketPriceManager.updatePrice(sc);
                        break;
                    case 10:
                        RatingAndReviewManager.printRatingsAndReviewList();
                        break;
                    case 11:
                        listMovies();
                        RatingAndReviewManager.createReview(sc);
                        break;
                    case 12:
                        RatingAndReviewManager.printRatingsAndReviewList();
                        System.out.println("Please select the review id you would like to update.");
                        input = sc.nextLine();
                        id = checkInput(input);
                        RatingAndReviewManager.updateReview(id, sc);
                        break;
                    case 13:
                        RatingAndReviewManager.printRatingsAndReviewList();
                        System.out.println("Please select the review id you would like to delete.");
                        input = sc.nextLine();
                        id = checkInput(input);
                        RatingAndReviewManager.deleteReview(id);
                        break;
                    case 14:
                        printAdminList();
                        break;
                    case 15:
                        createAdmin(sc);
                        break;
                    case 16:
                        System.out.println("Please select the admin id you would like to update. If unsure, please use option 7 to print the list of admins.");
                        input = sc.nextLine();
                        id = checkInput(input);
                        updateAdmin(id, sc);
                        break;
                    case 17:
                        System.out.println("Please select the admin id you would like to delete. If unsure, please use option 7 to print the list of admins.");
                        input = sc.nextLine();
                        id = checkInput(input);
                        deleteAdmin(id);
                        break;
                    case 18:
                        return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    /**
     * Display all movies (For administrator purposes, can see all movies including coming soon)
     */
    public void displayAllMovies(){
        MovieManagerAdmin.printMovieListAdmin();
    }

    /**
     * Helper function to print the movies and their associated movieIDs to assist Admins in selecting the IDs of their chosen movie.
     */
    private void printMovieId() {
        ArrayList movies = MovieManagerAdmin.getMovies();
        System.out.printf("%-30s | %-8s %n","Movie", "ID");
        System.out.println("-------------------------------------");
        for (int i=0; i<movies.size(); i++) {
            Movie m = (Movie)movies.get(i);
            System.out.printf("%-30s | %-8s %n",m.getMovieTitle(), m.getMovieId());
            String title = m.getMovieTitle();
            int id = m.getMovieId();
        }
    }

    /**
     * Enables Admins to list a new Movie. Invokes the static method of the MovieManagerAdmin class.
     * @param sc Scanner object to instantiate fields within the function.
     */
    public void createMovie(Scanner sc) {
        MovieManagerAdmin.createMovie(sc); // invokes the MovieManagerAdmin static method
    }

    /**
     * Enables Admins to update an existing Movie. Invokes the static method of the MovieManagerAdmin class.
     * @param movieId The ID of the movie to be updated.
     * @param sc Scanner object to instantiate fields within the function.
     */
    public void updateMovie(int movieId, Scanner sc) {
        MovieManagerAdmin.updateMovies(movieId, sc);
    }

    /**
     * Enables Admins to delete an existing Movie. Invokes the static method of the MovieManagerAdmin class.
     * @param movieId The ID of the movie to be deleted.
     */
    public void deleteMovie(int movieId) {
        MovieManagerAdmin.deleteMovie(movieId);
    }

    /**
     * Enables Admins to list a new Show. Invokes the static method of the ShowManager class.
     * @param sc Scanner object to instantiate fields within the function.
     */
    public void createShow(Scanner sc) {
        ShowManager.createShow(sc);
    }

    /**
     * Enables Admins to update an existing Show. Invokes the static method of the ShowManager class.
     * @param showId The ID of the show to be updated.
     * @param sc Scanner object to instantiate fields within the function.
     */
    public void updateShow(int showId, Scanner sc) {
        ShowManager.updateShows(showId, sc);
    }

    /**
     * Enables Admins to delete an existing Show. Invokes the static method of the ShowManager class.
     * @param showId The ID of the show to be deleted.
     */
    public void deleteShow(int showId) {
        ShowManager.deleteShow(showId);
    }

    /**
     * Enables an Admin to create another Admin account.
     * Declared private to prevent access outside of this Class.
     * @param sc Scanner object to instantiate fields within the function.
     */
    private void createAdmin(Scanner sc) {
        AdminManager.createAdmin(sc);
    }

    /**
     * Prints the list of existing Admins.
     * Declared private to prevent access outside of this Class.
     */
    private void printAdminList() {
        AdminManager.printAdminList();
    }

    /**
     * Enables an Admin to update an Admin account.
     * Declared private to prevent access outside of this Class.
     * @param adminId The ID of the Admin to be updated.
     * @param sc Scanner object to instantiate fields within the function.
     */
    private void updateAdmin(int adminId, Scanner sc) {
        AdminManager.updateAdmin(adminId, sc);
    }

    /**
     * Enables an Admin to delete an Admin account.
     * Declared private to prevent access outside of this Class.
     * @param adminId The ID of the Admin to be deleted.
     */
    private void deleteAdmin(int adminId) {
        AdminManager.deleteAdmin(adminId);
    }

}
