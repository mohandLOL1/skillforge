package courses;

import java.util.HashSet;
import java.util.Set;
import misc.Validations;


public class CourseEnrollment {
    private String courseID;
    private double percent;
    private final Set<String> completedLessons;

    public CourseEnrollment(String courseID, double percent) {
        setCourseID(courseID);
        setPercent(percent);
        this.completedLessons = new HashSet<>();    
    }

  

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        if(Validations.validateCourseID(courseID) == true){
            this.courseID = courseID;
        }
        else{
            throw new IllegalArgumentException("Invalid course ID");
        }
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        if(percent >= 0 && percent <= 100){
            this.percent = percent;
        }
        else{
            throw new IllegalArgumentException("Invalid percent value");
        }
    }

    public Set<String> getCompletedLessons() {
        Set<String> copy = new HashSet<>();
        for(String s : completedLessons){
            copy.add(s);
        }
        return copy;
    }

    public void addCompletedLesson(String lessonID) {
        if(Validations.validateLessonID(lessonID)){
            completedLessons.add(lessonID);
        }
        else{
            throw new IllegalArgumentException("Invalid lesson ID");
        }
        
    }
    
    
    
    
    
    
    
    
    
    
}
