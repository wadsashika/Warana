package com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor;

import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.FileManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thilina on 11/16/2014.
 */
public class TestPhraseAnalyser {

    public static void main(String[] args) {

//        String name="Thilina Premasiri";

//        ApplyAlgorithms(3, true);
//

        AlgorithmComparator comparotor = new AlgorithmComparator();
//        comparotor.ExtractAbbreviationsBatch(Config.skillsPath,Config.abbreviationsSkillsPath);
//        Config.weightingIteration=20;
        GenerateStats(true, true, true, true, true, true, true, true);

//        for(int i=1;i<10;i++) {
////            Config.TERM_MAX_WORDS=i;
//        Config.weightingIteration=i;
////            comparotor.ExtractTermsBatch(Config.skillsPath,Config.skillsOutputPath);
//        Config.enable_weights_learning=true;
//            GenerateStats(true, false, false, false, false, false, false, false);
//            GenerateStats(false, true, false, false, false, false, false, false);
//            GenerateStats(false, false, true, false, false, false, false, false);
//            GenerateStats(false, false, false, true, false, false, false, false);
//            GenerateStats(false, false, false, false, true, false, false, false);
//            GenerateStats(false, false, false, false, false, true, false, false);
//            GenerateStats(false, false, false, false, false, false, true, false);
//            GenerateStats(false, false, false, false, false, false, false, true);

//            Config.enable_weights_learning=false;
//            GenerateStats(true,true,true,true,true,true,true,true );
//        }


//        Config.enable_weights_learning=false;
//        GenerateStats(true,true,true,true,true,true,true,true );


//        GenerateStats(false,true,true,true,true,false,false,false );
//        GenerateStats(true,true,false,true,true,false,false,false,false );
//        GenerateStats(true,true,true,true,true,true,true,true);
//        GenerateStats(true,true,true,true,true,true,true,true,false);

//        GenerateStats(true,true,true,true,true,true,false,true);


//        GenerateStats(false,true,true,true,true,true,true,true,true);
//        GenerateStats(true,false,true,true,true,true,true,true,true);
//        GenerateStats(true,true,false,true,true,true,true,true,true);
//        GenerateStats(true,true,true,false,true,true,true,true,true);
//        GenerateStats(true,true,true,true,false,true,true,true,true);
//        GenerateStats(true,true,true,true,true,false,true,true,true);
//        GenerateStats(true,true,true,true,true,true,false,true,true);
//        GenerateStats(true,true,true,true,true,true,true,false,true);
//        GenerateStats(true,true,true,true,true,true,true,true,false);


//        ApplyAlgorithms(2);
//
//        GenerateStats(true,false,false,false,false,false,false,false,false);
//        GenerateStats(false,true,false,false,false,false,false,false,false);
//        GenerateStats(false,false,true,false,false,false,false,false,false);
//        GenerateStats(false,false,false,true,false,false,false,false,false);
//        GenerateStats(false,false,false,false,true,false,false,false,false);
//        GenerateStats(false,false,false,false,false,true,false,false,false);
//        GenerateStats(false,false,false,false,false,false,true,false,false);
//        GenerateStats(false,false,false,false,false,false,false,true,false);
//        GenerateStats(false,false,false,false,false,false,false,false,true);
//
//        GenerateStats(true,true,true,true,true,true,true,true,true );
//        GenerateStats(false,true,true,true,true,true,true,true,true);
//        GenerateStats(true,false,true,true,true,true,true,true,true);
//        GenerateStats(true,true,false,true,true,true,true,true,true);
//        GenerateStats(true,true,true,false,true,true,true,true,true);
//        GenerateStats(true,true,true,true,false,true,true,true,true);
//        GenerateStats(true,true,true,true,true,false,true,true,true);
//        GenerateStats(true,true,true,true,true,true,false,true,true);
//        GenerateStats(true,true,true,true,true,true,true,false,true);
//        GenerateStats(true,true,true,true,true,true,true,true,false);


//        ApplyAlgorithms(1);
//
//        GenerateStats(true,false,false,false,false,false,false,false,false);
//        GenerateStats(false,true,false,false,false,false,false,false,false);
//        GenerateStats(false,false,true,false,false,false,false,false,false);
//        GenerateStats(false,false,false,true,false,false,false,false,false);
//        GenerateStats(false,false,false,false,true,false,false,false,false);
//        GenerateStats(false,false,false,false,false,true,false,false,false);
//        GenerateStats(false,false,false,false,false,false,true,false,false);
//        GenerateStats(false,false,false,false,false,false,false,true,false);
//        GenerateStats(false,false,false,false,false,false,false,false,true);
//
//        GenerateStats(true,true,true,true,true,true,true,true,true );
//        GenerateStats(false,true,true,true,true,true,true,true,true);
//        GenerateStats(true,false,true,true,true,true,true,true,true);
//        GenerateStats(true,true,false,true,true,true,true,true,true);
//        GenerateStats(true,true,true,false,true,true,true,true,true);
//        GenerateStats(true,true,true,true,false,true,true,true,true);
//        GenerateStats(true,true,true,true,true,false,true,true,true);
//        GenerateStats(true,true,true,true,true,true,false,true,true);
//        GenerateStats(true,true,true,true,true,true,true,false,true);
//        GenerateStats(true,true,true,true,true,true,true,true,false);


//        ApplyAlgorithms(4);
//
//        GenerateStats(true,false,false,false,false,false,false,false,false);
//        GenerateStats(false,true,false,false,false,false,false,false,false);
//        GenerateStats(false,false,true,false,false,false,false,false,false);
//        GenerateStats(false,false,false,true,false,false,false,false,false);
//        GenerateStats(false,false,false,false,true,false,false,false,false);
//        GenerateStats(false,false,false,false,false,true,false,false,false);
//        GenerateStats(false,false,false,false,false,false,true,false,false);
//        GenerateStats(false,false,false,false,false,false,false,true,false);
//        GenerateStats(false,false,false,false,false,false,false,false,true);
//
//        GenerateStats(true,true,true,true,true,true,true,true,true );
//        GenerateStats(false,true,true,true,true,true,true,true,true);
//        GenerateStats(true,false,true,true,true,true,true,true,true);
//        GenerateStats(true,true,false,true,true,true,true,true,true);
//        GenerateStats(true,true,true,false,true,true,true,true,true);
//        GenerateStats(true,true,true,true,false,true,true,true,true);
//        GenerateStats(true,true,true,true,true,false,true,true,true);
//        GenerateStats(true,true,true,true,true,true,false,true,true);
//        GenerateStats(true,true,true,true,true,true,true,false,true);
//        GenerateStats(true,true,true,true,true,true,true,true,false);

    }

