package Models;

/**
 * User class stores the username and password of user.
 */
public class User{
    /**
     * Username associated with user
     */
    private String username;
    /**
     * Password associated with user
     */
    private String password;

    /**
     * Blank constructor to instantiate Admin and MovieGoer attributes in UI classes
     */
    public User() {}

    /**
     * Overloaded constructor for user
     * @param username Username associated with user
     * @param password Password associated with user
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Getter method for username
     * @return Username associated with user
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Getter method for password
     * @return password associated with user
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Setter method for username
     * @param username new username for user
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Setter method for password
     * @param password new password for user
     */
    public void setPassword(String password){
        this.password = password;
    }

}

