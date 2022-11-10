package Models;

public class Admin extends User {

    // inherits username, password, age from the user class
    public int adminId;

    /**
     * constructor to instantiate Admin attribute in AdminUI
     * @param adminId Constructor Method of admin
     */

    public Admin(int adminId) {
        this.adminId = adminId;
    }

    /**
     * Overloaded Constructor method for admin
     * @param username
     * @param password
     * @param adminId
     */
    public Admin(String username, String password, int adminId){
        super(username, password);
        this.adminId = adminId;
    }

    /**
     * Getter and setter methods
     */
    public int getAdminId(){
        return this.adminId;
    }

    public void setAdminId(int adminId){
        this.adminId = adminId;
    }

    /**
     * Check if an object is an instance of Admin
     * @param o
     * @return boolean
     */
    public boolean equals(Object o) {
        if (o instanceof Admin) {
            Admin a = (Admin)o;
            if (getAdminId() == (a.getAdminId())){
                return true;
            }
        }
        return false;
    }
    
}