    private static void ApplyAlgorithms(int maxWords, boolean enableFilter) {

        PhraseAnalyzer ph = new PhraseAnalyzer();
        Config.TERM_MAX_WORDS = maxWords;
        Config.enable_filter = enableFilter;
        File file = new File(Config.skillsPath);
        for (File f : file.listFiles()) {
            ph.RecognizeTerms(Config.skillsPath + "/" + f.getName(), Config.skillsOutputPath + "/" + f.getName());
        }
        AlgorithmComparator comparotor = new AlgorithmComparator();
        comparotor.AggregateAllSkills();

    }

    private static void GenerateStats(boolean enable_averageCorpusTF, boolean enable_ibMglossEx, boolean enable_weirdness, boolean enable_c_value, boolean enable_termex, boolean enable_tfidf, boolean enable_ridf, boolean enable_simpleTF) {

        PhraseAnalyzer ph = new PhraseAnalyzer();
        FileManager fileManager = new FileManager();

        Config.enable_averageCorpusTF = enable_averageCorpusTF;
        Config.enable_IBMglossEx = enable_ibMglossEx;
        Config.enable_weirdness = enable_weirdness;
        Config.enable_c_value = enable_c_value;
        Config.enable_termex = enable_termex;
        Config.enable_TFIDF = enable_tfidf;
        Config.enable_RIDF = enable_ridf;
        Config.enable_simpleTF = enable_simpleTF;


//        File file=new File(Config.skillsPath);
//        for (File f : file.listFiles()) {
//            ph.RecognizeTerms(Config.skillsPath+"/"+f.getName(),Config.skillsOutputPath+"/"+f.getName());
//        }

        AlgorithmComparator comparotor = new AlgorithmComparator();
//        comparotor.ExtractTermsBatch(Config.skillsPath,Config.skillsOutputPath);
//        comparotor.ExtractAbbreviationsBatch(Config.skillsPath,Config.abbreviationsSkillsPath);
        comparotor.Compare(Config.skillsOutputPath, Config.normalizedSkillsPath, Config.aggregatedSkillsPath, Config.abbreviationsSkillsPath);
//        comparotor.Compare(Config.profilesOutputPath, Config.normalizedProfilesPath, Config.aggregatedProfilesPath);
//        comparotor.Compare(Config);
//        System.out.println(Integer.MIN_VALUE);
        HashMap<String, Double> allDocs = fileManager.FileToMap(new File(Config.aggregatedAllDocsPath + "/SkillDocs.csv"));

        GetStats(Config.goldenStandardPath, Config.aggregatedSkillsPath, Config.abbreviationsSkillsPath, Config.statsOutPath, allDocs);
    }

