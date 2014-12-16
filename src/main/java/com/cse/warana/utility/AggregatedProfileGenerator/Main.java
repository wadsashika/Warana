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
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main().CallName();
    }

    public void CallName() {
        Candidate candidate=new Candidate();
        CandidateProfileGeneratorService generatorService=new CandidateProfileGeneratorServiceImpl();
        CVParserService cvParserService=new CVParserServiceImpl();
        File cv=new File("D:\\Projects\\Repositories\\Final Year Project\\Warana New\\Warana\\src\\main\\resources\\Docs\\CVs\\100408J_Thilina.pdf");

        generatorService.extractCVInformation(cvParserService,cv);
        generatorService.generateCandidateProfile(candidate);
        OnlineInfoExtractor onlineInfoExtractor=new OnlineInfoExtractor(candidate);


        CallName();



    }
}
