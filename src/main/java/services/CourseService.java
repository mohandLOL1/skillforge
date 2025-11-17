package services;

import courses.*;
import filehandler.*;
import java.util.ArrayList;
import users.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import misc.Generator;

public class CourseService {

    private static final UserDataBase userdb = new UserDataBase("users.json");
    private static final CourseDataBase coursedb = new CourseDataBase("courses.json");
    private static ArrayList<User> users;
    private static ArrayList<Course> courses;
    private static UserService userservice;

    public CourseService() throws IOException {
        userdb.read();
        coursedb.read();
        users = userdb.returnAllRecords();
        courses = coursedb.returnAllRecords();
        userservice = new UserService();
    }

    public boolean containsCourse(String courseID) {
        for (Course course : courses) {
            if (course.getCourseID().equals(courseID)) {
                return true;
            }
        }
        return false;
    }

    public Course findCourse(String courseID) {
        for (Course course : courses) {
            if (course.getCourseID().equals(courseID)) {
                return course;
            }
        }

        return null;
    }
    
    public boolean instructorOwnsCourse(String courseID, String InstructorID){
        Course c = findCourse(courseID);
        if(c.getInstructorID().equals(InstructorID))
            return true;
        
        return false;
    }

    public void createCourse(String title, String description, String instructorID) throws IOException {

        User tempUser = userservice.getUser(instructorID);

        if (tempUser != null) {
            if (tempUser instanceof Instructor) {
                String newCourseID = Generator.generateCourseID();

                while (containsCourse(newCourseID)) {
                    newCourseID = Generator.generateCourseID();
                }

                Course newCourse = new Course(newCourseID, title, description, instructorID);
                courses.add(newCourse);

                ((Instructor) tempUser).addCreatedCourse(newCourseID);

                coursedb.write();
                userdb.write();
            } else {
                throw new IllegalArgumentException("User isn't an instructor");
            }
        } else {
            throw new IllegalArgumentException("Couldn't find instructor");
        }
    }

    public void deleteCourse(String courseID, String instructorID) throws IOException {
        
        

        Course c = findCourse(courseID);
        if(c== null){
            throw new IllegalArgumentException("Couldn't find course");
        }
        if(instructorOwnsCourse(courseID,instructorID)){
            courses.remove(c);
            coursedb.write();
        }
        else{
            throw new IllegalArgumentException("Cannot delete unowned course");
        }
              
       
        
    }

    public void editCourse(String courseID, String title, String description, String instructorID) throws IOException {
        Course oldCourse = findCourse(courseID);
        if(oldCourse == null){
            throw new IllegalArgumentException("Couldn't find course");
        }
        if(instructorOwnsCourse(courseID,instructorID)){
            oldCourse.setTitle(title);
            oldCourse.setDescription(description);
        }
        else{
            throw new IllegalArgumentException("Can only edit owned course");          
        }

        
    }

    public static void enrollStudent(String studentID, String courseID) throws IOException {

        Student student = new Student();
        Course course = new Course();
        for (User u : users) {
            if (u.getID().equals(studentID)) {
                student = (Student) u;

            }
        }
        for (Course c : courses) {
            if (c.getCourseID().equals(courseID)) {
                course = c;
            }
        }
        CourseEnrollment courseEnrollment = new CourseEnrollment(courseID, 0.0);

        student.addCourseEnrollment(courseEnrollment);
        userdb.write();
        course.addStudent(student);
        coursedb.write();

    }

    public ArrayList<Course> returnAllCourses() {

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
        
        if(course == null){
            throw new IllegalArgumentException("Cannot find course");
        }
        if(!course.getInstructorID().equals(InstructorID))
            throw new IllegalArgumentException("Cannot edit unowned course");
        
        course.addLesson(lesson);
        coursedb.write();
    }

    public void removeLesson(String courseID, String lessonID,String InstructorID) throws IOException {
        Course course = findCourse(courseID);
        if (course == null) {
            throw new IllegalArgumentException("Couldn't find course");
        }
        
        if(!course.getInstructorID().equals(InstructorID))
            throw new IllegalArgumentException("Cannot edit unowned course");
        
        Lesson lesson = findLessonInCourse(lessonID, course);

        if (lesson == null) {
            throw new IllegalArgumentException("Couldn't find lesson in course");
        }

        course.getLessons().remove(lesson);
        coursedb.write();
    }

    public Set<Student> enrolledStudents(String courseID) {

        Course course = findCourse(courseID);
        return course.getStudents();
    }

    public Set<Course> enrolledcourses(String studentID) {
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
            enrolledCourses.add(findCourse(enrollment.getCourseID()));
        }

        return enrolledCourses;
    }

    
 public ArrayList<Lesson> getLessons(String courseID) {
    Course course = findCourse(courseID);
    if (course == null) {
        throw new IllegalArgumentException("Course not found");
    }

    return new ArrayList<>(course.getLessons());
    }
    
    public Set<String> completedLessons(String studentID, String courseID) {

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
}
