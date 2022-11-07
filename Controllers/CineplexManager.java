package Controllers;

import Models.Cineplex;
import Models.RatingAndReview;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CineplexManager { //crud
    public final static String FILENAME = "Databases/cineplexes.txt";
    public final static  String SEPARATOR = "|";

    /** Read method
     * Print method to display everything on the txt file database */
    public static void printCineplexList(){
        try{
            ArrayList cin = readCineplexes(FILENAME);
            for (int i = 0 ; i < cin.size() ; i++) {
                Cineplex c = (Cineplex) cin.get(i);
                System.out.println("Cineplex Code: " + c.getCineplexCode() );
                System.out.println("Cineplex Name: " + c.getName() );
            }
        }
        catch (IOException e){
        }
    }
    /** Read method
     * Find cineplex by cineplex code */
    public static Cineplex findCineplex(String cineplexCode){
        try{
            ArrayList cin = readCineplexes(FILENAME);
            for (int i = 0 ; i < cin.size() ; i++) {
                Cineplex c = (Cineplex) cin.get(i);
                if (c.getCineplexCode()==cineplexCode){
                    System.out.println("Cineplex Successfully Found!");
                    return c;
                }
            }
        }
        catch (IOException e){
        }
        System.out.println("Cineplex not found!");
        return null;
    }

    /** reading (helper func, declared as protected as it is called within child file)
     * This creates a list of instances of movies */

    private static ArrayList readCineplexes(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(filename);
        ArrayList cin = new ArrayList() ;// to store cinplex data

        for (int i = 0 ; i < stringArray.size() ; i++) {
            String st = (String)stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
            String  cineplexCode = star.nextToken().trim();	// first token
            String cineplexName = star.nextToken().trim(); // second token
            // create cinplex object from file data

            Cineplex c = new Cineplex(cineplexCode, cineplexName);
            // add to movie list
            cin.add(c) ;
        }
        return cin ;
    }

    /** Write fixed content to the given file.
     * (helper func, declared as private as it is only called within this file)*/
    private static void write(String fileName, List data) throws IOException  {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));

        try {
            for (int i =0; i < data.size() ; i++) {
                out.println((String)data.get(i));
            }
        }
        finally {
            out.close();
        }
    }

    /** Read the contents of the given file.
     * (helper func, declared as private as it is only called within this file)*/
    private static List read(String fileName) throws IOException {
        List data = new ArrayList() ;
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        try {
            while (scanner.hasNextLine()){
                data.add(scanner.nextLine());
            }
        }
        finally{
            scanner.close();
        }
        return data;
    }

    /** saving
     * (helper func, declared as private as it is called by child)*/

    private static void saveCineplexes(String filename, List al) throws IOException {
        List alw = new ArrayList() ;// to store movies data

        for (int i = 0 ; i < al.size() ; i++) {
            Cineplex c = (Cineplex)al.get(i);
            StringBuilder st =  new StringBuilder() ;
            st.append(c.getCineplexCode().trim());
            st.append(SEPARATOR);
            st.append(c.getName().trim());
            st.append(SEPARATOR);

            alw.add(st.toString()) ;
        }
        write(filename,alw);
    }
}
