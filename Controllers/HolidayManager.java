package Controllers;

import Models.Holiday;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class HolidayManager {

    public final static String FILENAME = "Databases/holidays.txt";
    public static final String SEPARATOR = "|";

    /** Read the contents of the given file.
     * (helper func, declared as private as it is only called within this file)*/
    private List read(String fileName) throws IOException {
        List data = new ArrayList();
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
    private Map readHolidays(String fileName) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(fileName);
        // ArrayList alr = new ArrayList() ;// to store Holiday data
        Map <String, String> hm = new HashMap<String, String>();

        for (Object o : stringArray) {
            String st = (String) o;
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, SEPARATOR);    // pass in the string to the string tokenizer using delimiter ","
            String holidayName = star.nextToken().trim();    // first token
            String date = star.nextToken().trim();    // second token
            // create price object from file data
            hm.put(holidayName, date);
        }
        return hm ;
    }


//    private Holiday createHolidays(Map <String, String> hashMap, Holiday holidayList) {
//
//    }


}
