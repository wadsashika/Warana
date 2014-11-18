package com.cse.warana.dto;

/**
 * Created by Nadeeshaan on 11/18/2014.
 */
public class AnalyticResultsDTO {

    private String email;
    private String name;
    private String score;


    public void setName(String name) {
        this.name = name;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public String getEmail() {
        return email;
    }
}
