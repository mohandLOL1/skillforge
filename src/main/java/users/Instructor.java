package users;

import misc.Validations;

public class Instructor extends User{
    public Instructor(){
    }
    public Instructor(String userID, String username, String email, String passwordHash){
        super(username,email,passwordHash);
        setUserID(userID);
    }
    
    @Override
    public void setUserID(String userID){
        if(Validations.validateInstructorID(userID)){
            this.userID = userID;
        }
        else
            throw new IllegalArgumentException();
    }
}
