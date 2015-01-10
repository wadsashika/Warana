package com.cse.warana.utility.AggregatedProfileGenerator.TechnolohySimilarity;

import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.AlgorithmComparotor;
import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.ExtractAbbrev;
import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.PhraseAnalyzer;
import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.TestPhraseAnalyser;
import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Skills.Wikipedia;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.FileManager;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Anushka on 2014-12-29.
 */
public class Extractor {
    private Wikipedia wiki;
    private PhraseAnalyzer pa;
    private FileManager fileManager;
    private AlgorithmComparotor ac;
    private ExtractAbbrev eabb;
    private TestPhraseAnalyser tac;

    public Extractor() {
        wiki = new Wikipedia();
        pa = new PhraseAnalyzer();
        fileManager = new FileManager();
        ac = new AlgorithmComparotor();
        eabb = new ExtractAbbrev();
        tac = new TestPhraseAnalyser();
    }

    public static void main(String al[]) {
        new Extractor().nodeKeywordExtractor("java");
    }

    public void nodeKeywordExtractor(String nodeValue) {
        wiki.GetTermsGoogle(nodeValue);
        pa.RecognizeTerms(Config.skillsPath + "/" + nodeValue, Config.skillsOutputPath + "/" + nodeValue);
        NormalizeFiles(Config.skillsOutputPath + "/" + nodeValue, Config.normalizedSkillsPath);
        eabb.Extract(Config.skillsPath + "/" + nodeValue, Config.abbreviationsSkillsPath);
        ac.CompareTerms(Config.normalizedSkillsPath + "/" + nodeValue, Config.aggregatedSkillsPath, Config.abbreviationsSkillsPath + "/game development.csv");
        File fileDirectory = new File(Config.aggregatedSkillsPath); // Need to place the list of skills generated for candidate

        HashMap<String, Double> technologyMap = fileManager.FileToMap(new File(Config.aggregatedSkillsPath + "/" + nodeValue + ".csv"));
        HashMap<String, Double> allDocs = fileManager.FileToMap(new File(Config.aggregatedAllDocsPath + "/SkillDocs.csv"));
        HashMap<String, Double> stats = new HashMap<String, Double>();

        double weight = 0;
        FileManager fileManager = new FileManager();
        for (String fileName : fileDirectory.list()) {
            if (fileName.contains(nodeValue)) {
                continue;
            }

            HashMap<String, Double> algoMap = fileManager.FileToMap(new File(Config.aggregatedSkillsPath + "/" + fileName));
            HashMap<String, String> abbrMap = fileManager.FileToStrStrMap(new File(Config.abbreviationsSkillsPath + "/" + fileName));
            algoMap = (HashMap<String, Double>) fileManager.SortByComparator(algoMap);

            double score = CompareMaps(technologyMap, algoMap, abbrMap, stats, allDocs) / technologyMap.size();
            weight += (score - Config.average_precision);
            stats.put(fileName.split(",")[0], score);

        }
        double total = 0.0;
        for (double i : stats.values()) {
            total += i;
        }
        System.out.println(total / stats.size());
    }

    private void NormalizeFiles(String path, String destPath) {
        File root = new File(path);
        for (File file : root.listFiles()) {
            fileManager.Normalize(file, destPath);
        }
    }

    private double CompareMaps(HashMap<String, Double> goldenMap, HashMap<String, Double> algoMap, HashMap<String, String> abbrMap, HashMap<String, Double> stats, HashMap<String, Double> allDocs) {
        int i = 0;
        double score = 0;
        AlgorithmComparotor comparotor = new AlgorithmComparotor();

        if (Config.removeDuplications) {
            FileManager fileManager = new FileManager();
            algoMap = fileManager.RemoveDuplications(algoMap);
        }
        for (String goldenKey : goldenMap.keySet()) {
            i = 0;
            goldenKey = goldenKey.toLowerCase().trim();
            for (String algoKey : algoMap.keySet()) {
                if (i > goldenMap.size())
                    break;

                if (algoKey.equals(goldenKey) || comparotor.GetAbbrSimilarity(abbrMap, algoKey, goldenKey)) {
                    score++;
                    break;
                }
                i++;
            }

        }
        return score;
    }

}

