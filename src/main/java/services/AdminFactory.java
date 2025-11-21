
package services;

import misc.Generator;
import users.Admin;
import users.User;

public class AdminFactory implements UserFactory {

    @Override
    public User createUser(String username, String email, String hashedPassword) {
        String id = Generator.generateAdminID();
        return new Admin(id, username, email, hashedPassword);
    }
}