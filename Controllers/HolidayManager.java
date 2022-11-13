package Controllers;

import Models.Holiday;

import java.io.*;
import java.util.*;
import java.time.LocalDate;

import Exceptions.ItemNotFoundException;
import java.util.InputMismatchException;
import java.lang.IllegalArgumentException;

/**
 * This class handles the CRUD (create, read, update, delete) methods for Holidays. Only Admins have access to the methods within this Class.
 * This class implements the read and write methods in the Manager interface.
 * When creating or updating Holiday dates, the following constraints are present:
 * 1) Month input must be >= 1 and <= 12
 * 2) Day of Month input must be >= 1 and <= 31
 */

public class HolidayManager implements Manager {

    /**
     * Path for the holidays.txt file
     */
    public final static String FILENAME = "Databases/holidays.txt";

    /**
     * Separator for parsing holidays.txt file
     */
    public static final String SEPARATOR = ",";

    /** Read the contents of the given file.
     * Declared as private as it is only called within the scope of this file
     * @param fileName This method assess data within the holidays.txt file
     * @return a List of Holiday objects
     * @throws IOException CHECKED --> Thrown when assessing data in the holidays.txt file fails
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


    /** Reading (Helper function)
     * Declared as private as it is only called within the scope of this file
     * @param fileName This method assess data within the holidays.txt file
     * @return Array list of Holiday objects
     * @throws IOException CHECKED --> Thrown when assessing data in the holidays.txt file fails
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
     *        InputMismatchException: UNCHECKED --> Thrown when user inputs an invalid format
     */
    public static void createHoliday(Scanner sc) {
        try {
            System.out.println("Please enter the holiday's Name: ");
            String holidayName = sc.nextLine();

            String input = "";
            System.out.println("Please enter the holiday's Year: ");
            input = sc.nextLine();
            int holidayYear = checkInput(input);

            // validate Month input
            int flag = -1;
            int holidayMonth = 0;
            while (flag == -1) { // repeatedly ask for input until input is valid
                System.out.println("Please enter the holiday's Month: ");
                input = sc.nextLine();
                holidayMonth = checkInput(input);
                if (holidayMonth < 1 || holidayMonth > 12) {
                    System.out.println("Your month is invalid. Ensure that it is between 1 and 12 inclusive.");
                    continue;
                }
                flag = 1;
            }

            // validate Day of Month input
            flag = -1;
            int holidayDate = 0;
            while (flag == -1) {
                System.out.println("Please enter the holiday's Day of Month: ");
                input = sc.nextLine();
                holidayDate = checkInput(input);
                if (holidayDate < 0 || holidayDate > 31) {
                    System.out.println("Your day of month is invalid. Ensure that it is between 1 and 31 inclusive.");
                    continue;
                }
                flag = 1;
            }

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
     * @param fileName This method assess data within the holidays.txt file
     * @param data List of Holiday objects that contains the Holiday instance to be updated
     * @throws IOException CHECKED --> Thrown when assessing data in the holidays.txt file fails
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

    /**Saving (Helper function)
     * declared as private as it is only called within this file
     * @param fileName This method assess data within the holidays.txt file
     * @param al List of Holiday objects to be saved into the database
     * @throws IOException CHECKED --> Thrown when assessing data in the holidays.txt file fails
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
        printHolidayList();
        System.out.println("Enter the name of the holiday you wish to change: ");
        String holidayName = sc.nextLine();
        String inputField = "0";
        LocalDate newDate = LocalDate.of(2022, 1, 1); // placeholder

        try {
            System.out.println("Select field to change: ");
            System.out.println("1: Name");
            System.out.println("2: Date");

            String input = sc.nextLine();
            int fieldEdit = checkInput(input);

            switch (fieldEdit) {
                case 1: // edit Name
                    System.out.println("Enter new Holiday Name: ");
                    inputField = sc.nextLine();
                    break;

                case 2: // edit Date
                    int newHolidayYear = 0;
                    int newHolidayMonth = 0;
                    int newHolidayDate = 0;
                    int flag = -1;

                    System.out.println("Enter new Holiday Year: ");
                    inputField = sc.nextLine();
                    newHolidayYear = checkInput(inputField);

                    while (flag == -1) {
                        System.out.println("Please enter the new Holiday Month: ");
                        input = sc.nextLine();
                        newHolidayMonth = checkInput(input);
                        if (newHolidayMonth < 1 || newHolidayMonth > 12) {
                            System.out.println("Your month is invalid. Ensure that it is between 1 and 12 inclusive.");
                            continue;
                        }
                        flag = 1;
                    }

                    flag = -1;
                    while (flag == -1) {
                        System.out.println("Please enter the new Holiday Day of Month: ");
                        input = sc.nextLine();
                        newHolidayDate = checkInput(input);
                        if (newHolidayDate < 0 || newHolidayDate > 31) {
                            System.out.println("Your day of month is invalid. Ensure that it is between 1 and 31 inclusive.");
                            continue;
                        }
                        flag = 1;
                    }

                    newDate = LocalDate.of(newHolidayYear, newHolidayMonth, newHolidayDate);

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
                            h.setDate(newDate.toString());
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
     * @param sc takes in scanner to ask which holiday is to be deleted
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

    /**
     * This method validates the String input of users and removes the problems of accepting integer input through the Scanner.
     * @param input String input by current user.
     * @return the integer representation of the input if parsing is successful.
     * @throws IllegalArgumentException UNCHECKED --> thrown when user input fails to be parsed as an integer.
     */
    public static int checkInput(String input) throws IllegalArgumentException {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("Input is not a valid integer. Please try again\n");
        }
    }

}


