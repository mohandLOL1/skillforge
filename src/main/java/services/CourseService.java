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
    private static  ArrayList<User> users;
    private static  ArrayList<Course> courses;
    private static UserService userservice;
    public CourseService()throws IOException{
       userdb.read();
       coursedb.read();
       users = userdb.returnAllRecords();
       courses = coursedb.returnAllRecords();
    }
  
    public static boolean containsCourse(String courseID){
        for(Course course : courses){
           if(course.getID().equals(courseID)){
               return true;
           }
       }
       return false;
    }
    public static Course findCourse(String courseID){
        for(Course course : courses){
            if(course.getID().equals(courseID)){
               return course;
           }
        }
        
        return null;
    }

    public static void createCourse(String title, String description, String instructorID) throws IOException{
        
     User tempUser = userservice.getUser(instructorID);
     
     if(tempUser != null){
         if(tempUser instanceof Instructor){
             String newCourseID = Generator.generateCourseID();
             
             while(CourseService.containsCourse(newCourseID)){
                 newCourseID = Generator.generateCourseID();
             }
             
             Course newCourse = new Course(newCourseID,title,description,instructorID);
             courses.add(newCourse);
             
             ((Instructor) tempUser).addCreatedCourse(newCourseID);
             
             coursedb.write();
             userdb.write();
         }
         else{
             throw new IllegalArgumentException("User isn't an instructor");
         }
     }
     else{
         throw new IllegalArgumentException("Couldn't find instructor");
     }
    }

    public static void editCourse(Course course) throws IOException {

        for (Course c : courses) {
            if (c.getID().equals(course.getID())) {
                courses.remove(c);
                courses.add(course);
                coursedb.write();
                return;
            }

        }

        throw new IllegalArgumentException("Couldn't find course with that ID");
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
            if (c.getID().equals(courseID)) {
                course = c;
            }
        }
        CourseEnrollment courseenrollment = new CourseEnrollment(courseID, 0.0);

        student.addCourseEnrollment(courseenrollment);
        userdb.write();
        course.addStudent(student);
        coursedb.write();

    }

    public ArrayList<Course> returnAllCourses() {

        ArrayList<Course> copies = new ArrayList<>(courses);
        return copies;

    }

    public void editLesson(Lesson lesson) throws IOException {

        Set<Lesson> lessons;

        for (Course c : courses) {
            lessons = c.getLessons();
            for (Lesson s : lessons) {
                if (s.getLessonID().equals(lesson.getLessonID())) {
                    c.removeLesson(s);
                    c.addLesson(lesson);
                    coursedb.write();

                    return;
                }
            }
        }
        throw new IllegalArgumentException("Couldn't find lesson with that ID");
    }

    public void addLesson(Lesson lesson) throws IOException {

        for (Course c : courses) {
            if (c.getID().equals(lesson.getcourseID())) {
                c.addLesson(lesson);
                coursedb.write();
                return;
            }
        }
        throw new IllegalArgumentException("Couldn't find course with that ID");

    }

    public void removeLesson(Lesson lesson) throws IOException {
        for (Course c : courses) {
            if (c.getID().equals(lesson.getcourseID())) {
                c.removeLesson(lesson);
                coursedb.write();
                return;
            }
        }
        throw new IllegalArgumentException("Couldn't find course with that ID");

    }

    public Set<Student> enrolledStudents(Course course) {

        return course.getStudents();
    }

    public Set<Course> enrolledcourses(String studentID) {

        Student student = new Student();
        for (User u : users) {
            if (u.getID().equals(studentID)) {
                student = (Student) u;

            }
        }
        Set<CourseEnrollment> returnedEnrolls = student.getCourseEnrollments();
        ArrayList<String> coursesIDs = new ArrayList<>();
        for (CourseEnrollment ce : returnedEnrolls) {
            coursesIDs.add(ce.getCourseID());
        }

        Set<Course> enrolledcourses = new HashSet<>();
        for (String str : coursesIDs) {
            for (Course course : courses) {

                if (course.getID().equals(str)) {
                    enrolledcourses.add(course);
                }
            }
        }
        return enrolledcourses;
    }

}
