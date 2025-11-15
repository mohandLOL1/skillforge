package services;

import courses.*;
import filehandler.*;
import java.util.ArrayList;
import users.*;
import java.io.IOException;

public class CourseService {

    private static UserDataBase userdatabase = new UserDataBase("users.json");
    private static CourseDataBase coursedatabase = new CourseDataBase("courses.json");
    private static final ArrayList<User> users = userdatabase.returnAllRecords();
    private static final ArrayList<Course> courses = coursedatabase.returnAllRecords();

    public static void createCourse(String courseId, String title, String description, String intructorId) throws IOException {

        Course course = new Course();
        course.setCourseID(courseId);
        course.setDescription(description);
        course.setInstructorID(intructorId);
        course.setTitle(title);
        for (User user : users) {
            if (user.getID().equals(intructorId) && user instanceof Instructor) {
                Instructor instrctor = (Instructor) user;
                instrctor.addCreatedCourse(courseId);
            }
        }
        userdatabase.write();

        courses.add(course);
        coursedatabase.write();
    }

    public static void editCourse(Course course) throws IOException {

        for (Course c : courses) {
            if (c.getCourseID().equals(course.getCourseID())) {
                courses.remove(c);
                courses.add(course);
            }

        }
        coursedatabase.write();

    }

    public static void Enrollement(String studentID, String courseID) throws IOException {

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
        CourseEnrollment courseenrollment = new CourseEnrollment(courseID, 0.0);

        student.addCourseEnrollment(courseenrollment);
        userdatabase.write();
        course.addStudent(student);
        coursedatabase.write();

    }

    public ArrayList<Course> returnAllCourses() {

        ArrayList<Course> copies = new ArrayList<>(courses);
        return copies;

    }

}
