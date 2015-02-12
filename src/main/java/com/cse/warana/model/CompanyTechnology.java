package com.cse.warana.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Anushka on 2015-02-02.
 */
@Entity
@Table(name = "company_technology")
public class CompanyTechnology implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "technology_id")
    private long technology_id;

    @Column(name = "score")
    private float score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTechnology(long technology_id) {
        this.technology_id = technology_id;
    }

    public long getTechnology() {
        return technology_id;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getScore() {
        return score;
    }
}
