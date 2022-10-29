package Controllers;

import Models.*;

public class TicketManager {

    public final static String FILENAME = "Databases/tickets.txt";

    public int createTicket(int showId, int userId, String seat, TheatreClass theatreClass){
        // create method
        // return ticketId;
    }

    public void readTicket(int ticketId){
        //read method
        // print method for ticket information
    }

    public boolean updateTicket(int ticketId, int showId, int userId, String seat, TheatreClass theatreClass){
        //update method
        // true if success, false otherwise
    }

    public void deleteTicket(int ticketId){
        // delete method
    }

}
