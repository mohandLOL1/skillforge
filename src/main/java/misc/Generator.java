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

    public static int random_ID() {
        Random random = new Random();
        int number;
        number = random.nextInt(1000, 10001);
        return number;
    }

}
