
package users;

import misc.Validations;


public class Student extends User{
    public Student(){
    }
    public Student(String userID, String username, String email, String passwordHash){
        super(username,email,passwordHash);
        setUserID(userID);
    }
    
    @Override
    public void setUserID(String userID){
        if(Validations.validateStudentID(userID)){
            this.userID = userID;
        }
        else
            throw new IllegalArgumentException();
    }
}
