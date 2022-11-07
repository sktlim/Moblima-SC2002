package Controllers;

import Models.*;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MovieManagerMovieGoer {

    public final static String FILENAME = "Databases/movies.txt";
    public final static  String SEPARATOR = "|";

    public static void getAllReview(int movieId) {
        // prints all the reviews related to a particular movie
    }

    public static void getOverallRatings(int movieId) {
        // prints the overall numerical ratings for a particular movie
        // return String "N/A" if no ratings are found for this movie
    }

    public static void addReview(int movieId, String review) {
        RatingAndReview ratingAndReview = new RatingAndReview(movieId); // calls overloaded constructor with only one param
        ratingAndReview.setReview(review);
    }

    public static void addRating(int movieId, int rating) {
        RatingAndReview ratingAndReview = new RatingAndReview(movieId); // calls overloaded constructor with only one param
        ratingAndReview.setRating(rating);
    }

    public static String[] getTop5MoviesByTicketSales() {
        String[] result = {"A"};
        return result; // placeholder
    }

    public static String[] getTop5MoviesByRating() {
        String[] result = {"A"};
        return result; // placeholder
    }

    /** Read method
     * Print method to display everything on the txt file database */
    public static void printMovieList(){
        try{
            ArrayList mov = readMovies(FILENAME);
            for (int i = 0 ; i < mov.size() ; i++) {
                Movie m = (Movie) mov.get(i);
                System.out.println("MovieID: " + m.getMovieId() );
                System.out.println("Movie Title: " + m.getMovieTitle() );
                System.out.println("Showing Status: " + m.getShowingStatus());
                System.out.println("Synopsis: " + m.getSynopsis() );
                System.out.println("Director: " + m.getDirector() );
                System.out.println("Cast: " + m.getCast() );
                System.out.println("Movie Runtime: " + m.getMovieRuntime() );
                System.out.println("Movie Rating: " + m.getMovieRating() );
                System.out.println("Movie Type: " + m.getMovieType() );
            }


        }
        catch (IOException e){

        }
    }


    /** reading (helper func, declared as protected as it is called within child file)
     * This creates a list of instances of movies */

    protected static ArrayList readMovies(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(filename);
        ArrayList mov = new ArrayList() ;// to store Movie data

        for (int i = 0 ; i < stringArray.size() ; i++) {
            String st = (String)stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
            String  movieTitle = star.nextToken().trim();	// first token
            Movie.ShowingStatus showingStatus = Movie.ShowingStatus.valueOf(star.nextToken().trim());	// second token, convert to type enum
            String synopsis = star.nextToken().trim(); // third token
            String  director = star.nextToken().trim(); // 4th token
            String cast = star.nextToken().trim(); // 5th token
            int movieRuntime = Integer.parseInt(star.nextToken().trim()); // 6th token
            Movie.MovieRating movieRating = Movie.MovieRating.valueOf(star.nextToken().trim()); // 7th token, convert to type enum
            Movie.MovieType movieType = Movie.MovieType.valueOf(star.nextToken().trim()); // 8th token
            int movieId = Integer.parseInt(star.nextToken().trim()); // 9th token
            // create movie object from file data

            Movie m = new Movie(movieId, movieTitle, showingStatus, synopsis, director, cast, movieRuntime, movieRating, movieType);
            // add to movie list
            mov.add(m) ;
        }
        return mov ;
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

    protected static void saveMovies(String filename, List al) throws IOException {
        List alw = new ArrayList() ;// to store movies data

        for (int i = 0 ; i < al.size() ; i++) {
            Movie m = (Movie)al.get(i);
            StringBuilder st =  new StringBuilder() ;
            st.append(m.getMovieTitle().trim());
            st.append(SEPARATOR);
            st.append(m.getShowingStatus().name().trim());
            st.append(SEPARATOR);
            st.append(m.getSynopsis().trim());
            st.append(SEPARATOR);
            st.append(m.getDirector().trim());
            st.append(SEPARATOR);
            st.append(m.getCast().trim());
            st.append(SEPARATOR);
            st.append(m.getMovieRuntime());
            st.append(SEPARATOR);
            st.append(m.getMovieRating().name().trim());
            st.append(SEPARATOR);
            st.append(m.getMovieType().name().trim());
            st.append(SEPARATOR);
            st.append(m.getMovieId());

            alw.add(st.toString()) ;
        }
        write(filename,alw);
    }
}
