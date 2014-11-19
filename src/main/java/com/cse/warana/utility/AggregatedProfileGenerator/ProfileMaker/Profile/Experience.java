/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Profile;

/**
 *
 * @author Thilina
 */
public class Experience {
    public String name="",summary="";

    public Experience(String name, String summary) {
        this.name = name;
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return name+"\n"+summary;
    }
}
