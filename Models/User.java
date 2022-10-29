package Models;

public class User{
    private String username;
    private String password;


    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    // getters setters

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

