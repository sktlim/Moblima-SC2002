package Controllers;

import Models.Holiday;
import Models.Transaction;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.time.LocalDate;

public class HolidayManager {

    public final static String FILENAME = "Databases/holidays.txt";
    public static final String SEPARATOR = ",";

    /** Read the contents of the given file.
     * (helper func, declared as private as it is only called within this file)*/
    private static List read(String fileName) throws IOException {
        ArrayList<String> data = new ArrayList<>();
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

    /** reading (helper func, declared as private as it is only called within this file) **/
    private static ArrayList readHolidays(String fileName) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(fileName);
        ArrayList alr = new ArrayList();// to store Holiday data

        for (Object o : stringArray) {
            String st = (String) o;
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, SEPARATOR);    // pass in the string to the string tokenizer using delimiter ","
            String holidayName = star.nextToken().trim();    // first token
            String date = star.nextToken().trim();    // second token
            // create holiday object from file data
            Holiday h = new Holiday(holidayName, date);
            alr.add(h);
        }
        return alr ;
    }


    private static void createHoliday(Scanner sc) {
        try {
            sc.nextLine();
            System.out.println("Please enter the holiday's Name: ");
            String holidayName = sc.nextLine();
            System.out.println("Please enter the holiday's Year: ");
            int holidayYear = sc.nextInt();
            sc.nextLine();
            System.out.println("Please enter the holiday's Month: ");
            int holidayMonth = sc.nextInt();
            sc.nextLine();
            System.out.println("Please enter the holiday's Date: ");
            int holidayDate = sc.nextInt();
            sc.nextLine();

            LocalDate date = LocalDate.of(holidayYear, holidayMonth, holidayDate);

            ArrayList arrayList = readHolidays(FILENAME);
            Holiday h = new Holiday(holidayName, date.toString());
            arrayList.add(h);
            saveHolidays(FILENAME, arrayList);
        } catch (IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
    }

    private static void write(String fileName, List data) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));

        try {
            for (int i =0; i < data.size() ; i++) {
                out.println((String)data.get(i));
            }
        }
        finally {
            out.close();
        }
    }

    /** saving
     * (helper func, declared as private as it is only called within this file)*/

    private static void saveHolidays(String fileName, List al) throws IOException {
        List alw = new ArrayList();

        for (int i = 0 ; i < al.size() ; i++) {
            Holiday h = (Holiday)al.get(i);
            StringBuilder st = new StringBuilder();
            st.append(h.getName());
            st.append(SEPARATOR);
            st.append(h.getDate());
            st.append(SEPARATOR);

            alw.add(st.toString()) ;

        }
        write(fileName, alw);
    }

    public static void printHolidayList(){
        try{
            List holidayList = readHolidays(FILENAME);
            for (int i = 0 ; i < holidayList.size() ; i++) {
                Holiday h = (Holiday) holidayList.get(i);
                System.out.println("Holiday Name: " + h.getName());
                System.out.println("Holiday Date: " + h.getDate());
                System.out.println();
            }


        }
        catch (IOException e){

        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1: printHolidayList");
        System.out.println("2: createHoliday");
        int choice = sc.nextInt();

        switch(choice) {
            case 1:
                HolidayManager.printHolidayList();
                break;
            case 2:
                HolidayManager.createHoliday(sc);
                HolidayManager.printHolidayList();

                break;
        }
    }


}
