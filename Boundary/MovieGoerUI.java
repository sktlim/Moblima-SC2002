package Boundary;
import Models.*;
import Controllers.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This Class represents the UI interface for MovieGoers. It extends the abstract UserUI class and provides the implementation for the showSelections and showUI methods.
 * This Class contains 2 key methods: showSelections and showUI to display the list of actions available for MovieGoers and to handle inputs.
 * The other methods below are helper functions that call the static methods of Controllers.
 */

public class MovieGoerUI extends UserUI {

    /**
     * Upon successful authentication, we store the movieGoerID of the current movieGoer as an attribute that will be passed as a param to the various functions below.
     */
    private MovieGoer sessionMovieGoer;

    /**
     * Default constructor invoked in GuestUI Class since no movieGoerID is tagged to a Guest.
     */
    public MovieGoerUI() {}

    /**
     * Constructor to initialize the current session movieGoerId
     * @param userId The movieGoerId of the current authenticated movieGoer
     */
    public MovieGoerUI(int userId) {
        this.sessionMovieGoer = new MovieGoer(userId);
    }

    /**
     * Implementation of abstract method in UserUI.
     * Displays the list of possible actions to the current authenticated movieGoer.
     * This method should be called repeatedly in the main While loop with each iteration.
     */
    public void showSelections() {
        System.out.println("Welcome to the MovieGoer page.");
        System.out.println("SELECT ONE OF THE FOLLOWING OPTIONS");
        System.out.println("========================================");
        System.out.println("1: Print Shows and Show Ids");
        System.out.println("2: View the Seat Plan of a Show");
        System.out.println("3: Check if a Seat is available");
        System.out.println("4: Book a new Ticket");
        System.out.println("5: View my Booking History");
        System.out.println("6: Display Top 5 Movies by Ticket Sales");
        System.out.println("7: Display Top 5 Movies by Rating");
        System.out.println("8: Create Review and Rating");
        System.out.println("9: Find Movie");
        System.out.println("========================================");
        System.out.println("10: Back to main menu");
    }

    /**
     * Implementation of abstract method in UserUI.
     * This method operates in sync with the showSelections method above to accept input from the current authenticated movieGoer.
     * @param sc Scanner object to allow movieGoers to make their selections.
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
                        printShowsId();
                        break;
                    case 2:
                        printShowsId();
                        System.out.println("Please enter the show id whose seat plan you would like to view.");
                        readSeatPlan(sc);
                        break;
                    case 3:
                        printShowsId();
                        isSeatAvail(sc);
                        break;
                    case 4:
                        printShowsId();
                        bookTicket(sc, sessionMovieGoer.getMovieGoerId());
                        break;
                    case 5:
                        viewBookingHistory(sessionMovieGoer.getMovieGoerId());
                        break;
                    case 6:
                        getTop5MoviesByTicketSales();
                        break;
                    case 7:
                        getTop5MoviesByRating();
                        break;
                    case 8:
                        listMovies();
                        createRatingAndReview(sc);
                        break;
                    case 9:
                        findMovie(sc);
                        break;
                    case 10:
                        return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Enables movieGoers to view the SeatPlan for a desired show.
     * @param sc Scanner object to allow movieGoers to make their selections.
     */
    public void readSeatPlan(Scanner sc) {
        try {
            String input = sc.nextLine();
            int sid = checkInput(input);
            SeatManager.readSeatPlan(sid);
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Enables movieGoers to check if a seat is available.
     * @param sc Scanner object to allow movieGoers to make their selections.
     */
    public void isSeatAvail(Scanner sc) {
        try {
            SeatManager.askSeatAvail(sc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Enables movieGoers to book a new ticket.
     * @param sc Scanner object to allow movieGoers to make their selections.
     * @param userId The movieGoerID of the currently authenticated movieGoer.
     */
    public void bookTicket(Scanner sc, int userId) {
        TicketManager.createTicket(sc, userId);
    }

    /**
     * Enables movieGoers to view their personal booking history.
     * @param userId The movieGoerID of the currently authenticated movieGoer.
     */
    public void viewBookingHistory(int userId) {
        // call TransactionManager's static method
        TransactionManager.printTransactionHistory(userId);
    }

    /**
     * Displays the Top 5 Movies ranked by ticket sales.
     */
    public static void getTop5MoviesByTicketSales() {
        MovieManagerMovieGoer.getTop5MoviesByTicketSales();
    }

    /**
     * Displays the Top 5 Movies ranked by their ratings.
     */
    public static void getTop5MoviesByRating() {
        MovieManagerMovieGoer.getTop5MoviesByRating();
    }

    /**
     * Enables movieGoers to rate and review a particular Movie.
     * @param sc Scanner object to allow movieGoers to make their selections.
     */
    public static void createRatingAndReview(Scanner sc) {
        RatingAndReviewManager.createReview(sc);
    }

    /**
     * Enables movieGoers to query a desired Movie.
     * @param sc Scanner object to allow movieGoers to make their selections.
     */
    public static void findMovie(Scanner sc){
        System.out.println("Enter the name of the movie you wish to find: ");
        String movieName = sc.nextLine();
        Movie m = MovieManagerMovieGoer.findMovie(movieName);
        if (m!=null){
            System.out.println("Movie found.");
            System.out.println("Movie Title: "+m.getMovieTitle());
            System.out.println("Showing Status: "+m.getShowingStatus());
            System.out.println("Synopsis: "+m.getSynopsis());
            System.out.println("Director: "+m.getDirector());
            System.out.println("Cast: "+m.getCast());
            System.out.println("Movie Runtime: "+m.getMovieRuntime());
            System.out.println("Movie Rating: "+m.getMovieRating());
            System.out.println("Movie Type:"+m.getMovieType());
        }
    }
}
