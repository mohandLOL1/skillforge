


import filehandler.UserDataBase;
import users.User;
import users.Student;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        try {
            // Create database instance pointing to "users.json"
            UserDataBase db = new UserDataBase("resources/users.json");

            // Load existing users from JSON
         

            // Create a new student
            Student s = new Student("S12345", "lol", "mohandamr@gmail.com", "xd");
            Student s2 = new Student("S12333", "haha","smoha@gmail.com","xdd");

           

            // Write updated list back to JSON
            db.write();

            // Read all records and print them
            ArrayList<User> users = db.returnAllRecords();
            System.out.println("Users in database:");
            for (User u : users) {
                System.out.println("ID: " + u.getID() + ", Name: " + u.getUsername()+ ", Email: " + u.getEmail());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

