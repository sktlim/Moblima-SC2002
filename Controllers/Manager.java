package Controllers;

import java.io.IOException;
import java.util.List;

/**
 * This Class is an interface which serves to ensure that all Manager classes implement the read and write methods.
 * This Class is open for implementation by new Manager classes if our application scales to include more models and controllers.
 */

public interface Manager {
    /** Read the contents of the given file.
     * Implementation of interface guarantees the implementation of this function
     * Declared as private as it is only called within the scope of the file
     * @param fileName Specifies the database .txt file to be accessed
     * @return a List of model objects
     * @throws IOException CHECKED --> Thrown when assessing data in the specified file fails
     */
    public static List read(String fileName) throws IOException {return null;}

    /** Write a fixed content to the given file
     * Implementation of interface guarantees the implementation of this function
     * Declared as private as it is only called within the scope of the file
     * @param fileName Specifies the database .txt file to be accessed
     * @param data List of model objects that contains the model instance to be updated
     * @throws IOException CHECKED --> Thrown when assessing data in the specified file fails
     */
    public static void write(String fileName, List data) throws IOException  {}
}
