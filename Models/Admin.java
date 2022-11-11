package Models;

/**
 * Represents an Admin in our application.
 * Subclass of the base User class
 */

public class Admin extends User {

    /**
     * This class inherits the following fields from the base User class:
     * username
     * password
     * age
     * */

    /**
     * The unique ID for each admin
     * */
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
     * Creates a new Admin with the given params
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


    /**
     * Gets the unique ID of the Admin.
     * @return this Admin's ID
     * */
    public int getAdminId(){
        return this.adminId;
    }

    /**
     * Changes the unique ID of this Admin.
     * SHOULD NOT BE USED UNNECESSARILY
     * @param adminId
     */
    public void setAdminId(int adminId){
        this.adminId = adminId;
    }

    /**
     * Check if an object is an instance of Admin
     * @param o
     * @return boolean if the object is an instance of Admin
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
