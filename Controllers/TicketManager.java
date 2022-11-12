package Controllers;

import Models.*;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import Exceptions.ItemNotFoundException;
import java.util.InputMismatchException;

/**
 * Ticket Manager allows console user to create, read, update, and delete tickets.
 */
public class TicketManager implements Manager{
    /**
     * Path of prices.txt file.
     */
    public final static String FILENAME = "Databases/tickets.txt";
    /**
     * Separator for parsing prices.txt file.
     */
    public static final String SEPARATOR = "|";


    /** Create Ticket to put into the database.
     * UserID must be provided unless created by guest.
     * @param sc takes in scanner to instantiate various attributes within the function
     * @param userId user ID to be associated with ticket
     * @return ticketID if successfully created, otherwise return -1.
     */
    public static int createTicket(Scanner sc, int userId){
        // create method - should be called by other managers (not from UI)
        try {
            int semaphore = -1;
            int showId = -1;
            while (semaphore == -1) {
                try {
                    System.out.print("Enter the show ID: ");
                    String input = sc.nextLine();
                    showId = checkInput(input);
                    if (ShowManager.findShow(showId) == null) throw new IllegalArgumentException("Show not found! Please re-enter.");
                    semaphore = 1;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            Show s = ShowManager.findShow(showId);
            int movieId = s.getMovieId();
            Movie m = MovieManagerAdmin.findMovie(movieId);

            // show seat plan relating to a show id
            semaphore = -1;
            while (semaphore == -1) {
                try {
                    SeatManager.readSeatPlan(showId);
                    semaphore = 1;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            // get seat number
            semaphore = -1;
            String seat = null;
            String strDate = "";
            ArrayList tickets = readTickets(FILENAME);
            Ticket.DayType dayType = Ticket.DayType.DEFAULT;
            while (semaphore == -1) {
                try {
                    System.out.println("Note: Seat numbers are case-sensitive. Enter your seat number: ");
                    seat = sc.nextLine();
                    int seatAvail = SeatManager.isSeatAvail(showId, seat);
                    if (seatAvail == 0) throw new IllegalArgumentException("Seat has been taken! Please choose another seat.");
                    semaphore = 1;

                    String dateString = s.getDate();
                    Date dateFormat = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(dateFormat);
                    int day = cal.get(Calendar.DAY_OF_WEEK);
                    if (day == 1 || day == 7){
                        dayType = Ticket.DayType.WEEKEND;
                    }
                    else{
                        dayType = Ticket.DayType.WEEKDAY;
                    }
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    strDate = df.format(dateFormat);
                    // System.out.println("price: " + price);

                    // Check if ticket already exists. But also check before if seat available.
                    for (int i=0; i<tickets.size(); i++) {
                        Ticket tic = (Ticket) tickets.get(i);
                        if (tic.getShowId() == showId && tic.getSeat().equals(seat)) {
                            throw new Exception("Ticket with same show time and seat already exists!");
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            // get user age type
            semaphore = -1;
            Ticket.UserAgeType userAgeType = Ticket.UserAgeType.ADULT;
            while (semaphore == -1) {
                try {
                    System.out.println("Enter user age type: ");
                    System.out.println("0: STUDENT");
                    System.out.println("1: SENIOR");
                    System.out.println("2: STANDARD");
                    String input = sc.nextLine();
                    int ageTypeSelector = checkInput(input);
                    if (ageTypeSelector < 0 || ageTypeSelector > 2) throw new IllegalArgumentException("Invalid input. Please re-enter.");
                    switch(ageTypeSelector){
                        case 0:
                            userAgeType = Ticket.UserAgeType.STUDENT;
                            break;
                        case 1:
                            userAgeType = Ticket.UserAgeType.SENIOR;
                            break;
                        case 2:
                            userAgeType = Ticket.UserAgeType.ADULT;
                            break;
                    }
                    semaphore = 1;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            double price = TicketPriceManager.calculatePrice(s, userAgeType, strDate, m);
            int ticketId = tickets.size()+1;
            Ticket t = new Ticket(ticketId, showId, userId, seat, userAgeType, dayType, price);
            tickets.add(t);
            saveTickets(FILENAME, tickets);

            // COUPLE seats and SINGLE seats are booked differently
            String seatType = SeatManager.getSeatType(showId, seat);
            SeatManager.updateSeatPlan(showId, seat, 1);
            TransactionManager.createTransaction(t, userId);

            if (seatType.equals("COUPLEL")) {
                int ticketId2 = ticketId + 1;
                String seat2 = seat.charAt(0) + Integer.toString(Integer.parseInt(seat.substring(1))+1);
                Ticket t2 = new Ticket(ticketId2, showId, userId, seat2, userAgeType, dayType, price);
                SeatManager.updateSeatPlan(showId, seat2, 1);
                tickets.add(t2);
                saveTickets(FILENAME, tickets);
                TransactionManager.createTransaction(t2, userId);
            } else if (seatType.equals("COUPLER")) {
                int ticketId2 = ticketId + 1;
                String seat2 = seat.charAt(0) + Integer.toString(Integer.parseInt(seat.substring(1))-1);
                Ticket t2 = new Ticket(ticketId2, showId, userId, seat2, userAgeType, dayType, price);
                SeatManager.updateSeatPlan(showId, seat2, 1);
                tickets.add(t2);
                saveTickets(FILENAME, tickets);
                TransactionManager.createTransaction(t2, userId);
            }
            System.out.println("Ticket(s) has been booked!");

            return ticketId;

        } catch (Exception e) {
            System.out.println("Exception > " + e.getMessage());
            return -1;
        }
    }

    /**
     * Ensure input is valid.
     * @param input input String
     * @return returns 0 or 1
     * @throws Exception Error in method
     */
    private static int checkInput (String input) throws Exception {
        if (input.length() <= 1) return Integer.parseInt(input);
        else return Integer.parseInt(input.substring(0, 2));
    }

    /**
     * Read ticket by ticketID.
     * @param ticketId ticket ID to be read
     */
    public static void readTicket(int ticketId){
        try {
            ArrayList<Ticket> tickets = readTickets(FILENAME);
            int idx = -1;
            for (int i=0; i<tickets.size(); i++) {
                if (tickets.get(i).getTicketId() == ticketId) {
                    idx = i;
                }
            }
            if (idx == -1) System.out.println("Ticket does not exist!");
            else {
                Ticket t = tickets.get(idx);
                System.out.printf("Ticket details are as follows: Ticket id: %d, show id: %d, user id: %d, seat: %s, userAgeType: %s, userDayType: %s, price: %f \n", t.getTicketId(), t.getShowId(),
                        t.getUserId(), t.getSeat().trim(), t.getUserAgeType(), t.getDayType(), t.getPrice());
            }
        } catch (Exception e) {
            System.out.println("Exception > " + e.getMessage());
        }
    }

    /**
     * Find ticket by ticketID
     * @param ticketId ticket ID of ticket to be found
     * @return Object of type Ticket
     */
    public static Ticket findTicket(int ticketId) {
        // get a single Ticket instance
        try {
            ArrayList<Ticket> tickets = readTickets(FILENAME);
            int idx = -1;
            for (int i=0; i<tickets.size(); i++) {
                if (tickets.get(i).getTicketId() == ticketId) {
                    idx = i;
                }
            }
            if (idx == -1) System.out.println("Ticket does not exist!");
            else {
                return tickets.get(idx);
            }
        } catch (Exception e) {
            System.out.println("Exception > " + e.getMessage());
        }
        return null;
    }

    /**
     * Update ticket
     * @param sc
     * Asks for ticket ID to search for to update fields
     * Uses scanner to update these fields
     * @return true if success, false otherwise
     */
    public static boolean updateTicket(Scanner sc){
        //update method
        String inputField;
        try{
            System.out.println("Select Ticket id to change:");
            int tid = sc.nextInt();
            sc.nextLine();
            ArrayList<Ticket> tickets = readTickets(FILENAME);
            int idx = -1;
            for (int i=0; i<tickets.size(); i++) {
                if (tickets.get(i).getTicketId() == tid) {
                    idx = i;
                }
            }
            if (idx == -1) throw new Exception("Ticket does not exist!");
            Ticket t = tickets.get(idx);

            System.out.println("Select field to change:");
            System.out.println("0: Ticket Id");
            System.out.println("1: Show Id");
            System.out.println("2: User Id");
            System.out.println("3: Seat");
            System.out.println("4: User Age Type");
            System.out.println("5: Day Type");
            System.out.println("6: Price");

            int fieldEdit = sc.nextInt();
            sc.nextLine();

            switch (fieldEdit) {
                case 0:
                    System.out.println("Enter new Ticket Id: ");
                    t.setTicketId(sc.nextInt());
                    sc.nextLine();
                    break;
                case 1:
                    System.out.println("Enter new Show Id: ");
                    t.setShowId(sc.nextInt());
                    sc.nextLine();
                    break;
                case 2:
                    System.out.println("Enter new User Id: ");
                    t.setUserId(sc.nextInt());
                    sc.nextLine();
                    break;
                case 3:
                    System.out.println("Enter new Seat: ");
                    t.setSeat(sc.nextLine());
                case 4:
                    System.out.println("Select new user age type:");
                    System.out.println("0: STUDENT");
                    System.out.println("1: SENIOR");
                    System.out.println("2: ADULT");
                    int option = sc.nextInt();
                    sc.nextLine();
                    if (option == 0) t.setUserAgeType(Ticket.UserAgeType.STUDENT);
                    else if (option == 1) t.setUserAgeType(Ticket.UserAgeType.SENIOR);
                    else if (option == 2) t.setUserAgeType(Ticket.UserAgeType.ADULT);
                    break;
                case 5:
                    System.out.println("Select new day type:");
                    System.out.println("0: Weekday");
                    System.out.println("1: Weekend");
                    option = sc.nextInt();
                    sc.nextLine();
                    if (option == 0) t.setDayType(Ticket.DayType.WEEKDAY);
                    else if (option == 1) t.setDayType(Ticket.DayType.WEEKEND);
                    break;
                case 6:
                    System.out.println("Enter new price: "); // manual override method, should be deleted
                    t.setPrice(sc.nextFloat());
                    sc.nextLine();
                    break;
            }

            saveTickets(FILENAME, tickets);
            System.out.println("Ticket has been updated");
        }
        catch(Exception e){
            System.out.println("Exception > " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Delete ticket by ticketID
     * @param movieGoerId ticket ID to be deleted
     */
    public static ArrayList deleteTicket(int movieGoerId){
        // delete method
        ArrayList deletedSeats = new ArrayList();
        try {
            ArrayList<Ticket> tickets = readTickets(FILENAME);
            int count = -1;
            while (count != 0) {
                count = 0;
                for (int i=0; i<tickets.size(); i++) {
                    if (tickets.get(i).getUserId() == movieGoerId) {
                        Ticket t = tickets.get(i);
                        HashMap t_data = new HashMap();
                        tickets.remove(i);
                        t_data.put("seat", t.getSeat());
                        t_data.put("showId", t.getShowId());
                        deletedSeats.add(t_data);
                        count++;
                        break;
                    }
                }
            }
            // if (idx == -1)  throw new Exception("Ticket to be deleted could not be found!");
            saveTickets(FILENAME, tickets);
            return deletedSeats;
        } catch (Exception e) {
            System.out.println("Exception > " + e.getMessage());
        }
        return deletedSeats;
    }

    public static void updateMovieGoerIdOfTickets(int oldMovieGoerId, int newMovieGoerId){
        // delete method
        try {
            ArrayList<Ticket> tickets = readTickets(FILENAME);
            int idx = -1;
            for (int i=0; i<tickets.size(); i++) {
                if (tickets.get(i).getUserId() == oldMovieGoerId) {
                    idx = i;
                    Ticket t = tickets.get(i);
                    t.setUserId(newMovieGoerId);
                }
            }
            if (idx == -1)  throw new Exception("Ticket with old movie goer id could not be found!");
            saveTickets(FILENAME, tickets);
        } catch (Exception e) {
            System.out.println("Exception > " + e.getMessage());
        }
    }

    /** reading (helper func, declared as private as it is only called within this file)
     * This creates a list of instances of movieGoers
     * @param filename File to be read
     * @throws IOException I/O Error with input
     */

    protected static ArrayList readTickets(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(filename);
        ArrayList alr = new ArrayList() ;// to store Tickets data
        if (stringArray.get(0) == "") return alr; // to handle case when tickets.txt is empty

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
            double price = Double.parseDouble(star.nextToken().trim());

            // create ticket object from file data
            Ticket t = new Ticket(ticketId, showId, userId, seat, userAgeType, dayType, price);
            // add to Tickers list
            alr.add(t) ;
        }
        // System.out.println(alr.size());
        return alr ;
    }

    /**
     * Read the contents of the given file (helper func, declared as private as it is only called within this file).
     * @param fileName File Name of content to be parsed
     * @return  List filled with data from file
     * @throws IOException Exception throw in event of I/O error
     */
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

    /**
     * Save tickets to the database
     * @param filename File Name of content saved to
     * @param al Array list for building String to be written into file
     * @throws IOException Exception throw in in event of I/O error
     */
    private static void saveTickets(String filename, List al) throws IOException {
        List alw = new ArrayList() ;// to store tickets data

        for (int i = 0 ; i < al.size() ; i++) {
            Ticket t = (Ticket)al.get(i);
            StringBuilder st =  new StringBuilder() ;
            st.append(t.getTicketId());
            st.append(SEPARATOR);
            st.append(t.getShowId());
            st.append(SEPARATOR);
            st.append(t.getUserId());
            st.append(SEPARATOR);
            st.append(t.getSeat().trim());
            st.append(SEPARATOR);
            st.append(t.getUserAgeType());
            st.append(SEPARATOR);
            st.append(t.getDayType());
            st.append(SEPARATOR);
            st.append(t.getPrice());
            alw.add(st.toString()) ;
        }
        write(filename,alw);
    }

    /** Write fixed content to the given file.
     * (helper func, declared as private as it is only called within this file)
     * @param fileName File Name of content to be parsed
     * @param data List to be written into file with data
     * @throws IOException Exception throw in in event of I/O error
     */
    private static void write(String fileName, List data) throws IOException  {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));
        try {
            for (int i =0; i < data.size() ; i++) {
                out.println((String)data.get(i));
            }
        }
        finally {
            out.close();
        }
    }
}
