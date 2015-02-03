package com.cse.warana.utility.AggregatedProfileGenerator;

import com.cse.warana.controller.ProcessResumeController;
import com.cse.warana.dto.AnalyticResultsDTO;
import com.cse.warana.service.CVParserService;
import com.cse.warana.service.CandidateProfileGeneratorService;
import com.cse.warana.service.impl.AnalyzeResumeServiceImpl;
import com.cse.warana.service.impl.AnalyzedResultsServiceImpl;
import com.cse.warana.service.impl.CVParserServiceImpl;
import com.cse.warana.service.impl.CandidateProfileGeneratorServiceImpl;
import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.PhraseAnalyzer;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;
import com.cse.warana.utility.infoExtractors.OnlineInfoExtractor;
import com.cse.warana.utility.infoHolders.Candidate;
import com.cse.warana.utility.infoHolders.Profile;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {

    static public  void  main(String[] args) {
        new Main().CallName();
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
        CVParserService cvParserService=new CVParserServiceImpl(paths);
        File cv=new File("C:\\Warana\\Docs\\CVs\\Andun_S_L_Gunawardana_WSO2_SE_CV.pdf");

        generatorService.extractCVInformation(cvParserService,cv);
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
