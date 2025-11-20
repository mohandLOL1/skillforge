package services;

import courses.*;
import filehandler.*;
import java.util.ArrayList;
import users.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import misc.Generator;

public class CourseService {

    private static final CourseDataBase coursedb = new CourseDataBase("courses.json");
    private static ArrayList<Course> courses;
    private static UserService userservice;

    public CourseService() throws IOException {
        courses = coursedb.returnAllRecords();
        userservice = new UserService();
    }
    
    private static void reload() throws IOException {
      coursedb.read();                
      courses = coursedb.returnAllRecords();
    }
    
    public boolean containsCourse(String courseID) {
        try {
            reload();
        } catch (IOException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Course course : courses) {
            if (course.getCourseID().equals(courseID)) {
                return true;
            }
        }
        return false;
    }

    public Course findCourse(String courseID) {
        try {
            reload();
        } catch (IOException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Course course : courses) {
            if (course.getCourseID().equals(courseID)) {
                return course;
            }
        }

        return null;
    }

    public boolean instructorOwnsCourse(String courseID, String InstructorID) {
        Course c = findCourse(courseID);
        if (c.getInstructorID().equals(InstructorID)) {
            return true;
        }

        return false;
    }

    public void createCourse(String title, String description, String instructorID) throws IOException, IllegalArgumentException {

        User tempUser = userservice.getUser(instructorID);

        if (tempUser instanceof Instructor) {

            String courseID = Generator.generateCourseID();
            while (containsCourse(courseID)) {
                courseID = Generator.generateCourseID();  //generates unique course ID
            }
            ((Instructor) tempUser).addCreatedCourse(courseID);
            Course c = new Course(courseID, title, description, instructorID);

            userservice.saveUsers();
            courses.add(c);
            coursedb.write();

        } else {
            throw new IllegalArgumentException("User isn't an instructor");
        }

    }

    public void deleteCourse(String courseID, String instructorID) throws IOException {

        Course c = findCourse(courseID);
        if (c == null) {
            throw new IllegalArgumentException("Couldn't find course");
        }
        if (instructorOwnsCourse(courseID, instructorID)) {
            courses.remove(c);
            coursedb.write();
        } else {
            throw new IllegalArgumentException("Cannot delete unowned course");
        }

    }

    public void editCourse(String courseID, String title, String description, String instructorID) throws IOException {
        Course oldCourse = findCourse(courseID);
        if (oldCourse == null) {
            throw new IllegalArgumentException("Couldn't find course");
        }
        if (instructorOwnsCourse(courseID, instructorID)) {
            oldCourse.setTitle(title);
            oldCourse.setDescription(description);
        } else {
            throw new IllegalArgumentException("Can only edit owned course");
        }
    }

    public void enrollStudent(String studentID, String courseID) throws IOException {

        User user = userservice.getUser(studentID);
        if (user instanceof Student) {
            
            Course course = findCourse(courseID);
            
            CourseEnrollment courseEnrollment = new CourseEnrollment(user.getID(), courseID, 0.0);
            
            
            course.addCourseEnrollment(courseEnrollment);
            ((Student)user).addCourseEnrollment(courseEnrollment);
            
            userservice.saveUsers();
            coursedb.write();
        } else {
            throw new IllegalArgumentException("User isn't a student, cannot enroll in course");
        }
    }

