package users;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import misc.Validations;



@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Student.class, name = "student"),
        @JsonSubTypes.Type(value = Instructor.class, name = "instructor"),
        @JsonSubTypes.Type(value = Admin.class, name = "admin")
})

public abstract class User {

    protected String userID;
    protected String username;
    protected String email;
    protected String passwordHash;

    public User() {
    }

    public User(String username, String email, String passwordHash) {
        setUsername(username);
        setEmail(email);
        setPasswordHash(passwordHash);
    }
    
    public abstract String getID();

    public abstract void setUserID(String userID);
    
    public abstract String getType();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (Validations.validateUsername(username) == true) {
            this.username = username;
        } else {
            throw new IllegalArgumentException("Invalid username");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        if (Validations.validateEmail(email) == true) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {

        this.passwordHash = passwordHash;

    }

  
}
