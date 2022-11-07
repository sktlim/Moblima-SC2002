package Controllers;
import Models.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionManager { //CRUD

    public static void readTransactionHistory(int movieGoerId) {
        // read .txt file
    }

    /* main method will generate a new Date object to be passed into this function to be converted into a String for database storage */
    public static void createTransaction (int ticketId, int movieGoerId, Date date) {
        // convert Date object into a String field
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy; HH:mm:ss"); // instantiates the required formatter
        String dateTime = sf.format(date);

        // create the transaction instance and store in db
    }
}
