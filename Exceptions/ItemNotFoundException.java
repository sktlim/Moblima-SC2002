package Exceptions;

/**
 * Custom exception class for Item not found
 */
public class ItemNotFoundException extends Exception {
    /** This Exception class is called when the user requests a read of database files and the requested item is not found **/
    public ItemNotFoundException() { // default constructor for custom error message
        super("The item requested is not found in the database. Please try again and ensure no spelling mistakes.");
    }

}
