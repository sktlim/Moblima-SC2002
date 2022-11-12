package Controllers;
import Exceptions.ItemNotFoundException;
import Models.*;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Transaction Manager handles the creating, reading, updating, and deleting of transactions.
 */
public class TransactionManager implements Manager{ //CRUD
    /**
     * Separator for tokenising String from input file
     */
    public static final String SEPARATOR = "|";
    /**
     * Input file path
     */
    public static final String FILENAME = "Databases/transactions.txt" ;

    /**
     * Cineplex file path
     */
    public static final String CINEPLEX = "Databases/cineplexes.txt";


    /**
     * Create transaction in the transactions database
     * responsibility of main function to generate the ticketId and movieGoerID
     * dateTime is generated within this create method itself
     * @param t Ticket associated with particular transaction
     * @param movieGoerId Movie Goer making the particular transaction
     */
    public static void createTransaction(Ticket t, int movieGoerId){
        try {
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd;HH:mm"); // instantiates the required formatter
            String dateTime = sf.format(date);
            String dateTimeNew = dateTime.replaceAll("-", "");
            dateTimeNew = dateTimeNew.replaceAll(";", "");
            dateTimeNew = dateTimeNew.replaceAll(":","");
            int showID = t.getShowId();
            Show s = ShowManager.findShow(showID);
            String cineplex = s.getCineplex();
            String cinCode = "";
            ArrayList cins = readCineplexes(CINEPLEX);
            for (int i=0; i<cins.size();i++){
                Cineplex c = (Cineplex) cins.get(i);
                if (c.getName().equals(s.getCineplex())){
                    cinCode = c.getCineplexCode();
                }
            }
            if (cinCode.equals("")){
                throw new ItemNotFoundException();
            }
            String TID = cinCode+dateTimeNew;


            ArrayList tl = readTransactions();
            Transaction t1 = new Transaction(TID, t.getTicketId(), movieGoerId, dateTime);
            tl.add(t1);
            saveTransactions(FILENAME, tl);

        }
        catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
        catch(ItemNotFoundException e){
            System.out.println("Cineplex not found > " + e.getMessage());
        }
    }

    /**
     * Read method
     * Prints the entire transaction list in the database
     */
    public static void printTransactionList(){
        try{
            ArrayList trans = readTransactions();
            for (int i = 0 ; i < trans.size() ; i++) {
                Transaction t = (Transaction) trans.get(i);
                System.out.println("TID: " + t.getTID() );
                System.out.println("Ticket ID: " + t.getTicketId());
                System.out.println("Movie Goer ID: " + t.getMovieGoerId());
                System.out.println("Date/Time: " + t.getDateTime() );
            }
        }
        catch (IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
    }

    /**
     * Prints transaction history for a given user
     * @param movieGoerId Movie Goer ID
     * **/
    public static void printTransactionHistory(int movieGoerId){
        try{
            ArrayList trans = readTransactions();
            boolean foundUser = false;
            for (int i = 0 ; i < trans.size() ; i++) {
                Transaction t = (Transaction) trans.get(i);
                if (t.getMovieGoerId() == movieGoerId) { // found
                    foundUser = true;
                    System.out.println("TID: " + t.getTID() );
                    System.out.println("Ticket ID: " + t.getTicketId());
                    System.out.println("Movie Goer ID: " + t.getMovieGoerId());
                    System.out.println("Date/Time: " + t.getDateTime() );
                }
            }
            if (!foundUser) {
                throw new ItemNotFoundException();
            }
        }
        catch (IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e){
            System.out.println("MovieGoer not found > " + e.getMessage());
        }
    }

    /**
     * Reading (helper func, declared as private as it is only called within this file).
     * This creates a list of instances of admins.
     * @return Array list of transaction data
     * @throws IOException Exception throw in in event of I/O error
     */
    private static ArrayList readTransactions() throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(TransactionManager.FILENAME);
        ArrayList trans = new ArrayList() ;// to store shows data

        for (int i = 0 ; i < stringArray.size() ; i++) {
            String st = (String)stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
            String TID = star.nextToken().trim();
            int ticketId = Integer.parseInt(star.nextToken().trim());
            int movieGoerId = Integer.parseInt(star.nextToken().trim());
            String dateTime = star.nextToken().trim();
            // create transaction object from file data

            Transaction t = new Transaction(TID, ticketId, movieGoerId, dateTime);

            // add to show list
            trans.add(t) ;
        }
        return trans ;
    }

    /**
     * Write fixed content to the given file (helper func, declared as private as it is only called within this file).
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

    /** Read the contents of the given file(helper func, declared as private as it is only called within this file).
     * @param fileName File Name of content to be parsed
     * @return  List filled with data from file
     * @throws IOException Exception throw in in event of I/O error
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
     * Save transactions (helper function, declared as private as it is only called within this file)
     * @param filename File Name of content to be parsed
     * @param al Array list for building String to be written into file
     * @throws IOException Exception throw in in event of I/O error
     */
    private static void saveTransactions(String filename, List al) throws IOException { // edit for show files
        List alw = new ArrayList() ;// to store admins data

        for (int i = 0 ; i < al.size() ; i++) {
            Transaction t = (Transaction)al.get(i);
            StringBuilder st = new StringBuilder();
            st.append(t.getTID());
            st.append(SEPARATOR);
            st.append(t.getTicketId());
            st.append(SEPARATOR);
            st.append(t.getMovieGoerId());
            st.append(SEPARATOR);
            st.append(t.getDateTime());
            st.append(SEPARATOR);

            alw.add(st.toString()) ;

        }
        write(filename,alw);
    }
    protected static ArrayList readCineplexes(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(filename);
        ArrayList mov = new ArrayList() ;// to store Cineplex data

        for (int i = 0 ; i < stringArray.size() ; i++) {
            String st = (String)stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
            String  cinCode = star.nextToken().trim();	// first token
            String cinName = star.nextToken().trim(); // second token
            // create movie object from file data

            Cineplex c = new Cineplex(cinCode, cinName);

            // add to Cineplex list
            mov.add(c) ;
        }
        return mov ;
    }

    // used in GuestUI to delete transactions with -1 as moviegoerid
    public static void deleteTransaction(int tid) throws Exception {
        ArrayList trans = readTransactions();
        int count = -1;
        while (count != 0) {
            count = 0;
            for (int i = 0 ; i < trans.size() ; i++) {
                Transaction t = (Transaction) trans.get(i);
                if (t.getMovieGoerId() == tid) {
                    trans.remove(i);
                    count++;
                    break;
                }
            }
        }

        saveTransactions(FILENAME, trans);
    }

    // used in GuestUI to delete transactions with -1 as moviegoerid
    public static void updateMovieGoerIdOfTransactions(int oldMovieGoerId, int newMovieGoerId) throws Exception {
        ArrayList trans = readTransactions();
        for (int i = 0 ; i < trans.size() ; i++) {
            Transaction t = (Transaction) trans.get(i);
            if (t.getMovieGoerId() == oldMovieGoerId) {
                t.setMovieGoerId(newMovieGoerId);
            }
        }
        saveTransactions(FILENAME, trans);
    }
}
