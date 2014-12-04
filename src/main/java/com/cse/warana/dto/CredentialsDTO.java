package com.cse.warana.dto;

import java.io.Serializable;

/**
 * Created by Sashika
 * on Dec 04 0004, 2014.
 */
public class CredentialsDTO implements Serializable {

    private static final long serialVersionUID = -5835461954132704076L;

    private long id;
    private String firstName;
    private String lastName;
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
}
