package Controllers;

import Models.*;
import java.io.*;
import java.util.*;



public class AdminManager {


    public final static String FILENAME = "Databases/admins.txt";



    // CRUD methods
    public int createAdmin(String username, String password, int adminId){
        // create method
        try{
            Random rand = new Random()
            ArrayList<Admin> admins = new ArrayList<Admin>();
            File tempFile = new File(FILENAME);
            if (tempFile.exists()){
                admins = read();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME));
            admins.add(adm);

        }
        




        Admin adm = new Admin(username, password, adminId);

        // return adminId;
    }


    public ArrayList<Admin> read(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Admin> adminListing = (ArrayList<Admin>) ois.readObject();
            ois.close();
            return adminListing;
        }  

        catch(ClassNotFoundException | IOException e){

        }
        return new ArrayList<Admin>(); // returns empty admin list if no database is found
    }

    public void readAdmin(int adminId){
        //read method
        // print method for admin information
    }

    public boolean updateAdmin(String username, String password){
        //update method
        // true if success, false otherwise
    }

    public void deleteAdmin(int adminId){
        // delete method
    }



 
}