    public static void GetStats(String goldenStandardPath, String algorithmgeneratedPath, String abbrPath, String statOutPath, HashMap<String, Double> allDocs) {
        File goldenRoot = new File(goldenStandardPath);
        HashMap<String, Double> stats = new HashMap<String, Double>();
        double precisionDifference = 0;
        FileManager fileManager = new FileManager();
        for (String fileName : goldenRoot.list()) {
            HashMap<String, Double> goldenMap = fileManager.FileToMap(new File(goldenRoot + "/" + fileName));
            HashMap<String, Double> algoMap = fileManager.FileToMap(new File(algorithmgeneratedPath + "/" + fileName));
            HashMap<String, String> abbrMap = fileManager.FileToStrStrMap(new File(algorithmgeneratedPath + "/" + fileName));
            algoMap = (HashMap<String, Double>) fileManager.SortByComparator(algoMap);
            double score = CompareMaps(goldenMap, algoMap, abbrMap, stats, allDocs) / goldenMap.size();
            precisionDifference += (score - Config.average_precision);

//            System.out.println("precisionDifference ==================== "+score/10.0);
            stats.put(fileName.split(",")[0], score);
        }
        for (Map.Entry<String, Double> entry : stats.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        precisionDifference = precisionDifference / goldenRoot.list().length;
        WriteOutput(stats, precisionDifference);

//        fileManager.WriteFile();

    }

    private static void WriteOutput(HashMap<String, Double> stats, double precisionDifference) {
        String fileName = "" + Config.TERM_MAX_WORDS;
        WeightLearner learner = new WeightLearner();
        FileManager fileManager = new FileManager();
        HashMap<String, Double> weightMap = fileManager.GetWeightMap(Config.weightMapPath);

        if (Config.enable_averageCorpusTF) {
            fileName += "_AvgCorpusTF";
            weightMap.put(Config.averageCorpusTF, learner.UpdateWeight(precisionDifference, weightMap.get(Config.averageCorpusTF)));
        }

        if (Config.enable_c_value) {
            fileName += "_Cvalue";
//            weightMap.put(Config.c_value, precisionDifference +weightMap.get(Config.c_value));
            weightMap.put(Config.c_value, learner.UpdateWeight(precisionDifference, weightMap.get(Config.c_value)));
        }

        if (Config.enable_IBMglossEx) {
            fileName += "_GlossEx";
            weightMap.put(Config.IBMglossEx, learner.UpdateWeight(precisionDifference, weightMap.get(Config.IBMglossEx)));
        }

        if (Config.enable_RIDF) {
            fileName += "_RIDF";
            weightMap.put(Config.RIDF, learner.UpdateWeight(precisionDifference, weightMap.get(Config.RIDF)));
        }

        if (Config.enable_simpleTF) {
            fileName += "_SimpleTF";
            weightMap.put(Config.simpleTF, learner.UpdateWeight(precisionDifference, weightMap.get(Config.simpleTF)));
        }

        if (Config.enable_termex) {
            fileName += "_Termex";
            weightMap.put(Config.termex, learner.UpdateWeight(precisionDifference, weightMap.get(Config.termex)));
        }

        if (Config.enable_TFIDF) {
            fileName += "_TFIDF";
            weightMap.put(Config.TFIDF, learner.UpdateWeight(precisionDifference, weightMap.get(Config.TFIDF)));
        }

        if (Config.enable_weirdness) {
            fileName += "_Weirdness";
            weightMap.put(Config.weirdness, learner.UpdateWeight(precisionDifference, weightMap.get(Config.weirdness)));
        }
        fileName += ".csv";
//        System.out.println("hereeeeeeeeeee");
        for (Map.Entry<String, Double> entry : weightMap.entrySet()) {
            System.out.println(entry.getKey() + "===================" + entry.getValue());
        }
        if (Config.enable_weights_learning)
            fileManager.WriteFile(Config.weightMapPath, weightMap);
        fileManager.WriteFile(Config.weightingIteration+"_"+fileName, stats, Config.statsOutPath);
    }

    private static double CompareMaps(HashMap<String, Double> goldenMap, HashMap<String, Double> algoMap, HashMap<String, String> abbrMap, HashMap<String, Double> stats, HashMap<String, Double> allDocs) {
        int i = 0;
        double score = 0;
        AlgorithmComparator comparotor = new AlgorithmComparator();

        if (Config.removeDuplications) {
            FileManager fileManager = new FileManager();
            algoMap = fileManager.RemoveDuplications(algoMap);
        }
        for (String goldenKey : goldenMap.keySet()) {
            i = 0;
//            System.out.println(goldenKey+"===================");
            goldenKey = goldenKey.toLowerCase().trim();
            for (String algoKey : algoMap.keySet()) {
//                System.out.println(algoKey);
//                if (i>Config.statEvaluationDepth)
//                    break;
                if (i > goldenMap.size())
                    break;


                // =============  Absolute similarity
//                if (algoKey.equals(goldenKey) || comparotor.GetAbbrSimilarity(abbrMap, algoKey, goldenKey)) {
//                    score++;
//                    break;
//                }

//                =============  Contains similarity
                if (algoKey.contains(goldenKey) || comparotor.GetAbbrSimilarity(abbrMap, algoKey, goldenKey)) {
                    score++;
                    break;
                }


//                =============  Contains similarity without abbr
//                if (algoKey.contains(goldenKey) ) {
//                    score++;
//                    break;
//                }
//
//                    score++;
//                    break;
//                }


                // =============  Contains precisionDifference similarity
//                if (algoKey.toLowerCase().contains(goldenKey.toLowerCase())){
//                    if (allDocs.get(algoKey)>50 && Config.enable_filter){
//                        continue;
//                    }
//
//                    score+=1.0*goldenKey.length()/algoKey.length();
//                    break;
//                }
//                else {
//                    if ( goldenKey.toLowerCase().contains(algoKey.toLowerCase())){
//                        if (allDocs.get(algoKey)>50 && Config.enable_filter){
//                            continue;
//                        }
//
//                        score+=1.0*algoKey.length()/goldenKey.length();
//                        break;
//                    }
//                }


                i++;
            }

        }
        return score;
    }

}
