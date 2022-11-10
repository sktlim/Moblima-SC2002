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

public class TicketManager {
    public final static String FILENAME = "Databases/tickets.txt";
    public static final String SEPARATOR = "|";


    /** Create Ticket to put into the database
     * UserID must be provided unless created by guest
     * @param sc takes in scanner to instantiate various attributes within the function
     * @param userId
     * @return ticketID if successfully created, otherwise return -1.
     */
    public static int createTicket(Scanner sc, int userId){
        // create method - should be called by other managers (not from UI)
        try {
            System.out.println("Enter the show ID: ");
            int showId = sc.nextInt();
            sc.nextLine();
            while(ShowManager.findShow(showId)==null){
                System.out.println("Show not found! Please re-enter.");
                showId = sc.nextInt();
                sc.nextLine();
            }
            Show s = ShowManager.findShow(showId);
            int movieId = s.getMovieId();
            Movie m = MovieManagerAdmin.findMovie(movieId);
            System.out.println("Enter your seat number: ");
            String seat = sc.nextLine();
            System.out.println("Enter user age type: ");
            System.out.println("0: STUDENT");
            System.out.println("1: SENIOR");
            System.out.println("2: STANDARD");
            Ticket.UserAgeType userAgeType = Ticket.UserAgeType.ADULT;
            int ageTypeSelector = sc.nextInt();
            sc.nextLine();
            while (ageTypeSelector < 0 || ageTypeSelector > 2){
                System.out.println("Invalid input. Please re-enter.");
                ageTypeSelector = sc.nextInt();
                sc.nextLine();
            }
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


            //dayType generated by Date of Show (placeholder)
            String dateString = s.getDate();

            Date dateFormat = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateFormat);
            Ticket.DayType dayType = Ticket.DayType.DEFAULT;
            int day = cal.get(Calendar.DAY_OF_WEEK);
            if (day == 1 || day == 7){
                dayType = Ticket.DayType.WEEKEND;
            }
            else{
                dayType = Ticket.DayType.WEEKDAY;
            }
            DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
            String strDate = df.format(dateFormat);
            double price = TicketPriceManager.calculatePrice(s, userAgeType, strDate, m);
            ArrayList<Ticket> tickets = readTickets(FILENAME);
            int ticketId = tickets.size()+1;
            Ticket t = new Ticket(ticketId, showId, userId, seat, userAgeType, dayType, price);
            for (int i=0; i<tickets.size(); i++) {
                if (tickets.get(i).getShowId() == t.getShowId() && tickets.get(i).getSeat().equals(t.getSeat())) {
                    throw new Exception("ticket with same show time and seat already exists!");
                }
            }
            tickets.add(t);
            saveTickets(FILENAME, tickets);
            return ticketId;
        } catch (Exception e) {
            System.out.println("Exception > " + e.getMessage());
            return -1;
        }
    }

    /**
     * Read Method
     * Read ticket by ticketID
     * @param ticketId
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
     * @param ticketId
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
     * @param ticketId
     */
    public static void deleteTicket(int ticketId){
        // delete method
        try {
            ArrayList<Ticket> tickets = readTickets(FILENAME);
            int idx = -1;
            for (int i=0; i<tickets.size(); i++) {
                if (tickets.get(i).getTicketId() == ticketId) {
                    idx = i;
                }
            }
            if (idx == -1)  throw new Exception("ticket with same show time and seat already exists!");
            else tickets.remove(idx);
            saveTickets(FILENAME, tickets);
        } catch (Exception e) {
            System.out.println("Exception > " + e.getMessage());
        }
    }

    /** reading (helper func, declared as private as it is only called within this file)
     * This creates a list of instances of movieGoers */

    protected static ArrayList readTickets(String filename) throws IOException {
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
            double price = Double.parseDouble(star.nextToken().trim());

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

    /**
     * Save tickets to the database
     * @param filename
     * @param al
     * @throws IOException
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
     * (helper func, declared as private as it is only called within this file)*/
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
