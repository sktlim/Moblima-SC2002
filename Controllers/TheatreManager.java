package Controllers;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import Models.Show;
import Models.Theatre;
import Exceptions.ItemNotFoundException;
import java.util.InputMismatchException;

/**
 * Theatre Manager allows console user to create, read, update, and delete theatres
 */
public class TheatreManager implements Manager { // crud
    /**
     * Path of prices.txt file.
     */
    public final static String FILENAME = "Databases/theatres.txt";
    /**
     * Separator for parsing prices.txt file.
     */
    public final static String SEPARATOR = "|";

    /**
     * Create method
     * Create new theatre and add it to the database
     * This method should not be accessed elsewhere as theatres are fixed
     * Hence, declared as private
     * @param sc takes in scanner for first initialization
     */
    private static void createTheatre(Scanner sc){
        try {
            System.out.println("Enter cineplex code: ");
            String cineplexcode = sc.nextLine();
            Show.TheatreClass theatreClass = Show.TheatreClass.STANDARD;
            int busy = 1;

            ArrayList al = readTheatres(FILENAME);
            Theatre a1 = new Theatre(al.size()+1,cineplexcode, theatreClass, busy);
            al.add(a1);
            saveTheatres(FILENAME, al);

        }
        catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println("Your input was of a wrong format!");
        }
    }


    /**
     * Read method
     * Find show by Cineplex Code and TheatreId
     * @param cineplexCode Cineplex code that theatre belongs to
     * @param theatreId Theatre ID of theatre
     * @return Object of type Theatre
     */
    public static Theatre findTheatre(String cineplexCode, int theatreId){
        try{
            ArrayList al = readTheatres(FILENAME);
            boolean foundTheatre = false;
            for (int i = 0 ; i < al.size() ; i++) {
                Theatre t = (Theatre)al.get(i);
                if (t.getTheatreId()==theatreId && t.getCineplexCode()==cineplexCode){ // found
                    foundTheatre = true;
                    System.out.println("Theatre successfully found!");
                    return t;
                }
            }
            throw new ItemNotFoundException();
        }
        catch (IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("Theatre not found > " + e.getMessage());
        }
        return null;
    }

    /**
     * Set theatre busy by finding a match to cineplexCode and theatreID.
     * Sets that theatre to busy.
     * @param cineplexCode Cineplex code that theatre belongs to
     * @param theatreId Theatre ID of theatre
     */
    public static void setBusy(String cineplexCode, int theatreId){
        try{
            ArrayList al = readTheatres(FILENAME);
            for (int i = 0 ; i < al.size() ; i++) {
                Theatre t = (Theatre)al.get(i);
                if (t.getTheatreId()==theatreId && t.getCineplexCode().equals(cineplexCode)){
                    t.setStatus(1);
                }
            }
            saveTheatres(FILENAME, al);
        }
        catch (IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
    }

    /** Set theatre free by finding a match to cineplexCode and theatreID.
     * Sets that theatre to free.
     * @param cineplexCode Cineplex code that theatre belongs to
     * @param theatreId Theatre ID of theatre
     */
    public static void setFree(String cineplexCode, int theatreId){
        try{
            ArrayList al = readTheatres(FILENAME);
            for (int i = 0 ; i < al.size() ; i++) {
                Theatre t = (Theatre)al.get(i);
                if (t.getTheatreId()==theatreId && t.getCineplexCode().equals(cineplexCode)){
                    t.setStatus(0);
                }
            }
            saveTheatres(FILENAME, al);
        }
        catch (IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
    }

    /** Read method.
     * Print method to display everything on the txt file database. */
    public static void printTheatreList(){
        try{
            ArrayList the = readTheatres(FILENAME);
            System.out.println("readTheatres");
            for (int i = 0 ; i < the.size() ; i++) {
                System.out.println("for");
                Theatre t = (Theatre) the.get(i);
                System.out.println("Cineplex Code: " + t.getCineplexCode() );
                System.out.println("Theatre Class: " + t.getTheatreClass() );
                System.out.println("Theatre busy: "+ t.getStatus());
                System.out.println("TheatreID: "+ t.getTheatreId());
            }
        }
        catch (IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
    }

    /**
     * Read theatres
     * @param filename File name of content to be read
     * @return ArrayList of theatres
     * @throws IOException I/O Error with input
     */
    private static ArrayList readTheatres(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(filename);
        ArrayList the = new ArrayList() ;// to store theatre data

        for (int i = 0 ; i < stringArray.size() ; i++) {
            String st = (String)stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
            String  cineplexCode = star.nextToken().trim();	// first token
            Show.TheatreClass theatreClass = Show.TheatreClass.valueOf(star.nextToken().trim()); // second token
            int isBusy = Integer.parseInt(star.nextToken().trim());
            int theatreId = Integer.parseInt(star.nextToken().trim());
            // create Theatre object from file data

            Theatre t = new Theatre(theatreId, cineplexCode, theatreClass, isBusy);

            // add to theatre list
            the.add(t) ;
//            System.out.println("added" + the.size());

        }
        return the ;
    }

    /** Write fixed content to the given file.
     * (helper func, declared as private as it is only called within this file)
     * @param fileName File Name of content to be parsed
     * @param data List to be written into file with data
     * @throws IOException Exception throw in in event of I/O error
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

    /**
     * Read the contents of the given file (helper func, declared as private as it is only called within this file).
     * @param fileName File Name of content to be parsed
     * @return  List filled with data from file
     * @throws IOException Exception throw in in event of I/O error
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
     * Save theatres to the database
     * @param filename File Name of content to be parsed
     * @param al Array list for building String to be written into file
     * @throws IOException Exception throw in in event of I/O error
     */
    private static void saveTheatres(String filename, List al) throws IOException {
        List alw = new ArrayList() ;// to store movies data

        for (int i = 0 ; i < al.size() ; i++) {
            Theatre t = (Theatre)al.get(i);
            StringBuilder st =  new StringBuilder() ;
            st.append(t.getCineplexCode().trim());
            st.append(SEPARATOR);
            st.append(t.getTheatreClass());
            st.append(SEPARATOR);
            st.append(t.getStatus());
            st.append(SEPARATOR);
            st.append(t.getTheatreId());
            st.append(SEPARATOR);

            alw.add(st.toString()) ;
        }
        write(filename,alw);
    }
}
