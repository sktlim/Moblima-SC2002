package Controllers;
import Exceptions.ItemNotFoundException;
import Models.*;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;


public class TransactionManager implements Manager{ //CRUD
    public static final String SEPARATOR = "|";
    public static final String FILENAME = "Databases/transactions.txt" ;


    /**
     * Create transaction in the transactions database
     * responsibility of main function to generate the ticketId and movieGoerID
     * dateTime is generated within this create method itself
     * @param t
     * @param movieGoerId
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
            String cinCode = s.getCineplex();
            String TID = cinCode+dateTimeNew;


            ArrayList tl = readTransactions(FILENAME);
            Transaction t1 = new Transaction(TID, t.getTicketId(), movieGoerId, dateTime);
            tl.add(t1);
            saveTransactions(FILENAME, tl);

        }
        catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
    }

    /**
     * Read method
     * Print the entire transaction list in the database
     */
    public static void printTransactionList(){
        try{
            ArrayList trans = readTransactions(FILENAME);
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

    /** Prints transaction history for a given user **/
    public static void printTransactionHistory(int movieGoerId){
        try{
            ArrayList trans = readTransactions(FILENAME);
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

    /** reading (helper func, declared as private as it is only called within this file)
     * This creates a list of instances of admins */

    private static ArrayList readTransactions(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(filename);
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

    /** saving
     * (helper func, declared as private as it is only called within this file)*/

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

}
