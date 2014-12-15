/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cse.warana.utility.infoExtractors;

import CONTROLLER.Calculate;
import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.PhraseAnalyzer;
import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.GitHubExtractor;
import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.GoogleScholarExtractor;
import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.LinkedInExtractor;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;
import com.cse.warana.utility.infoHolders.Candidate;

public class  OnlineInfoExtractor {

    private String profileDocsPath = Config.profilesPath;
    private Candidate candidate;
    PhraseAnalyzer phraseAnalyzer;
    public String name = "", title = "", summary = "", pic_url = "", education = "";
//    private ArrayList<Experience> experienceList = new ArrayList<Experience>();
//    private ArrayList<Project> projectsList = new ArrayList<Project>();
//    private ArrayList<Publication> publicationList = new ArrayList<Publication>();

    public OnlineInfoExtractor(Candidate candidate){
        this.candidate = candidate;
    }


    private Calculate similarityCalculator;

    public OnlineInfoExtractor(String name) {
        this.name = name;
    }

    public OnlineInfoExtractor(String searchName, PhraseAnalyzer phraseAnalyzer) {
        this.phraseAnalyzer = phraseAnalyzer;
        LinkedInExtractor linkedIn = new LinkedInExtractor();
        GoogleScholarExtractor gscholar = new GoogleScholarExtractor();
        GitHubExtractor github = new GitHubExtractor("69e07dde89a8a0a6713f810cfd4c461f04f47e85");

        similarityCalculator = new Calculate();

        linkedIn.ExtractInformation(searchName, candidate);
        gscholar.Extract(searchName, candidate);
        github.Extract(searchName, candidate);
        if (pic_url.equalsIgnoreCase("")) {
            pic_url = "http://ryonaitis.files.wordpress.com/2012/03/images.jpg";
        }

//        Display();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//        System.out.println(toString());
//        writeFile();
//        ExtractPhrases();
    }
}
