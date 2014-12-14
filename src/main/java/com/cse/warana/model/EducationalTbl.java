package com.cse.warana.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Nadeeshaan on 12/13/2014.
 */

@Entity
@Table(name = "educational_details")
public class EducationalTbl implements Serializable{

    private static final long serialVersionUID = -169278466099479055L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "candidate_id")
    private long candidate_id;

    @Column(name = "institution_name")
    private String institution_name;

    @Column(name = "duration")
    private String duration;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "grade")
    private String grade;

    public long getId() {
        return id;
    }

    public void setCandidateId(long id) {
        this.id = id;
    }

    public long getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(long candidate_id) {
        this.candidate_id = candidate_id;
    }

    public String getInstitution_name() {
        return institution_name;
    }

    public void setInstitution_name(String institution_name) {
        this.institution_name = institution_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
