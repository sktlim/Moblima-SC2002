package Exceptions;

/**
 * Custom exception class for Review not found
 */
public class ReviewNotFoundException extends Exception {
    /** This Exception class is called when the user requests a read of database files and the requested item is not found **/
    public ReviewNotFoundException() { // default constructor for custom error message
        super("No reviews are found for this MovieID. Please try again.");
    }

}
