package com.cse.warana.service.impl;

import com.cse.warana.dao.GetTechnologyIdDao;
import com.cse.warana.service.CVParserService;
import com.cse.warana.service.CandidateProfileGeneratorService;
import com.cse.warana.service.StoreTechnologyService;
import com.cse.warana.utility.infoExtractors.OnlineInfoExtractor;
import com.cse.warana.utility.infoHolders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nadeeshaan on 11/23/2014.
 */

@Service("candidateProfileGeneratorService")
public class CandidateProfileGeneratorServiceImpl implements CandidateProfileGeneratorService {

    //    private static Candidate candidate;
    private static Profile profile;
    private static ArrayList<Education> educationList;
    private static ArrayList<Achievement> achievementsList;
    private static ArrayList<Project> projectsList;
    private static ArrayList<Referee> refereesList;
    private static ArrayList<Technology> technologiesList;
    private static ArrayList<Publication> publicationList;
    private static ArrayList<Work> worksList;
    private static HashMap<String, Double> skillScoreList;
    private static HashMap<String, Object> infoCategoryTypes;

    @Autowired
    @Qualifier("getTechnologyIdDao")
    private GetTechnologyIdDao technologyIdDao;

    @Autowired
    @Qualifier("storeTechnologyService")
    private StoreTechnologyService technologyService;

    /**
     * Setup the constructor
     */
    public CandidateProfileGeneratorServiceImpl() {
        infoCategoryTypes = new HashMap<>();

//        candidate = new Candidate();

        profile = new Profile();
        infoCategoryTypes.put("PROFILE", profile);

        educationList = new ArrayList<>();
        infoCategoryTypes.put("EDUCATION_LIST", educationList);

        achievementsList = new ArrayList<>();
        infoCategoryTypes.put("ACHIEVEMENTS_LIST", achievementsList);

        projectsList = new ArrayList<>();
        infoCategoryTypes.put("PROJECTS_LIST", projectsList);

        refereesList = new ArrayList<>();
        infoCategoryTypes.put("REFEREE_LIST", refereesList);

        technologiesList = new ArrayList<>();
        infoCategoryTypes.put("TECHNOLOGIES_LIST", technologiesList);

        publicationList = new ArrayList<>();
        infoCategoryTypes.put("PUBLICATIONS_LIST", publicationList);

        worksList = new ArrayList<>();
        infoCategoryTypes.put("WORK_LIST", worksList);


        skillScoreList = new HashMap<String, Double>();
        infoCategoryTypes.put("SKILLSCORE_LIST", skillScoreList);


    }

    @Override
    public void extractCVInformation(CVParserService cvParser, File resumeFile) {
        cvParser.initializeHeadingTokens();
//        cvParser.identifyHeadings();
        cvParser.readPdfDocument(resumeFile);
        cvParser.identifyHeadings();
        cvParser.parseLines(infoCategoryTypes);
    }


    @Override
    public Candidate generateCandidateProfile(Candidate candidate) {
        candidate.setProfile(profile);
        candidate.setAchievementsList(achievementsList);
        candidate.setEducationsList(educationList);
        candidate.setProjectsLists(projectsList);
        candidate.setRefereesList(refereesList);
        candidate.setTechnologiesList(technologiesList);
        candidate.setPublicationList(publicationList);
        candidate.setWorksList(worksList);
        return candidate;
    }


    @Override
    public void extractOnlineProfileInformation(Candidate candidate, String rootPath) {                  // Candidate should be initalized with CV info before calling this method
        OnlineInfoExtractor onlineInfoExtractor = new OnlineInfoExtractor(candidate, rootPath);

        Map<String, Long> technologyIdMap = technologyIdDao.getTechnologyIdMap(candidate.getTechnologiesList());

       //TODO logic to remove redundant technologies
        ArrayList<Technology> techList = candidate.getTechnologiesList();
        for (String key : technologyIdMap.keySet()) {
            for (int i = 0; i < techList.size(); i++) {
                if (key.equals(techList.get(i).getName())){
                    techList.remove(i);
                    break;
                }
            }
        }

        for (Technology technology : techList) {
            System.out.println(technology.getName()+"****************************************");
        }


        technologyService.storeTechnologies(candidate.getTechnologiesList());
    }

//    public static void main(String[] args){
//        CandidateProfileGeneratorServiceImpl test = new CandidateProfileGeneratorServiceImpl();
//        test.extractCVInformation(new CVParserServiceImpl());
//        test.generateCandidateProfile();
//    }


}
