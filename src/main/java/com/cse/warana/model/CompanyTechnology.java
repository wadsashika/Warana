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

    @Column(name = "technology")
    private String technology;

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
}
