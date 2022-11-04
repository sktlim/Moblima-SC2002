import Controllers.AdminManager;
import java.util.Scanner;


public class MoblimaApp {
    static void showSelections()  {
        System.out.println("Please make a selection:\n1:Login as admin\n2:Login as moviegoer\n3:Create a new movie goer\n4:Exit application");
    }

    static boolean adminLogin(String username, String password) {
        return true;
    }

    static boolean movieGoerLogin(String username, String password) {
        return true;
    }

    static boolean createMovieGoer(String username, String password) {
        return true;
    }

    public static void main (String [] args){
        Scanner sc = new Scanner(System.in);
        int input = -1;
        try {
            while(true) {
                showSelections();
                input = sc.nextInt();
                sc.nextLine();
                switch(input) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        System.out.println("enter username:");
                        String username = sc.nextLine();
                        System.out.println("enter password:");
                        String password = sc.nextLine();


                        AdminManager am = new AdminManager();
                        am.createAdmin(username, password);

                        am.printAdminList();
                        break;
                    case 4:
                        System.out.println("Exiting Application...");
                        return;
                    default:
                        System.out.println("Invalid input! Please try again.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("hi");
            System.out.println(e.get);
        }
    }
}
