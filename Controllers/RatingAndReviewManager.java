package Controllers;

import Models.Movie;
import Models.MovieGoer;
import Models.RatingAndReview;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import Exceptions.ItemNotFoundException;
import java.util.InputMismatchException;

import static Controllers.MovieManagerMovieGoer.readMovies;


public class RatingAndReviewManager {
    public final static String FILENAME = "Databases/ratingAndReviews.txt";
    public static final String SEPARATOR = "|";
    public final static String MOVIES = "Databases/movies.txt";

    /**
     * Create method
     * Create new movieGoer and add it to the database
     * @param sc takes in a scanner to instantiate fields within the function*/
    public static void createReview(Scanner sc){
        int semaphore = 0;
        try {
            int movieId = 0;
            while (semaphore == 0){
                System.out.println("Enter MovieID: ");
                movieId = sc.nextInt();
                sc.nextLine();
                ArrayList movieList = readMovies(MOVIES);
                for (int i = 0 ; i < movieList.size() ; i++) {
                    Movie m = (Movie) movieList.get(i);
                    if (m.getMovieId() == movieId) {
                        semaphore = 1;
                        System.out.println("You are now reviewing: " + m.getMovieTitle());
                    }
                    else{
                    }
                }
                if (semaphore == 0){
                    System.out.println("Invalid MovieID Entered. Please enter again.");
                }
            }
            System.out.println("Enter a rating for this movie (1-5): ");
            int rating = sc.nextInt();
            sc.nextLine();
            while (rating<1 || rating>5){
                System.out.println("Invalid Rating entered. Please enter again.");
                rating = sc.nextInt();
                sc.nextLine();
            }
            System.out.println("Enter your review for this movie.");
            String review = sc.nextLine();

            ArrayList revList = readReviews(FILENAME);
            RatingAndReview r1 = new RatingAndReview(revList.size()+1, movieId, review, rating);
            revList.add(r1);
            saveReviews(FILENAME, revList);
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
    public static void printRatingsAndReviewList(){
        try{
            ArrayList ml = readReviews(FILENAME);
            for (int i = 0 ; i < ml.size() ; i++) {
                RatingAndReview mg = (RatingAndReview) ml.get(i);
                System.out.println("Review ID: " + mg.getReviewId());
                System.out.println("Movie ID: " + mg.getMovieId() );
                System.out.println("Rating: " + mg.getRating() );
                System.out.println("Review: " + mg.getReview());
            }

        }
        catch (IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
    }



    /**
     * Update method
     * this updates the various field of review based on reviewId
     * @param reviewId
     * @param sc takes in scanner to update various fields within the function
     */
    public static void updateReview(int reviewId, Scanner sc){
        String inputField = "0";
        int inputInt = 0;

        try{
            System.out.println("Select field to change:");
            System.out.println("0: Movie ID");
            System.out.println("1: Review");
            System.out.println("2: Rating");

            int fieldEdit = sc.nextInt();
            sc.nextLine();
            if (fieldEdit == 0){
                System.out.println("Enter new Movie ID: ");
                inputInt = sc.nextInt();
                sc.nextLine();
            }
            else if (fieldEdit == 1){
                System.out.println("Enter new Review: ");
                inputField = sc.nextLine();
            }

            else if (fieldEdit == 2){
                System.out.println("Enter new Rating: ");
                inputInt = sc.nextInt();
                sc.nextLine();

            }


            ArrayList ml = readReviews(FILENAME);
            boolean foundReview = false;
            for (int i=0; i<ml.size(); i++){
                RatingAndReview mg = (RatingAndReview) ml.get(i);
                if (mg.getReviewId() == reviewId){ // false
                    foundReview = true;
                    if (fieldEdit == 0 && inputInt != 0){
                        mg.setMovieId(inputInt);
                        System.out.println("Movie ID successfully updated.");

                    }
                    else if (fieldEdit == 1 && inputField != "0"){
                        mg.setReview(inputField);
                        System.out.println("Review successfully updated.");
                    }
                    else if (fieldEdit == 2 && inputInt != 0){
                        mg.setRating(inputInt);
                        System.out.println("Rating successfully updated.");
                    }
                }
            }
            saveReviews(FILENAME, ml);

            if (!foundReview) {
                throw new ItemNotFoundException();
            }
        }
        catch(IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("No reviews found that match the given reviewId > " + e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println("Your input was of a wrong format! Please ensure that your input is an integer.");
        }
    }

    /**
     * Delete method
     * delete review based on reviewID
     * @param reviewId
     */
    public static void deleteReview(int reviewId){
        try{
            ArrayList ml = readReviews(FILENAME);
            boolean foundReview = false;
            for (int i=0; i<ml.size(); i++){
                RatingAndReview m = (RatingAndReview) ml.get(i);
                if (m.getReviewId() == reviewId){ // found
                    foundReview = true;
                    ml.remove(i);
                }
            }
            saveReviews(FILENAME, ml);

            if (!foundReview) {
                throw new ItemNotFoundException();
            }
        }
        catch(IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("No reviews found that match the given reviewId > " + e.getMessage());
        }

    }


    /** reading (helper func, declared as protected as it is
     * accessed within this package)
     * This creates a list of instances of reviews */

    protected static ArrayList readReviews(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(filename);
        ArrayList alr = new ArrayList() ;// to store MovieGoer data

        for (int i = 0 ; i < stringArray.size() ; i++) {
            String st = (String)stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
            int  movieID = Integer.parseInt(star.nextToken().trim());	// first token
            int rating = Integer.parseInt(star.nextToken().trim()); //second token
            String  review = star.nextToken().trim();	// third token
            int reviewId = Integer.parseInt(star.nextToken().trim());
            // create review object from file data
            RatingAndReview r = new RatingAndReview(reviewId,movieID,review,rating);
            // add to review list
            alr.add(r) ;
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

    /**
     * saving (helper func)
     * declared as private as it is only called within this file
     */
    private static void saveReviews(String filename, List al) throws IOException {
        List alw = new ArrayList() ;// to store review data

        for (int i = 0 ; i < al.size() ; i++) {
            RatingAndReview r = (RatingAndReview) al.get(i);
            StringBuilder st =  new StringBuilder() ;
            st.append(r.getMovieId());
            st.append(SEPARATOR);
            st.append(r.getRating());
            st.append(SEPARATOR);
            st.append(r.getReview().trim());
            st.append(SEPARATOR);
            st.append(r.getReviewId());
            st.append(SEPARATOR);
            alw.add(st.toString()) ;
        }
        write(filename,alw);
    }



}
