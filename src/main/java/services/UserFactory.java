
package services;
import users.User;


public interface UserFactory {
    User createUser(String username, String email, String hashedPassword);
}
