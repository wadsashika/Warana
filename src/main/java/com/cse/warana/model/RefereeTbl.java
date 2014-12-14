package com.cse.warana.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Nadeeshaan on 12/14/2014.
 */

@Entity
@Table(name = "referee")
public class RefereeTbl implements Serializable{

    private static final long serialVersionUID = -6034028547853542209L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "candidate_id")
    private long candidate_id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(long candidate_id) {
        this.candidate_id = candidate_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
