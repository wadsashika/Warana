package com.cse.warana.utility.infoHolders;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nadeeshaan on 11/12/2014.
 */
public class Candidate {
    Profile profile;
    ArrayList<Education> educationsList;
    ArrayList<Achievement> achievementsList;
    ArrayList<Project> projectsLists;
    ArrayList<Referee> refereesList;
    ArrayList<Technology> technologiesList;
    ArrayList<Work> worksList;
    ArrayList<Publication> publicationList;
    HashMap<String,Double> skillScoreList;

    public ArrayList<Publication> getPublicationList() {
        return publicationList;
    }

    public void setPublicationList(ArrayList<Publication> publicationList) {
        this.publicationList = publicationList;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public ArrayList<Education> getEducationsList() {
        return educationsList;
    }

    public void setEducationsList(ArrayList<Education> educationsList) {
        this.educationsList = educationsList;
    }

    public ArrayList<Achievement> getAchievementsList() {
        return achievementsList;
    }

    public void setAchievementsList(ArrayList<Achievement> achievementsList) {
        this.achievementsList = achievementsList;
    }

    public ArrayList<Project> getProjectsLists() {
        return projectsLists;
    }

    public void setProjectsLists(ArrayList<Project> projectsLists) {
        this.projectsLists = projectsLists;
    }

    public ArrayList<Referee> getRefereesList() {
        return refereesList;
    }

    public void setRefereesList(ArrayList<Referee> refereesList) {
        this.refereesList = refereesList;
    }

    public ArrayList<Technology> getTechnologiesList() {
        return technologiesList;
    }

    public void setTechnologiesList(ArrayList<Technology> technologiesList) {
        this.technologiesList = technologiesList;
    }

    public ArrayList<Work> getWorksList() {
        return worksList;
    }

    public void setWorksList(ArrayList<Work> worksList) {
        this.worksList = worksList;
    }
}
