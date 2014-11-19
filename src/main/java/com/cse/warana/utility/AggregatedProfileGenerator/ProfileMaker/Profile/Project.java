/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Profile;

/**
 *
 * @author Thilina
 */
public class Project {
    public String name="",summary="",partners="",technology="";

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

    public String getPartners() {
        return partners;
    }

    public String getTechnology() {
        return technology;
    }

    public void setPartners(String partners) {
        this.partners = partners;
    }

    public void completeInformation(Project p){
        if (name=="")
            name=p.getName();
        if (summary=="")
            summary=p.getSummary();
        if (partners=="")
            partners=p.getPartners();
        if (technology=="")
            p.getTechnology();
    }


    @Override
    public String toString() {
        return name+"\n"+summary+"\n"+partners+"\n"+technology;
    }
}
