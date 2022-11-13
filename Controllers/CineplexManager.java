package Controllers;

import Models.Cineplex;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import Exceptions.ItemNotFoundException;

/**
 * This class handles the CRUD (create, read, update, delete) methods for Cineplexes. Only Admins have access to the methods within this Class.
 * This class implements the read and write methods in the Manager interface.
 */

public class CineplexManager implements Manager{ //crud

    /**
     * Path for the cineplexes.txt file
     */
    public final static String FILENAME = "Databases/cineplexes.txt";

    /**
     * Separator for parsing cineplexes.txt file
     */
    public final static  String SEPARATOR = "|";


    /**
     * Read method
     * Print method to display every entry in the cineplexes.txt file
     * IOException: CHECKED --> Thrown when assessing data in the specified file fails
     */
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
            System.out.println("IOException > " + e.getMessage());
        }
    }

    /**
     * Read Method, find a single Cineplex by Cineplex code
     * @param cineplexCode Unique 3-Character Code of the Cineplex
     * @return an object of type Cineplex
     * IOException: CHECKED --> Thrown when assessing data in the specified file fails
     * ItemNotFoundException: UNCHECKED --> Thrown during runtime when users enter a cineplexCode that does not exist in the database
     */
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
            throw new ItemNotFoundException();
        }
        catch (IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("Cineplex not found > " + e.getMessage());
        }
        return null;
    }

    /**
     * Read (Helper function)
     * Declared as private as it is only called within the scope of this file
     * @param filename This method assess data within the cineplexes.txt file
     * @return ArrayList of Cineplex objects
     * @throws IOException CHECKED --> Thrown when assessing data in the cineplexes.txt file fails
     */
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

    /** Write a fixed content to the given file
     * Declared as private as it is only called within the scope of this file
     * @param fileName This method assess data within the cineplexes.txt file
     * @param data List of Cineplex objects that contains the Cineplex instance to be updated
     * @throws IOException CHECKED --> Thrown when assessing data in the cineplexes.txt file fails
     */
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
     * Declared as private as it is only called within the scope of this file
     * @param fileName This method assess data within the cineplexes.txt file
     * @return a List of Cineplex objects
     * @throws IOException CHECKED --> Thrown when assessing data in the cineplexes.txt file fails
     */
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

    /**
     * Save to database (Helper function)
     * Declared as private as it is only called within the scope of this file
     * @param filename This method assess data within the cineplexes.txt file
     * @param al List of Cineplex objects to be saved into the database
     * @throws IOException CHECKED --> Thrown when assessing data in the cineplexes.txt file fails
     */

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
