package com.cse.warana.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Anushka on 2015-01-02.
 */
@Entity
@Table(name = "concepts")
public class ConceptTbl implements Serializable {
    @Id
    @Column(name = "conceptId")
    private Integer conceptId;

    @Column(name = "conceptName")
    private String conceptName;

    public Integer getConceptId() {
        return conceptId;
    }
    public String getConceptName() {
        return conceptName;
    }
}
