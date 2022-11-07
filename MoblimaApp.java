import Controllers.*;

import java.util.*;


public class MoblimaApp {

    

    /** Scanner initiated in main, passed in all other functions as Scanner sc */
    public static void main (String [] args){
        Scanner sc = new Scanner(System.in);

        MovieGoerManager mgm = new MovieGoerManager();
        AdminManager am = new AdminManager();
        MovieManagerMovieGoer mgp = new MovieManagerMovieGoer();
        MovieManagerAdmin mga = new MovieManagerAdmin();

        mga.createMovie(sc);
        mga.printMovieList();
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
