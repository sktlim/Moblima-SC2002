import Boundary.*;
import Controllers.*;
import Models.*;
import java.util.*;


public class MoblimaApp {

    


    public static void main (String [] args){
        Scanner sc = new Scanner(System.in);



        System.out.println("enter username:");
        String username = sc.nextLine();
        System.out.println("enter password:");
        String password = sc.nextLine();


        AdminManager am = new AdminManager();
        am.createAdmin(username, password);

        am.printAdminList();
        


        
    }
    
    
}
