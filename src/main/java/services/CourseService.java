package services;

import courses.*;
import filehandler.*;
import java.util.ArrayList;
import users.*;
import java.io.IOException;
public class CourseService {

    private  static UserDataBase userdatabase=new UserDataBase("users.json");
    private static CourseDataBase coursedatabase=new CourseDataBase("courses.json");


    public static void createCourse (String courseId, String title, String description, String intructorId)throws IOException {
        ArrayList<User> users = userdatabase.returnAllRecords();
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
       ArrayList<Course> courses=coursedatabase.returnAllRecords();
       courses.add(course);
       coursedatabase.write();
    }
    
    
    public static void editCourse(Course course) throws IOException{
      ArrayList<Course> courses=coursedatabase.returnAllRecords();
      for(Course c:courses){
        if(c.getCourseID().equals(course.getCourseID())){
            courses.remove(c);
            courses.add(course);
        }
          
      }
        coursedatabase.write();
        
    }

}
