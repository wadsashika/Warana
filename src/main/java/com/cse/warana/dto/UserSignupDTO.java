package com.cse.warana.dto;

import java.io.Serializable;

/**
 * Created by Sashika
 * on Dec 16 0016, 2014.
 */
public class UserSignupDTO implements Serializable {

    private static final long serialVersionUID = -8339804902020582395L;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String rePassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
