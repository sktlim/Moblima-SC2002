import Controllers.*;
import Models.*;
import Boundary.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;
import static Controllers.AdminAuthManager.checker;

public class MoblimaApp {
    static void showSelections() {
        System.out.println("Welcome to MOBLIMA. ");
        System.out.println("Please make a selection:\n1:Login as Admin\n2:Login as Movie Goer\n3:Create a new Movie Goer\n4:Exit application");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = -1;
        while (true) {
            try {
                showSelections();
                input = sc.nextInt();
                sc.nextLine();
                switch (input) {
                    case 1:
                        // Login as admin
                        System.out.println("You have chosen to login as Admin. Please enter your login information below.");
                        String [] login_array = new String [2];
                        String username, password;

                        System.out.println("Enter your username: ");
                        username = sc.next();
                        System.out.println("Enter your password: ");
                        password = sc.next();

                        // Stores username and password to the array
                        login_array[0] = username;
                        login_array[1] = password;

                        boolean auth = checker(login_array [0],login_array [1]);
                        if (auth) {
                            return;
                        }
                        else{
                            System.out.println("Error! Details incorrect, please try again. ");
                        }
                        break;
                    case 2:
                        // Login as moviegoer
                        System.out.println("You have chosen to login as MovieGoer. ");
                        System.out.println("Enter your username: ");
                        username = sc.nextLine();
                        System.out.println("Enter your password: ");
                        password = sc.nextLine();
                        break;
                    case 3:
                        // Create a new moviegoer
                        MovieGoerManager.createMovieGoer(sc);
                        break;
                    case 4:
                        System.out.println("Exiting Application...");
                        return;
                    default:
                        System.out.println("Invalid input! Please try again.");
                        break;
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}