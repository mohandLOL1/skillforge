package users;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;
import misc.Validations;

public class Instructor extends User {

    private String type = "instructor";
    private Set<String> createdCoursesIDs;

    public Instructor() {
        createdCoursesIDs = new HashSet<>();
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

    public void addCreatedCourse(String courseID) {
        if (Validations.validateCourseID(courseID)) {
            this.createdCoursesIDs.add(courseID);
        } else {
            throw new IllegalArgumentException("Invalid course ID");
        }
    }

    public void removeCreatedCourse(String courseID) {

        this.createdCoursesIDs.remove(courseID);
    }

    public Set<String> getCreatedCoursesIDs() {
        return createdCoursesIDs;
    }

    @JsonProperty("createdCoursesIDs")
    public void setCreatedCoursesIDs(Set<String> createdCoursesIDs) {
        if (createdCoursesIDs != null) {
            this.createdCoursesIDs = createdCoursesIDs;
        } else {
            this.createdCoursesIDs = new HashSet<>();
        }
    }

    @JsonProperty("id")
    public String getID() {
        return userID;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("type")
    public String getType() {
        return "instructor";
    }

}
