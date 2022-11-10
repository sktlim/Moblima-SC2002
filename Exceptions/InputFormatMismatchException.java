package Exceptions;

public class InputFormatMismatchException extends Exception {

    /** THIS EXCEPTION CLASS IS CALLED WHEN THE USER REQUESTS A READ OF DATABASE FILES AND THE REQUESTED ITEM IS NOT FOUND **/

    public InputFormatMismatchException() { // default constructor for custom
        super("The input format you provided is incorrect. PLease check the format carefully.");
    }

}
