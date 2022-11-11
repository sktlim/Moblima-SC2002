package Controllers;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * This class represents the Authentication Manager Class for movieGoers. This class has no fields and no instances will be created.
 * To invoke this class' methods, simply call MovieGoerAuthManager.method()
 */

public class MovieGoerAuthManager {

    /**
     * This class performs read operations on the movieGoers.txt database to validate movieGoers
     */
    public static final String FILENAME = "Databases/movieGoers.txt";

    /**
     * This static method serves to authenticate movieGoers based on username and password inputs.
     * These 2 params are queried in the movieGoers database
     * @param username Username input by user
     * @param password Password input by user
     * @return userId if movieGoer successfully authenticated. Else, return -1 to indicate that auth failed.
     * @throws IOException Checked exception: if this method fails to read database file
     * @throws FileNotFoundException Checked exception: if the specified database file is not found
     */
    public static int authenticateMovieGoer(String username, String password) throws IOException, FileNotFoundException {
        // This will reference one line at a time
        String line = null;
        int moviegoerId = -1;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(FILENAME);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                //create a token based on
                String [] token = line.split("\\|");
                // because you know first and second word of each line in
                // given file is id and password
                if (token[0].equals(username) && token[1].equals(password)){
                    moviegoerId = Integer.parseInt(token[3]);
                    return moviegoerId;
                }
            }

            // Close file
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) { System.out.println("Unable to open file '" + FILENAME + "'");}
        catch(IOException ex) { System.out.println("Error reading file '" + FILENAME + "'");}
        return -1;
    }

}


