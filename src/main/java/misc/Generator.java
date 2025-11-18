/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misc;

import java.util.Random;


public class Generator {

    public static String generateStudentID(){ //generates an ID in the form of S12345
        Random rand = new Random();
        int ID = 10000 + rand.nextInt(10001);
        return "S" + Integer.toString(ID);
    }
    
    public static String generateInstructorID(){ //generates an ID in the form of I12345
        Random rand = new Random();
        int ID = 10000 + rand.nextInt(10001);
        return "I" + Integer.toString(ID);
    }
    
    public static String AdminID(){ //generates an ID in the form of A12345
        Random rand = new Random();
        int ID = 10000 + rand.nextInt(10001);
        return "A" + Integer.toString(ID);
    }
    
    public static String generateCourseID(){ //generates an ID in the form of C12345
        Random rand = new Random();
        int ID = 10000 + rand.nextInt(10001);
        return "C" + Integer.toString(ID);
    }
    
    public static String generateLessonID(){
        Random rand = new Random();
        int ID = 10000 + rand.nextInt(10001);
        return "L" + Integer.toString(ID);
    }
    
}
