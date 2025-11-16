
package users;

import filehandler.Searchable;
import misc.Validations;

public abstract class User implements Searchable{
    private String userID;
    private String username;
    private String email;
    private String passwordHash;
    
    public User(){
        
    }
    public User(String userID, String username, String email, String passwordHash){
        setUserID(userID);
        setUsername(username);
        setEmail(email);
        setPasswordHash(passwordHash);
    }
    

    public String getUserID() {
        return userID;
    }
    
    public abstract void setUserID(String userID);
   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(Validations.validateUsername(username) == true){
            this.username = username;
        }
        else{
            throw new IllegalArgumentException("Invalid username");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        if(Validations.validateEmail(email) == true){
            this.email = email;
        }
        else{
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        if(Validations.validatePasswordHash(passwordHash) == true){
            this.passwordHash = passwordHash;
        }
        else{
            throw new IllegalArgumentException("Invalid password hash, internal hashing error ");
        }
    }
    
    
    
    
    
    
    
    
}
