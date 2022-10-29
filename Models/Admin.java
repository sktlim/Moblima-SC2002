package Models;

public class Admin extends User {

    // inherits username, password, age from the user class

    public int adminId;

    public Admin(String username, String password, int adminId){
        super(username, password);
        this.adminId = adminId;
    }


    //getters setters
    public int getAdminId(){
        return this.adminId;
    }

    public void setAdminId(int adminId){
        this.adminId = adminId;
    }
    
}
