
package services;

import certification.CertificateRecord;
import courses.Course;
import courses.CourseEnrollment;
import filehandler.UserDataBase;
import java.io.IOException;
import java.util.ArrayList;
import misc.Generator;
import misc.SHA256;
import users.*;


public class UserService {
    private static final UserDataBase userdb = new UserDataBase("users.json");
    private static ArrayList<User> users;
    
    public UserService() throws IOException{
        userdb.read();
        users = userdb.returnAllRecords();
    }
    
    public User validateLogin(String username, String password,String Type){
        String hashedPassword = SHA256.hash(password);
        for(User user : users){
            if(user.getUsername().equals(username) && user.getPasswordHash().equals(hashedPassword)&& user.getType().equalsIgnoreCase(Type)){
                return user;
            }
        }
        return null;
    }
    
    public boolean containsUser(String userID){
       for(User user : users){
           if(user.getID().equals(userID)){
               return true;
           }
       }
       return false;
    }
    
    public User getUser(String userID){
        for(User user : users){
            if(user.getID().equals(userID))
                return user;
        }
        
        throw new IllegalArgumentException("Couldn't get user");
    }
    
    public void registerUser(String role, String username, String email, String password) throws IOException{ //only two users
        
        String ID;
        String hashedPassword = SHA256.hash(password); 
  
        if(role.equalsIgnoreCase("student")){
            
            ID = Generator.generateStudentID();
            while(containsUser(ID)){
                ID = Generator.generateStudentID();
            }
            
        }
        else {
            ID = Generator.generateInstructorID();
            while(containsUser(ID))
                ID = Generator.generateInstructorID();
        }
        if(role.equalsIgnoreCase("student")){
        users.add(new Student(ID,username,email,hashedPassword));
        }
        else{
        users.add(new Instructor(ID,username,email,hashedPassword)); 
        }
        userdb.write();
    }
    
    public boolean usernameExists(String username){
    for (User u : users) {
        if (u.getUsername().equalsIgnoreCase(username)) {
            return true;
        }
    }
    return false;
   }
    
    public void deleteUser(String userID) throws IOException{
       for(User user : users){
           if(user.getID().equals(userID)){
               users.remove(user);
               userdb.write();
           }
       }
       
       throw new IllegalArgumentException("Couldn't find user with that ID");
    } 
    
    public ArrayList<User> returnAllUsers(){
        ArrayList<User> copies = new ArrayList<>(users);
        return copies;
    }
    
    public void saveUsers() throws IOException{
        this.userdb.write();
    }
    
    
    public CertificateRecord generateCertificate(String studentID, String courseID) throws IOException {
    
    userdb.read();
    users = userdb.returnAllRecords();

    User user = getUser(studentID);
    if (!(user instanceof Student student)) {
        throw new IllegalArgumentException("User is not a student");
    }

    CourseService courseService = new CourseService();
    Course course = courseService.findCourse(courseID);

    if (course == null) {
        throw new IllegalArgumentException("Course not found");
    }

    CourseEnrollment enrollment = null;
    for (CourseEnrollment ce : student.getCourseEnrollments()) {
        if (ce.getCourseID().equals(courseID)) {
            enrollment = ce;
            break;
        }
    }

    if (enrollment == null) {
        throw new IllegalArgumentException("Student is not enrolled in this course");
    }

    int Lessons = course.getLessons().size();
    int completedLessons = enrollment.getCompletedLessons().size();

    if (Lessons == 0) {
        throw new IllegalArgumentException("Course has no lessons, certificate cannot be issued");
    }

    if (completedLessons < Lessons) {
        throw new IllegalArgumentException("Student has not completed all lessons");
    }

    String certID = Generator.generateCertificateID();
    String issueDate = java.time.LocalDate.now().toString();

    CertificateRecord record =new CertificateRecord(certID, studentID,courseID, issueDate);

    student.addCertificate(record);

    userdb.write();

    return record;
    
   }
    
    public void addCertificateToStudent(String certID, String studentID, String courseID, String issueDate) throws IOException {

    userdb.read();
    ArrayList<User> users = userdb.returnAllRecords();  

    for (User user : users) {

        if (user.getID().equals(studentID) && user instanceof Student student) {

            for (CertificateRecord rec : student.getCertificates()) {
                if (rec.getCourseID().equals(courseID)) {
                    throw new IllegalArgumentException("Certificate already exists for course" );
                }
            }

            CertificateRecord record =new CertificateRecord(certID, studentID, courseID, issueDate);

            student.addCertificate(record);
            userdb.write(); 
            return;
        }
    }

    throw new IllegalArgumentException("Student not found");
}

    
    public ArrayList<CertificateRecord> getCertificatesByStudentID(String studentID) throws IOException {

    for (User user : users) {

        if (user.getID().equals(studentID) && user instanceof Student student) {

            return new ArrayList<>(student.getCertificates());
        }
    }
    
    return new ArrayList<>();
    }
    
     
}
