package Controllers;

import Models.MovieGoer;
import Models.Price;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;

public class TicketPriceManager {

    public final static String FILENAME = "Databases/prices.txt";
    public static final String SEPARATOR = ",";

    /** reading (helper func, declared as private as it is only called within this file) **/
    private Map readPrices(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(filename);
//        ArrayList alr = new ArrayList() ;// to store Price data
        Map <String, Double> hm = new HashMap<String, Double>();

        for (Object o : stringArray) {
            String st = (String) o;
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, SEPARATOR);    // pass in the string to the string tokenizer using delimiter ","
            String field = star.nextToken().trim();    // first token
            double price = Double.parseDouble(star.nextToken().trim());    // second token
            // create price object from file data
            hm.put(field, price);
        }
        return hm ;
    }

    /** Read the contents of the given file.
     * (helper func, declared as private as it is only called within this file)*/
    private List read(String fileName) throws IOException {
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

    private Price createPrices(Map<String, Double> hashMap, Price priceList){
        priceList.setStandard_2DWeekday(hashMap.get("Standard_2DWeekday"));
        priceList.setStandard_2DWeekend(hashMap.get("Standard_2DWeekend"));
        priceList.setStandard_2DHoliday(hashMap.get("Standard_2DHoliday"));
        priceList.setStandard_3DWeekday(hashMap.get("Standard_3DWeekday"));
        priceList.setStandard_3DWeekend(hashMap.get("Standard_3DWeekend"));
        priceList.setStandard_3DHoliday(hashMap.get("Standard_3DHoliday"));
        priceList.setStandard_BlockBusterWeekday(hashMap.get("Standard_BlockBusterWeekday"));
        priceList.setStandard_BlockBusterWeekend(hashMap.get("Standard_BlockBusterWeekend"));
        priceList.setStandard_BlockBusterHoliday(hashMap.get("Standard_BlockBusterHoliday"));
        priceList.setSilver_2DWeekday(hashMap.get("Silver_2DWeekday"));
        priceList.setSilver_2DWeekend(hashMap.get("Silver_2DWeekend"));
        priceList.setSilver_2DHoliday(hashMap.get("Silver_2DHoliday"));
        priceList.setSilver_3DWeekday(hashMap.get("Silver_3DWeekday"));
        priceList.setSilver_3DWeekend(hashMap.get("Silver_3DWeekend"));
        priceList.setSilver_3DHoliday(hashMap.get("Silver_3DHoliday"));
        priceList.setSilver_BlockBusterWeekday(hashMap.get("Silver_BlockBusterWeekday"));
        priceList.setSilver_BlockBusterWeekend(hashMap.get("Silver_BlockBusterWeekend"));
        priceList.setSilver_BlockBusterHoliday(hashMap.get("Silver_BlockBusterHoliday"));
        priceList.setGold_2DWeekday(hashMap.get("Gold_2DWeekday"));
        priceList.setGold_2DWeekend(hashMap.get("Gold_2DWeekend"));
        priceList.setGold_2DHoliday(hashMap.get("Gold_2DHoliday"));
        priceList.setGold_3DWeekday(hashMap.get("Gold_3DWeekday"));
        priceList.setGold_3DWeekend(hashMap.get("Gold_3DWeekend"));
        priceList.setGold_3DHoliday(hashMap.get("Gold_3DHoliday"));
        priceList.setGold_BlockBusterWeekday(hashMap.get("Gold_BlockBusterWeekday"));
        priceList.setGold_BlockBusterWeekend(hashMap.get("Gold_BlockBusterWeekend"));
        priceList.setGold_BlockBusterHoliday(hashMap.get("Gold_BlockBusterHoliday"));
        priceList.setStudent(hashMap.get("student_discount"));
        priceList.setSeniorCitizen(hashMap.get("seniorCitizen_discount"));

        return priceList;
    }

    public void printPriceList(){
        try {
            Price priceList = new Price();
            Map hm = readPrices(FILENAME);
            createPrices(hm, priceList);
            System.out.println("Price List");
            System.out.println("--------------------------");
            System.out.println("1. Standard 2D Weekday " + priceList.getStandard_2DWeekday());
            System.out.println("2. Standard 2D Weekend " + priceList.getStandard_2DWeekend());
            System.out.println("3. Standard 2D Holiday " + priceList.getStandard_2DHoliday());
            System.out.println("4. Standard 3D Weekday " + priceList.getStandard_3DWeekday());
            System.out.println("5. Standard 3D Weekend " + priceList.getStandard_3DWeekend());
            System.out.println("6. Standard 3D Holiday " + priceList.getStandard_3DHoliday());
            System.out.println("7. Standard BlockBuster Weekday " + priceList.getStandard_BlockBusterWeekday());
            System.out.println("8. Standard BlockBuster Weekend " + priceList.getStandard_BlockBusterWeekend());
            System.out.println("9. Standard BlockBuster Holiday " + priceList.getStandard_BlockBusterHoliday());
            System.out.println("10. Silver 2D Weekday " + priceList.getSilver_2DWeekday());
            System.out.println("11. Silver 2D Weekend " + priceList.getSilver_2DWeekend());
            System.out.println("12. Silver 2D Holiday " + priceList.getSilver_2DHoliday());
            System.out.println("13. Silver 3D Weekday " + priceList.getSilver_3DWeekday());
            System.out.println("14. Silver 3D Weekend " + priceList.getSilver_3DWeekend());
            System.out.println("15. Silver 3D Holiday " + priceList.getSilver_3DHoliday());
            System.out.println("16. Silver BlockBuster Weekday " + priceList.getSilver_BlockBusterWeekday());
            System.out.println("17. Silver BlockBuster Weekend " + priceList.getSilver_BlockBusterWeekend());
            System.out.println("18. Silver BlockBuster Holiday " + priceList.getSilver_BlockBusterHoliday());
            System.out.println("19. Gold 2D Weekday " + priceList.getGold_2DWeekday());
            System.out.println("20. Gold 2D Weekend " + priceList.getGold_2DWeekend());
            System.out.println("21. Gold 2D Holiday " + priceList.getGold_2DHoliday());
            System.out.println("22. Gold 3D Weekday " + priceList.getGold_3DWeekday());
            System.out.println("23. Gold 3D Weekend " + priceList.getGold_3DWeekend());
            System.out.println("24. Gold 3D Holiday " + priceList.getGold_3DHoliday());
            System.out.println("25. Gold BlockBuster Weekday " + priceList.getGold_BlockBusterWeekday());
            System.out.println("26. Gold BlockBuster Weekend " + priceList.getGold_BlockBusterWeekend());
            System.out.println("27. Gold BlockBuster Holiday " + priceList.getGold_BlockBusterHoliday());
            System.out.println("28. Student discount " + priceList.getStudent());
            System.out.println("29. Senior Citizen discount " + priceList.getSeniorCitizen());
        }

        catch (IOException e){
            System.out.println(e);
        };
    }

    public void updatePrice(int selection ,int updatedPrice){
        try {
            Price priceList = new Price();
            Map hm = readPrices(FILENAME);
            System.out.println("Select price to change from Price List: \n");
            printPriceList();
            //        Scanner input = new Scanner(System.in);
            //        input.nextInt()
            switch (selection) {
                case 1:
                    priceList.setStandard_2DWeekday(updatedPrice);
                    break;
                case 2:
                    priceList.setStandard_2DWeekend(updatedPrice);
                    break;
                case 3:
                    priceList.setStandard_2DHoliday(updatedPrice);
                    break;
                case 4:
                    priceList.setStandard_3DWeekday(updatedPrice);
                    break;
                case 5:
                    priceList.setStandard_3DWeekend(updatedPrice);
                    break;
                case 6:
                    priceList.setStandard_3DHoliday(updatedPrice);
                    break;
                case 7:
                    priceList.setStandard_BlockBusterWeekday(updatedPrice);
                    break;
                case 8:
                    priceList.setStandard_BlockBusterWeekend(updatedPrice);
                    break;
                case 9:
                    priceList.setStandard_BlockBusterHoliday(updatedPrice);
                    break;
                case 10:
                    priceList.setSilver_2DWeekday(updatedPrice);
                    break;
                case 11:
                    priceList.setSilver_2DWeekend(updatedPrice);
                    break;
                case 12:
                    priceList.setSilver_2DHoliday(updatedPrice);
                    break;
                case 13:
                    priceList.setSilver_3DWeekday(updatedPrice);
                    break;
                case 14:
                    priceList.setSilver_3DWeekend(updatedPrice);
                    break;
                case 15:
                    priceList.setSilver_3DHoliday(updatedPrice);
                    break;
                case 16:
                    priceList.setSilver_BlockBusterWeekday(updatedPrice);
                    break;
                case 17:
                    priceList.setSilver_BlockBusterWeekend(updatedPrice);
                    break;
                case 18:
                    priceList.setSilver_BlockBusterHoliday(updatedPrice);
                    break;
                case 19:
                    priceList.setGold_2DWeekday(updatedPrice);
                    break;
                case 20:
                    priceList.setGold_2DWeekend(updatedPrice);
                    break;
                case 21:
                    priceList.setGold_2DHoliday(updatedPrice);
                    break;
                case 22:
                    priceList.setGold_3DWeekday(updatedPrice);
                    break;
                case 23:
                    priceList.setGold_3DWeekend(updatedPrice);
                    break;
                case 24:
                    priceList.setGold_3DHoliday(updatedPrice);
                    break;
                case 25:
                    priceList.setGold_BlockBusterWeekday(updatedPrice);
                    break;
                case 26:
                    priceList.setGold_BlockBusterWeekend(updatedPrice);
                    break;
                case 27:
                    priceList.setGold_BlockBusterHoliday(updatedPrice);
                    break;
                case 28:
                    priceList.setStudent(updatedPrice);
                    break;
                case 29:
                    priceList.setSeniorCitizen(updatedPrice);
                    break;
                default:
                    break;
            }
        }
            catch (IOException e){
                System.out.println(e);
            }
        }

//    public int calculatePrice(theatreClass theatreClass, int age, String date, movieType movieType ){
//
//    }
}

