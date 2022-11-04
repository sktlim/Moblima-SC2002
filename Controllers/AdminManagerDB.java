package Controllers;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import Models.Admin;

public class AdminManagerDB {
    public static final String SEPARATOR = ",";

    /** reading */

    public static ArrayList readAdmins(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(filename);
        ArrayList alr = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < stringArray.size() ; i++) {
            String st = (String)stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","



            String  username = star.nextToken().trim();	// first token
            String  password = star.nextToken().trim();	// second token
            int  adminId = Integer.parseInt(star.nextToken().trim()); // third token
            // create admin object from file data
            Admin adm = new Admin(username, password, adminId);
            // add to Admin list
            alr.add(adm) ;
        }
        return alr ;
    }

    /** Write fixed content to the given file. */
    public static void write(String fileName, List data) throws IOException  {
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

    /** Read the contents of the given file. */
    public static List read(String fileName) throws IOException {
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

    /** saving */

    public static void saveAdmins(String filename, List al) throws IOException {
        List alw = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < al.size() ; i++) {
            Admin adm = (Admin)al.get(i);
            StringBuilder st =  new StringBuilder() ;
            st.append(adm.getUsername().trim());
            st.append(SEPARATOR);
            st.append(adm.getPassword().trim());
            st.append(SEPARATOR);
            st.append(adm.getAdminId());
            alw.add(st.toString()) ;
        }
        write(filename,alw);
    }

    public static void main(String[] aArgs)  {
        AdminManagerDB admDB = new AdminManagerDB();
        String filename = "Databases/admins.txt" ;
        try {
            // read file containing Professor records.
            ArrayList al = admDB.readAdmins(filename) ;
            for (int i = 0 ; i < al.size() ; i++) {
                Admin adm = (Admin)al.get(i);
                System.out.println("AdminID: " + adm.getAdminId() );
                System.out.println("Username: " + adm.getUsername() );
                System.out.println("Password: " + adm.getPassword());
            }
            Admin a1 = new Admin("Joseph","josephho",al.size()+1);
            // al is an array list containing Professor objs
            al.add(a1);
            // write Admin record/s to file.
            admDB.saveAdmins(filename, al);
        }catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
    }



}




