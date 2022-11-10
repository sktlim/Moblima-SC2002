package Exceptions;

public class ItemNotFoundException extends Exception {

    /** THIS EXCEPTION CLASS IS CALLED WHEN THE USER REQUESTS A READ OF DATABASE FILES AND THE REQUESTED ITEM IS NOT FOUND **/

    public ItemNotFoundException() { // default constructor for custom error message
        super("The item requested is not found in the database. Please try again and ensure no spelling mistakes.");
    }

}
