/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cse.warana.utility.infoHolders;

import CONTROLLER.Calculate;
import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.PhraseAnalyzer;
import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.GitHubExtractor;
import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.GoogleScholarExtractor;
import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.LinkedInExtractor;
import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Profile.*;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Profile {

    private String profileDocsPath=Config.profilesPath;
    PhraseAnalyzer phraseAnalyzer;
    public String name="", title="", summary="", pic_url="",education="";
    private ArrayList<Experience> experienceList = new ArrayList<Experience>();
    private ArrayList<com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Profile.Project> projectsList = new ArrayList<com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Profile.Project>();
    private ArrayList<Publication> publicationList = new ArrayList<Publication>();



    private Calculate similarityCalculator;

    public Profile(String name){
        this.name=name;
    }

    public Profile(String searchName, PhraseAnalyzer phraseAnalyzer) {
        this.phraseAnalyzer = phraseAnalyzer;
        LinkedInExtractor linkedIn = new LinkedInExtractor();
        GoogleScholarExtractor gscholar = new GoogleScholarExtractor();
        GitHubExtractor github = new GitHubExtractor("69e07dde89a8a0a6713f810cfd4c461f04f47e85");

        similarityCalculator=new Calculate();

        linkedIn.ExtractInformation(searchName, this);
        gscholar.Extract(searchName, this);
        github.Extract(searchName, this);
        if (pic_url.equalsIgnoreCase("")) {
            pic_url="http://ryonaitis.files.wordpress.com/2012/03/images.jpg";
        }

//        Display();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//        System.out.println(toString());
        writeFile();
//        ExtractPhrases();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ArrayList getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(ArrayList experienceList) {
        this.experienceList = experienceList;
    }

    public ArrayList getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(ArrayList projectsList) {
        this.projectsList = projectsList;
    }

    public ArrayList getPublicationList() {
        return publicationList;
    }



    public void addProject(com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Profile.Project project) {

        for (com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Profile.Project p : projectsList) {
            if (isSimilar(p.name,project.name)) {
                p.completeInformation(project);
                return;
            }
        }
        projectsList.add(project);
    }

    public void addExperience(Experience experience) {

        for (Experience ex : experienceList) {
            if (isSimilar(ex.name,experience.name)) {

                return;
            }
        }
        experienceList.add(experience);
    }

    public void setPublicationList(ArrayList<Publication> list) {
        this.publicationList = list;
    }

    public void addPublication(Publication publication) {
        for (Publication pb : publicationList) {
            if (isSimilar(pb.name,publication.name)) {
                pb.completeInformation(publication);
                return;
            }
        }

        publicationList.add(publication);
    }
    private boolean isSimilar(String a,String b){

        if (similarityCalculator.calculateNNSimilarity(a, b)>0.8) {
            System.out.println("============================================================hit ");
            System.out.println(a+"   :  "+b);
            return true;
        }
        return false;
    }
    public void Display(){

        System.out.println("\n=========== Research Papers ===========\n");
        for (Publication pb : publicationList) {
            System.out.println(pb.getName());
            System.out.println("Summary :"+pb.getSummary());
            System.out.println("Authors :"+pb.getAuthors());
            System.out.println("---------------\n");
        }
        System.out.println("\n=========== Projects ===========\n");
        for (com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Profile.Project proj : projectsList) {
            System.out.println(proj.getName());
            System.out.println("Summary :" + proj.getSummary());
            System.out.println("---------------\n");
        }

    }

    @Override
    public String toString() {
        String output=name+"\n"+title+"\n"+summary+"\n"+education+"\n";
        if(experienceList!=null)
        for (Experience experience : experienceList) {
            output+=experience.toString();
        }
        if(projectsList!=null)
        for (com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Profile.Project project : projectsList) {
            output+=project.toString();
        }
        if(publicationList!=null)
        for (Publication publication : publicationList) {
            output+=publication.toString();
        }


        return output;
    }


    private void writeFile(){
        String text=toString();
        boolean b = new File(profileDocsPath+"/"+name).mkdirs();
//        b = new File(profileDocsPath+"/"+name+"/out").mkdirs();
//        if(b) {
            File file = new File(profileDocsPath + "/" + name + "/" + name + ".txt");
            // creates the file
            try {
                file.createNewFile();
                // creates a FileWriter Object
                FileWriter writer = new FileWriter(file);
                // Writes the content to the file
                writer.write(text);
                writer.flush();
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
//        }

    }
    public void ExtractPhrases(){
        phraseAnalyzer.RecognizeTerms(profileDocsPath + "/" + name, Config.profilesOutputPath + "/" + name );
    }

}
