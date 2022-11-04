package Controllers;

import Models.*;

public class TicketManager {

    public final static String FILENAME = "Databases/tickets.txt";

    public static int createTicket(int showId, int userId, String seat, Show.TheatreClass theatreClass){
        // create method
        // return ticketId;
        return 1;
    }

    public static void readTicket(int ticketId){
        //read method
        // print method for ticket information
    }

    public static boolean updateTicket(int ticketId, int showId, int userId, String seat, Show.TheatreClass theatreClass){
        //update method
        // true if success, false otherwise
        return true;
    }

    public static void deleteTicket(int ticketId){
        // delete method
    }

}
