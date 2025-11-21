/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import misc.Generator;
import users.Student;
import users.User;

public class StudentFactory implements UserFactory {

    @Override
    public User createUser(String username, String email, String hashedPassword) {
        String id = Generator.generateStudentID();
        return new Student(id, username, email, hashedPassword);
    }
}
