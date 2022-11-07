package Controllers;

import Models.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TicketManager {

    public final static String FILENAME = "Databases/tickets.txt";
    public static final String SEPARATOR = "|";
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

    /** reading (helper func, declared as private as it is only called within this file)
     * This creates a list of instances of movieGoers */

    private static ArrayList readTickets(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(filename);
        ArrayList alr = new ArrayList() ;// to store Tickets data

        for (int i = 0 ; i < stringArray.size() ; i++) {
            String st = (String)stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
            int ticketId = Integer.parseInt(star.nextToken().trim());	// first token
            int showId = Integer.parseInt(star.nextToken().trim());	// second token
            int userId = Integer.parseInt(star.nextToken().trim());
            String  seat = star.nextToken().trim();
            Ticket.UserAgeType userAgeType = Ticket.UserAgeType.valueOf(star.nextToken().trim());
            Ticket.DayType dayType = Ticket.DayType.valueOf(star.nextToken().trim());
            float price = Float.parseFloat(star.nextToken().trim());

            // create ticket object from file data
            Ticket t = new Ticket(ticketId, showId, userId, seat, userAgeType, dayType, price);
            // add to Tickers list
            alr.add(t) ;
        }
        return alr ;
    }

    /** Read the contents of the given file.
     * (helper func, declared as private as it is only called within this file)*/
    private static List read(String fileName) throws IOException {
        List data = new ArrayList() ;
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        try {
            while (scanner.hasNextLine()){
                data.add(scanner.nextLine());
            }
        }
        finally{
            scanner.close();
        }
        return data;
    }
}
