package Boundary;
import Controllers.*;
import Models.Movie;
import Models.Show;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This Class is an abstract class which ensures that the AdminUI and MovieGoerUI subclasses implement the showSelections and showUI methods.
 * The other concrete methods in this Class are inherited by both subclasses.
 */

public abstract class UserUI {

    /**
     * Displays the list of possible actions to the current user.
     * Abstract method.
     */
    public abstract void showSelections();

    /**
     * This method operates in sync with the showSelections method above to accept input from the current user.
     * @param sc Scanner object to allow users to make their selections.
     */
    public abstract void showUI(Scanner sc);

    /**
     * Enables users to view the full list of Movies available.
     */
    public void listMovies() {
        MovieManagerAdmin.printMovieList();
    };

    /**
     * Helper function to print the shows and their associated showIDs to assist users in selecting the IDs of their chosen show.
     */
    public void printShowsId() {
        ArrayList shows = ShowManager.getShows("Databases/shows.txt");
        System.out.printf("%-25s | %-8s | %-12s | %-10s | %-10s | %-30s | %-10s %n", "CINEPLEX", "THEATRE", "DATE", "START TIME", "END TIME", "MOVIE NAME", "ID");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        for (int i=0; i<shows.size(); i++) {
            Show s = (Show)shows.get(i);
            Movie m = MovieManagerAdmin.findMovie(s.getMovieId());
            System.out.printf("%-25s | %-8s | %-12s | %-10s | %-10s | %-30s | %-10s %n", s.getCineplex(), s.getTheatre(), s.getDate(), s.getStartTime(), s.getEndTime(),
                    m.getMovieTitle(), s.getShowId());
        }
        System.out.println("");
    }

    /**
     * This method validates the String input of users and removes the problems of accepting integer input through the Scanner.
     * @param input String input by current user.
     * @return the integer representation of the input if parsing is successful.
     * @throws IllegalArgumentException UNCHECKED --> thrown when user input fails to be parsed as an integer.
     */
    public int checkInput (String input) throws IllegalArgumentException {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("Input is not a valid ID. Please try again\n");
        }
    }


}
