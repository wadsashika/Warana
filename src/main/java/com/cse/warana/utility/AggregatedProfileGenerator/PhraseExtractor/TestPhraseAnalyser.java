package com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor;

import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.PhraseAnalyzer;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.FileManager;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thilina on 11/16/2014.
 */
public class TestPhraseAnalyser {

    public  static void main(String[] args){

//        String name="Thilina Premasiri";

//        ApplyAlgorithms(3, true);
        Config.statEvaluationDepth=10;
        GenerateStats(true,false,false,false,false,false,false,false);
        GenerateStats(false,true,false,false,false,false,false,false);
        GenerateStats(false,false,true,false,false,false,false,false);
        GenerateStats(false,false,false,true,false,false,false,false);
        GenerateStats(false,false,false,false,true,false,false,false);
        GenerateStats(false,false,false,false,false,true,false,false);
        GenerateStats(false,false,false,false,false,false,true,false);
        GenerateStats(false,false,false,false,false,false,false,true);
//        GenerateStats(false,false,false,false,false,false,false,false);

//        GenerateStats(false,true,true,true,true,false,false,false,false );
//        GenerateStats(true,true,false,true,true,false,false,false,false );
        GenerateStats(true,true,true,true,true,true,true,true);
//        GenerateStats(true,true,true,true,true,true,true,true,false);

        GenerateStats(true,true,true,true,true,true,false,true);


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

        PhraseAnalyzer ph=new PhraseAnalyzer();
        Config.TERM_MAX_WORDS=maxWords;
        Config.enable_filter=enableFilter;
        File file=new File(Config.skillsPath);
        for (File f : file.listFiles()) {
            ph.RecognizeTerms(Config.skillsPath+"/"+f.getName(),Config.skillsOutputPath+"/"+f.getName());
        }
        AlgorithmComparotor comparotor=new AlgorithmComparotor();
        comparotor.AggregateAllSkills();

    }

    private static void GenerateStats(boolean enable_averageCorpusTF, boolean enable_ibMglossEx, boolean enable_weirdness, boolean enable_c_value, boolean enable_termex, boolean enable_tfidf, boolean enable_ridf, boolean enable_simpleTF) {

        PhraseAnalyzer ph=new PhraseAnalyzer();
        FileManager fileManager=new FileManager();

        Config.enable_averageCorpusTF   =enable_averageCorpusTF;
        Config.enable_IBMglossEx        =enable_ibMglossEx;
        Config.enable_weirdness         =enable_weirdness;
        Config.enable_c_value           =enable_c_value;
        Config.enable_termex            =enable_termex;
        Config.enable_TFIDF             =enable_tfidf;
        Config.enable_RIDF              =enable_ridf;
        Config.enable_simpleTF          =enable_simpleTF;


//        File file=new File(Config.skillsPath);
//        for (File f : file.listFiles()) {
//            ph.RecognizeTerms(Config.skillsPath+"/"+f.getName(),Config.skillsOutputPath+"/"+f.getName());
//        }

        AlgorithmComparotor comparotor=new AlgorithmComparotor();
//        comparotor.ExtractTerms(Config.skillsPath,Config.skillsOutputPath);
        comparotor.Compare(Config.skillsOutputPath,Config.normalizedSkillsPath,Config.aggregatedSkillsPath);
//        comparotor.Compare(Config.profilesOutputPath, Config.normalizedProfilesPath, Config.aggregatedProfilesPath);
//        comparotor.Compare(Config);
//        System.out.println(Integer.MIN_VALUE);
        HashMap<String, Double> allDocs = fileManager.FileToMap(new File(Config.aggregatedAllDocsPath + "/SkillDocs.csv"));
        GetStats(Config.goldenStandardPath, Config.aggregatedSkillsPath, Config.statsOutPath,allDocs);
    }

    public static void GetStats(String goldenStandardPath,String algorithmgeneratedPath, String statOutPath,HashMap<String,Double> allDocs){
        File goldenRoot=new File(goldenStandardPath);
        HashMap<String,Integer> stats=new HashMap<String,Integer>();
        FileManager fileManager=new FileManager();
        for (String fileName : goldenRoot.list()) {
            HashMap<String, Double> goldenMap = fileManager.FileToMap(new File(goldenRoot + "/" + fileName));
            HashMap<String, Double> algoMap = fileManager.FileToMap(new File(algorithmgeneratedPath + "/" + fileName));
            algoMap= (HashMap<String, Double>) fileManager.SortByComparator(algoMap);
            int score = CompareMaps(goldenMap, algoMap, stats,allDocs);
            stats.put(fileName.split(",")[0],score);
        }
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }
        WriteOutput(stats);

//        fileManager.WriteFile();

    }

    private static void WriteOutput(HashMap<String, Integer> stats) {
        String fileName=""+Config.TERM_MAX_WORDS;
        if(Config.enable_averageCorpusTF)
            fileName+="_AvgCorpusTF";

        if (Config.enable_c_value)
            fileName+="_Cvalue";

        if (Config.enable_IBMglossEx)
            fileName+="_GlossEx";

        if (Config.enable_RIDF)
            fileName+="_RIDF";

        if (Config.enable_simpleTF)
            fileName+="_SimpleTF";

        if (Config.enable_termex)
            fileName+="_Termex";

        if (Config.enable_TFIDF)
            fileName+="_TFIDF";

        if (Config.enable_weirdness)
            fileName+="_Weirdness";
        fileName+=".csv";

        FileManager fileManager=new FileManager();

        fileManager.WriteFile(fileName,stats,Config.statsOutPath);
    }

    private static int CompareMaps(HashMap<String, Double> goldenMap, HashMap<String, Double> algoMap, HashMap<String, Integer> stats,HashMap<String, Double> allDocs) {
        int i=0;
        int score=0;
        if (Config.removeDuplications){
            FileManager fileManager=new FileManager();
            algoMap=fileManager.RemoveDuplications(algoMap);
        }
        for (String goldenKey : goldenMap.keySet()) {
            i=0;
//            System.out.println(goldenKey+"===================");
            for (String algoKey : algoMap.keySet()) {
//                System.out.println(algoKey);
                if (i>Config.statEvaluationDepth)
                    break;
                if (algoKey.toLowerCase().contains(goldenKey.toLowerCase())|| goldenKey.toLowerCase().contains(algoKey.toLowerCase())){
                        if (allDocs.get(algoKey)>50 && Config.enable_filter){
                            continue;
                        }
                    score++;
                    break;
                }
                i++;
            }

        }
        return score;
    }
}
