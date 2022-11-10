package Controllers;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Exceptions.ItemNotFoundException;
import java.util.InputMismatchException;
import Models.MovieGoer;

public class MovieGoerManager {

    public final static String FILENAME = "Databases/movieGoers.txt";
    public static final String SEPARATOR = "|";

    /**
     * Create method
     * @param sc takes in scanner to input fields within the function
     * Create new movieGoer and add it to the database
     */
    public static void createMovieGoer(Scanner sc){
        try {
            System.out.println("Enter Username: ");
            String username = sc.nextLine();
            int semaphore = 0; // password validation flag
            String password = "password";
            while(semaphore != 1){
                System.out.println("Enter Password: ");
                password = sc.nextLine();
                System.out.println("Confirm Password: ");
                String confirmation = sc.nextLine();
                if (password.equals(confirmation)){
                    semaphore = 1;
                }
                else{
                    System.out.println("Passwords do not match. Please re-enter.");
                }
            }
            System.out.println("Enter your age: ");
            int age = sc.nextInt();

            ArrayList ml = readMovieGoers(FILENAME);
            MovieGoer m1 = new MovieGoer(username, password, age,ml.size()+1);
            ml.add(m1);
            saveMovieGoers(FILENAME, ml);

        }
        catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println("Your input was of a wrong format! Please ensure that your input is an integer.");
        }
    }

    /**
     * Read method
     * Print method to display everything on the txt file database
     */
    public static void printMovieGoerList(){
        try{
            ArrayList ml = readMovieGoers(FILENAME);
            for (int i = 0 ; i < ml.size() ; i++) {
                MovieGoer mg = (MovieGoer)ml.get(i);
                System.out.println("MovieGoerID: " + mg.getMovieGoerId() );
                System.out.println("Username: " + mg.getUsername() );
                System.out.println("Password: " + mg.getPassword());
                System.out.println("Age: " + mg.getAge());
            }
        }
        catch (IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
    }

    /**
     * Read method
     * Find movie goer by movieGoerID
     * @param movieGoerID
     * @return object of type MovieGoer
     */
    public static MovieGoer findMovieGoer(int movieGoerID){
        try{
            ArrayList ml = readMovieGoers(FILENAME);
            boolean foundRequestedMovieGoer = false;
            for (int i = 0 ; i < ml.size() ; i++) {
                MovieGoer mg = (MovieGoer)ml.get(i);
                if (mg.getMovieGoerId()==movieGoerID){ // found
                    foundRequestedMovieGoer = true;
                    System.out.println("Movie Goer successfully found!");
                    return mg;
                }
            }
            throw new ItemNotFoundException();

        }
        catch (IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("MovieGoer not found > " + e.getMessage());
        }
        return null;
    }

    /**
     * Update method
     * @param movieGoerId takes in the movieGoerId for updating
     * @param sc takes in scanner for field update within the function
     */
    public static void updateMovieGoer(int movieGoerId, Scanner sc){
        int semaphore = 0; // flag variable for password validation
        String inputField = "0";
        int newAge = 0;

        try{
            System.out.println("Select field to change:");
            System.out.println("0: Username");
            System.out.println("1: Password");
            System.out.println("2: Age");

            int fieldEdit = sc.nextInt();
            sc.nextLine();
            if (fieldEdit == 0){
                System.out.println("Enter new Username: ");
                inputField = sc.nextLine();
            }
            else if (fieldEdit == 1){
                System.out.println("Enter new Password: ");
                inputField = sc.nextLine();
                System.out.println("Re-Enter new Password:");
                String check = sc.nextLine();
                if (check.equals(inputField)){
                    semaphore = 1;
                }
            }
            else if (fieldEdit == 2){
                System.out.println("Enter new Age: ");
                newAge = sc.nextInt();
                sc.nextLine();

            }


            ArrayList ml = readMovieGoers(FILENAME);
            boolean foundRequestedMovieGoer = false;
            for (int i=0; i<ml.size(); i++){
                MovieGoer mg = (MovieGoer) ml.get(i);
                if (mg.getMovieGoerId() == movieGoerId){ // found
                    foundRequestedMovieGoer = true;
                    if (fieldEdit == 0 && inputField != "0"){
                        mg.setUsername(inputField);
                        System.out.println("Username successfully updated.");

                    }
                    else if (fieldEdit == 1 && semaphore == 1 && inputField != "0"){
                        mg.setPassword(inputField);
                        System.out.println("Password successfully updated.");
                    }
                    else if (fieldEdit == 1 && semaphore == 0 && inputField != "0"){
                        System.out.println("Password does not match.");
                        System.out.println("Password update unsuccessful.");
                    }
                    else if (fieldEdit == 2 && newAge != 0){
                        mg.setAge(newAge);
                        System.out.println("Age successfully updated.");
                    }
                }
            }
            saveMovieGoers(FILENAME, ml);

            if (!foundRequestedMovieGoer) {
                throw new ItemNotFoundException();
            }
        }
        catch(IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("MovieGoer not found > " + e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println("Your input was of a wrong format! Please ensure that your input is an integer.");
        }
    }

    /**
     * Delete method
     * delete admin based on adminID
     * @param movieGoerId
     */
    public static void deleteMovieGoer(int movieGoerId){
        try{
            ArrayList ml = readMovieGoers(FILENAME);
            boolean foundRequestedMovieGoer = false;
            for (int i=0; i<ml.size(); i++){
                MovieGoer m = (MovieGoer) ml.get(i);
                if (m.getMovieGoerId() == movieGoerId){ // found
                    foundRequestedMovieGoer = true;
                    ml.remove(i);
                }
            }
            saveMovieGoers(FILENAME, ml);

            if (!foundRequestedMovieGoer) {
                throw new ItemNotFoundException();
            }
        }
        catch(IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("MovieGoer not found > " + e.getMessage());
        }
    }


    /** reading (helper func, declared as private as it is only called within this file)
     * This creates a list of instances of movieGoers */

    private static ArrayList readMovieGoers(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(filename);
        ArrayList alr = new ArrayList() ;// to store MovieGoer data

        for (int i = 0 ; i < stringArray.size() ; i++) {
            String st = (String)stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
            String  username = star.nextToken().trim();	// first token
            String  password = star.nextToken().trim();	// second token
            int age = Integer.parseInt(star.nextToken().trim()); //third token
            int  movieGoerId = Integer.parseInt(star.nextToken().trim()); // fourth token
            // create movieGoer object from file data
            MovieGoer mg = new MovieGoer(username, password, age, movieGoerId);
            // add to MovieGoer list
            alr.add(mg) ;
        }
        return alr ;
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
     * (helper func, declared as private as it is only called within this file)*/

    private static void saveMovieGoers(String filename, List al) throws IOException {
        List alw = new ArrayList() ;// to store movieGoer data

        for (int i = 0 ; i < al.size() ; i++) {
            MovieGoer mg = (MovieGoer)al.get(i);
            StringBuilder st =  new StringBuilder() ;
            st.append(mg.getUsername().trim());
            st.append(SEPARATOR);
            st.append(mg.getPassword().trim());
            st.append(SEPARATOR);
            st.append(mg.getAge());
            st.append(SEPARATOR);
            st.append(mg.getMovieGoerId());
            alw.add(st.toString()) ;
        }
        write(filename,alw);
    }


}


























