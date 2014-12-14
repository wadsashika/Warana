package com.cse.warana.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Nadeeshaan on 12/14/2014.
 */

@Entity
@Table(name = "work_experience")
public class WorkExperienceTbl implements Serializable{

    private static final long serialVersionUID = 493018561252518437L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "candidate_id")
    private long candidate_id;

    @Column(name = "company_name")
    private String company_name;

    @Column(name = "post")
    private String post;

    @Column(name = "duration_from")
    private String duration_from;

    @Column(name = "duration_to")
    private String duration_to;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDuration_from() {
        return duration_from;
    }

    public void setDuration_from(String duration_from) {
        this.duration_from = duration_from;
    }

    public String getDuration_to() {
        return duration_to;
    }

    public void setDuration_to(String duration_to) {
        this.duration_to = duration_to;
    }
}
