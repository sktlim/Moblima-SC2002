package Controllers;

import Models.*;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

import Exceptions.ItemNotFoundException;

public class TicketPriceManager {

    public final static String FILENAME = "Databases/prices.txt";
    public static final String SEPARATOR = ",";

    /**
     * reading (helper func, declared as private as it is only called within this file)
     **/
    private Map readPrices(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList) read(filename);
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
     * Read the contents of the given file.
     * (helper func, declared as private as it is only called within this file)
     */
    private List read(String fileName) throws IOException {
        List data = new ArrayList();
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
     * working and gtg
     */
    private Price createPrices(Map<String, Double> hashMap, Price priceList) {
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
     * working and gtg
     */
    public void printPriceList() {
        try {
            Price priceList = new Price();
            Map hm = readPrices(FILENAME);
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
     * need fix update price; not completely done
     */
    public void updatePrice(int selection, int updatedPrice) {
        try {
            Price priceList = new Price();
            Map hm = readPrices(FILENAME);
            System.out.println("Select price to change from Price List: \n");
            printPriceList();
            //        Scanner input = new Scanner(System.in);
            //        input.nextInt()
            switch (selection) {
                case 1:
                    priceList.setStandard_2DWeekday_MonWed(updatedPrice);
                    break;
                case 2:
                    priceList.setStandard_2DWeekday_Thu(updatedPrice);
                    break;
                case 3:
                    priceList.setStandard_2DWeekday_Fri(updatedPrice);
                    break;
                case 4:
                    priceList.setStandard_2DWeekend(updatedPrice);
                    break;
                case 5:
                    priceList.setStandard_2DHoliday(updatedPrice);
                    break;
                case 6:
                    priceList.setStandard_3DWeekday_MonWed(updatedPrice);
                    break;
                case 7:
                    priceList.setStandard_3DWeekday_Thu(updatedPrice);
                    break;
                case 8:
                    priceList.setStandard_3DWeekday_Fri(updatedPrice);
                    break;
                case 9:
                    priceList.setStandard_3DWeekend(updatedPrice);
                    break;
                case 10:
                    priceList.setStandard_3DHoliday(updatedPrice);
                    break;
                case 11:
                    priceList.setStandard_BlockBusterWeekday_MonWed(updatedPrice);
                    break;
                case 12:
                    priceList.setStandard_BlockBusterWeekday_Thu(updatedPrice);
                    break;
                case 13:
                    priceList.setStandard_BlockBusterWeekday_Fri(updatedPrice);
                    break;
                case 14:
                    priceList.setStandard_BlockBusterWeekend(updatedPrice);
                    break;
                case 15:
                    priceList.setStandard_BlockBusterHoliday(updatedPrice);
                    break;
                case 16:
                    priceList.setPlatinum_2DWeekday_MonWed(updatedPrice);
                    break;
                case 17:
                    priceList.setPlatinum_2DWeekday_Thu(updatedPrice);
                    break;
                case 18:
                    priceList.setPlatinum_2DWeekday_Fri(updatedPrice);
                    break;
                case 19:
                    priceList.setPlatinum_2DWeekend(updatedPrice);
                    break;
                case 20:
                    priceList.setPlatinum_2DHoliday(updatedPrice);
                    break;
                case 21:
                    priceList.setPlatinum_3DWeekday_MonWed(updatedPrice);
                    break;
                case 22:
                    priceList.setPlatinum_3DWeekday_Thu(updatedPrice);
                    break;
                case 23:
                    priceList.setPlatinum_3DWeekday_Fri(updatedPrice);
                    break;
                case 24:
                    priceList.setPlatinum_3DWeekend(updatedPrice);
                    break;
                case 25:
                    priceList.setPlatinum_3DHoliday(updatedPrice);
                    break;
                case 26:
                    priceList.setPlatinum_BlockBusterWeekday_MonWed(updatedPrice);
                    break;
                case 27:
                    priceList.setPlatinum_BlockBusterWeekday_Thu(updatedPrice);
                    break;
                case 28:
                    priceList.setPlatinum_BlockBusterWeekday_Fri(updatedPrice);
                    break;
                case 29:
                    priceList.setPlatinum_BlockBusterWeekend(updatedPrice);
                    break;
                case 30:
                    priceList.setPlatinum_BlockBusterHoliday(updatedPrice);
                    break;
                case 31:
                    priceList.setStudent(updatedPrice);
                    break;
                case 32:
                    priceList.setSeniorCitizen(updatedPrice);
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public double calculatePrice(Show show, Ticket ticket, String strDate, Movie movie) {
        double price = 0;
        Movie.MovieType movieType = movie.getMovieType();
        Show.TheatreClass theatreClass = show.getTheaterClass();
        String startTime = show.getStartTime();
        LocalTime time = LocalTime.parse(startTime);
        Ticket.UserAgeType age = ticket.getUserAgeType();

        try {
            Price priceList = new Price();
            Map hm = readPrices(FILENAME);
            createPrices(hm, priceList);

            /* Find day of week */
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = format.parse(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            calendar.setTime(date);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (theatreClass == Show.TheatreClass.STANDARD) {
                if (movieType == Movie.MovieType.TWO_D) {
                    if (dayOfWeek > 1 && dayOfWeek < 5) {
                        // check Mon-Wed
                        if (age == Ticket.UserAgeType.SENIOR) {
                            // check elderly
                            price = priceList.getSeniorCitizen();
                        }
                        if (age == Ticket.UserAgeType.STUDENT) {
                            // check student
                            price = priceList.getStudent();
                        } else {
                            //charge adult
                            price = priceList.getStandard_2DWeekday_MonWed();
                        }
                    } else if (dayOfWeek == 5) {
                        // check Thu
                        if (age == Ticket.UserAgeType.SENIOR) {
                            // check elderly
                            price = priceList.getSeniorCitizen();
                        }
                        if (age == Ticket.UserAgeType.STUDENT) {
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

                    }
                    else if (HolidayManager.isHoliday(strDate)) {
                        price = priceList.getStandard_2DHoliday();
                    }
                    else if (dayOfWeek == 1 || dayOfWeek == 7) {
                        // check weekends
                        price = priceList.getStandard_2DWeekend();
                    }
                    else {
                        System.out.println("you shouldn't be able to reach here");
                    }
                }
                if (movieType == Movie.MovieType.THREE_D) {
                    if (dayOfWeek > 1 && dayOfWeek < 5) {
                        // check Mon-Wed
                        price = priceList.getStandard_3DWeekday_MonWed();

                    } else if (dayOfWeek == 5) {
                        // check Thu
                        price = priceList.getStandard_3DWeekday_Thu();

                    } else if (dayOfWeek == 6 && time.isBefore(LocalTime.parse("18:00"))) {
                        // check Fri before 6pm
                        price = priceList.getStandard_3DWeekday_Fri();

                    }
                    else if (HolidayManager.isHoliday(strDate)) {
                        price = priceList.getStandard_3DHoliday();
                    }
                    else if (dayOfWeek == 1 || dayOfWeek == 7) {
                        // check weekends
                        price = priceList.getStandard_3DWeekend();
                    }
                    else {
                        System.out.println("you shouldn't be able to reach here");
                    }
                }

                if (movieType == Movie.MovieType.BLOCKBUSTER) {
                    if (dayOfWeek > 1 && dayOfWeek < 5) {
                        // check Mon-Wed
                        price = priceList.getStandard_BlockBusterWeekday_MonWed();

                    } else if (dayOfWeek == 5) {
                        // check Thu
                        price = priceList.getStandard_BlockBusterWeekday_Thu();

                    } else if (dayOfWeek == 6 && time.isBefore(LocalTime.parse("18:00"))) {
                        // check Fri before 6pm
                        price = priceList.getStandard_BlockBusterWeekday_Fri();

                    }
                    else if (HolidayManager.isHoliday(strDate)) {
                        price = priceList.getStandard_BlockBusterHoliday();
                    }
                    else if (dayOfWeek == 1 || dayOfWeek == 7) {
                        // check weekends
                        price = priceList.getStandard_BlockBusterWeekend();
                    }
                    else {
                        System.out.println("you shouldn't be able to reach here");
                    }
                }
            }

            if (theatreClass == Show.TheatreClass.PLATINUM) {
                if (movieType == Movie.MovieType.TWO_D) {
                    if (dayOfWeek > 1 && dayOfWeek < 5) {
                        // check Mon-Wed
                        price = priceList.getPlatinum_2DWeekday_MonWed();

                    } else if (dayOfWeek == 5) {
                        // check Thu
                        price = priceList.getPlatinum_2DWeekday_Thu();

                    } else if (dayOfWeek == 6 && time.isBefore(LocalTime.parse("18:00"))) {
                        // check Fri before 6pm
                        price = priceList.getPlatinum_2DWeekday_Fri();

                    }
                    else if (HolidayManager.isHoliday(strDate)) {
                        price = priceList.getPlatinum_2DHoliday();
                    }
                    else if (dayOfWeek == 1 || dayOfWeek == 7) {
                        // check weekends
                        price = priceList.getPlatinum_2DWeekend();
                    }
                    else {
                        System.out.println("you shouldn't be able to reach here");
                    }
                }
                if (movieType == Movie.MovieType.THREE_D) {
                    if (dayOfWeek > 1 && dayOfWeek < 5) {
                        // check Mon-Wed
                        price = priceList.getPlatinum_3DWeekday_MonWed();

                    } else if (dayOfWeek == 5) {
                        // check Thu
                        price = priceList.getPlatinum_3DWeekday_Thu();

                    } else if (dayOfWeek == 6 && time.isBefore(LocalTime.parse("18:00"))) {
                        // check Fri before 6pm
                        price = priceList.getPlatinum_3DWeekday_Fri();

                    }
                    else if (HolidayManager.isHoliday(strDate)) {
                        price = priceList.getPlatinum_3DHoliday();
                    }
                    else if (dayOfWeek == 1 || dayOfWeek == 7) {
                        // check weekends
                        price = priceList.getPlatinum_3DWeekend();
                    }
                    else {
                        System.out.println("you shouldn't be able to reach here");
                    }
                }
                if (movieType == Movie.MovieType.BLOCKBUSTER) {
                    if (dayOfWeek > 1 && dayOfWeek < 5) {
                        // check Mon-Wed
                        price = priceList.getPlatinum_BlockBusterWeekday_MonWed();

                    } else if (dayOfWeek == 5) {
                        // check Thu
                        price = priceList.getPlatinum_BlockBusterWeekday_Thu();

                    } else if (dayOfWeek == 6 && time.isBefore(LocalTime.parse("18:00"))) {
                        // check Fri before 6pm
                        price = priceList.getPlatinum_BlockBusterWeekday_Fri();

                    }
                    else if (HolidayManager.isHoliday(strDate)) {
                        price = priceList.getPlatinum_BlockBusterHoliday();
                    }
                    else if (dayOfWeek == 1 || dayOfWeek == 7) {
                        // check weekends
                        price = priceList.getPlatinum_BlockBusterWeekend();
                    }
                    else {
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
}

