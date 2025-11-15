package users;

import courses.CourseEnrollment;
import java.util.HashSet;
import java.util.Set;
import misc.Validations;

public class Student extends User {

    private Set<CourseEnrollment> courseEnrollments;

    public Student() {
    }

    public Student(String userID, String username, String email, String passwordHash) {
        super(username, email, passwordHash);
        setUserID(userID);
        courseEnrollments = new HashSet<>();
    }

    @Override
    public void setUserID(String userID) {
        if (Validations.validateStudentID(userID)) {
            this.userID = userID;
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    public void addCourseEnrollment(CourseEnrollment courseEnrollment){
        this.courseEnrollments.add(courseEnrollment);
    }
}
