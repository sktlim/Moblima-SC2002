package Boundary;
import Models.*;
import Controllers.*;

public class MovieGoerUI {

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
        System.out.println("1: View the Seat Plan of a Show");
        System.out.println("2: Check if a Seat is available");
        System.out.println("3: Book a new Ticket");
        System.out.println("4: Purchase a Booked Ticket");
        System.out.println("5: View my Booking History");
        System.out.println("6: Display Top 5 Movies by Ticket Sales");
        System.out.println("7: Display Top 5 Movies by Rating");
    }

    public void readSeatPlan(int showId) {
        SeatManager.readSeatPlan(showId);
    }

    public void isSeatAvail(int showId, String seat) {
        SeatManager.isSeatAvail(showId, seat);
    }

    public void bookTicket(int showId, int userId, String seat, Show.TheatreClass theatreClass) {
//        TicketManager.createTicket(showId, userId, seat, theatreClass);
    }

    public void purchaseTicket(int ticketId) {
        // allow user to purchase the ticket according to ticket.getPrice()
    }

    public void viewBookingHistory() {
        // call TransactionManager's static method
    }

    public static String[] getTop5MoviesByTicketSales() {
        return MovieManagerMovieGoer.getTop5MoviesByTicketSales();
    }

    public String[] getTop5MoviesByRating() {
        return MovieManagerMovieGoer.getTop5MoviesByRating();
    }

}
