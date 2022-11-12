package Boundary;

import Controllers.MovieGoerManager;
import Controllers.SeatManager;
import Controllers.TicketManager;
import Controllers.TransactionManager;
import Models.MovieGoer;
import Models.Transaction;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GuestUI extends MovieGoerUI {
    public GuestUI() {
        super();
    }

    /* Overrides showSelections method in MovieGoerUI */
    public void showSelections() {
        System.out.println("Welcome to the Guest page.");
        System.out.println("SELECT ONE OF THE FOLLOWING OPTIONS");
        System.out.println("========================================");
        System.out.println("1: Print Shows and Show Ids");
        System.out.println("2: View the Seat Plan of a Show");
        System.out.println("3: Check if a Seat is available");
        System.out.println("4: Book a new Ticket");
        System.out.println("5: Display Top 5 Movies by Ticket Sales");
        System.out.println("6: Display Top 5 Movies by Rating");
        System.out.println("7: Create Review and Rating");
        System.out.println("8: Find Movie");
        System.out.println("========================================");
        System.out.println("9: Back to main menu");
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
                        bookTicket(sc, -1); // default guest id
                        while (true) {
                            try {
                                System.out.println("To continue your ticket purchase, you must sign up to be a movie goer.\n1: Proceed to sign up\n2: Back to main menu");
                                input = sc.nextLine();
                                option = checkInput(input);
                                if (option == 1) {
                                    int mid = MovieGoerManager.createMovieGoer(sc);
                                    // change all transactions with -1 in moviegoerid to mid
                                    TransactionManager.updateMovieGoerIdOfTransactions(-1, mid);
                                    // change all tickets with -1 in moviegoerid to mid
                                    TicketManager.updateMovieGoerIdOfTickets(-1, mid);
                                    System.out.println("Ticket has been successfully booked. You will be redirected back to the main menu.");
                                    sc.nextLine();
                                    return;
                                } else if (option == 2) {
                                    revertGuestBooking();
                                    return;
                                } else throw new IllegalArgumentException("Invalid input. Please try again.");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    case 5:
                        getTop5MoviesByTicketSales();
                        break;
                    case 6:
                        getTop5MoviesByRating();
                        break;
                    case 7:
                        listMovies();
                        createRatingAndReview(sc);
                        break;
                    case 8:
                        findMovie(sc);
                        break;
                    case 9:
                        return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void revertGuestBooking() {
        try {
            // remove transactions with -1 as moviegoer id
            TransactionManager.deleteTransaction(-1);
            // remove tickets with -1 as mid & get seats from the deleted tickets
            ArrayList deletedSeats = TicketManager.deleteTicket(-1);
            // update Seat plan to option 0
            for (int i=0; i<deletedSeats.size(); i++) {
                HashMap t_data = (HashMap) deletedSeats.get(i);
                SeatManager.updateSeatPlan((int)t_data.get("showId"),(String)t_data.get("seat"), 0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
