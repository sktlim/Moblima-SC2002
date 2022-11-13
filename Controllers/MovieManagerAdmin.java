package Controllers;
import Models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.ItemNotFoundException;
import java.util.InputMismatchException;

/**
 * This Class handles the CRUD (create, read, update, delete) methods for Movies that are authorized for Admins. This class extends the MovieManagerMovieGoer parent class to inherit the base methods that MovieGoers can perform.
 * The CRUD operations performed in this class generally handle 1 checked exception and 1 unchecked exception:
 * 1) IOException: CHECKED --> Thrown when assessing data in the specified file fails
 * 2) ItemNotFoundException: UNCHECKED --> Thrown during runtime when users enter a movieId that does not exist in the database
 */

public class MovieManagerAdmin extends MovieManagerMovieGoer {

    /**
     * Create method
     * Create new movie and add it to the database
     * @param sc takes in scanner to instantiate fields within the function
     * InputMismatchException: UNCHECKED --> Thrown when user inputs an invalid format
     */
    public static void createMovie(Scanner sc){
        try {
            System.out.println("Enter Movie Title: ");
            String movieTitle = sc.nextLine();

            String showStatusSelectorString = "-1";
            Movie.ShowingStatus showingStatus = Movie.ShowingStatus.DEFAULT;
            int flag = 0;
            while(flag == 0){
                try{
                    System.out.println("Enter Showing Status: ");
                    System.out.println("0: COMING_SOON");
                    System.out.println("1: PREVIEW");
                    System.out.println("2: NOW_SHOWING");
                    showStatusSelectorString = sc.nextLine();
                    int showStatusSelector = checkInput(showStatusSelectorString);
                    showingStatus = Movie.ShowingStatus.DEFAULT;
                    while(showStatusSelector<0 || showStatusSelector>2){
                        throw new Exception();
                    }
                    flag = 1;
                    switch(showStatusSelector){
                        case 0:
                            showingStatus = Movie.ShowingStatus.COMING_SOON;
                            break;
                        case 1:
                            showingStatus = Movie.ShowingStatus.PREVIEW;
                            break;

                        case 2:
                            showingStatus = Movie.ShowingStatus.NOW_SHOWING;
                            break;
                    }
                }
                catch (Exception e) {
                    System.out.println("Invalid Input. Please re-enter");
                }
            }


            System.out.println("Enter Synopsis: ");
            String synopsis = sc.nextLine();
            System.out.println("Enter Director: ");
            String director = sc.nextLine();
            System.out.println("Enter Cast: ");
            String cast = sc.nextLine();
            flag = 0;
            int movieRuntime = 0;
            while(flag==0){
                try{
                    System.out.println("Enter Movie Runtime: ");
                    String input = sc.nextLine();
                    movieRuntime = checkInput(input);
                    flag = 1;
                }
                catch(Exception e){
                    System.out.println("Invalid Input. Please re-enter.");
                }
            }

            flag = 0;
            Movie.MovieRating movieRating = Movie.MovieRating.DEFAULT;

            while(flag == 0){
                try{
                    System.out.println("Enter Movie Rating: ");
                    System.out.println("0: G");
                    System.out.println("1: PG13");
                    System.out.println("2: NC16");
                    System.out.println("3: M18");
                    System.out.println("4: R21");
                    String input = sc.nextLine();
                    int movieRatingSelector = checkInput(input);
                    while(movieRatingSelector<0 || movieRatingSelector>4){
                        throw new Exception();
                    }
                    switch(movieRatingSelector){
                        case 0:
                            movieRating = Movie.MovieRating.G;
                            break;
                        case 1:
                            movieRating = Movie.MovieRating.PG13;
                            break;
                        case 2:
                            movieRating = Movie.MovieRating.NC16;
                            break;
                        case 3:
                            movieRating = Movie.MovieRating.M18;
                            break;
                        case 4:
                            movieRating = Movie.MovieRating.R21;
                            break;
                    }
                    flag = 1;
                }
                catch(Exception e){
                    System.out.println("Invalid Input. Please re-enter.");
                }
            }

            flag = 0;
            Movie.MovieType movieType = Movie.MovieType.DEFAULT;
            while (flag == 0){
                try{
                    System.out.println("Enter Movie Type: ");
                    System.out.println("0: 2D");
                    System.out.println("1: 3D");
                    System.out.println("2: BLOCKBUSTER");
                    String input = sc.nextLine();
                    int movieTypeSelector = checkInput(input);
                    while(movieTypeSelector<0||movieTypeSelector>2){
                        throw new Exception("Movie type not valid");
                    }
                    switch(movieTypeSelector){
                        case 0:
                            movieType = Movie.MovieType.TWO_D;
                            break;
                        case 1:
                            movieType = Movie.MovieType.THREE_D;
                            break;
                        case 2:
                            movieType = Movie.MovieType.BLOCKBUSTER;
                            break;
                    }
                    flag = 1;
                }
                catch(Exception e){
                    System.out.println("Invalid Input. Please re-enter");
                }
            }

            String endOfShowingDate = "";
            System.out.println("Enter the Movie's End of Showing Date: (format DD/MM/YYYY)");
            endOfShowingDate = sc.nextLine();

            System.out.println("Movie successfully created!");
            ArrayList al = readMovies(FILENAME);
            Movie mFinal = (Movie)al.get(al.size()-1);
            int finalMovieId = mFinal.getMovieId();
            Movie m1 = new Movie(finalMovieId+1, movieTitle, showingStatus, synopsis, director, cast, movieRuntime, movieRating, movieType, endOfShowingDate);
            al.add(m1);
            saveMovies(FILENAME, al);

        }
        catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println("Your input was of a wrong format! Please ensure that your input is an integer.");
        }
    }

    /**
     * Update method
     * Allows admins to update a movie with new fields
     * @param movieId update movie in the database by movieId
     * @param sc takes in scanner to update the various fields
     */
    public static void updateMovies(int movieId, Scanner sc){
        String inputField = "0";
        Movie.ShowingStatus showingStatus = Movie.ShowingStatus.DEFAULT;
        int runTime = 0;
        Movie.MovieRating movieRating = Movie.MovieRating.DEFAULT;
        Movie.MovieType movieType = Movie.MovieType.DEFAULT;

        try{
            int flag = 0;
            int fieldEdit = 0;
            while(flag == 0){
                try{
                    System.out.println("Select field to change:");
                    System.out.println("0: Movie Title");
                    System.out.println("1: Showing Status");
                    System.out.println("2: Synopsis");
                    System.out.println("3: Director");
                    System.out.println("4: Cast");
                    System.out.println("5: Runtime");
                    System.out.println("6: Rating");
                    System.out.println("7: Movie Type");
                    System.out.println("8: End of Showing Date");
                    String input = sc.nextLine();
                    fieldEdit = checkInput(input);
                    while(fieldEdit<0 || fieldEdit > 8){
                        throw new Exception();
                    }
                    flag = 1;
                }
                catch(Exception e){
                    System.out.println("Invalid Input. Please re-enter.");
                }
            }

            switch(fieldEdit){
                case 0: //edit movie title
                    System.out.println("Enter new Movie Title: ");
                    inputField = sc.nextLine();
                    break;
                case 1: //edit showing status
                    flag = 0;
                    int showStatusSelector = 0;
                    while(flag == 0){
                        try{
                            System.out.println("Enter Movie Showing Status: ");
                            System.out.println("0: COMING_SOON");
                            System.out.println("1: PREVIEW");
                            System.out.println("2: NOW_SHOWING");
                            System.out.println("3: SHOWING_ENDED");
                            String input = sc.nextLine();
                            showStatusSelector = checkInput(input);
                            while(showStatusSelector<0 || showStatusSelector>3){
                                throw new Exception();
                            }
                        }
                        catch(Exception e){
                            System.out.println("Invalid Input. Please re-enter.");
                        }
                        flag = 1;
                    }
                    switch(showStatusSelector){
                        case 0:
                            showingStatus = Movie.ShowingStatus.COMING_SOON;
                            break;

                        case 1:
                            showingStatus = Movie.ShowingStatus.PREVIEW;
                            break;

                        case 2:
                            showingStatus = Movie.ShowingStatus.NOW_SHOWING;
                            break;
                        case 3:
                            showingStatus = Movie.ShowingStatus.SHOWING_ENDED;
                            break;
                    }
                    break;

                case 2: // edit synopsis
                    System.out.println("Enter Synopsis: ");
                    inputField = sc.nextLine();
                    break;


                case 3: // edit director
                    System.out.println("Enter Director: ");
                    inputField = sc.nextLine();
                    break;

                case 4: // edit cast
                    System.out.println("Enter Cast: ");
                    inputField = sc.nextLine();
                    break;


                case 5: // edit run time
                    flag = 0;
                    while(flag == 0){
                        try{
                            System.out.println("Enter Movie Runtime: ");
                            String input = sc.nextLine();
                            runTime = checkInput(input);
                            flag = 1;
                        }
                        catch(Exception e){
                            System.out.println("Invalid Input. Please re-enter.");
                        }
                    }
                    break;

                case 6: // edit rating
                    flag = 0;
                    int movieRatingSelector=0;
                    while(flag == 0){
                        try{
                            System.out.println("Enter Movie Rating: ");
                            System.out.println("0: G");
                            System.out.println("1: PG13");
                            System.out.println("2: NC16");
                            System.out.println("3: M18");
                            System.out.println("4: R21");
                            String input = sc.nextLine();
                            movieRatingSelector = checkInput(input);
                            while(movieRatingSelector<0 || movieRatingSelector>4){
                                throw new Exception();
                            }
                            flag = 1;
                        }
                        catch(Exception e){
                            System.out.println("Invalid Input. Please re-enter.");
                        }

                    }

                    switch(movieRatingSelector){
                        case 0:
                            movieRating = Movie.MovieRating.G;
                            break;
                        case 1:
                            movieRating = Movie.MovieRating.PG13;
                            break;
                        case 2:
                            movieRating = Movie.MovieRating.NC16;
                            break;
                        case 3:
                            movieRating = Movie.MovieRating.M18;
                            break;
                        case 4:
                            movieRating = Movie.MovieRating.R21;
                            break;
                    }
                    break;



                case 7: // edit movie type
                    flag = 0;
                    int movieTypeSelector = 0;
                    while(flag == 0){
                        try{
                            System.out.println("Enter Movie Type: ");
                            System.out.println("0: 2D");
                            System.out.println("1: 3D");
                            System.out.println("2: BLOCKBUSTER");
                            String input = sc.nextLine();
                            movieTypeSelector = checkInput(input);
                            while(movieTypeSelector<0 || movieTypeSelector>2){
                                throw new Exception();
                            }
                            flag = 1;
                        }
                        catch(Exception e){
                            System.out.println("Invalid Input. Please re-enter.");
                        }
                    }

                    switch(movieTypeSelector){
                        case 0:
                            movieType = Movie.MovieType.TWO_D;
                            break;
                        case 1:
                            movieType = Movie.MovieType.THREE_D;
                            break;
                        case 2:
                            movieType = Movie.MovieType.BLOCKBUSTER;
                            break;
                    }
                    break;

                case 8: // edit end of showing date
                    System.out.println("Enter the Movie's Updated End of Showing Date: (format DD/MM/YYYY)");
                    inputField = sc.nextLine();
                    break;
            }


            ArrayList ml = readMovies(FILENAME);
            boolean foundMovie = false;
            for (int i=0; i<ml.size(); i++){
                Movie m = (Movie) ml.get(i);
                if (m.getMovieId() == movieId){ // found
                    foundMovie = true;
                    switch(fieldEdit){
                        case 0:
                            m.setMovieTitle(inputField);
                            System.out.println("Movie Title successfully updated.");
                            break;
                        case 1:
                            m.setShowingStatus(showingStatus);
                            System.out.println("Showing Status successfully updated.");

                            break;

                        case 2:
                            m.setSynopsis(inputField);
                            System.out.println("Synopsis successfully updated.");
                            break;

                        case 3:
                            m.setDirector(inputField);
                            System.out.println("Director successfully updated.");

                            break;

                        case 4:
                            m.setCast(inputField);
                            System.out.println("Cast successfully updated.");
                            break;

                        case 5:
                            m.setMovieRuntime(runTime);
                            System.out.println("Runtime successfully updated.");
                            break;

                        case 6:
                            m.setMovieRating(movieRating);
                            System.out.println("Movie Rating successfully updated.");
                            break;

                        case 7:
                            m.setMovieType(movieType);
                            System.out.println("Movie Type successfully updated.");
                            break;

                        case 8:
                            m.setEndOfShowingDate(inputField);
                            System.out.println("End of Showing Date successfully updated.");
                            break;
                    }
                }
            }
            saveMovies(FILENAME, ml);

            if (!foundMovie) {
                throw new ItemNotFoundException();
            }
        }
        catch(IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("Movie not found > " + e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println("Your input was of a wrong format! Please ensure that your input is an integer.");
        }
    }

    /**
     * Display all movies on the database including PREVIEW and COMING_SOON (For administrator purposes)
     */
    public static void printMovieListAdmin(){
        try{
            ArrayList mov = readMovies(FILENAME);
            System.out.printf("%-10s | %-40s | %-15s | %-220s | %-23s | %-125s | %-13s | %-13s | %-15s | %-10s %n",
                    "MovieID", "Movie Title", "Showing Status", "Synopsis", "Director", "Cast", "Movie Runtime", "Movie Rating", "Movie Type", "End of Showing Date");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (int i = 0 ; i < mov.size() ; i++) {
                Movie m = (Movie) mov.get(i);
                System.out.printf("%-10s | %-40s | %-15s | %-220s | %-23s | %-125s | %-13s | %-13s | %-15s | %-10s %n",
                        m.getMovieId(), m.getMovieTitle(), m.getShowingStatus(), m.getSynopsis(), m.getDirector(),
                        m.getCast(), m.getMovieRuntime(), m.getMovieRating(), m.getMovieType(), m.getEndOfShowingDate());

            }
        }
        catch (IOException e){

        }
    }

    /**
     * Read method
     * Find a single movie by movieId
     * @param movieID The movieID of the movie to be queried
     * @return Object of type Movie if successful search, else return null
     */
    public static Movie findMovie(int movieID){
        try{
            ArrayList mov = readMovies(FILENAME);
            for (int i = 0 ; i < mov.size() ; i++) {
                Movie m = (Movie) mov.get(i);
                if (m.getMovieId()==movieID){ // found
                    return m;
                }
            }
            throw new ItemNotFoundException();
        }
        catch (IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("Movie not found > " + e.getMessage());
        }
        return null;
    }

    /**
     * Delete method
     * Delete a single Movie based on movieId
     * @param movieId The movieId of the movie to be deleted
     */
    public static void deleteMovie(int movieId){
        try{
            ArrayList ml = readMovies(FILENAME);
            boolean foundMovie = false;
            for (int i=0; i<ml.size(); i++){
                Movie m = (Movie) ml.get(i);
                if (m.getMovieId() == movieId){ // found
                    foundMovie = true;
                    ml.remove(i);
                    System.out.println("Movie successfully deleted!\n");
                }
            }
            saveMovies(FILENAME, ml);
            if (!foundMovie) {
                throw new ItemNotFoundException();
            }
        }
        catch(IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("Movie not found > " + e.getMessage());
        }
    }

    /**
     * Allows admins to retrieve all movies in the database as an array list
     * @return an array list of Movie objects from the database
     */
    public static ArrayList getMovies() {
        try {
            return readMovies("Databases/movies.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /** Helper function invoked within this class to validate that users only input a single digit when selecting from a list of provided options.
     * @param input the input from the user to be validated if it is legitimate
     * @return an integer representation of the user input if validation successful, else return an integer representation of the first 3 characters in the input.
     * @throws IllegalArgumentException UNCHECKED --> Thrown when the user does not provide any input
     */
    private static int checkInput (String input) throws IllegalArgumentException {
        if (input.length() == 0) throw new IllegalArgumentException();
        else return Integer.parseInt(input);
    }

}
