package com.cse.warana.service.impl;

import com.cse.warana.service.CVParserService;
import com.cse.warana.service.CandidateProfileGeneratorService;
import com.cse.warana.utility.infoHolders.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nadeeshaan on 11/23/2014.
 */
public class CandidateProfileGeneratorServiceImpl implements CandidateProfileGeneratorService {

    private static Candidate candidate;
    private static Profile profile;
    private static ArrayList<Education> educationList;
    private static ArrayList<Achievement> achievementsList;
    private static ArrayList<Project> projectsList;
    private static ArrayList<Referee> refereesList;
    private static ArrayList<Technology> technologiesList;
    private static ArrayList<Work> worksList;
    private static HashMap<String,Object> infoCategoryTypes;

    /**
     * Setup the constructor
     */
    public CandidateProfileGeneratorServiceImpl(){
        infoCategoryTypes = new HashMap<>();

        candidate = new Candidate();

        profile = new Profile();
        infoCategoryTypes.put("PROFILE",profile);

        educationList = new ArrayList<>();
        infoCategoryTypes.put("EDUCATION_LIST",educationList);

        achievementsList = new ArrayList<>();
        infoCategoryTypes.put("ACHIEVEMENTS_LIST",achievementsList);

        projectsList = new ArrayList<>();
        infoCategoryTypes.put("PROJECTS_LIST",projectsList);

        refereesList = new ArrayList<>();
        infoCategoryTypes.put("REFEREE_LIST",refereesList);

        technologiesList = new ArrayList<>();
        infoCategoryTypes.put("TECHNOLOGIES_LIST",technologiesList);

        worksList = new ArrayList<>();
        infoCategoryTypes.put("WORK_LIST",worksList);
    }

    @Override
    public void extractCVInformation(CVParserServiceImpl cvParser) {
        cvParser.initializeHeadingTokens();
        cvParser.identifyHeadings();
        cvParser.readPdfDocument(new File("F:\\Accademic\\Semister 7\\Final_Year_Project\\CareersDay2013_CVs\\CareersDay2013_CVs\\pdfs\\090067P_CHATHURANGA R.G.pdf"));
        cvParser.identifyHeadings();
        cvParser.parseLines(infoCategoryTypes);
    }

    @Override
    public void extractOnlineProfileInformation() {

    }

    @Override
    public void generateCandidateProfile() {
        candidate.setProfile(profile);
        candidate.setAchievementsList(achievementsList);
        candidate.setEducationsList(educationList);
        candidate.setProjectsLists(projectsList);
        candidate.setRefereesList(refereesList);
        candidate.setTechnologiesList(technologiesList);
        candidate.setWorksList(worksList);
    }

    public static void main(String[] args){
        CandidateProfileGeneratorServiceImpl test = new CandidateProfileGeneratorServiceImpl();
        test.extractCVInformation(new CVParserServiceImpl());
        test.generateCandidateProfile();
    }


}
