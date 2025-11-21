/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package users;

import com.fasterxml.jackson.annotation.JsonProperty;
import misc.Validations;


public class Admin extends User {

    public String type = "admin";

    public Admin(String userID, String username, String email, String passwordHash) {
        super(username, email, passwordHash);
        setUserID(userID);

    }

    @JsonProperty("id")
    @Override
    public void setUserID(String userID) {
        if (Validations.validateAdminID(userID)) {
            this.userID = userID;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @JsonProperty("id")
    public String getID() {
        return userID;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("type")
    public String getType() {
        return "admin";
    }
}
