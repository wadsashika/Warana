/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Profile;

/**
 *
 * @author Thilina
 */
public class Publication {
    public String name="",summary="",year="",authors="",link="";
    public int citations;

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public int getCitations() {
        return citations;
    }

    public void setCitations(int citations) {
        this.citations = citations;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void completeInformation(Publication p){
        if (name=="")
            name=p.getName();
        if (summary=="")
            summary=p.getSummary();
        if (year=="")
            year=p.getYear();
        if (authors=="")
            authors=p.getAuthors();
        if (link=="")
            link=p.getLink();
    }
    @Override
    public String toString() {
        return name+"\n"+summary+"\n"+"\n"+authors+"\n"+link;
    }
}
