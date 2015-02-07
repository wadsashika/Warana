package com.cse.warana.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Nadeeshaan on 1/2/2015.
 */

@Entity
@Table(name = "candidate_technology")
public class TechnologyCandidateTbl implements Serializable {

    private static final long serialVersionUID = -7377423394328680387L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "candidate_id")
    private long candidate_id;

    @Column(name = "technology_id")
    private long technology_id;

    @Column(name = "percentage")
    private double percentage;

    public long getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(long candidate_id) {
        this.candidate_id = candidate_id;
    }

    public long getTechnology_id() {
        return technology_id;
    }

    public void setTechnology_id(long technology_id) {
        this.technology_id = technology_id;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
