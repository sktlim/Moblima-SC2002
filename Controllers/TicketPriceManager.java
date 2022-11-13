package Controllers;

import Models.*;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Ticket Price Manager allows console user to print price list, update prices, and calculate prices of tickets
 */
public class TicketPriceManager implements Manager{
    /**
     * Path of prices.txt file
     */
    public final static String FILENAME = "Databases/prices.txt";

    /**
     * Separator for parsing prices.txt file
     */
    public static final String SEPARATOR = ",";

    /**
     * Initialises Ticket Price Manager
     */
    public TicketPriceManager() {
    }

    /**
     * Helper function for converting prices from .txt file to hash map
     * @return returns Hashmap with Key-Value of Price Type and Price respectively
     * @throws IOException Exception throw in in event of I/O error
     */
    private static Map<String, Double> readPrices() throws IOException {

        // read String from text file
        ArrayList stringArray = (ArrayList) read();
//        ArrayList alr = new ArrayList() ;// to store Price data
        Map<String, Double> hm = new HashMap<String, Double>();

        for (Object o : stringArray) {
            String st = (String) o;
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, SEPARATOR);    // pass in the string to the string tokenizer using delimiter ","
            String field = star.nextToken().trim();    // first token
            double price = Double.parseDouble(star.nextToken().trim());    // second token
            // create price object from file data
            hm.put(field, price);
        }
        return hm;
    }

    /**
     * Read the contents of the given file(helper func, declared as private as it is only called within this file).
     * @return Data input from .txt file
     * @throws IOException Error with reading .txt file
     */
    private static List read() throws IOException {

        List data = new ArrayList();
        Scanner scanner = new Scanner(new FileInputStream(TicketPriceManager.FILENAME));
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
     * Write fixed content to the given file(helper func, declared as private as it is only called within this file).
     * @param data Data input from .txt file
     * @throws IOException Error with reading to .txt file
     */
    private static void write(List data) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(TicketPriceManager.FILENAME));

        try {
            for (int i = 0; i < data.size(); i++) {
                out.println((String) data.get(i));
            }
        } finally {
            out.close();
        }
        System.out.println();
    }

    /**
     * Save prices from Prices Object to .txt file
     * @param hm Takes in hashmap of prices
     * @throws IOException Error with reading to .txt file
     */
    private static void savePrices(Map<String, Double> hm) throws IOException {
        List alw = new ArrayList();// to store prices data
        for (Map.Entry<String, Double> entry : hm.entrySet()) {
            StringBuilder st = new StringBuilder();
            st.append(entry.getKey());
            st.append(SEPARATOR);
            st.append(entry.getValue());
            st.append(SEPARATOR);

            alw.add(st.toString());
        }
        write(alw);
        System.out.println("Update Success!");
    }

    /**
     * Returns a Price Object with the initialised variables for the various different prices under different conditions
     *
     * @param hashMap   Hashmap with key-value pair of type String and Double respectively
     * @param priceList Price Object to update with Prices
     * @return Updated Price Object will be returned
     */
    private static Price createPrices(Map<String, Double> hashMap, Price priceList) {
        priceList.setStandard_2DWeekday_MonWed(hashMap.get("Standard_2DWeekday_MonWed"));
        priceList.setStandard_2DWeekday_Thu(hashMap.get("Standard_2DWeekday_Thu"));
        priceList.setStandard_2DWeekday_Fri(hashMap.get("Standard_2DWeekday_Fri"));
        priceList.setStandard_2DWeekend(hashMap.get("Standard_2DWeekend"));
        priceList.setStandard_2DHoliday(hashMap.get("Standard_2DHoliday"));
        priceList.setStandard_3DWeekday_MonWed(hashMap.get("Standard_3DWeekday_MonWed"));
        priceList.setStandard_3DWeekday_Thu(hashMap.get("Standard_3DWeekday_Thu"));
        priceList.setStandard_3DWeekday_Fri(hashMap.get("Standard_3DWeekday_Fri"));
        priceList.setStandard_3DWeekend(hashMap.get("Standard_3DWeekend"));
        priceList.setStandard_3DHoliday(hashMap.get("Standard_3DHoliday"));
        priceList.setStandard_BlockBusterWeekday_MonWed(hashMap.get("Standard_BlockBusterWeekday_MonWed"));
        priceList.setStandard_BlockBusterWeekday_Thu(hashMap.get("Standard_BlockBusterWeekday_Thu"));
        priceList.setStandard_BlockBusterWeekday_Fri(hashMap.get("Standard_BlockBusterWeekday_Fri"));
        priceList.setStandard_BlockBusterWeekend(hashMap.get("Standard_BlockBusterWeekend"));
        priceList.setStandard_BlockBusterHoliday(hashMap.get("Standard_BlockBusterHoliday"));
        priceList.setPlatinum_2DWeekday_MonWed(hashMap.get("Platinum_2DWeekday_MonWed"));
        priceList.setPlatinum_2DWeekday_Thu(hashMap.get("Platinum_2DWeekday_Thu"));
        priceList.setPlatinum_2DWeekday_Fri(hashMap.get("Platinum_2DWeekday_Fri"));
        priceList.setPlatinum_2DWeekend(hashMap.get("Platinum_2DWeekend"));
        priceList.setPlatinum_2DHoliday(hashMap.get("Platinum_2DHoliday"));
        priceList.setPlatinum_3DWeekday_MonWed(hashMap.get("Platinum_3DWeekday_MonWed"));
        priceList.setPlatinum_3DWeekday_Thu(hashMap.get("Platinum_3DWeekday_Thu"));
        priceList.setPlatinum_3DWeekday_Fri(hashMap.get("Platinum_3DWeekday_Fri"));
        priceList.setPlatinum_3DWeekend(hashMap.get("Platinum_3DWeekend"));
        priceList.setPlatinum_3DHoliday(hashMap.get("Platinum_3DHoliday"));
        priceList.setPlatinum_BlockBusterWeekday_MonWed(hashMap.get("Platinum_BlockBusterWeekday_MonWed"));
        priceList.setPlatinum_BlockBusterWeekday_Thu(hashMap.get("Platinum_BlockBusterWeekday_Thu"));
        priceList.setPlatinum_BlockBusterWeekday_Fri(hashMap.get("Platinum_BlockBusterWeekday_Fri"));
        priceList.setPlatinum_BlockBusterWeekend(hashMap.get("Platinum_BlockBusterWeekend"));
        priceList.setPlatinum_BlockBusterHoliday(hashMap.get("Platinum_BlockBusterHoliday"));
        priceList.setStudent(hashMap.get("student_discount"));
        priceList.setSeniorCitizen(hashMap.get("seniorCitizen_discount"));

        return priceList;
    }

    /**
     * Prints the full list of prices for every type
     */
    public static void printPriceList() {
        try {
            Price priceList = new Price();
            Map<String, Double> hm = readPrices();
            createPrices(hm, priceList);
            System.out.println("Price List");
            System.out.println("--------------------------");
            System.out.println("1. Standard 2D Weekday (Mon-Wed) " + priceList.getStandard_2DWeekday_MonWed());
            System.out.println("2. Standard 2D Weekday (Thu) " + priceList.getStandard_2DWeekday_Thu());
            System.out.println("3. Standard 2D Weekday (Fri before 6pm) " + priceList.getStandard_2DWeekday_Fri());
            System.out.println("4. Standard 2D Weekend " + priceList.getStandard_2DWeekend());
            System.out.println("5. Standard 2D Holiday " + priceList.getStandard_2DHoliday());
            System.out.println("6. Standard 3D Weekday (Mon-Wed) " + priceList.getStandard_3DWeekday_MonWed());
            System.out.println("7. Standard 3D Weekday (Thu) " + priceList.getStandard_3DWeekday_Thu());
            System.out.println("8. Standard 3D Weekday (Fri before 6pm)" + priceList.getStandard_3DWeekday_Fri());
            System.out.println("9. Standard 3D Weekend " + priceList.getStandard_3DWeekend());
            System.out.println("10. Standard 3D Holiday " + priceList.getStandard_3DHoliday());
            System.out.println("11. Standard BlockBuster Weekday (Mon-Wed) " + priceList.getStandard_BlockBusterWeekday_MonWed());
            System.out.println("12. Standard BlockBuster Weekday (Thu)" + priceList.getStandard_BlockBusterWeekday_Thu());
            System.out.println("13. Standard BlockBuster Weekday (Fri before 6pm) " + priceList.getStandard_BlockBusterWeekday_Fri());
            System.out.println("14. Standard BlockBuster Weekend " + priceList.getStandard_BlockBusterWeekend());
            System.out.println("15. Standard BlockBuster Holiday " + priceList.getStandard_BlockBusterHoliday());
            System.out.println("16. Platinum 2D Weekday (Mon-Wed)" + priceList.getPlatinum_2DWeekday_MonWed());
            System.out.println("17. Platinum 2D Weekday (Thu)" + priceList.getPlatinum_2DWeekday_Thu());
            System.out.println("18. Platinum 2D Weekday (Fri before 6pm)" + priceList.getPlatinum_2DWeekday_Fri());
            System.out.println("19. Platinum 2D Weekend " + priceList.getPlatinum_2DWeekend());
            System.out.println("20. Platinum 2D Holiday " + priceList.getPlatinum_2DHoliday());
            System.out.println("21. Platinum 3D Weekday (Mon-Wed)" + priceList.getPlatinum_3DWeekday_MonWed());
            System.out.println("22. Platinum 3D Weekday (Thu)" + priceList.getPlatinum_3DWeekday_Thu());
            System.out.println("23. Platinum 3D Weekday (Fri before 6pm)" + priceList.getPlatinum_3DWeekday_Fri());
            System.out.println("24. Platinum 3D Weekend " + priceList.getPlatinum_3DWeekend());
            System.out.println("25. Platinum 3D Holiday " + priceList.getPlatinum_3DHoliday());
            System.out.println("26. Platinum BlockBuster Weekday (Mon-Wed)" + priceList.getPlatinum_BlockBusterWeekday_MonWed());
            System.out.println("27. Platinum BlockBuster Weekday (Thu)" + priceList.getPlatinum_BlockBusterWeekday_Thu());
            System.out.println("28. Platinum BlockBuster Weekday (Fri before 6pm)" + priceList.getPlatinum_BlockBusterWeekday_Fri());
            System.out.println("29. Platinum BlockBuster Weekend " + priceList.getPlatinum_BlockBusterWeekend());
            System.out.println("30. Platinum BlockBuster Holiday " + priceList.getPlatinum_BlockBusterHoliday());
            System.out.println("31. Student discount " + priceList.getStudent());
            System.out.println("32. Senior Citizen discount " + priceList.getSeniorCitizen());
        } catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
    }

    /**
     * Updates prices based on input from user
     * @param sc Takes in Scanner object as input
     */
    public static void updatePrice(Scanner sc) {
        try {
            int selection;
            double updatedPrice;
            Price priceList = new Price();
            Map<String, Double> hm = readPrices();
            printPriceList();
            System.out.println("\nSelect price to change from Price List:");
            System.out.println("(Enter '0' to exit price update)");
            selection = sc.nextInt();

            while (selection != 0) {
                switch (selection) {
                    case 1:
                        System.out.println("Update Standard 2D Weekday(Mon-Wed): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setStandard_2DWeekday_MonWed(updatedPrice);
                        hm.replace("Standard_2DWeekday_MonWed", priceList.getStandard_2DWeekday_MonWed());
                        break;
                    case 2:
                        System.out.println("Update Standard 2D Weekday(Thu): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setStandard_2DWeekday_Thu(updatedPrice);
                        hm.replace("Standard_2DWeekday_Thu",priceList.getStandard_2DWeekday_Thu());
                        break;
                    case 3:
                        System.out.println("Update Standard 2D Weekday(Before Fri 6pm): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setStandard_2DWeekday_Fri(updatedPrice);
                        hm.replace("Standard_2DWeekday_Fri", priceList.getStandard_2DWeekday_Fri());
                        break;
                    case 4:
                        System.out.println("Update Standard 2D Weekend(After Fri 6pm): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setStandard_2DWeekend(updatedPrice);
                        hm.replace("Standard_2DWeekend", priceList.getStandard_2DWeekend());

                        break;
                    case 5:
                        System.out.println("Update Standard 2D Holiday: ");
                        updatedPrice = sc.nextDouble();
                        priceList.setStandard_2DHoliday(updatedPrice);
                        hm.replace("Standard_2DHoliday", priceList.getStandard_2DHoliday());

                        break;
                    case 6:
                        System.out.println("Update Standard 3D Weekday(Mon-Wed): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setStandard_3DWeekday_MonWed(updatedPrice);
                        hm.replace("Standard_3DWeekday_MonWed", priceList.getStandard_3DWeekday_MonWed());

                        break;
                    case 7:
                        System.out.println("Update Standard 3D Weekday(Thu): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setStandard_3DWeekday_Thu(updatedPrice);
                        hm.replace("Standard_3DWeekday_Thu", priceList.getStandard_3DWeekday_Thu());

                        break;
                    case 8:
                        System.out.println("Update Standard 3D Weekday(Before Fri 6pm): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setStandard_3DWeekday_Fri(updatedPrice);
                        hm.replace("Standard_3DWeekday_Fri", priceList.getStandard_3DWeekday_Fri());

                        break;
                    case 9:
                        System.out.println("Update Standard 3D Weekend(After Fri 6pm): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setStandard_3DWeekend(updatedPrice);
                        hm.replace("Standard_3DWeekend", priceList.getStandard_3DWeekend());

                        break;
                    case 10:
                        System.out.println("Update Standard 3D Holiday: ");
                        updatedPrice = sc.nextDouble();
                        priceList.setStandard_3DHoliday(updatedPrice);
                        hm.replace("Standard_3DHoliday", priceList.getStandard_3DHoliday());

                        break;
                    case 11:
                        System.out.println("Update Standard BlockBuster Weekday(Mon-Wed): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setStandard_BlockBusterWeekday_MonWed(updatedPrice);
                        hm.replace("Standard_BlockBusterWeekday_MonWed", priceList.getStandard_BlockBusterWeekday_MonWed());

                        break;
                    case 12:
                        System.out.println("Update Standard BlockBuster Weekday(Thu): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setStandard_BlockBusterWeekday_Thu(updatedPrice);
                        hm.replace("Standard_BlockBusterWeekday_Thu", priceList.getStandard_BlockBusterWeekday_Thu());

                        break;
                    case 13:
                        System.out.println("Update Standard BlockBuster Weekday(Before Fri 6pm): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setStandard_BlockBusterWeekday_Fri(updatedPrice);
                        hm.replace("Standard_BlockBusterWeekday_Fri", priceList.getStandard_BlockBusterWeekday_Fri());

                        break;
                    case 14:
                        System.out.println("Update Standard BlockBuster Weekend(After Fri 6pm): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setStandard_BlockBusterWeekend(updatedPrice);
                        hm.replace("Standard_BlockBusterWeekend", priceList.getStandard_BlockBusterWeekend());

                        break;
                    case 15:
                        System.out.println("Update Standard BlockBuster Holiday: ");
                        updatedPrice = sc.nextDouble();
                        priceList.setStandard_BlockBusterHoliday(updatedPrice);
                        hm.replace("Standard_BlockBusterHoliday", priceList.getStandard_BlockBusterHoliday());

                        break;
                    case 16:
                        System.out.println("Update Platinum 2D Weekday(Mon-Wed): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setPlatinum_2DWeekday_MonWed(updatedPrice);
                        hm.replace("Platinum_2DWeekday_MonWed", priceList.getPlatinum_2DWeekday_MonWed());

                        break;
                    case 17:
                        System.out.println("Update Platinum 2D Weekday(Thu): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setPlatinum_2DWeekday_Thu(updatedPrice);
                        hm.replace("Platinum_2DWeekday_Thu", priceList.getPlatinum_2DWeekday_Thu());

                        break;
                    case 18:
                        System.out.println("Update Platinum 2D Weekday(Before Fri 6pm): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setPlatinum_2DWeekday_Fri(updatedPrice);
                        hm.replace("Platinum_2DWeekday_Fri", priceList.getPlatinum_2DWeekday_Fri());

                        break;
                    case 19:
                        System.out.println("Update Platinum 2D Weekend(After Fri 6pm): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setPlatinum_2DWeekend(updatedPrice);
                        hm.replace("Platinum_2DWeekend", priceList.getPlatinum_2DWeekend());

                        break;
                    case 20:
                        System.out.println("Update Platinum 2D Holiday: ");
                        updatedPrice = sc.nextDouble();
                        priceList.setPlatinum_2DHoliday(updatedPrice);
                        hm.replace("Platinum_2DHoliday", priceList.getPlatinum_2DHoliday());

                        break;
                    case 21:
                        System.out.println("Update Platinum 3D Weekday(Mon-Wed): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setPlatinum_3DWeekday_MonWed(updatedPrice);
                        hm.replace("Platinum_3DWeekday_MonWed", priceList.getPlatinum_3DWeekday_MonWed());

                        break;
                    case 22:
                        System.out.println("Update Platinum 3D Weekday(Thu): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setPlatinum_3DWeekday_Thu(updatedPrice);
                        hm.replace("Platinum_3DWeekday_Thu", priceList.getPlatinum_3DWeekday_Thu());

                        break;
                    case 23:
                        System.out.println("Update Platinum 3D Weekday(Before Fri 6pm): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setPlatinum_3DWeekday_Fri(updatedPrice);
                        hm.replace("Platinum_3DWeekday_Fri", priceList.getPlatinum_3DWeekday_Fri());

                        break;
                    case 24:
                        System.out.println("Update Platinum 3D Weekend(After Fri 6pm): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setPlatinum_3DWeekend(updatedPrice);
                        hm.replace("Platinum_3DWeekend", priceList.getPlatinum_3DWeekend());

                        break;
                    case 25:
                        System.out.println("Update Platinum 3D Holiday: ");
                        updatedPrice = sc.nextDouble();
                        priceList.setPlatinum_3DHoliday(updatedPrice);
                        hm.replace("Platinum_3DHoliday", priceList.getPlatinum_3DHoliday());

                        break;
                    case 26:
                        System.out.println("Update Platinum BlockBuster Weekday(Mon-Wed): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setPlatinum_BlockBusterWeekday_MonWed(updatedPrice);
                        hm.replace("Platinum_BlockBusterWeekday_MonWed", priceList.getPlatinum_BlockBusterWeekday_MonWed());

                        break;
                    case 27:
                        System.out.println("Update Platinum BlockBuster Weekday(Thu): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setPlatinum_BlockBusterWeekday_Thu(updatedPrice);
                        hm.replace("Platinum_BlockBusterWeekday_Thu", priceList.getPlatinum_BlockBusterWeekday_Thu());

                        break;
                    case 28:
                        System.out.println("Update Platinum BlockBuster Weekday(Before Fri 6pm): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setPlatinum_BlockBusterWeekday_Fri(updatedPrice);
                        hm.replace("Platinum_BlockBusterWeekday_Fri", priceList.getPlatinum_BlockBusterWeekday_Fri());

                        break;
                    case 29:
                        System.out.println("Update Platinum BlockBuster Weekend(After Fri 6pm): ");
                        updatedPrice = sc.nextDouble();
                        priceList.setPlatinum_BlockBusterWeekend(updatedPrice);
                        hm.replace("Platinum_BlockBusterWeekend", priceList.getPlatinum_BlockBusterWeekend());

                        break;
                    case 30:
                        System.out.println("Update Platinum BlockBuster Holiday: ");
                        updatedPrice = sc.nextDouble();
                        priceList.setPlatinum_BlockBusterHoliday(updatedPrice);
                        hm.replace("Platinum_BlockBusterHoliday", priceList.getPlatinum_BlockBusterHoliday());

                        break;
                    case 31:
                        System.out.println("Update Student Price: ");
                        updatedPrice = sc.nextDouble();
                        priceList.setStudent(updatedPrice);
                        hm.replace("student_discount", priceList.getStudent());

                        break;
                    case 32:
                        System.out.println("Update Senior Citizen Price: ");
                        updatedPrice = sc.nextDouble();
                        priceList.setSeniorCitizen(updatedPrice);
                        hm.replace("seniorCitizen_discount", priceList.getSeniorCitizen());

                        break;
                    default:
                        break;
                }
                System.out.println("Price Updated");
                System.out.println("Enter new price to update or '0' to exit");
                selection = sc.nextInt();
//                sc.nextLine();
            }
            savePrices(hm);
            System.out.println("Exiting...");

        } catch (IOException e) {
            System.out.println(e);
        }
    }


    /**
     * Calculate price of ticket based on the show, age, date, time, theatre class and movie type
     * @param show    Show object
     * @param userAgeType  Ticket object
     * @param strDate Date in 'YYYY-MM-DD' format with type String
     * @param movie   Movie Object
     * @return Price of ticket for particular show and movie at particular date
     */
    public static double calculatePrice(Show show, Ticket.UserAgeType userAgeType, String strDate, Movie movie) {
        double price = 0;
        Movie.MovieType movieType = movie.getMovieType();
        Show.TheatreClass theatreClass = show.getTheatreClass();
        String startTime = show.getStartTime();
        LocalTime time = LocalTime.parse(startTime);
        Ticket.UserAgeType age = userAgeType;

//        System.out.println(movieType); //************tobeDeleted***********
//        System.out.println(theatreClass);
//        System.out.println(startTime);
//        System.out.println(strDate);
//        System.out.println(age); //*************

        try {
            Price priceList = new Price();
            Map<String, Double> hm = readPrices();
            createPrices(hm, priceList);

            /* Find day of week */
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = format.parse(strDate);
            } catch (ParseException e) {
                System.out.println("Parse Error > " + e.getMessage());
            }
            Date in = new Date();
            LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());

            String dateBeforeString = ldt.plusDays(1).toString();
            dateBeforeString = dateBeforeString.substring(0,dateBeforeString.length()-6);

            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            assert date != null;
            calendar.setTime(date);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (theatreClass == Show.TheatreClass.STANDARD) {
                 if (movieType == Movie.MovieType.TWO_D) {
                     if (HolidayManager.isHoliday(strDate) || HolidayManager.isHoliday(dateBeforeString)) {
                         price = priceList.getStandard_2DHoliday();
                     } else if (dayOfWeek > 1 && dayOfWeek < 5) {
                        // check Mon-Wed
                        if (age == Ticket.UserAgeType.SENIOR && time.isBefore(LocalTime.parse("18:00"))) {
                            // check elderly
                            price = priceList.getSeniorCitizen();
                        }
                        if (age == Ticket.UserAgeType.STUDENT && time.isBefore(LocalTime.parse("18:00"))) {
                            // check student
                            price = priceList.getStudent();
                        } else {
                            //charge adult
                            price = priceList.getStandard_2DWeekday_MonWed();
                        }
                    } else if (dayOfWeek == 5) {
                        // check Thu
                        if (age == Ticket.UserAgeType.SENIOR && time.isBefore(LocalTime.parse("18:00"))) {
                            // check elderly
                            price = priceList.getSeniorCitizen();
                        }
                        if (age == Ticket.UserAgeType.STUDENT && time.isBefore(LocalTime.parse("18:00"))) {
                            // check student
                            price = priceList.getStudent();
                        } else {
                            //charge adult
                            price = priceList.getStandard_2DWeekday_Thu();

                        }

                    } else if (dayOfWeek == 6 && time.isBefore(LocalTime.parse("18:00"))) {
                        // check Fri before 6pm
                        if (age == Ticket.UserAgeType.SENIOR) {
                            // check elderly
                            price = priceList.getSeniorCitizen();
                        }
                        if (age == Ticket.UserAgeType.STUDENT) {
                            // check student
                            price = priceList.getStudent();
                        } else {
                            //charge adult
                            price = priceList.getStandard_2DWeekday_Fri();
                        }

                    } else if (dayOfWeek == 1 || dayOfWeek == 7) {
                        // check weekends
                        price = priceList.getStandard_2DWeekend();
                    } else {
                        System.out.println("you shouldn't be able to reach here");
                    }
                }
                if (movieType == Movie.MovieType.THREE_D) {
                    if (HolidayManager.isHoliday(strDate) || HolidayManager.isHoliday(dateBeforeString)) {
                        price = priceList.getStandard_3DHoliday();
                    }
                    else if (age == Ticket.UserAgeType.STUDENT && time.isBefore(LocalTime.parse("18:00"))) {
                        // check student
                        price = priceList.getStudent() + 2;
                    }
                    else if (dayOfWeek > 1 && dayOfWeek < 5) {
                        // check Mon-Wed
                        price = priceList.getStandard_3DWeekday_MonWed();

                    } else if (dayOfWeek == 5) {
                        // check Thu
                        price = priceList.getStandard_3DWeekday_Thu();

                    } else if (dayOfWeek == 6 && time.isBefore(LocalTime.parse("18:00"))) {
                        // check Fri before 6pm
                        price = priceList.getStandard_3DWeekday_Fri();

                    } else if (dayOfWeek == 1 || dayOfWeek == 7) {
                        // check weekends
                        price = priceList.getStandard_3DWeekend();
                    } else {
                        System.out.println("you shouldn't be able to reach here");
                    }
                }

                if (movieType == Movie.MovieType.BLOCKBUSTER) {
                    if (HolidayManager.isHoliday(strDate) || HolidayManager.isHoliday(dateBeforeString)) {
                        price = priceList.getStandard_BlockBusterHoliday();
                    }
                    else if (dayOfWeek > 1 && dayOfWeek < 5) {
                        // check Mon-Wed
                        price = priceList.getStandard_BlockBusterWeekday_MonWed();

                    } else if (dayOfWeek == 5) {
                        // check Thu
                        price = priceList.getStandard_BlockBusterWeekday_Thu();

                    } else if (dayOfWeek == 6 && time.isBefore(LocalTime.parse("18:00"))) {
                        // check Fri before 6pm
                        price = priceList.getStandard_BlockBusterWeekday_Fri();

                    } else if (dayOfWeek == 1 || dayOfWeek == 7) {
                        // check weekends
                        price = priceList.getStandard_BlockBusterWeekend();
                    } else {
                        System.out.println("you shouldn't be able to reach here");
                    }
                }
            }

            if (theatreClass == Show.TheatreClass.PLATINUM) {
                if (movieType == Movie.MovieType.TWO_D) {
                    if (HolidayManager.isHoliday(strDate) || HolidayManager.isHoliday(dateBeforeString)) {
                        price = priceList.getPlatinum_2DHoliday();
                    }
                    else if (dayOfWeek > 1 && dayOfWeek < 5) {
                        // check Mon-Wed
                        price = priceList.getPlatinum_2DWeekday_MonWed();

                    } else if (dayOfWeek == 5) {
                        // check Thu
                        price = priceList.getPlatinum_2DWeekday_Thu();

                    } else if (dayOfWeek == 6 && time.isBefore(LocalTime.parse("18:00"))) {
                        // check Fri before 6pm
                        price = priceList.getPlatinum_2DWeekday_Fri();

                    } else if (dayOfWeek == 1 || dayOfWeek == 7) {
                        // check weekends
                        price = priceList.getPlatinum_2DWeekend();
                    } else {
                        System.out.println("you shouldn't be able to reach here");
                    }
                }
                if (movieType == Movie.MovieType.THREE_D) {
                    if (HolidayManager.isHoliday(strDate) || HolidayManager.isHoliday(dateBeforeString)) {
                        price = priceList.getPlatinum_3DHoliday();
                    }
                    else if (dayOfWeek > 1 && dayOfWeek < 5) {
                        // check Mon-Wed
                        price = priceList.getPlatinum_3DWeekday_MonWed();

                    } else if (dayOfWeek == 5) {
                        // check Thu
                        price = priceList.getPlatinum_3DWeekday_Thu();

                    } else if (dayOfWeek == 6 && time.isBefore(LocalTime.parse("18:00"))) {
                        // check Fri before 6pm
                        price = priceList.getPlatinum_3DWeekday_Fri();

                    } else if (dayOfWeek == 1 || dayOfWeek == 7) {
                        // check weekends
                        price = priceList.getPlatinum_3DWeekend();
                    } else {
                        System.out.println("you shouldn't be able to reach here");
                    }
                }
                if (movieType == Movie.MovieType.BLOCKBUSTER) {
                    if (HolidayManager.isHoliday(strDate) || HolidayManager.isHoliday(dateBeforeString)) {
                        price = priceList.getPlatinum_BlockBusterHoliday();
                    }
                    else if (dayOfWeek > 1 && dayOfWeek < 5) {
                        // check Mon-Wed
                        price = priceList.getPlatinum_BlockBusterWeekday_MonWed();

                    } else if (dayOfWeek == 5) {
                        // check Thu
                        price = priceList.getPlatinum_BlockBusterWeekday_Thu();

                    } else if (dayOfWeek == 6 && time.isBefore(LocalTime.parse("18:00"))) {
                        // check Fri before 6pm
                        price = priceList.getPlatinum_BlockBusterWeekday_Fri();

                    } else if (dayOfWeek == 1 || dayOfWeek == 7) {
                        // check weekends
                        price = priceList.getPlatinum_BlockBusterWeekend();
                    } else {
                        System.out.println("you shouldn't be able to reach here");
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
            System.out.println("Error reading price file");
        }
        return price;

    }


//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
////        TicketPriceManager tpm = new TicketPriceManager();
//        updatePrice(sc);
//    }

}


