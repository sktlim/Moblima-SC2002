package Controllers;

import Models.*;

public class MovieManagerParent {

    public final static String FILENAME = "Databases/movies.txt";

    public int createMovie(String movieTitle, Movie.ShowingStatus showingStatus, String synopsis, String director, String cast, int movieRuntime, Movie.MovieRating movieRating, int overallReviewRating){
        // create method
        // return movieId;
    }

    public void readMovie(int movieId){
        //read method
        // print method for show information
    }

    public boolean updateMovie(int movieId, String movieTitle, Movie.ShowingStatus showingStatus, String synopsis, String director, String cast, int movieRuntime, Movie.MovieRating movieRating, int overallReviewRating){
        //update method
        // true if success, false otherwise
    }

    public void deleteMovie(int movieId){
        // delete method
    }





    
}
