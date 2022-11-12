package Boundary;
import Models.*;
import Controllers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class AdminUI extends UserUI{

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
        System.out.println("PRICES");
        System.out.println("7: Print Price List");
        System.out.println("8: Update Prices");
        System.out.println("REVIEWS");
        System.out.println("9: Print Reviews and Ratings List");
        System.out.println("10: Create Review");
        System.out.println("11: Update Review");
        System.out.println("12: Delete Review");
        System.out.println("ADMINS");
        System.out.println("13: Print Admin List");
        System.out.println("14: Create a New Admin");
        System.out.println("15: Update an Existing Admin");
        System.out.println("16: Delete an Existing Admin");
        System.out.println("========================================");
        System.out.println("17: Back to main menu");
    }

    // User interface
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
                        printMovieId();
                        createShow(sc);
                        break;
                    case 5:
                        System.out.println("Please select the show id you would like to update.");
                        printShowsId();
                        input = sc.nextLine();
                        id = checkInput(input);
                        updateShow(id, sc);
                        break;
                    case 6:
                        System.out.println("Please select the show id you would like to delete.");
                        printShowsId();
                        input = sc.nextLine();
                        id = checkInput(input);
                        deleteShow(id);
                        break;
                    case 7:
                        TicketPriceManager.printPriceList();
                        break;
                    case 8:
                        TicketPriceManager.updatePrice(sc);
                        break;
                    case 9:
                        RatingAndReviewManager.printRatingsAndReviewList();
                        break;
                    case 10:
                        listMovies();
                        RatingAndReviewManager.createReview(sc);
                        break;
                    case 11:
                        RatingAndReviewManager.printRatingsAndReviewList();
                        System.out.println("Please select the review id you would like to update.");
                        input = sc.nextLine();
                        id = checkInput(input);
                        RatingAndReviewManager.updateReview(id, sc);
                        break;
                    case 12:
                        RatingAndReviewManager.printRatingsAndReviewList();
                        System.out.println("Please select the review id you would like to delete.");
                        input = sc.nextLine();
                        id = checkInput(input);
                        RatingAndReviewManager.deleteReview(id);
                        break;
                    case 13:
                        printAdminList();
                        break;
                    case 14:
                        createAdmin(sc);
                        break;
                    case 15:
                        System.out.println("Please select the admin id you would like to update. If unsure, please use option 7 to print the list of admins.");
                        input = sc.nextLine();
                        id = checkInput(input);
                        updateAdmin(id, sc);
                        break;
                    case 16:
                        System.out.println("Please select the admin id you would like to delete. If unsure, please use option 7 to print the list of admins.");
                        input = sc.nextLine();
                        id = checkInput(input);
                        deleteAdmin(id);
                        break;
                    case 17:
                        return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int checkInput (String input) throws IllegalArgumentException {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("Input is not a valid movie id. Please try again\n");
        }
    }
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

    private void printShowsId() {
//        ShowManager.printShowList();
        ArrayList shows = ShowManager.getShows("Databases/shows.txt");
        System.out.printf("%-25s | %-8s | %-12s | %-10s | %-10s | %-30s | %-10s %n", "CINEPLEX", "THEATRE", "DATE", "START TIME", "END TIME", "MOVIE NAME", "ID");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");        for (int i = 0; i < shows.size(); i++) {
            Show s = (Show) shows.get(i);
            StringBuilder sb = new StringBuilder();
            Movie m = MovieManagerAdmin.findMovie(s.getMovieId());
            System.out.printf("%-25s | %-8s | %-12s | %-10s | %-10s | %-30s | %-10s %n", s.getCineplex(), s.getTheatre(), s.getDate(), s.getStartTime(), s.getEndTime(),
                    m.getMovieTitle(), s.getShowId());
        }
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

    public void createShow(Scanner sc) {
        ShowManager.createShow(sc);
    }

    public void updateShow(int showId, Scanner sc) {
        ShowManager.updateShows(showId, sc);
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
