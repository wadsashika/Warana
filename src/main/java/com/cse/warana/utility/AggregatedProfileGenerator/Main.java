package com.cse.warana.utility.AggregatedProfileGenerator;

import com.cse.warana.controller.ProcessResumeController;
import com.cse.warana.service.CandidateProfileGeneratorService;
import com.cse.warana.service.impl.*;
import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.AlgorithmComparator;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;
import com.cse.warana.utility.infoExtractors.OnlineInfoExtractor;
import com.cse.warana.utility.infoHolders.Candidate;

import java.io.File;
import java.util.HashMap;


public class Main {

    static public  void  main(String[] args) {
        new Main().companyDocTest();
//        Config.initialize("C:/Warana");

//        AlgorithmComparotor comparotor=new AlgorithmComparotor();
//        comparotor.AggregateAllSkills();
//        SkillAnalyzer skillAnalyzer =new SkillAnalyzer();
//        skillAnalyzer.SortSkills(120);

    }

    public void companyDocTest(){
//        docParserService.readCompanyDoc();
        AlgorithmComparator comparotor=new AlgorithmComparator();
        Config.initialize("C:/Warana");
        comparotor.ExtractTermsBatch(Config.companyDocs,Config.companyDocsOut);
    }


    private String uploadsPath;
    public void CallName() {

        String root="C:/Warana";
        String classifirePath="\\\\classifiers\\\\english.muc.7class.distsim.crf.ser.gz";

        HashMap<String,String> paths = new HashMap<>();
        System.out.println(root);
        paths.put("root", root);
        paths.put("classifirePath",classifirePath);
        paths.put("listPath","\\gazeteerLists");

        Candidate candidate=new Candidate();
        CandidateProfileGeneratorService generatorService=new CandidateProfileGeneratorServiceImpl();
        //CVParserService cvParserService=new CVParserServiceImpl(paths);
        File cv=new File("C:\\Warana\\Docs\\CVs\\andun.pdf");

        //generatorService.extractCVInformation(cvParserService,cv);
        generatorService.generateCandidateProfile(candidate);
        Config.initialize("C:\\Warana");
        candidate.getProfile().setId(107);
        OnlineInfoExtractor onlineInfoExtractor=new OnlineInfoExtractor(candidate,"C:\\Warana");

//        CallName();
    }
    void Test(){
        ProcessResumeController resumeController=new ProcessResumeController();
        File file=new File("C:\\Warana\\Docs\\CVs");
        resumeController.processSelectedResumes(file.list());
    }
}
