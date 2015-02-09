package com.cse.warana.dto;

/**
 * Created by Anushka on 2015-02-10.
 */
public class CompanyTechnologyViewDTO {

    private int id;

    private int technology_id;

    private float score;

    private String technology;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTechnologyID(int technology_id) {
        this.technology_id = technology_id;
    }

    public int getTechnologyID() {
        return technology_id;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getScore() {
        return score;
    }

    public void setTechnologyName(String technology) {
        this.technology = technology;
    }

    public String getTechnologyName() {
        return technology;
    }
}
