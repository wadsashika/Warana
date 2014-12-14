package com.cse.warana.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Nadeeshaan on 12/14/2014.
 */

@Entity
@Table(name = "project_details")
public class ProjectDetailsTbl implements Serializable{

    private static final long serialVersionUID = -3541356137598515076L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "candidate_id")
    private long candidate_id;

    @Column(name = "proj_name")
    private String proj_name;

    @Column(name = "description")
    private String description;

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

    public String getProj_name() {
        return proj_name;
    }

    public void setProj_name(String proj_name) {
        this.proj_name = proj_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
