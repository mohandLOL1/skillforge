package users;

import com.fasterxml.jackson.annotation.JsonProperty;
import courses.CourseEnrollment;
import java.util.HashSet;
import java.util.Set;
import misc.Generator;
import misc.Validations;

public class Student extends User {
    public String type = "student";
    private Set<CourseEnrollment> courseEnrollments;
    
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Student() {
    }

    public Student(String userID, String username, String email, String passwordHash) {
        super(username, email, passwordHash);
        setUserID(userID);
        courseEnrollments = new HashSet<>();
    }

    @JsonProperty("id")
    @Override
    public void setUserID(String userID) {
        if (Validations.validateStudentID(userID)) {
            this.userID = userID;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @JsonProperty("id")
    public String getID() {
        return userID;
    }

    public void addCourseEnrollment(CourseEnrollment courseEnrollment) {
        this.courseEnrollments.add(courseEnrollment);
    }

    public Set<CourseEnrollment> getCourseEnrollments() {
        return this.courseEnrollments;
    }

    @JsonProperty("type")
    public String getType() {
        return "student";
    }

}
