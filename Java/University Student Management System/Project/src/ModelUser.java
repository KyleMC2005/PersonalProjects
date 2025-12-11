package src;

import java.util.Arrays;

public class ModelUser {
    private String id;
    private char[] password;
    private String firstName;
    private String lastName;
    private String email;
    private Long dob;
    private String gender;
    private String qual;

    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

//    // Method to check login credentials
//    // Note: Connection with database not implemented yet so this doesn't work properly
//    public boolean login(String id, char[] password) {
//
//        setId(id); // Do this for now so that log in error message actually displays
//
//        if (this.id.equals(id) && Arrays.equals(this.password, password)) {     // Check if id and password match current instance variables
//            return true;    // Successful login
//        }
//        return false; // Failed login
//    }
//
//    // when database link added need to look at start of id to identify what type of user is logging in


}
