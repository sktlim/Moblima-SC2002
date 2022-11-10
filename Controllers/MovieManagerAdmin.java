package Controllers;
import Models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.ItemNotFoundException;

public class MovieManagerAdmin extends MovieManagerMovieGoer {

    /** Create method
     * Create new movie and add it to the data base*/
    public static void createMovie(Scanner sc){
        try {
            System.out.println("Enter Movie Title: ");
            String movieTitle = sc.nextLine();
            System.out.println("Enter Showing Status: ");
            System.out.println("0: COMING_SOON");
            System.out.println("1: PREVIEW");
            System.out.println("2: NOW_SHOWING");
            int showStatusSelector = sc.nextInt();
            sc.nextLine();
            Movie.ShowingStatus showingStatus = Movie.ShowingStatus.DEFAULT;

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
            System.out.println("Enter Synopsis: ");
            String synopsis = sc.nextLine();
            System.out.println("Enter Director: ");
            String director = sc.nextLine();
            System.out.println("Enter Cast: ");
            String cast = sc.nextLine();
            System.out.println("Enter Movie Runtime: ");
            int movieRuntime = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Movie Rating: ");
            System.out.println("0: G");
            System.out.println("1: PG13");
            System.out.println("2: NC16");
            System.out.println("3: M18");
            System.out.println("4: R21");
            int movieRatingSelector = sc.nextInt();
            sc.nextLine();
            Movie.MovieRating movieRating = Movie.MovieRating.DEFAULT;
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

            System.out.println("Enter Movie Type: ");
            System.out.println("0: 2D");
            System.out.println("1: 3D");
            System.out.println("2: BLOCKBUSTER");
            int movieTypeSelector = sc.nextInt();
            sc.nextLine();
            Movie.MovieType movieType = Movie.MovieType.DEFAULT;
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

            ArrayList al = readMovies(FILENAME);
            Movie m1 = new Movie(al.size()+1, movieTitle, showingStatus, synopsis, director, cast, movieRuntime, movieRating, movieType);
            al.add(m1);
            saveMovies(FILENAME, al);

        }
        catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
    }

    /** Update method
     * this updates the various field of movieGoer */
    /**
     *
     * @param movieId
     * @param sc
     */
    public static void updateMovies(int movieId, Scanner sc){
        String inputField = "0";
        Movie.ShowingStatus showingStatus = Movie.ShowingStatus.DEFAULT;
        int runTime = 0;
        Movie.MovieRating movieRating = Movie.MovieRating.DEFAULT;
        Movie.MovieType movieType = Movie.MovieType.DEFAULT;

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
            int fieldEdit = sc.nextInt();
            sc.nextLine();
            switch(fieldEdit){
                case 0: //edit movie title
                    System.out.println("Enter new Movie Title: ");
                    inputField = sc.nextLine();
                    break;
                case 1: //edit showing status
                    System.out.println("Enter Movie Showing Status: ");
                    System.out.println("0: COMING_SOON");
                    System.out.println("1: PREVIEW");
                    System.out.println("2: NOW_SHOWING");
                    int showStatusSelector = sc.nextInt();
                    sc.nextLine();
                    showingStatus = Movie.ShowingStatus.DEFAULT;

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
                    System.out.println("Enter Movie Runtime: ");
                    runTime = sc.nextInt();
                    sc.nextLine();
                    break;

                case 6: // edit rating
                    System.out.println("Enter Movie Rating: ");
                    System.out.println("0: G");
                    System.out.println("1: PG13");
                    System.out.println("2: NC16");
                    System.out.println("3: M18");
                    System.out.println("4: R21");
                    int movieRatingSelector = sc.nextInt();
                    sc.nextLine();
                    movieRating = Movie.MovieRating.DEFAULT;
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
                    System.out.println("Enter Movie Type: ");
                    System.out.println("0: 2D");
                    System.out.println("1: 3D");
                    System.out.println("2: BLOCKBUSTER");
                    int movieTypeSelector = sc.nextInt();
                    sc.nextLine();
                    movieType = Movie.MovieType.DEFAULT;
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
    }
    /** Read method
     * Find movie by movieId*/
    public static Movie findMovie(int movieID){
        try{
            ArrayList mov = readMovies(FILENAME);
            for (int i = 0 ; i < mov.size() ; i++) {
                Movie m = (Movie) mov.get(i);
                if (m.getMovieId()==movieID){ // found
                    System.out.println("Movie successfully found!");
                    return m;
                }
            }
            throw new ItemNotFoundException();
        }
        catch (IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("Admin not found > " + e.getMessage());
        }
        return null;
    }
    /** Delete method
     * delete admin based on movieId */
    public static void deleteMovie(int movieId){
        try{
            ArrayList ml = readMovies(FILENAME);
            boolean foundMovie = false;
            for (int i=0; i<ml.size(); i++){
                Movie m = (Movie) ml.get(i);
                if (m.getMovieId() == movieId){ // found
                    foundMovie = true;
                    ml.remove(i);
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
            System.out.println("Admin not found > " + e.getMessage());
        }
    }

    public static ArrayList getMovies() {
        try {
            return readMovies("Databases/movies.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
