package com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor;

import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.PhraseAnalyzer;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;

import java.io.File;

/**
 * Created by Thilina on 11/16/2014.
 */
public class TestPhraseAnalyser {

    public  static void main(String[] args){

//        String name="Thilina Premasiri";
        String name="Nisansa Dilushan de Silva";
        PhraseAnalyzer ph=new PhraseAnalyzer();
//        phraseAnalyzer.RecognizeTerms(profileDocsPath + "/" + name, profileDocsPath + "/" + name + "_out");
        File file=new File(Config.skillsPath);
        for (File f : file.listFiles()) {
            ph.RecognizeTerms(Config.skillsPath+"/"+f.getName(),Config.skillsOutputPath+"/"+f.getName());
        }

    }
}
