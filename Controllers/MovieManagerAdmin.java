package Controllers;
import Models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Models.Movie.ShowingStatus.DEFAULT;

public class MovieManagerAdmin extends MovieManagerMovieGoer {

    /** Create method
     * Create new movie and add it to the data base*/
    public static void createMovie(Scanner sc){
        try {
            System.out.println("Enter Movie Title: ");
            String movieTitle = sc.nextLine();
            System.out.println("Enter Showing Status: ");
            Movie.ShowingStatus showingStatus = Movie.ShowingStatus.valueOf(sc.nextLine());
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
            Movie.MovieRating movieRating = Movie.MovieRating.valueOf(sc.nextLine());
            System.out.println("Enter Movie Type: ");
            Movie.MovieType movieType = Movie.MovieType.valueOf(sc.nextLine());

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
                    showingStatus = Movie.ShowingStatus.valueOf(sc.nextLine());
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
                    movieRating = Movie.MovieRating.valueOf(sc.nextLine());
                    break;



                case 7: // edit movie type
                    System.out.println("Enter Movie Type: ");
                    movieType = Movie.MovieType.valueOf(sc.nextLine());
                    break;
            }


            ArrayList ml = readMovies(FILENAME);
            for (int i=0; i<ml.size(); i++){
                Movie m = (Movie) ml.get(i);
                if (m.getMovieId() == movieId){
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
        }
        catch(IOException e){

        }


    }
    /** Delete method
     * delete admin based on movieId */
    public static void deleteMovie(int movieId){
        try{
            ArrayList ml = readMovies(FILENAME);
            for (int i=0; i<ml.size(); i++){
                Movie m = (Movie) ml.get(i);
                if (m.getMovieId() == movieId){
                    ml.remove(i);
                }
            }
            saveMovies(FILENAME, ml);
        }
        catch(IOException e){

        }


    }


}
