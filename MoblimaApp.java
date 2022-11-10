import Controllers.*;
import Models.*;
import Boundary.*;

import java.util.Scanner;

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
        System.out.println("Please make a selection:\n1:Login as admin\n2:Login as moviegoer\n3:Create a new movie goer\n4:Exit application");
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

//                        SeatManager.updateSeatPlan(1, "A3", 0);
                        SeatManager.isSeatAvail(1, "A3");

                        break;
                    case 2:
                        // Login as moviegoer
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
