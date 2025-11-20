
package services;

import filehandler.UserDataBase;
import java.io.IOException;
import java.util.ArrayList;
import misc.Generator;
import misc.SHA256;
import users.*;


public class UserService {
    private static final UserDataBase userdb = new UserDataBase("users.json");
    private static ArrayList<User> users;
    
    public UserService() throws IOException{
        userdb.read();
        users = userdb.returnAllRecords();
    }
    
    public User validateLogin(String username, String password){
        String hashedPassword = SHA256.hash(password);
        for(User user : users){
            if(user.getUsername().equals(username) && user.getPasswordHash().equals(hashedPassword)){
                return user;
            }
        }
        return null;
    }
    
    public boolean containsUser(String userID){
       for(User user : users){
           if(user.getID().equals(userID)){
               return true;
           }
       }
       return false;
    }
    
    public User getUser(String userID){
        for(User user : users){
            if(user.getID().equals(userID))
                return user;
        }
        
        throw new IllegalArgumentException("Couldn't get user");
    }
    
    public void registerUser(String role, String username, String email, String password) throws IOException{ //only two users
        
        String ID;
        String hashedPassword = SHA256.hash(password); 
  
        if(role.equalsIgnoreCase("student")){
            
            ID = Generator.generateStudentID();
            while(containsUser(ID)){
                ID = Generator.generateStudentID();
            }
            
        }
        else {
            ID = Generator.generateInstructorID();
            while(containsUser(ID))
                ID = Generator.generateInstructorID();
        }
        if(role.equalsIgnoreCase("student")){
        users.add(new Student(ID,username,email,hashedPassword));
        }
        else{
        users.add(new Instructor(ID,username,email,hashedPassword)); 
        }
        userdb.write();
    }
    
    public boolean usernameExists(String username){
    for (User u : users) {
        if (u.getUsername().equalsIgnoreCase(username)) {
            return true;
        }
    }
    return false;
   }
    
    public void deleteUser(String userID) throws IOException{
       for(User user : users){
           if(user.getID().equals(userID)){
               users.remove(user);
               userdb.write();
           }
       }
       
       throw new IllegalArgumentException("Couldn't find user with that ID");
    } 
    
    public ArrayList<User> returnAllUsers(){
        ArrayList<User> copies = new ArrayList<>(users);
        return copies;
    }
    
    public void saveUsers() throws IOException{
        this.userdb.write();
    }
    
    
    
    
    
}