    public ArrayList<Course> returnAllCourses() {
        try {
            reload();
        } catch (IOException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Course> copies = new ArrayList<>(courses);
        return copies;

    }

    public Lesson findLessonInCourse(String lessonID, Course course) {

        Set<Lesson> lessons = course.getLessons();

        for (Lesson lesson : lessons) {
            if (lesson.getLessonID().equals(lessonID)) {
                return lesson;
            }
        }

        return null;

    }

    public void editLesson(Lesson editedLesson, String courseID, String InstructorID) throws IOException {
        Set<Lesson> lessons = null;

        Course course = findCourse(courseID);

        if (course == null) {
            throw new IllegalArgumentException("Course not found");
        }
        lessons = course.getLessons();
        for (Lesson les : lessons) {

            if (les.getLessonID().equals(editedLesson.getLessonID())) {
                lessons.remove(les);
                lessons.add(editedLesson);
                break;
            }
        }

        coursedb.write();

    }

    public void addLesson(String title, String content, String courseID, String InstructorID) throws IOException {

        String lessonID = Generator.generateLessonID();
        Lesson lesson = new Lesson(lessonID, title, content, courseID);
        Course course = findCourse(courseID);

        if (course == null) {
            throw new IllegalArgumentException("Cannot find course");
        }
        if (!course.getInstructorID().equals(InstructorID)) {
            throw new IllegalArgumentException("Cannot edit unowned course");
        }

        course.addLesson(lesson);
        coursedb.write();
    }

    public void removeLesson(String courseID, String lessonID, String InstructorID) throws IOException {
        Course course = findCourse(courseID);
        if (course == null) {
            throw new IllegalArgumentException("Couldn't find course");
        }

        if (!course.getInstructorID().equals(InstructorID)) {
            throw new IllegalArgumentException("Cannot edit unowned course");
        }

        Lesson lesson = findLessonInCourse(lessonID, course);

        if (lesson == null) {
            throw new IllegalArgumentException("Couldn't find lesson in course");
        }

        course.getLessons().remove(lesson);
        coursedb.write();
    }

    public ArrayList<Student> enrolledStudents(String courseID) {
        try {
            reload();
        } catch (IOException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        Course course = findCourse(courseID);
        Set<CourseEnrollment> enrollments = course.getCourseEnrollments();
        if(enrollments.isEmpty())
            throw new IllegalArgumentException("No enrollments found");
        
           ArrayList<Student> students = new ArrayList<>();
      
        for(CourseEnrollment enrollment : enrollments){
           students.add( (Student) userservice.getUser(enrollment.getStudentID()));
        }   
        
        return students;
        
       
       
    }

    public Set<Course> enrolledcourses(String studentID) {
        try {
            reload();
        } catch (IOException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user = userservice.getUser(studentID);

        if (user == null) {
            throw new IllegalArgumentException("Couldn't find user");
        }
        if (!(user instanceof Student)) {
            throw new IllegalArgumentException("User isn't a student");
        }

        Student s = (Student) user;
        Set<CourseEnrollment> courseEnrollments = s.getCourseEnrollments();
        Set<Course> enrolledCourses = new HashSet<>();
        for (CourseEnrollment enrollment : courseEnrollments) {
          Course c = findCourse(enrollment.getCourseID());
          if (c != null) {
            enrolledCourses.add(c);
          }
        }

        return enrolledCourses;
    }

    public ArrayList<Lesson> getLessons(String courseID) {
        try {
            reload();
        } catch (IOException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        Course course = findCourse(courseID);
        if (course == null) {
            throw new IllegalArgumentException("Course not found");
        }

        return new ArrayList<>(course.getLessons());
    }

    public Set<String> completedLessons(String studentID, String courseID) {
        try {
            reload();
        } catch (IOException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user = userservice.getUser(studentID);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (!(user instanceof Student)) {
            throw new IllegalArgumentException("User is not a student");
        }

        Student student = (Student) user;

        for (CourseEnrollment ce : student.getCourseEnrollments()) {
            if (ce.getCourseID().equals(courseID)) {
                return ce.getCompletedLessons();
            }
        }
        return new HashSet<>();
    }

    public boolean studentInCourse(String studentID, String courseID) {
        try {
            reload();
        } catch (IOException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        Student s = (Student) userservice.getUser(studentID);
        if (s.getCourseEnrollments().isEmpty()) {
            return false;
        }

        Set<CourseEnrollment> temp = s.getCourseEnrollments();
        for (CourseEnrollment c : temp) {
            if (c.getCourseID().equals(courseID)) {
                return true;
            }
        }

        return false;
    }
    
    public String getLessonContent(String lessonID) {
        try {
            reload();
        } catch (IOException ex) {
            Logger.getLogger(CourseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Course c : courses) {
        if (c.getLessons() == null) 
            continue;

        for (Lesson l : c.getLessons()) {
            if (l.getLessonID().equals(lessonID)) {
                return l.getContent();
            }
        }
    }
        return null;
    }
}
