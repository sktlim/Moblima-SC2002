package Controllers;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Exceptions.ItemNotFoundException;
import java.util.InputMismatchException;
import Models.Admin;



/** Completed MH (10Nov)*/

public class AdminManager implements Manager{
    public static final String SEPARATOR = "|";
    public static final String FILENAME = "Databases/admins.txt" ;



    /**
     * Create method to add new admin to the database
     * @param sc Takes in the scanner to instantiate variables within
     *           the function itself.
     *           Password is checked with a confirm password field as per
     *           normal sign up operations.
     */
    public static void createAdmin(Scanner sc){
        try {
            System.out.println("Enter Username: ");
            String username = sc.nextLine();
            int semaphore = 0; // password validation flag
            String password = "password";
            while(semaphore != 1){
                System.out.println("Enter Password: ");
                password = sc.nextLine();
                System.out.println("Confirm Password: ");
                String confirmation = sc.nextLine();
                if (password.equals(confirmation)){
                    semaphore = 1;
                }
                else{
                    System.out.println("Passwords do not match. Please re-enter.");
                }
            }

            ArrayList al = readAdmins(FILENAME);
            Admin a1 = new Admin(username, password, al.size()+1);
            al.add(a1);
            saveAdmins(FILENAME, al);
            System.out.println("New admin successfully created!\n");

        }
        catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println("String inputs were expected, but your input was of a different format > " + e.getMessage());
        }
    }


    /**
     * Prints the entire admin list from the database,
     */
    public static void printAdminList(){
        try{
            ArrayList al = readAdmins(FILENAME);
            for (int i = 0 ; i < al.size() ; i++) {
                Admin adm = (Admin)al.get(i);
                System.out.println("AdminID: " + adm.getAdminId() );
                System.out.println("Username: " + adm.getUsername() );
                System.out.println("Password: " + adm.getPassword());
            }


        }
        catch (IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
    }


    /**
     * Read Method
     * Find Admins by their Admin ID
     * @param adminID
     * @return Admin Object
     */
    public static Admin findAdmin(int adminID){
        try{
            ArrayList al = readAdmins(FILENAME);
            for (int i = 0 ; i < al.size() ; i++) {
                Admin adm = (Admin)al.get(i);
                if (adm.getAdminId()==adminID){
                    System.out.println("Admin Successfully Found.");
                    return adm;
                }
            }
            throw new ItemNotFoundException();

        }
        catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("Admin not found > " + e.getMessage());
        }
        return null;
    }

    /** Update Method
     * Used to update the various fields of admin based on admin ID
     * @param adminID
     * @param sc scanner is passed in to change various fields within the function
     */
    public static void updateAdmin(int adminID, Scanner sc){
        int semaphore = 0; // flag variable for password validation
        String inputField = "0";

        try{
            System.out.println("Select field to change:");
            System.out.println("0: Username");
            System.out.println("1: Password");

            int fieldEdit = sc.nextInt();
            sc.nextLine();

            if (fieldEdit == 0){
                System.out.println("Enter new Username: ");
                inputField = sc.nextLine();
            }
            else if (fieldEdit == 1){
                System.out.println("Enter new Password: ");
                inputField = sc.nextLine();
                System.out.println("Re-Enter new Password:");
                String check = sc.nextLine();
                if (check.equals(inputField)){
                    semaphore = 1;
                }
            }


            ArrayList al = readAdmins(FILENAME);
            boolean foundRequestedAdmin = false; // flag to determine if the admin to be updated exists
            for (int i=0; i<al.size(); i++){
                Admin adm = (Admin) al.get(i);
                if (adm.getAdminId() == adminID){ // admin has been found
                    foundRequestedAdmin = true;
                    if (fieldEdit == 0 && inputField != "0"){
                        adm.setUsername(inputField);
                        System.out.println("Username successfully updated.");

                    }
                    else if (fieldEdit == 1 && semaphore == 1 && inputField != "0"){
                        adm.setPassword(inputField);
                        System.out.println("Password successfully updated.");
                    }
                    else if (fieldEdit == 1 && semaphore == 0 && inputField != "0"){
                        System.out.println("Password does not match.");
                        System.out.println("Password update unsuccessful.");
                    }
                }
            }
            saveAdmins(FILENAME, al);

            if (!foundRequestedAdmin) { // the requested admin was not found
                throw new ItemNotFoundException();
            }
        }
        catch(IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("Admin not found > " + e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println("Your input was of a wrong format! Please ensure that your input is an integer.");
        }
    }

    /**
     * Delete Method
     * Deletes entry on the database based on adminID
     * @param adminID
     */
    public static void deleteAdmin(int adminID){
        try{
            boolean foundRequestedAdmin = false;
            ArrayList al = readAdmins(FILENAME);
            for (int i=0; i<al.size(); i++){
                Admin adm = (Admin) al.get(i);
                if (adm.getAdminId() == adminID){ // admin has been found
                    foundRequestedAdmin = true;
                    al.remove(i);
                    System.out.println("Admin " + adminID + " has been deleted!\n");
                }
            }
            saveAdmins(FILENAME, al);

            if (!foundRequestedAdmin) {
                throw new ItemNotFoundException();
            }
        }
        catch(IOException e){
            System.out.println("IOException > " + e.getMessage());
        }
        catch (ItemNotFoundException e) {
            System.out.println("Admin not found > " + e.getMessage());
        }
    }



    /**
     * Reading (Helper function)
     * Declared as private as it is only called within the scope of this file
     * @param filename
     * @return Array list of admins
     * @throws IOException
     */

    private static ArrayList readAdmins(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(filename);
        ArrayList alr = new ArrayList() ;// to store Admin data

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

    /** Write a fixed content to the given file
     * Declared as private as it is only called within the scope of this file
     * @param fileName
     * @param data
     * @throws IOException
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

    /** Read the contents of the given file.
     * Declared as private as it is only called within the scope of this file
     * @param fileName
     * @return
     * @throws IOException
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


    /** Saving (Helper function)
     * declared as private as it is only called within this file
     * @param filename
     * @param al
     * @throws IOException
     */
    private static void saveAdmins(String filename, List al) throws IOException {
        List alw = new ArrayList() ;// to store admins data

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


}




