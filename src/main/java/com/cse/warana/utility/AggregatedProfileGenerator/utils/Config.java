package com.cse.warana.utility.AggregatedProfileGenerator.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Thilina on 11/16/2014.
 */
public final class Config {

    public static  String rootPath;
    static {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("src/main/resources/warana.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            rootPath = prop.getProperty("warana.resources.root");


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static final String skillsPath=            rootPath+"/Docs/SkillDocs";
    public static final String skillsOutputPath=      rootPath+"/Docs/SkillDocs_out";
    public static final String normalizedSkillsPath=  rootPath+"/Docs/Normalized/SkillDocs";
    public static final String profilesOutputPath=    rootPath+"/Docs/UserDocs_out";
    public static final String profilesPath=          rootPath+"/Docs/UserDocs";
    public static final String normalizedProfilesPath=rootPath+"/Docs/Normalized/UserDocs";
    public static final String aggregatedSkillsPath=  rootPath+"/Docs/Aggregated/SkillDocs/";
    public static final String aggregatedAllDocsPath =rootPath+"/Docs/Aggregated/AllDocs/";
    public static final String aggregatedProfilesPath=rootPath+"/Docs/Aggregated/UserDocs/";
    public static final String processedProfilesPath =rootPath+"/Docs/Processed/UserDocs/";
    public static final String processedSkillsPath   =rootPath+"/Docs/Processed/SkillDocs/";
    public static final String goldenStandardPath    =rootPath+"/Golden Standard/";
    public static final String statsOutPath          =rootPath+"/StatsOut/";
    public static final String weightMapPath         =rootPath+"/Docs/WeightMap.csv";
    public static final String abbreviationsSkillsPath     =rootPath+"/Docs/Abbreviations/SkillDocs/";
    public static final String abbreviationsProfilesPath   =rootPath+"/Docs/Abbreviations/UserDocs/";


    public static final int user_max_docs=10;

//    public static String skillsPath=            "src/com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker/Skills/SkillDocs";
//    public static String normalizedSkillsPath=  "src/com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker/Skills/Normalized/SkillDocs";
//    public static String profilesPath=          "src/com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker/Skills/UserDocs";
//    public static String normalizedProfilesPath="src/com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker/Skills/Normalized/UserDocs";
//    public static String aggregatedSkillsPath=  "src/com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker/Skills/Aggregated/SkillDocs/";
//    public static String aggregatedAllDocsPath ="src/com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker/Skills/Aggregated/AllDocs/";
//    public static String aggregatedProfilesPath="src/com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker/Skills/Aggregated/UserDocs/";
//    public static String processedProfilesPath ="src/com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker/Skills/Processed/UserDocs/";
//    public static String processedSkillsPath    ="src/com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker/Skills/Processed/SkillDocs/";


    public static final String averageCorpusTF  ="AverageCorpusTF_ALGORITHM.csv";
    public static final String c_value          ="CValue_ALGORITHM.csv";
    public static final String IBMglossEx       ="IBM_GlossEx_ALGORITHM.csv";
    public static final String RIDF             ="RIDF_ALGORITHM.csv";
    public static final String simpleTF         ="Simple_term_frequency_ALGORITHM.csv";
    public static final String termex           ="TermEx_ALGORITHM.csv";
    public static final String TFIDF            ="TfIdf_ALGORITHM.csv";
    public static final String weirdness        ="Weirdness_ALGORITHM.csv";

    public static boolean enable_averageCorpusTF  = true;
    public static boolean enable_c_value          = true;
    public static boolean enable_IBMglossEx       = true;
    public static boolean enable_RIDF             = true;
    public static boolean enable_simpleTF         = true;
    public static boolean enable_termex           = true;
    public static boolean enable_TFIDF            = true;
    public static boolean enable_weirdness        = true;


    public static final boolean enable_weights   = false;
    public static boolean enable_weights_learning= false;
    public static final boolean enable_abbreviations= true;


    public static final double  average_precision= 0.164848341;
    public static final double  learning_rate= .5;
    public static int weightingIteration;


    public static boolean enable_filter        =false;

    public static int maxEntries=50;
    public static final int statEvaluationDepth=60;
    public static boolean removeDuplications=false;


    //    jate properties
    public static final String NLP_PATH ="src/main/java/com/cse/warana/utility/AggregatedProfileGenerator/PhraseExtractor/nlp_resources";
    public static final String TEST_PATH="src/main/java/com/cse/warana/utility/AggregatedProfileGenerator/PhraseExtractor/test";
    public static int TERM_MAX_WORDS=3;
    public static final int MULTITHREAD_COUNTER_NUMBERS=5;
    public static final Boolean TERM_IGNORE_DIGITS=true;

    public static int minTextLength=100;
    public static int maxReloadTimes=3;
    public static int skillDocsLimit =10;

}
