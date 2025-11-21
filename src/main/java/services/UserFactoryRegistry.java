
package services;

import java.util.HashMap;
import java.util.Map;

public class UserFactoryRegistry {

    private static final Map<String, UserFactory> factories = new HashMap<>();

    static {
        factories.put("student", new StudentFactory());
        factories.put("instructor", new InstructorFactory());
        factories.put("admin", new AdminFactory());
    }

    public static UserFactory getFactory(String role) {
        return factories.get(role.toLowerCase());
    }

    public static void registerFactory(String role, UserFactory factory) {
        factories.put(role.toLowerCase(), factory);
    }
}