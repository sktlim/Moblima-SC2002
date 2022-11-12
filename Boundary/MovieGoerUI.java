package Boundary;
import Models.*;
import Controllers.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieGoerUI extends UserUI {

    private MovieGoer sessionMovieGoer;

    // constructor
    public MovieGoerUI(int userId) {
        this.sessionMovieGoer = new MovieGoer(userId);
    }

    /* implementation of abstract method
       this method should be called repeatedly in the main While loop */
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
                        printShowsId();
                        break;
                    case 2:
                        printShowsId();
                        System.out.println("Please enter the show id whose seat plan you would like to view.");
                        readSeatPlan(sc);
                        break;
                    case 3:
                        printShowsId();
                        System.out.println("Which seat would you like to check is available?");
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
                    case 10:
                        return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private int checkInput (String input) throws Exception {
        if (input.length() <= 1) return Integer.parseInt(input);
        else return Integer.parseInt(input.substring(0, 2));
    }

    private void printShowsId() {
        ArrayList shows = ShowManager.getShows("Databases/shows.txt");
        System.out.printf("%-25s | %-8s | %-12s | %-10s | %-10s | %-30s | %-10s %n", "CINEPLEX", "THEATRE", "DATE", "START TIME", "END TIME", "MOVIE NAME", "ID");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        for (int i=0; i<shows.size(); i++) {
            Show s = (Show)shows.get(i);
            Movie m = MovieManagerAdmin.findMovie(s.getMovieId());
            System.out.printf("%-25s | %-8s | %-12s | %-10s | %-10s | %-30s | %-10s %n", s.getCineplex(), s.getTheatre(), s.getDate(), s.getStartTime(), s.getEndTime(),
                    m.getMovieTitle(), s.getShowId());
        }
        System.out.println("");
    }

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

    public void isSeatAvail(Scanner sc) {
        try {
            System.out.print("show id: ");
            String input = sc.nextLine();
            int sid = checkInput(input);
            System.out.print("seat: ");
            String seat = sc.nextLine();
            int isAvail = SeatManager.isSeatAvail(sid, seat);
            if (isAvail == 1) System.out.println("Seat is available!");
            else if (isAvail == 0) System.out.println("Seat has been taken!");
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void bookTicket(Scanner sc, int userId) {
        TicketManager.createTicket(sc, userId);
    }

    public void viewBookingHistory(int userId) {
        // call TransactionManager's static method
        TransactionManager.printTransactionHistory(userId);
    }

    public static void getTop5MoviesByTicketSales() {
        MovieManagerMovieGoer.getTop5MoviesByTicketSales();
    }

    public static void getTop5MoviesByRating() {
        MovieManagerMovieGoer.getTop5MoviesByRating();
    }

    public static void createRatingAndReview(Scanner sc) {
        RatingAndReviewManager.createReview(sc);
    }

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
