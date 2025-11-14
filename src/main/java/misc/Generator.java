/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misc;

import java.util.Random;

/**
 *
 * @author amr
 */
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
    
}
