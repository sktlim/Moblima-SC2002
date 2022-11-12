package Exceptions;

public class PasswordIncorrectException extends Exception{
    /** THIS EXCEPTION CLASS IS CALLED WHEN THE USER KEYS IN THE WRONG PASSWORD **/

    public PasswordIncorrectException() { // default constructor for custom error message
        super("Incorrect password entered.");
    }
}
