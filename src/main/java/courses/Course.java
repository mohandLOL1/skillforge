
package courses;

import java.util.ArrayList;
import users.Student;

public class Course{
    private String courseID;
    private String title;
    private String description;
    private String instructorID;
    private final ArrayList<Lesson> lessons = new ArrayList<>();
    private final ArrayList<Student> students  = new ArrayList<>();
    public Course(){
    }
    public Course(String courseID, String title, String description, String instructorID){
        this.courseID = courseID;
        this.title = title;
        this.description = description;
        this.instructorID = instructorID;
    }
    public String getCourseID(){
        return courseID;
    }
     public void setCourseID(String courseID) {
        if (courseID == null || courseID.trim().isEmpty()){
            throw new IllegalArgumentException("Course ID cannot be empty");
        }
        this.courseID = courseID.trim();
    }
    public String getTitle(){
        return title;
    }
     public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title.trim();
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()){
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description.trim();
    }
    public String getInstructorID(){
        return instructorID;
    }
    public void setInstructorID(String instructorID) {
        if (instructorID == null || instructorID.trim().isEmpty()){
            throw new IllegalArgumentException("Instructor ID cannot be empty");
        }
        this.instructorID = instructorID.trim();
    }
    public ArrayList<Lesson> getLessons(){
        return lessons;
    }
    public ArrayList<Student> getStudents(){
        return students;
    }

    
}
