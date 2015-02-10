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

    public String toString(){
        String str="";
        try {
            for (Education education : educationsList) {
                str += education.getDegree() + education.getInstitution() + "\n";
            }
            for (Achievement ach : achievementsList) {
                str += ach.getAchievement() + ach.getDescription();
            }
            for (Project p : projectsLists) {
                str += p.getName() + p.getDescription();
            }
            for (Referee ref : refereesList) {
                str += ref.getName() + " " + ref.getDescription() + "\n";
            }
            for (Technology tech : technologiesList) {
                str += tech.getName() + "\n";
            }
            for (Work w : worksList) {
                str += w.getCompanyName() + " " + w.getPosition();
            }
            for (Publication pub : publicationList) {
                str += pub.getName() + " " + pub.getSummary();
            }
        }catch (Exception e){
            System.out.println("Exception at candidate.toString()");
        }

        return str;
    }
}
