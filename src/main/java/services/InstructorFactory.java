
package services;

import misc.Generator;
import users.Instructor;
import users.User;

public class InstructorFactory implements UserFactory {

    @Override
    public User createUser(String username, String email, String hashedPassword) {
        String id = Generator.generateInstructorID();
        return new Instructor(id, username, email, hashedPassword);
    }
}
