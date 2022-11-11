package Controllers;

import Models.*;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

import Exceptions.ItemNotFoundException;


import static Controllers.RatingAndReviewManager.readReviews;
import static Controllers.TicketManager.readTickets;

/**
 * Manage movie goers and associated functions. Can contain multiple movie goers.
 */
public class MovieManagerMovieGoer implements Manager{
    /**
     * Path to movies.txt file
     */
    public final static String FILENAME = "Databases/movies.txt";
    /**
     * Separator for parsing .txt files.
     */
    public final static  String SEPARATOR = "|";
    /**
     * Path to ratingAndReviews.txt file
     */
    public final static String REVIEWS = "Databases/ratingAndReviews.txt";
    /**
     * Path to tickets.txt file
     */
    public final static String TICKETS = "Databases/tickets.txt";

    /**
     * Prints all available reviews for a given Movie.
     * Search by movieId.
     * @param movieId movie ID of associated movie
     */
    public static void getAllReview(int movieId) {
        // prints all the reviews related to a particular movie
        int reviewNum = 1;
        try{
            ArrayList al = readReviews(REVIEWS);
            for (int i=0 ;i<al.size(); i++){
                RatingAndReview r = (RatingAndReview) al.get(i);
                if (r.getMovieId() == movieId){
                    System.out.printf("Review %d: %s\n", reviewNum, r.getReview());
                    reviewNum++;
                }
            }
            if (reviewNum == 1){
                throw new ItemNotFoundException();
            }
        }
        catch(IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("Sorry, it seems like there are no available reviews for this movie. > " + e.getMessage());
        }
    }

    /**
     * Find the overall average rating for a given movie.
     * @param movieId search by movie ID
     * @return an average score of the rating of the movie
     */
    public static float getOverallRatings(int movieId){
        // return method
        float reviewNum = 0;
        float ratingTot = 0;
        try{
            ArrayList al = readReviews(REVIEWS);
            for (int i=0 ;i<al.size(); i++){
                RatingAndReview r = (RatingAndReview) al.get(i);
                if (r.getMovieId() == movieId){
                    ratingTot += r.getRating();
                    reviewNum++;
                }
            }

            if (reviewNum == 0) {
                throw new ItemNotFoundException();
            }
        }
        catch(IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("No ratings found for this movie > " + e.getMessage());
        }
        return ratingTot/reviewNum;
    }

    /**
     * Print the overall ratings of movieId
     * @param movieId movie ID of associated movie
     */
    public static void printOverallRatings(int movieId) {
        // prints the overall numerical ratings for a particular movie
        // return String "N/A" if no ratings are found for this movie
        float reviewNum = 0;
        float ratingTot = 0;
        try{
            ArrayList al = readReviews(REVIEWS);
            for (int i=0 ;i<al.size(); i++){
                RatingAndReview r = (RatingAndReview) al.get(i);
                if (r.getMovieId() == movieId){
                    ratingTot += r.getRating();
                    reviewNum++;
                }
            }
            if (reviewNum == 0){
                throw new ItemNotFoundException();
            }
            else{
                System.out.printf("The average rating for this movie is %.2f.\n", ratingTot/reviewNum);
            }
        }
        catch(IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("N/A > " + e.getMessage());
        }
    }

    /**
     * Add rating and review.
     * Calls RatingAndReviewManager's static method.
     * @param sc to pass into the createReview method
     */
    public static void addRatingAndReview(Scanner sc) {
        RatingAndReviewManager.createReview(sc);
    }

    /**
     * Get ticket sales by movie ID.
     * @param movieId search field
     * @return total number of tickets that the movie sold
     */
    public static int getTicketSales(int movieId){
        // return method
        int ticketSold = 0;
        try{
            ArrayList al = readTickets(TICKETS);
            boolean foundMovie = false;
            for (int i=0 ;i<al.size(); i++){
                Ticket t = (Ticket) al.get(i);
                Show s = ShowManager.findShow(t.getShowId());
                Movie m = MovieManagerAdmin.findMovie(s.getShowId());
                if (m.getMovieId() == movieId){ // found
                    foundMovie = true;
                    ticketSold++;
                }
            }

            if (!foundMovie) {
                throw new ItemNotFoundException();
            }
        }
        catch(IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("There was no movie found matching the given movieId > " + e.getMessage());
        }
        return ticketSold;
    }

