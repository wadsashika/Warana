package com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Skills;

import java.util.ArrayList;

/**
 * Created by Thilina on 11/1/2014.
 */
public class Skill {
    private String name="";
    private ArrayList<String> relatedTerms=new ArrayList<String>();

    public Skill(String name, ArrayList<String> relatedTerms) {
        this.name = name;
        this.relatedTerms = relatedTerms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getRelatedTerms() {
        return relatedTerms;
    }

    public void setRelatedTerms(ArrayList<String> relatedTerms) {
        this.relatedTerms = relatedTerms;
    }
}
