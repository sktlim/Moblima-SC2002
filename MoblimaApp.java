import Controllers.*;
import Models.*;
import Boundary.*;

import java.io.Console;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

import static Controllers.AdminAuthManager.authenticateAdmin;
import static Controllers.MovieGoerAuthManager.authenticateMovieGoer;
import static java.lang.String.valueOf;


public class MoblimaApp {
    private static void showSelections() {
        System.out.println("Please make a selection:\n1:Login as Admin\n2:Login as Movie Goer\n3:Create a new Movie Goer\n4.Enter as guest\n5:Exit application");
    }

    private static void wallPaper(){
        System.out.println("=========================================================================================================================================================================================================================");
        System.out.println("\n" +
                "                                                                                                                                                                        \n" +
                "MMMMMMMM               MMMMMMMM     OOOOOOOOO     BBBBBBBBBBBBBBBBB   LLLLLLLLLLL             IIIIIIIIIIMMMMMMMM               MMMMMMMM               AAA               \n" +
                "M:::::::M             M:::::::M   OO:::::::::OO   B::::::::::::::::B  L:::::::::L             I::::::::IM:::::::M             M:::::::M              A:::A              \n" +
                "M::::::::M           M::::::::M OO:::::::::::::OO B::::::BBBBBB:::::B L:::::::::L             I::::::::IM::::::::M           M::::::::M             A:::::A             \n" +
                "M:::::::::M         M:::::::::MO:::::::OOO:::::::OBB:::::B     B:::::BLL:::::::LL             II::::::IIM:::::::::M         M:::::::::M            A:::::::A            \n" +
                "M::::::::::M       M::::::::::MO::::::O   O::::::O  B::::B     B:::::B  L:::::L                 I::::I  M::::::::::M       M::::::::::M           A:::::::::A           \n" +
                "M:::::::::::M     M:::::::::::MO:::::O     O:::::O  B::::B     B:::::B  L:::::L                 I::::I  M:::::::::::M     M:::::::::::M          A:::::A:::::A          \n" +
                "M:::::::M::::M   M::::M:::::::MO:::::O     O:::::O  B::::BBBBBB:::::B   L:::::L                 I::::I  M:::::::M::::M   M::::M:::::::M         A:::::A A:::::A         \n" +
                "M::::::M M::::M M::::M M::::::MO:::::O     O:::::O  B:::::::::::::BB    L:::::L                 I::::I  M::::::M M::::M M::::M M::::::M        A:::::A   A:::::A        \n" +
                "M::::::M  M::::M::::M  M::::::MO:::::O     O:::::O  B::::BBBBBB:::::B   L:::::L                 I::::I  M::::::M  M::::M::::M  M::::::M       A:::::A     A:::::A       \n" +
                "M::::::M   M:::::::M   M::::::MO:::::O     O:::::O  B::::B     B:::::B  L:::::L                 I::::I  M::::::M   M:::::::M   M::::::M      A:::::AAAAAAAAA:::::A      \n" +
                "M::::::M    M:::::M    M::::::MO:::::O     O:::::O  B::::B     B:::::B  L:::::L                 I::::I  M::::::M    M:::::M    M::::::M     A:::::::::::::::::::::A     \n" +
                "M::::::M     MMMMM     M::::::MO::::::O   O::::::O  B::::B     B:::::B  L:::::L         LLLLLL  I::::I  M::::::M     MMMMM     M::::::M    A:::::AAAAAAAAAAAAA:::::A    \n" +
                "M::::::M               M::::::MO:::::::OOO:::::::OBB:::::BBBBBB::::::BLL:::::::LLLLLLLLL:::::LII::::::IIM::::::M               M::::::M   A:::::A             A:::::A   \n" +
                "M::::::M               M::::::M OO:::::::::::::OO B:::::::::::::::::B L::::::::::::::::::::::LI::::::::IM::::::M               M::::::M  A:::::A               A:::::A  \n" +
                "M::::::M               M::::::M   OO:::::::::OO   B::::::::::::::::B  L::::::::::::::::::::::LI::::::::IM::::::M               M::::::M A:::::A                 A:::::A \n" +
                "MMMMMMMM               MMMMMMMM     OOOOOOOOO     BBBBBBBBBBBBBBBBB   LLLLLLLLLLLLLLLLLLLLLLLLIIIIIIIIIIMMMMMMMM               MMMMMMMMAAAAAAA                   AAAAAAA\n" +
                "                                                                                                                                                                        \n" +
                "                                                                                                                                                                        \n");
        System.out.println("=========================================================================================================================================================================================================================");
        System.out.println("\n");
    }

    private static int checkInput (String input) throws Exception {
        if (input.length() == 1) return Integer.parseInt(input);
        else return Integer.parseInt(input.substring(0, 2));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        wallPaper();
        String input = null;

        while (true) {
            try {
                int option = -1;
                showSelections();
                input = sc.nextLine();
                option = checkInput(input);
                String [] login_array = new String [2];
                String username, password;
                switch (option) {
                    case 1:
                        // Login as admin
                        System.out.println("You have chosen to login as Admin. Please enter your login information below in a case sensitive format. ");
                        System.out.print("Enter your username: ");
                        username = sc.nextLine();
                        System.out.print("Enter your password: ");
                        password = sc.nextLine();
//                         password = String.valueOf(PasswordField.getPassword(System.in, "Enter password: "));
                        // Stores username and password to the array
                        login_array[0] = username;
                        login_array[1] = password;
                        int adminId = authenticateAdmin(login_array [0],login_array [1]);
                        if (adminId != -1) {
                            AdminUI aui = new AdminUI(adminId);
                            aui.showUI(sc);
                        }
                        else{
                            System.out.println("Error! Details incorrect, please try again. ");
                        }

                        break;
                    case 2:
                        // Login as moviegoer
                        System.out.println("You have chosen to login as a Movie Goer. Please enter your login information below in a case sensitive format.");
                        System.out.println("Enter your username: ");
                        username = sc.nextLine();
                        System.out.println("Enter your password: ");
                        password = sc.nextLine();
//                        password = String.valueOf(PasswordField.getPassword(System.in, "Enter password: "));


                        // Stores username and password to the array
                        login_array[0] = username;
                        login_array[1] = password;

                        int moviegoerId = authenticateMovieGoer(login_array [0],login_array [1]);
                        if (moviegoerId != -1) {
                            MovieGoerUI mui = new MovieGoerUI(moviegoerId);
                            mui.showUI(sc);
                        }
                        else{
                            System.out.println("Error! Details incorrect, please try again. ");
                            boolean movieGoerExists = MovieGoerManager.doesUserExist(username);
                            if (movieGoerExists) {
                                System.out.println("Your username is correct but password was incorrect.");
                            }
                            else {
                                System.out.println("You do not have an account with that username.\nPlease sign up.");
                            }
                        }
                        break;
                    case 3:
                        // Create a new moviegoer
                        MovieGoerManager.createMovieGoer(sc);
                        break;
                    case 4:

                    case 5:
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
