package users;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;
import misc.Validations;

public class Instructor extends User {
    
    
    private Set<String> createdCoursesIDs;
    
    public Instructor() {
    }

    public Instructor(String userID, String username, String email, String passwordHash) {
        super(username, email, passwordHash);
        setUserID(userID);
        createdCoursesIDs = new HashSet<>();
    }
    
    @JsonProperty("id")
    @Override
    public void setUserID(String userID) {
        if (Validations.validateInstructorID(userID)) {
            this.userID = userID;
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    public void addCreatedCourse(String courseID){
        if(Validations.validateCourseID(courseID)){
            this.createdCoursesIDs.add(courseID);
        }
        else{
            throw new IllegalArgumentException("Invalid course ID");
        }
    }
    
    @JsonProperty("type")
    public String getType() {
        return "instructor";
    }
    @JsonProperty("id")
     public String getID() {
        return userID;
    }

}
