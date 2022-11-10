package Controllers;

import Models.Holiday;

import java.io.*;
import java.util.*;
import java.time.LocalDate;

import Exceptions.ItemNotFoundException;
import java.util.InputMismatchException;

public class HolidayManager {

    public final static String FILENAME = "Databases/holidays.txt";
    public static final String SEPARATOR = ",";

    /**
     * Read the contents of the given file.
     * (helper func, declared as private as it is only called within this file)
     */
    private static List read(String fileName) throws IOException {
        ArrayList<String> data = new ArrayList<>();
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        try {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } finally {
            scanner.close();
        }
        return data;
    }

    /**
     * reading (helper func)
     * declared as private as it is only called within this file)
     * @return ArrayList of holidays
     */

    private static ArrayList readHolidays(String fileName) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList) read(fileName);
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
        return alr;
    }



    /**
     * Create method to add new Holiday to database
     * @param sc Takes in the scanner to instantiate variables within
     *        the function itself.
     *        Admins will input the year, month, date separately.
     *        New date object will then be create with these 3 fields.
     */

    private static void createHoliday(Scanner sc) {
        try {
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
            System.out.println(String.format("%s has successfully been created.", holidayName));
        } catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Your input was of a wrong format! Please ensure that your input is an integer.");
        }
    }

    /**

     * Write fixed content to the given file (helper function)
     * Declared as private as it is only called within this file
     * @param fileName
     * @param data
     * @throws IOException
     */
    private static void write(String fileName, List data) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));

        try {
            for (int i = 0; i < data.size(); i++) {
                out.println((String) data.get(i));
            }
        } finally {
            out.close();
        }
    }

    /**
     * saving (Helper function)
     * Declared as private as it is only called within this file
     */
    private static void saveHolidays(String fileName, List al) throws IOException {
        List alw = new ArrayList();

        for (int i = 0; i < al.size(); i++) {
            Holiday h = (Holiday) al.get(i);
            StringBuilder st = new StringBuilder();
            st.append(h.getName());
            st.append(SEPARATOR);
            st.append(h.getDate());
            st.append(SEPARATOR);

            alw.add(st.toString());

        }
        write(fileName, alw);
    }


    /**
     * Read method
     * Print all holidays in the database
     */
    public static void printHolidayList(){
        try{
            List holidayList = readHolidays(FILENAME);
            for (int i = 0; i < holidayList.size(); i++) {
                Holiday h = (Holiday) holidayList.get(i);
                System.out.println("Holiday Name: " + h.getName());
                System.out.println("Holiday Date: " + h.getDate());
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
    }

    /**
     * Update Method
     * @param sc takes in scanner to update fields within the function
     * Allows admins to update existing holidays. All fields are allowed to be updated.
     * Holidays are queried according to their Name
     * Users must input the Holiday Name exact (case-sensitive) including its Year in the name e.g. Christmas 2022
     */
     
    public static void updateHoliday(Scanner sc) {
        System.out.println("Enter the name of the holiday you wish to change: ");
        String holidayName = sc.nextLine();
        String inputField = "0";

        try {
            System.out.println("Select field to change: ");
            System.out.println("1: Name");
            System.out.println("2: Date");

            int fieldEdit = sc.nextInt();
            sc.nextLine();

            switch (fieldEdit) {
                case 1: // edit Name
                    System.out.println("Enter new Holiday Name: ");
                    inputField = sc.nextLine();
                    break;
                case 2: // edit Date
                    System.out.println("Enter new Holiday Date: ");
                    inputField = sc.nextLine();
                    break;
            }

            ArrayList holidayList = readHolidays(FILENAME);
            boolean foundRequestedHoliday = false;
            for (int i = 0; i < holidayList.size(); i++) {
                Holiday h = (Holiday) holidayList.get(i);
                if (Objects.equals(h.getName(), holidayName)) { // holiday has been found
                    foundRequestedHoliday = true;
                    switch (fieldEdit) {
                        case 1:
                            h.setName(inputField);
                            System.out.println("Name successfully updated.");
                            break;
                        case 2:
                            h.setDate(inputField);
                            System.out.println("Date successfully updated.");
                            break;
                    }
                }
            }

            saveHolidays(FILENAME, holidayList);

            if (!foundRequestedHoliday) {
                throw new ItemNotFoundException();
            }

        } catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        } catch (ItemNotFoundException e) {
            System.out.println("Holiday not found > " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Your input was of a wrong format! Please ensure that your input is an integer.");
        }
    }

    /**
     * Delete method
     * @param sc takes in scanner to ask which holiday to delete
     * delete holiday based on holidayName 
     */

    public static void deleteHoliday(Scanner sc) {
        System.out.println("Which holiday would you like to delete?: ");
        String holidayToBeDeleted = sc.nextLine();

        try {
            ArrayList hl = readHolidays(FILENAME);
            boolean foundRequestedHoliday = false;
            for (int i = 0; i < hl.size(); i++) {
                Holiday h = (Holiday) hl.get(i);
                if (Objects.equals(h.getName(), holidayToBeDeleted)) { // holiday has been found
                    foundRequestedHoliday = true;
                    hl.remove(i);
                }
            }
            saveHolidays(FILENAME, hl);
            System.out.println(String.format("%s has successfully been deleted.", holidayToBeDeleted));

            if (!foundRequestedHoliday) {
                throw new ItemNotFoundException();
            }
        } catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        } catch (ItemNotFoundException e) {
            System.out.println("Holiday not found > " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Your input was of a wrong format!");
        }
    }

    /**
     * Validation for holiday
     * @param holidayDate of format YYYY-MM-DD
     * @return boolean value if the date is a holiday or not
     */

    public static boolean isHoliday(String holidayDate) {
        try {
            ArrayList hl = readHolidays(FILENAME);
            for (int i = 0; i < hl.size(); i++) {
                Holiday h = (Holiday) hl.get(i);
                if (Objects.equals(h.getDate(), holidayDate)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            // do something
            return false;
        }
    }

}