    /**
     * Get top 5 movies by ticket sales.
     * Utilizes the getTicketSales function and puts this data into a hashmap,
     * where the movieId is the key and the tickets sold are the values.
     * Sorts the hashmap from largest to smallest.
     * Prints the top 5 movies by ticket sales.
     */
    public static void getTop5MoviesByTicketSales() {
        HashMap<Integer, Integer> hm = new HashMap<>();
        try{
            ArrayList al = readTickets(TICKETS);
            for (int i=0; i<al.size(); i++){
                Ticket t = (Ticket) al.get(i);
                int showId = t.getShowId();
                Show s = ShowManager.findShow(showId);
                int movieId = s.getMovieId();
                if (!hm.containsKey(movieId)){
                    hm.put(movieId, getTicketSales(movieId));
                }

            }
        }
        catch(IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, Collections.reverseOrder());
        for (Integer num : list) {
            for (Entry<Integer, Integer> entry : hm.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
        int numElements = 5;
        System.out.println("Top 5 movies by Ticket Sales: ");
        for (Integer key : sortedMap.keySet()) {
            if (numElements <= 0) {
                break;
            }
            Movie m = MovieManagerAdmin.findMovie(key);
            System.out.println(m.getMovieTitle());
            numElements--;
        }
    }

    /**
     * Get top 5 movies by rating
     * Utilizes the getOverallRatings function to put the overall ratings for
     * a movie (as values) with the movieID as the key.
     * Does a descending sort of the hashmap.
     * Prints the top 5 movies by rating.
     */
    public static void getTop5MoviesByRating() {
        HashMap<Integer, Float> hm = new HashMap<>();
        try {
            ArrayList al = readReviews(REVIEWS);
            for (int i = 0; i < al.size(); i++) {
                RatingAndReview r = (RatingAndReview) al.get(i);
                if (!hm.containsKey(r.getMovieId())) {
                    hm.put(r.getMovieId(), getOverallRatings(r.getMovieId()));
                }
            }
        } catch (IOException e) {

        }

        LinkedHashMap<Integer, Float> sortedMap = new LinkedHashMap<>();
        ArrayList<Float> list = new ArrayList<>();
        for (Map.Entry<Integer, Float> entry : hm.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, Collections.reverseOrder());
        for (Float num : list) {
            for (Entry<Integer, Float> entry : hm.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
//        System.out.println(sortedMap);
        int numElements = 5;
        System.out.println("Top 5 movies by Rating: ");
        for (Integer key : sortedMap.keySet()) {
            if (numElements <= 0) {
                break;
            }
            Movie m = MovieManagerAdmin.findMovie(key);
            System.out.println(m.getMovieTitle());
            numElements--;
        }
    }

    /**
     * Print method to display everything on the txt file database.
     */
    public static void printMovieList(){
        try{
            ArrayList mov = readMovies(FILENAME);
            System.out.println("MovieID | Movie Title | Showing Status | Synopsis | " +
                    "Director | Cast | Movie Runtime | Movie Rating | Movie Type");
            for (int i = 0 ; i < mov.size() ; i++) {
                Movie m = (Movie) mov.get(i);
                System.out.println(m.getMovieId() + " | " + m.getMovieTitle() + " | "
                + m.getShowingStatus() + " | " + m.getSynopsis() + " | " + m.getDirector() +
                        " | " + m.getCast() + " | " + m.getMovieRuntime() + " | "
                + m.getMovieRating() + " | " + m.getMovieType());
            }
        }
        catch (IOException e){

        }
    }

    /**
     * Reading helper function that creates a list of instances of movies
     * @param filename filename of file to be read
     * @return Array List with extracted values from file
     * @throws IOException I/O Error with input
     */
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

    /**
     * Write fixed content to the given file.
     * @param fileName filename of write location
     * @param data data for writing to file
     * @throws IOException I/O Error with input
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
     * Read the contents of the given file.
     * @param fileName filename of read location
     * @return list with read contents from file
     * @throws IOException I/O Error with input
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
     * Save function for movies. Converts from list to String format to subsequently be written to target file.
     * @param filename of write location
     * @param al list with new contents for saving
     * @throws IOException I/O Error with input
     */
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
