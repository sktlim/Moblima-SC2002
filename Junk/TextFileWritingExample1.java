package Junk;
import java.io.FileWriter;
import java.io.IOException;
 
/**
 * This program demonstrates how to write characters to a text file.
 * @author www.codejava.net
 *
 */
public class TextFileWritingExample1 {
 
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("admins.txt", true);
            writer.write("\r\n"); 
            writer.write("Hello World");
            writer.write("\r\n");   // write new line
            writer.write("Good Bye!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
 
}