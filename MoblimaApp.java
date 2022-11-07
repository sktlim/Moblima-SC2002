import Controllers.MovieGoerManager;
import Controllers.TransactionManager;

import java.util.Scanner;

public class MoblimaApp {
    static void showSelections() {
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
                        TransactionManager.createTransaction(12,15);
                        TransactionManager.printTransactionList();
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

//        MovieGoerManager mgm = new MovieGoerManager();
//        AdminManager am = new AdminManager();
//        MovieManagerMovieGoer mgp = new MovieManagerMovieGoer();
//        MovieManagerAdmin mga = new MovieManagerAdmin();
//
//        mga.createMovie(sc);
//        mga.printMovieList();
//        mgp.printMovieList();

//        mgp.printMovieList();

//        am.updateAdmin(5, sc);
//        am.printAdminList();

//        mgm.createMovieGoer(sc);
//        mgm.deleteMovieGoer(1);
//        mgm.printMovieGoerList();

//






    }
    
    
}
