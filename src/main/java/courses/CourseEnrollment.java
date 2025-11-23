package courses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import misc.Validations;

public class CourseEnrollment {
    
    private String courseEnrollmentID;
    private String studentID;
    private String courseID;
    private double percent;
    private Set<String> completedLessons;
    private ArrayList<StudentQuizAttempt> quizAttempts;

    public CourseEnrollment() {
        this.completedLessons = new HashSet<>();
        this.quizAttempts = new ArrayList<>(); 
    }

    public CourseEnrollment(String courseEnrollmentID, String studentID, String courseID, double percent) {
        
        
        setStudentID(studentID);
        setCourseID(courseID);
        setPercent(percent);
        this.completedLessons = new HashSet<>();
        this.quizAttempts = new ArrayList<>();  // initialize here too
    }
    
    public void setCourseEnrollmentID(String courseEnrollmentID){
        if(Validations.validateCourseEnrollmentID(courseEnrollmentID))
            this.courseEnrollmentID = courseEnrollmentID;
        else
            throw new IllegalArgumentException("Invalid course enrollment ID");
    }
    public String getID(){
        return this.courseEnrollmentID;
    }

// getter that returns a copy to avoid exposing internal list
    public ArrayList<StudentQuizAttempt> getQuizAttempts() {
        return new ArrayList<>(quizAttempts);
    }

    public void setQuizAttempts(ArrayList<StudentQuizAttempt> quizAttempts) {
        this.quizAttempts = quizAttempts;
    }
    

// add a new attempt
    public void addQuizAttempt(StudentQuizAttempt attempt) {
        if (attempt != null) {
            quizAttempts.add(attempt);
        } else {
            throw new IllegalArgumentException("Quiz attempt cannot be null");
        }
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        if (Validations.validateStudentID(studentID)) {
            this.studentID = studentID;
        } else {
            throw new IllegalArgumentException("Invalid student ID");
        }
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        if (Validations.validateCourseID(courseID) == true) {
            this.courseID = courseID;
        } else {
            throw new IllegalArgumentException("Invalid course ID");
        }
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        if (percent >= 0 && percent <= 100) {
            this.percent = percent;
        } else {
            throw new IllegalArgumentException("Invalid percent value");
        }
    }

    public Set<String> getCompletedLessons() {
        Set<String> copy = new HashSet<>();
        for (String s : completedLessons) {
            copy.add(s);
        }
        return copy;
    }

    public void addCompletedLesson(String lessonID) {
        if (Validations.validateLessonID(lessonID)) {
            completedLessons.add(lessonID);
        } else {
            throw new IllegalArgumentException("Invalid lesson ID");
        }

    }

}
