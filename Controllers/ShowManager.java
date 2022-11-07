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


public class ShowManager {

    public static final String SEPARATOR = "|";
    public static final String FILENAME = "Databases/shows.txt" ;

    /** Create method
     * Create new show and add it to the data base*/
    public static void createShow(Scanner sc){
        try {
            System.out.println("Enter Movie ID: ");
            int movieId = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Date (DD/MM/YYYY): ");
            String date = sc.nextLine();
            System.out.println("Enter Start Time (HH:MM, 24HRS): ");
            String startTime = sc.nextLine();
            System.out.println("Enter End Time (HH:MM, 24HRS): ");
            String endTime = sc.nextLine();
            System.out.println("Enter Theatre: ");
            int theatre = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Theatre Class: ");
            Show.TheatreClass theatreClass = Show.TheatreClass.valueOf(sc.nextLine());
            System.out.println("Enter Cineplex: ");
            String cineplex = sc.nextLine();

            ArrayList sl = readShows(FILENAME);
            Show s1 = new Show(sl.size()+1, movieId, date, startTime, endTime, theatre, theatreClass, cineplex);
            sl.add(s1);
            saveShows(FILENAME, sl);

        }
        catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());

        }

    }


    /** Read method
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
                System.out.println("Theatre Class: " + s.getTheaterClass() );
                System.out.println("Cineplex: " + s.getCineplex() );
            }


        }
        catch (IOException e){

        }
    }

    /** Read method
     * Find show by show ID */
    public static Show findShow(int showID){
        try{
            ArrayList al = readShows(FILENAME);
            for (int i = 0 ; i < al.size() ; i++) {
                Show s = (Show)al.get(i);
                if (s.getShowId()==showID){
                    System.out.println("Show successfully found!");
                    return s;
                }
            }


        }
        catch (IOException e){

        }
        System.out.println("Show not found!");
        return null;
    }

    /** Update method
     * this updates the various field of movieGoer */
    public static void updateShows(int showId, Scanner sc){
        String inputField = "0";
        Show.TheatreClass theatreClass = Show.TheatreClass.DEFAULT;
        int theatre = 0;

        try{
            System.out.println("Select field to change:");
            System.out.println("0: Date");
            System.out.println("1: Start Time");
            System.out.println("2: End Time");
            System.out.println("3: Theatre");
            System.out.println("4: Theatre Class");
            System.out.println("5: Cineplex");

            int fieldEdit = sc.nextInt();
            sc.nextLine();

            switch(fieldEdit){
                case 0: //edit Date
                    System.out.println("Enter new Date: ");
                    inputField = sc.nextLine();
                    break;
                case 1: //edit Start Time
                    System.out.println("Enter new Start Time (HH:MM, 24HRS): ");
                    inputField = sc.next();
                    break;

                case 2: // edit End Time
                    System.out.println("Enter new End Time (HH:MM, 24HRS): ");
                    inputField = sc.nextLine();
                    break;


                case 3: // edit Theatre
                    System.out.println("Enter new Theatre: ");
                    theatre = sc.nextInt();
                    sc.nextLine();
                    break;

                case 4: // edit Theatre Class
                    System.out.println("Enter new Theatre Class: ");
                    theatreClass = Show.TheatreClass.valueOf(sc.nextLine());
                    break;


                case 5: // edit Cineplex
                    System.out.println("Enter Cineplex: ");
                    inputField = sc.nextLine();
                    break;

            }


            ArrayList sl = readShows(FILENAME);
            for (int i=0; i<sl.size(); i++){
                Show s = (Show) sl.get(i);
                if (s.getShowId() == showId){
                    switch(fieldEdit){
                        case 0:
                            s.setDate(inputField);
                            System.out.println("Date successfully updated.");
                            break;
                        case 1:
                            s.setStartTime(inputField);
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
                            s.setTheaterClass(theatreClass);
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
        }
        catch(IOException e){

        }


    }

    /** Delete method
     * delete show based on showID */
    public static void deleteShow(int showID){
        try{
            ArrayList al = readShows(FILENAME);
            for (int i=0; i<al.size(); i++){
                Show s = (Show) al.get(i);
                if (s.getShowId() == showID){
                    al.remove(i);
                }
            }
            saveShows(FILENAME, al);
        }
        catch(IOException e){

        }


    }



    /** reading (helper func, declared as private as it is only called within this file)
     * This creates a list of instances of admins */

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
     * (helper func, declared as private as it is only called within this file)*/

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
            st.append(s.getTheaterClass().name().trim());
            st.append(SEPARATOR);
            st.append(s.getCineplex().trim());
            st.append(SEPARATOR);
            st.append(s.getShowId());
            st.append(SEPARATOR);

            alw.add(st.toString()) ;

        }
        write(filename,alw);
    }


}
