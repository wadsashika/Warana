package com.cse.warana.utility.AggregatedProfileGenerator;

import com.cse.warana.service.CVParserService;
import com.cse.warana.service.CandidateProfileGeneratorService;
import com.cse.warana.service.impl.CVParserServiceImpl;
import com.cse.warana.service.impl.CandidateProfileGeneratorServiceImpl;
import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.PhraseAnalyzer;
import com.cse.warana.utility.infoExtractors.OnlineInfoExtractor;
import com.cse.warana.utility.infoHolders.Candidate;
import com.cse.warana.utility.infoHolders.Profile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static public  void  main(String[] args) {
        new Main().CallName();
    }

    public void CallName() {
        /**
         * TODO implement methods to confirm whether online profiles exactly represent the desired candidate
         */

        HashMap<String,Integer> map=new HashMap<String,Integer>();

        map.put("one",1);
        map.put("two",2);
        map.put("three",3);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            entry.setValue(5);
        }
        System.out.println(map.get("a"));

        Candidate candidate=new Candidate();
        CandidateProfileGeneratorService generatorService=new CandidateProfileGeneratorServiceImpl();
//        CVParserService cvParserService=new CVParserServiceImpl();
        File cv=new File("src\\main\\resources\\Docs\\CVs\\100408J_Thilina.pdf");

//        generatorService.extractCVInformation(cvParserService,cv);
        generatorService.generateCandidateProfile(candidate);
        OnlineInfoExtractor onlineInfoExtractor=new OnlineInfoExtractor(candidate);
        CallName();
    }
}
