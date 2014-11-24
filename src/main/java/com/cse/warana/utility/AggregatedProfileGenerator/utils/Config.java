package com.cse.warana.utility.AggregatedProfileGenerator.utils;

/**
 * Created by Thilina on 11/16/2014.
 */
public class Config {
    public static final String skillsPath=            "src/main/resources/Docs/SkillDocs";
    public static final String skillsOutputPath=      "src/main/resources/Docs/SkillDocs_out";
    public static final String normalizedSkillsPath=  "src/main/resources/Docs/Normalized/SkillDocs";
    public static final String profilesOutputPath=    "src/main/resources/Docs/UserDocs_out";
    public static final String profilesPath=          "src/main/resources/Docs/UserDocs";
    public static final String normalizedProfilesPath="src/main/resources/Docs/Normalized/UserDocs";
    public static final String aggregatedSkillsPath=  "src/main/resources/Docs/Aggregated/SkillDocs/";
    public static final String aggregatedAllDocsPath ="src/main/resources/Docs/Aggregated/AllDocs/";
    public static final String aggregatedProfilesPath="src/main/resources/Docs/Aggregated/UserDocs/";
    public static final String processedProfilesPath ="src/main/resources/Docs/Processed/UserDocs/";
    public static final String processedSkillsPath   ="src/main/resources/Docs/Processed/SkillDocs/";

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


    public static final String averageCorpusTF ="AverageCorpusTF_ALGORITHM.csv";
    public static final String c_value ="CValue_ALGORITHM.csv";
    public static final String IBMglossEx ="IBM_GlossEx_ALGORITHM.csv";
    public static final String RIDF ="RIDF_ALGORITHM.csv";
    public static final String simpleTF ="Simple_term_frequency_ALGORITHM.csv";
    public static final String termex ="TermEx_ALGORITHM.csv";
    public static final String TFIDF ="TfIdf_ALGORITHM.csv";
    public static final String weirdness ="Weirdness_ALGORITHM.csv";

    public static int maxEntries=50;

//    jate properties
    public static final String NLP_PATH="src/main/java/com/cse/warana/utility/AggregatedProfileGenerator/PhraseExtractor/nlp_resources";
    public static final String TEST_PATH="src/main/java/com/cse/warana/utility/AggregatedProfileGenerator/PhraseExtractor/test";
    public static final int TERM_MAX_WORDS=5;
    public static final int MULTITHREAD_COUNTER_NUMBERS=5;
    public static final Boolean TERM_IGNORE_DIGITS=true;

    public static int minTextLength=100;
    public static int maxReloadTimes=3;
    public static int skillDosLimit=3;
}
