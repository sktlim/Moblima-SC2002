package Controllers;
import Models.*;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.time.format.DateTimeFormatter;

import Exceptions.ItemNotFoundException;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

/**
 * Show manager can create, read, update, and delete Shows
 */
public class ShowManager implements Manager{
    /**
     * Separator for parsing shows.txt file.
     */
    public static final String SEPARATOR = "|";
    /**
     * Path of shows.txt file.
     */
    public static final String FILENAME = "Databases/shows.txt" ;

    /**
     * Helper function to check if the start and end times of the show being created, conflicts with an existing show for the same cineplex, theatre, and date.
     * @param start1 Start time for the new show as requested by user
     * @param end1 End time for the new show as requested by user
     * @param start2 Start time for the existing show in database
     * @param end2 End time for the existing show in database
     * @return true if there is a conflict, else return false
     */
    public static boolean isOverlapping(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        boolean overlapCase1 = start1.isBefore(end2) && start2.isBefore(end1);
        boolean overlapCase2 = start2.isBefore(end1) && start1.isBefore(end2);
        return overlapCase1 || overlapCase2; // as long as one overlapCase is true, return true to signify an overlap
    }


    /** Create method
     * Create new show and add it to the database
     * @param sc Takes in scanner to instantiate various fields in the function
     */
    public static void createShow(Scanner sc){
        try {
            System.out.println("Enter Movie ID: ");
            int movieId = sc.nextInt();
            sc.nextLine();
            while (MovieManagerAdmin.findMovie((movieId))==null){
                System.out.println("Invalid Movie Selected. Please re-enter.");
                movieId = sc.nextInt();
                sc.nextLine();
            }
            Movie m = MovieManagerAdmin.findMovie(movieId);

            // Checks if the movie has been listed as SHOWING_ENDED. Such movies cannot be listed for screening.
            if (m.getShowingStatus().equals(Movie.ShowingStatus.SHOWING_ENDED)) {
                System.out.println("This movie is no longer available for screening.");
                System.out.println("Please select another movie.");
                return;
            }

            int semaphore = -1;
            String date = "";
            while (semaphore == -1) {
                try {
                    System.out.println("Enter Date (DD/MM/YYYY): ");
                    date = sc.nextLine();
                    Pattern DATE_PATTERN = Pattern.compile(
                            "^\\d{2}\\/\\d{2}\\/\\d{4}$");
                    if (!DATE_PATTERN.matcher(date).matches()) throw new IllegalArgumentException("Date is invalid!");
                    semaphore = 1;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            semaphore = -1;
            String startTime = "";
            while (semaphore == -1) {
                try {
                    System.out.println("Enter Start Time (HH:MM, 24HRS): ");
                    startTime = sc.nextLine();
                    Pattern DATE_PATTERN = Pattern.compile(
                            "^([2][0-3]|[0-1][0-9]|[1-9]):[0-5][0-9]$");
                    if (!DATE_PATTERN.matcher(startTime).matches()) throw new IllegalArgumentException("Start time is invalid!");
                    semaphore = 1;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            Calendar cal = Calendar.getInstance();
            Date dateFormat = new SimpleDateFormat("HH:mm").parse(startTime);
            cal.setTime(dateFormat);
            cal.add(Calendar.MINUTE, m.getMovieRuntime());
            Date endDate = cal.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String endTime = sdf.format(endDate);

            String cineplex = "null";
            int cineplexSelector = 0;
            int flag = 0;
            while (flag == 0){
                try{
                    System.out.println("Enter Cineplex: ");
                    System.out.println("0: AMK Hub Mall");
                    System.out.println("1: Bishan Town Hall");
                    System.out.println("2: Jurong Point Junction");
                    String input = sc.nextLine();
                    cineplexSelector = checkInput(input);
                    while(cineplexSelector<0 || cineplexSelector>2){
                        throw new Exception();
                    }
                    flag = 1;
                }
                catch(Exception e){
                    System.out.println("Invalid Input. Please re-enter.");
                }
            }

            switch(cineplexSelector){
                case 0:
                    cineplex = "AMK Hub Mall";
                    break;
                case 1:
                    cineplex = "Bishan Town Hall";
                    break;
                case 2:
                    cineplex = "Jurong Point Junction";
                    break;
            }

            semaphore = -1;
            int theatre = -1;
            TheatreManager.printTheatreList(cineplexSelector);
            while (semaphore == -1) {
                try {
                    System.out.println("Enter Theatre: ");
                    String input = sc.nextLine();
                    theatre = checkInput(input);
                    semaphore = 1;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            flag = 0;
            int theatreClassSelector = 0;
            Show.TheatreClass theatreClass = Show.TheatreClass.DEFAULT;
            while(flag == 0){
                try{
                    System.out.println("Enter Theatre Class: ");
                    System.out.println("0: STANDARD");
                    System.out.println("1: PLATINUM");
                    String input = sc.nextLine();
                    theatreClassSelector = checkInput(input);
                    while(theatreClassSelector < 0 || theatreClassSelector > 1){
                        throw new Exception();
                    }
                    flag = 1;
                }
                catch(Exception e){
                    System.out.println("Invalid Input. Please re-enter.");
                }
            }

            switch(theatreClassSelector){
                case 0:
                    theatreClass = Show.TheatreClass.STANDARD;
                    break;
                case 1:
                    theatreClass = Show.TheatreClass.PLATINUM;
                    break;
            }

            ArrayList sl = readShows(FILENAME);

            // check if the start and end times conflict with any shows on the same date from the same cineplex and theatre in the database
            for (int i=0; i<sl.size(); i++) {
                Show show = (Show) sl.get(i);
                if (show.getCineplex().equals(cineplex) && show.getTheatre() == theatre && show.getDate().equals(date)) { // matching entry in db
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault());
                    LocalTime dbStartTime = LocalTime.parse(show.getStartTime(), inputFormatter); // start time of show from db
                    LocalTime dbEndTime = LocalTime.parse(show.getEndTime(), inputFormatter); // end time of show from db
                    LocalTime userStartTime = LocalTime.parse(startTime, inputFormatter); // start time input by user
                    LocalTime userEndTime = LocalTime.parse(endTime, inputFormatter); // end time input by user

                    if (isOverlapping(userStartTime, userEndTime, dbStartTime, dbEndTime)) {
                        System.out.println("An existing show for this theatre conflicts with your chosen show time for this date");
                        System.out.println("Please create another show with a different show time");
                        return; // terminate the function if a conflict exists
                    }
                }
            }
            Show sFinal = (Show) sl.get(sl.size()-1);
            int finalShowId = sFinal.getShowId();

            Show s1 = new Show(finalShowId+1, movieId, date, startTime, endTime, theatre, theatreClass, cineplex);
            sl.add(s1);
            saveShows(FILENAME, sl);
            SeatManager.createShowPlan(finalShowId+1, theatre);
            System.out.println("Show created successfully!");
        }
        catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
        catch(ParseException e){
            System.out.println("ParseException > " + e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println("Your input was of a wrong format! Please ensure that your input is an integer.");
        }
    }


    /** Read method.
     * Print method to display everything on the txt file database */
    public static void printShowList(){
        try{
            ArrayList al = readShows(FILENAME);
            for (int i = 0 ; i < al.size() ; i++) {
                Show s = (Show)al.get(i);
                System.out.println("ShowID: " + s.getShowId() );
                System.out.println("MovieID: " + s.getMovieId() );
                System.out.println("Date: " + s.getDate());
                System.out.println("Start Time: " + s.getStartTime() );
                System.out.println("End Time: " + s.getEndTime() );
                System.out.println("Theatre: " + s.getTheatre());
                System.out.println("Theatre Class: " + s.getTheatreClass() );
                System.out.println("Cineplex: " + s.getCineplex() );
            }


        }
        catch (IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
    }

    /** Read method.
     * Find show by show ID.
     * @param showID show ID of show to be found.
     * @return Object of type Show
     */
    public static Show findShow(int showID){
        try{
            ArrayList al = readShows(FILENAME);
            boolean foundShow = false;
            for (int i = 0 ; i < al.size() ; i++) {
                Show s = (Show)al.get(i);
                if (s.getShowId()==showID){ // found
                    // System.out.println("Show successfully found!");
                    return s;
                }
            }
            throw new ItemNotFoundException();
        }
        catch (IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("Show not found > " + e.getMessage());
        }
        return null;
    }

    /** Update method
     * this updates the various field of movieGoer by showId
     * @param showId show ID of show to be updated
     * @param sc Takes in scanner to update various fields within the function
     */
    public static void updateShows(int showId, Scanner sc){
        String inputField = "0";
        Show.TheatreClass theatreClass = Show.TheatreClass.DEFAULT;
        int theatre = 0;

        try{

            int flag = 0;
            int fieldEdit = -1;
            while (flag == 0){
                try{
                    System.out.println("Select field to change:");
                    System.out.println("0: Date");
                    System.out.println("1: Start Time");
                    System.out.println("2: End Time");
                    System.out.println("3: Theatre");
                    System.out.println("4: Theatre Class");
                    System.out.println("5: Cineplex");
                    String input = sc.nextLine();
                    fieldEdit = checkInput(input);
                    while(fieldEdit < 0 || fieldEdit > 5){
                        throw new Exception();
                    }
                    flag = 1;
                }
                catch(Exception e){
                    System.out.println("Invalid Input. Please re-enter.");
                }
            }
            Show show = ShowManager.findShow(showId);
            Movie m = MovieManagerAdmin.findMovie(show.getMovieId());

            switch(fieldEdit){
                case 0: //edit Date
                    int semaphore = -1;
                    while (semaphore == -1) {
                        try {
                            System.out.println("Enter new Date (DD/MM/YYYY): ");
                            inputField = sc.nextLine();
                            Pattern DATE_PATTERN = Pattern.compile(
                                    "^\\d{2}\\/\\d{2}\\/\\d{4}$");
                            if (!DATE_PATTERN.matcher(inputField).matches()) throw new IllegalArgumentException("Date is invalid!");
                            semaphore = 1;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 1: //edit Start Time
                    semaphore = -1;
                    while (semaphore == -1) {
                        try {
                            System.out.println("Enter new Start Time (HH:MM, 24HRS): ");
                            inputField = sc.nextLine();
                            Pattern DATE_PATTERN = Pattern.compile(
                                    "^([2][0-3]|[0-1][0-9]|[1-9]):[0-5][0-9]$");
                            if (!DATE_PATTERN.matcher(inputField).matches()) throw new IllegalArgumentException("Start time is invalid!");
                            semaphore = 1;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case 2: // edit End Time
                    semaphore = -1;
                    while (semaphore == -1) {
                        try {
                            System.out.println("WARNING: You are manually editing the end time now.");
                            System.out.println("Enter new End Time (HH:MM, 24HRS): ");
                            inputField = sc.nextLine();
                            Pattern DATE_PATTERN = Pattern.compile(
                                    "^([2][0-3]|[0-1][0-9]|[1-9]):[0-5][0-9]$");
                            if (!DATE_PATTERN.matcher(inputField).matches()) throw new IllegalArgumentException("End time is invalid!");
                            semaphore = 1;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 3: // edit Theatre
                    System.out.println("Enter new Theatre: ");
                    theatre = sc.nextInt();
                    sc.nextLine();
                    break;

                case 4: // edit Theatre Class
                    flag = 0;
                    int theatreClassSelector = 0;
                    while(flag == 0){
                        try{
                            System.out.println("Enter new Theatre Class: ");
                            System.out.println("0: STANDARD");
                            System.out.println("1: PLATINUM");
                            String input = sc.nextLine();
                            theatreClassSelector = checkInput(input);
                            while(theatreClassSelector<0||theatreClassSelector>1){
                                throw new Exception();
                            }
                        }
                        catch(Exception e){
                            System.out.println("Invalid Input. Please re-enter.");
                        }
                    }

                    switch(theatreClassSelector){
                        case 0:
                            theatreClass = Show.TheatreClass.STANDARD;
                            break;
                        case 1:
                            theatreClass = Show.TheatreClass.PLATINUM;
                            break;
                    }
                    break;

                case 5: // edit Cineplex
                    String cineplex = "null";
                    int cineplexSelector = 0;
                    flag = 0;
                    while (flag == 0){
                        try{
                            System.out.println("Enter Cineplex: ");
                            System.out.println("0: AMK Hub Mall");
                            System.out.println("1: Bishan Town Hall");
                            System.out.println("2: Jurong Point Junction");
                            String input = sc.nextLine();
                            cineplexSelector = checkInput(input);
                            while(cineplexSelector<0 || cineplexSelector>2){
                                throw new Exception();
                            }
                            flag = 1;
                        }
                        catch(Exception e){
                            System.out.println("Invalid Input. Please re-enter.");
                        }
                    }


                    switch(cineplexSelector){
                        case 0:
                            cineplex = "AMK Hub Mall";
                            break;
                        case 1:
                            cineplex = "Bishan Town Hall";
                            break;
                        case 2:
                            cineplex = "Jurong Point Junction";
                            break;
                    }
            }

            ArrayList sl = readShows(FILENAME);
            boolean foundShow = false;
            for (int i=0; i<sl.size(); i++){
                Show s = (Show) sl.get(i);
                if (s.getShowId() == showId){ // found
                    foundShow = true;
                    switch(fieldEdit){
                        case 0:
                            s.setDate(inputField);
                            System.out.println("Date successfully updated.");
                            break;
                        case 1:
                            s.setStartTime(inputField);
                            Calendar cal = Calendar.getInstance();
                            try{
                                Date dateFormat = new SimpleDateFormat("HH:mm").parse(inputField);
                                cal.setTime(dateFormat);
                                cal.add(Calendar.MINUTE, m.getMovieRuntime());
                                Date endDate = cal.getTime();
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                                String endTime = sdf.format(endDate);
                                s.setEndTime(endTime);
                            }
                            catch(ParseException e){

                            }

                            System.out.println("Start Time successfully updated.");
                            break;

                        case 2:
                            s.setEndTime(inputField);
                            System.out.println("End Time successfully updated.");
                            break;

                        case 3:
                            s.setTheatre(theatre);
                            System.out.println("Theatre successfully updated.");

                            break;

                        case 4:
                            s.setTheatreClass(theatreClass);
                            System.out.println("Theatre Class successfully updated.");
                            break;

                        case 5:
                            s.setCineplex(inputField);
                            System.out.println("Cineplex successfully updated.");
                            break;
                    }
                }
            }
            saveShows(FILENAME, sl);

            if (!foundShow) {
                throw new ItemNotFoundException();
            }
        }
        catch(IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("Show not found > " + e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println("Your input was of a wrong format! Please ensure that your input is an integer.");
        }
    }

    /**
     * Delete method.
     * Delete show based on showID
     * @param showID show ID of show to be deleted
     */
    public static void deleteShow(int showID){
        try{
            ArrayList al = readShows(FILENAME);
            boolean foundShow = false;
            for (int i=0; i<al.size(); i++){
                Show s = (Show) al.get(i);
                if (s.getShowId() == showID){ // found
                    foundShow = true;
                    al.remove(i);
                }
            }
            if (!foundShow) {
                throw new ItemNotFoundException();
            }
            saveShows(FILENAME, al);
            SeatManager.deleteSeatPlan(showID);
            System.out.println("Show successfully deleted!");
        }
        catch(IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("Show not found > " + e.getMessage());
        }
    }

    /**
     * Reading helper function.
     * This creates a list of instances of shows
     * @param filename File name of content to be read
     * @return ArrayList of Shows
     * @throws IOException I/O Error with input
     */
    private static ArrayList readShows(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(filename);
        ArrayList shows = new ArrayList() ;// to store shows data

        for (int i = 0 ; i < stringArray.size() ; i++) {
            String st = (String)stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
            int  movieId = Integer.parseInt(star.nextToken().trim());	// first token
            String date = star.nextToken().trim();
            String startTime = star.nextToken().trim();
            String endTime = star.nextToken().trim();
            int theatre = Integer.parseInt(star.nextToken().trim());
            Show.TheatreClass theatreClass = Show.TheatreClass.valueOf(star.nextToken().trim());	// second token, convert to type enum
            String cineplex = star.nextToken().trim();
            int showId = Integer.parseInt(star.nextToken().trim()); // 9th token
            // create show object from file data

            Show s = new Show(showId, movieId,date, startTime, endTime, theatre, theatreClass, cineplex);
            // add to show list
            shows.add(s) ;
        }
        return shows ;
    }

    /**
     * Write fixed content to the given file.
     * (helper func, declared as private as it is only called within this file)
     * @param fileName File name of content to be written over
     * @param data Data to overwrite file
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
     * Read the contents of the given file(helper func, declared as private as it is only called within this file).
     * @param fileName File name of content to be read
     * @return List of data
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
     * Save Shows to database
     * @param filename File Name of content to be saved to
     * @param al Array list for building String to be written into file
     * @throws IOException Exception throw in in event of I/O error
     */
    private static void saveShows(String filename, List al) throws IOException { // edit for show files
        List alw = new ArrayList() ;// to store admins data

        for (int i = 0 ; i < al.size() ; i++) {
            Show s = (Show)al.get(i);
            StringBuilder st = new StringBuilder();
            st.append(s.getMovieId());
            st.append(SEPARATOR);
            st.append(s.getDate().trim());
            st.append(SEPARATOR);
            st.append(s.getStartTime().trim());
            st.append(SEPARATOR);
            st.append(s.getEndTime().trim());
            st.append(SEPARATOR);
            st.append(s.getTheatre());
            st.append(SEPARATOR);
            st.append(s.getTheatreClass().name().trim());
            st.append(SEPARATOR);
            st.append(s.getCineplex().trim());
            st.append(SEPARATOR);
            st.append(s.getShowId());
            st.append(SEPARATOR);

            alw.add(st.toString()) ;

        }
        write(filename,alw);
    }

    // For admin UI

    /**
     * Get shows from file
     * @param fileName file to be read
     * @return Array List with Shows
     */
    public static ArrayList getShows(String fileName) {
        try {
            return readShows(fileName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Ensure input is valid.
     * @param input input String
     * @return returns 0 or 1
     * @throws Exception Error in method
     */
    private static int checkInput (String input) throws Exception {
        if (input.length()==0) throw new Exception();
        if (input.length() == 1) return Integer.parseInt(input);
        else return Integer.parseInt(input.substring(0, 2));
    }

}
