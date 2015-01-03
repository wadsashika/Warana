package com.cse.warana.dto;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nadeeshaan on 11/19/2014.
 */
public class ViewStatDTO {

    private long id;
    private String name;
    private String email;
    private String score;
    private ArrayList<String> technologies;
    private HashMap<String,String> technologyScoreMap;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public ArrayList<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(ArrayList<String> technologies) {
        this.technologies = technologies;
    }

    public HashMap<String, String> getTechnologyScoreMap() {
        return technologyScoreMap;
    }

    public void setTechnologyScoreMap(HashMap<String, String> technologyScoreMap) {
        this.technologyScoreMap = technologyScoreMap;
    }
}
