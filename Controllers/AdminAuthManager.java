package Controllers;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class AdminAuthManager {

    static String fileName = "./databases/admins.txt";

    // returns user id if exist. Else -1.
    public static int adminchecker(String username, String password) throws IOException, FileNotFoundException {
        // This will reference one line at a time
        String line = null;
        int userId = -1;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                //create a token based on
                String [] token = line.split("\\|");
                // because you know first and second word of each line in
                // given file is id and password
                if (token[0].equals(username) && token[1].equals(password)){
                    userId = Integer.parseInt(token[2]);
                    return userId;
                }
            }

            // Close file
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) { System.out.println("Unable to open file '" + fileName + "'");}
        catch(IOException ex) { System.out.println("Error reading file '" + fileName + "'");}
        return -1;
    }

}


