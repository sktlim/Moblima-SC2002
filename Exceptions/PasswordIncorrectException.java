package Exceptions;

/**
 *  Custom exception class for password incorrect
 */
public class PasswordIncorrectException extends Exception{
    /**
     * Custom exception class for password incorrect. Exception called during password verification.
     */
    public PasswordIncorrectException() { // default constructor for custom error message
        super("Password is incorrect. Please try again and ensure no spelling mistakes.");
    }
}
