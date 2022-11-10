import Controllers.*;
import Models.*;
import Boundary.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;
import static Controllers.AdminAuthManager.adminchecker;
import static Controllers.MovieGoerAuthManager.mgchecker;

public class MoblimaApp {
    static void showSelections() {
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

                        boolean auth = adminchecker(login_array [0],login_array [1]);
                        if (auth) {
                            return;
                        }
                        else{
                            System.out.println("Error! Details incorrect, please try again. ");
                        }
                        break;
                    case 2:
                        // Login as moviegoer
                        System.out.println("You have chosen to login as a Movie Goer. Please enter your login information below.");
                        String [] login_array2 = new String [2];
                        String username2, password2;

                        System.out.println("Enter your username: ");
                        username2 = sc.next();
                        System.out.println("Enter your password: ");
                        password2 = sc.next();

                        // Stores username and password to the array
                        login_array2[0] = username2;
                        login_array2[1] = password2;

                        boolean auth2 = mgchecker(login_array2 [0],login_array2 [1]);
                        if (auth2 == true) {
                            return;
                        }
                        else{
                            System.out.println("Error! Details incorrect, please try again. ");
                        }
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