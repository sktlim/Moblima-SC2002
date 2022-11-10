package Models;

public class User{
    private String username;
    private String password;

    /* blank constructor to instantiate Admin and MovieGoer attributes in UI classes */
    public User() {}

    /**
     * Constructore for user
     * @param username
     * @param password
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Getter and Setter Methods
     * @return
     */
    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }



    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

}

