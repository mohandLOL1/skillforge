package courses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import misc.Validations;

public class Course {

    private String courseID;
    private String title;
    private String description;
    private String instructorID;
    private Set<Lesson> lessons;
    private ArrayList<String> courseEnrollmentsIDs;
    private String status;

    public Course() {
    }

    public Course(String courseID, String title, String description, String instructorID) {

        setID(courseID);
        setTitle(title);
        setDescription(description);
        setInstructorID(instructorID);
        this.lessons = new HashSet<>();
        this.courseEnrollmentsIDs = new ArrayList<>();
        this.status = "PENDING";       
    }

    

    public void setID(String courseID) {
        if (courseID == null || courseID.trim().isEmpty()) {
            throw new IllegalArgumentException("Course ID cannot be empty");
        }
        this.courseID = courseID.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description.trim();
    }

    public String getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(String instructorID) {
        if (!Validations.validateInstructorID(instructorID)) {
            throw new IllegalArgumentException("Instructor ID cannot be empty");
        }
        this.instructorID = instructorID.trim();
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }
    
    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status; 
    }
    
    public void removeLesson(Lesson lesson){
        this.lessons.remove(lesson);
    }
    
    public ArrayList<String> getCourseEnrollments() {
        return courseEnrollmentsIDs;
    }
    
    public void addCourseEnrollment(String string){
        this.courseEnrollmentsIDs.add(string);
    }

   public String getCourseID() {
       return this.courseID;
    }
}
