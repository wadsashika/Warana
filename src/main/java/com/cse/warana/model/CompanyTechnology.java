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
    private String technology;

    @Column(name = "score")
    private float strength;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getTechnology() {
        return technology;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public float getStrength() {
        return strength;
    }
}
