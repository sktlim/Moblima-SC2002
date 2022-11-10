package Boundary;
import Models.*;
import Controllers.*;

import java.util.ArrayList;
import java.util.Scanner;


public class AdminUI {

    private Admin sessionAdmin;

    // constructor
    public AdminUI(int userId) {
        this.sessionAdmin = new Admin(userId);
    }

    /* implementation of abstract method
       this method should be called repeatedly in the main While loop */
    private void showSelections() {
        System.out.println("Welcome to the admin page.");
        System.out.println("SELECT ONE OF THE FOLLOWING OPTIONS");
        System.out.println("========================================");
        System.out.println("MOVIES");
        System.out.println("1: Create a New Movie");
        System.out.println("2: Update an Existing Movie");
        System.out.println("3: Delete an Existing Movie");
        System.out.println("SHOWS");
        System.out.println("4: Create a New Show");
        System.out.println("5: Update an existing Show");
        System.out.println("6: Delete an existing Show");
        System.out.println("ADMINS");
        System.out.println("7: Print Admin List");
        System.out.println("8: Create a New Admin");
        System.out.println("9: Update an Existing Admin");
        System.out.println("10: Delete an Existing Admin");
        System.out.println("========================================");
        System.out.println("11: Back to main menu");
    }

    // User interface
    public void showUI (Scanner sc) {
        while (true) {
            try {
                String input = null;
                int option = -1;

                showSelections();
                input = sc.nextLine();
                option = checkInput(input);
                switch(option) {
                    case 1:
                        createMovie(sc);
                        break;
                    case 2:
                        System.out.println("Please select the movie id you would like to update.");
                        printMovieId();
                        input = sc.nextLine();
                        int id = checkInput(input);
                        updateMovie(id, sc);
                        break;
                    case 3:
                        System.out.println("Please select the movie id you would like to delete.");
                        printMovieId();
                        input = sc.nextLine();
                        id = checkInput(input);
                        deleteMovie(id);
                        break;
                    case 4:
                        createMovie(sc);
                        break;
                    case 5:
                        System.out.println("Please select the show id you would like to update.");
                        printShowsId();
                        input = sc.nextLine();
                        id = checkInput(input);
                        updateShow(id, sc);
                        break;
                    case 6:
                        System.out.println("Please select the show id you would like to delete.");
                        printShowsId();
                        input = sc.nextLine();
                        id = checkInput(input);
                        deleteShow(id);
                        break;
                    case 7:
                        printAdminList();
                        break;
                    case 8:
                        createAdmin(sc);
                        break;
                    case 9:
                        System.out.println("Please select the admin id you would like to update. If unsure, please use option 7 to print the list of admins.");
                        input = sc.nextLine();
                        id = checkInput(input);
                        updateAdmin(id, sc);
                        break;
                    case 10:
                        System.out.println("Please select the admin id you would like to delete. If unsure, please use option 7 to print the list of admins.");
                        input = sc.nextLine();
                        id = checkInput(input);
                        deleteAdmin(id);
                        break;
                    case 11:
                        return;
                    }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int checkInput (String input) throws Exception {
        if (input.length() == 1) return Integer.parseInt(input);
        else return Integer.parseInt(input.substring(0, 2));
    }
    private void printMovieId() {
        ArrayList movies = MovieManagerAdmin.getMovies();
        System.out.println("Movie | Id");
        for (int i=0; i<movies.size(); i++) {
            Movie m = (Movie)movies.get(i);
            String title = m.getMovieTitle();
            int id = m.getMovieId();
            System.out.printf("%s | %d\n", title, id);
        }
    }

    private void printShowsId() {
        ArrayList shows = ShowManager.getShows("Database/shows.txt");
        System.out.println("Cineplex | Theatre  | Date | Start Time | End Time | Movie Name | Id");
        for (int i=0; i<shows.size(); i++) {
            Show s = (Show)shows.get(i);
            StringBuilder sb = new StringBuilder();
            sb.append(s.getCineplex() + " | ");
            sb.append(s.getTheatre() + " | ");
            sb.append(s.getDate() + " | ");
            sb.append(s.getStartTime() + " | ");
            sb.append(s.getEndTime() + " | ");
            Movie m = MovieManagerAdmin.findMovie(s.getMovieId());
            sb.append(m.getMovieTitle() + " | ");
            sb.append(s.getShowId());
        }
    }

    public void createMovie(Scanner sc) {
        MovieManagerAdmin.createMovie(sc); // invokes the MovieManagerAdmin static method
    }

    public void updateMovie(int movieId, Scanner sc) {
        MovieManagerAdmin.updateMovies(movieId, sc);
    }

    public void deleteMovie(int movieId) {
        MovieManagerAdmin.deleteMovie(movieId);
    }

    public int createShow(Scanner sc) {
        return 1; // Placeholder
    }

    public boolean updateShow(int showId, Scanner sc) {

        return true; // place holder
    }

    public void deleteShow(int showId) {
        ShowManager.deleteShow(showId);
    }

    public void createAdmin(Scanner sc) {
        AdminManager.createAdmin(sc);
    }

    public void printAdminList() {
        AdminManager.printAdminList();
    }

    public void updateAdmin(int adminId, Scanner sc) {
        AdminManager.updateAdmin(adminId, sc);
    }

    public void deleteAdmin(int adminId) {
        AdminManager.deleteAdmin(adminId);
    }

}
