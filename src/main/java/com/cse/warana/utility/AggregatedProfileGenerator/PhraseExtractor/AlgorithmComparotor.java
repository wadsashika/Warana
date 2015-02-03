package com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor;

import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.FileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Thilina on 11/14/2014.
 */
public class AlgorithmComparotor {
    private FileManager fileManager;
    private PhraseAnalyzer phraseAnalyzer;

    public AlgorithmComparotor() {

        fileManager = new FileManager();
        phraseAnalyzer = new PhraseAnalyzer();
    }

    public static void main(String[] args) {
        Config.initialize("C:\\Warana");
        AlgorithmComparotor comparotor = new AlgorithmComparotor();
//        comparotor.ExtractTermsBatch(Config.skillsPath,Config.skillsOutputPath);
        comparotor.ExtractTermsBatch(Config.profilesPath, Config.profilesOutputPath);
//        comparotor.Compare(Config.skillsOutputPath,Config.normalizedSkillsPath,Config.aggregatedSkillsPath, Config.abbreviationsSkillsPath);
        comparotor.Compare(Config.profilesOutputPath, Config.normalizedProfilesPath, Config.aggregatedProfilesPath, Config.abbreviationsProfilesPath);
//        comparotor.Compare(Config);
//        System.out.println(Integer.MIN_VALUE);

        comparotor.AggregateAllSkills();
    }

    public void Compare(String rootPath, String normalizedFilesPath, String aggregatedFilesPath, String abbrFilesPath) {
//        System.out.println("Comparing "+rootPath);
        File directories = new File(rootPath);
        String[] directoryNames = directories.list();
        for (String name : directoryNames) {
//            if (name.contains("_out")){
            NormalizeFilesBatch(rootPath + "/" + name, normalizedFilesPath);
//                fileManager.Normalize();
//            }
        }
        directories = new File(normalizedFilesPath);
        directoryNames = directories.list();
        for (String name : directoryNames) {
            CompareTerms(normalizedFilesPath + "/" + name, aggregatedFilesPath, abbrFilesPath + "/" + name + ".csv");
        }

    }

    private void NormalizeFilesBatch(String path, String destPath) {             // normalize batch
        File root = new File(path);
        HashMap<String, String> termsMap = new HashMap<String, String>();
//        termsMap.put("000","");
        for (File file : root.listFiles()) {
            fileManager.Normalize(file, destPath);
        }
    }

    public void NormalizeFiles(String path, String destPath) {             // normalize 1 corpus
        File root = new File(path);
        HashMap<String, String> termsMap = new HashMap<String, String>();
//        termsMap.put("000","");
        for (File file : root.listFiles()) {
            fileManager.Normalize(file, destPath);
        }
    }
    public void CompareTerms(String normalizedSrc, String aggregatedDestination, String abbrPath) {
        File root = new File(normalizedSrc);
        File abbrFile = new File(abbrPath);
        HashMap<String, Double> termsMap = new HashMap<String, Double>();
//        termsMap.put("000","");
//        for (File file : root.listFiles()) {
//            fileManager.Normalize(file);
//        }
//        root=new File("src/com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker/Skills/Normalized");
        ArrayList<File> fileList = new ArrayList<File>();
        if (Config.enable_averageCorpusTF)
            fileList.add(new File(normalizedSrc + "/" + Config.averageCorpusTF));

        if (Config.enable_c_value)
            fileList.add(new File(normalizedSrc + "/" + Config.c_value));

        if (Config.enable_IBMglossEx)
            fileList.add(new File(normalizedSrc + "/" + Config.IBMglossEx));

        if (Config.enable_RIDF)
            fileList.add(new File(normalizedSrc + "/" + Config.RIDF));

        if (Config.enable_simpleTF)
            fileList.add(new File(normalizedSrc + "/" + Config.simpleTF));

        if (Config.enable_termex)
            fileList.add(new File(normalizedSrc + "/" + Config.termex));

        if (Config.enable_TFIDF)
            fileList.add(new File(normalizedSrc + "/" + Config.TFIDF));

        if (Config.enable_weirdness)
            fileList.add(new File(normalizedSrc + "/" + Config.weirdness));

        HashMap<String, Double> weightMap = fileManager.GetWeightMap(Config.weightMapPath);
        for (File file : fileList) {
            aggregateTerms(termsMap, file);
        }
        for (File file : fileList) {
            aggregateValues(termsMap, file, weightMap.get(file.getName()));
        }
        termsMap = (HashMap<String, Double>) fileManager.NormalizeMap(termsMap);

//        termsMap = aggregateAbbreviations(termsMap, abbrFile);

//        for (Map.Entry<String, Double> entry : termsMap.entrySet()) {
//            System.out.println(entry.getKey()+" , "+entry.getValue());
//        }
        termsMap = (HashMap<String, Double>) fileManager.SortByComparator(termsMap);
        fileManager.writeFile(root.getName(), termsMap, aggregatedDestination);
    }

    private void aggregateTerms(HashMap<String, Double> termsMap, File file) {
//        termsMap.put("000",termsMap.get("000")+","+file.getName());
        try {
            Scanner sc = new Scanner(file);
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine().trim().toLowerCase();
                String s = line.split(",")[0].split("\\|")[0];
                termsMap.put(s, 0.0);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, Double> aggregateAbbreviations(HashMap<String, Double> termsMap, File abbrFile) {

        if (!Config.enable_abbreviations)
            return termsMap;


        HashMap<String, String> abbrMap = fileManager.FileToStrStrMap(abbrFile);
        HashMap<String, String> abbrMap2 = new HashMap<String, String>();
        HashMap<String, Double> clone = (HashMap<String, Double>) termsMap.clone();

        for (Map.Entry<String, String> entry : abbrMap.entrySet()) {
            abbrMap2.put(entry.getValue(), entry.getKey());
        }


        for (Map.Entry<String, Double> entry : termsMap.entrySet()) {
            String key = entry.getKey();
            Double val = entry.getValue();
            if (abbrMap.containsKey(key)) {
                if (termsMap.containsKey(abbrMap.get(key))) {
                    entry.setValue(val + termsMap.get(abbrMap.get(key)));
                    clone.put(key, val + termsMap.get(abbrMap.get(key)));
                    clone.put(abbrMap.get(key), val + termsMap.get(abbrMap.get(key)));
                } else {
                    clone.put(abbrMap.get(key), val);
                }
            }
        }

        for (Map.Entry<String, Double> entry : termsMap.entrySet()) {
            String key = entry.getKey();
            Double val = entry.getValue();
            if (abbrMap2.containsKey(key)) {
                if (termsMap.containsKey(abbrMap2.get(key))) {
                    entry.setValue(val + termsMap.get(abbrMap2.get(key)));
                    clone.put(key, val + termsMap.get(abbrMap2.get(key)));
                    clone.put(abbrMap2.get(key), val + termsMap.get(abbrMap2.get(key)));
                } else {
                    clone.put(abbrMap2.get(key), val);
                }
            }
        }


        termsMap = clone;
        return clone;
    }

    private void aggregateValues(HashMap<String, Double> termsMap, File file, Double weight) {
//        termsMap.put("000",termsMap.get("000")+","+file.getName());
        HashMap<String, String> fileMap = new HashMap<String, String>();
        try {
            Scanner sc = new Scanner(file);
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String s = line.split(",")[0].split("\\|")[0].toLowerCase();
                fileMap.put(s, line.split(",")[1]);
            }
            for (Map.Entry<String, Double> entry : termsMap.entrySet()) {
                System.out.println(entry.getKey() + " :   " + entry.getValue().toString());
                Double val;
                if (fileMap.get(entry.getKey()) != null)
                    val = Double.parseDouble(fileMap.get(entry.getKey()));
                else
                    val = 0.0;
//                if (val==null) {
//                    val = "0";
//                }else {
//                    val="1";
//                }

                termsMap.put(entry.getKey(), entry.getValue() + val);
//                termsMap.put(entry.getKey(), entry.getValue() + val*weight);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void AggregateAllSkills() {
        File file = new File(Config.aggregatedSkillsPath);
        HashMap<String, Double> termsMap = new HashMap<String, Double>();
        for (File f : file.listFiles()) {
            aggregateTerms(termsMap, f);
        }
        HashMap<String, Double> weightMap = fileManager.GetWeightMap(Config.weightMapPath);
        for (File f : file.listFiles()) {

            aggregateValues(termsMap, f, weightMap.get(file.getName()));
        }
        fileManager.NormalizeMap(termsMap);
        termsMap = (HashMap<String, Double>) fileManager.SortByComparator(termsMap);
        fileManager.writeFile(file.getName(), termsMap, Config.aggregatedAllDocsPath);

    }

    public void ExtractTermsBatch(String rootPath, String destPath) {           // extract terms as a batch
        File root = new File(rootPath);
        for (String s : root.list()) {
            phraseAnalyzer.RecognizeTerms(rootPath + "/" + s, destPath + "/" + s);
        }
    }

    public void ExtractTerms(String rootPath, String destPath) {           // extract terms from 1 corpus

        phraseAnalyzer.RecognizeTerms(rootPath, destPath);

    }

    public void ExtractAbbreviationsBatch(String rootPath, String destPath) {        // extract abbreviations batch
        File root = new File(rootPath);
        ExtractAbbrev extractAbbrev = new ExtractAbbrev();
        File file = new File(destPath);
        if (!file.exists())
            file.mkdirs();
        for (String s : root.list()) {
            extractAbbrev.Extract(rootPath + "/" + s, destPath);
        }
    }

    public void ExtractAbbreviations(String rootPath, String destPath) {        // extract abbreviations one corpus
        File root = new File(rootPath);
        ExtractAbbrev extractAbbrev = new ExtractAbbrev();
        File file = new File(destPath);
        if (!file.exists())
            file.mkdirs();
        extractAbbrev.Extract(rootPath, destPath);
    }


    public boolean GetAbbrSimilarity(HashMap<String, String> abbrMap, String algoKey, String goldenKey) {
        if (!Config.enable_abbreviations) {
            return false;
        }

        if (abbrMap.equals(algoKey)) {
            if (abbrMap.get(algoKey).equals(goldenKey))
                return true;
        }
        if (abbrMap.equals(goldenKey)) {
            if (abbrMap.get(goldenKey).equals(algoKey))
                return true;
        }

//        if (abbrMap.containsKey(algoKey)) {
//            if (abbrMap.get(algoKey).contains(goldenKey))
//                return true;
//        }
//        if (abbrMap.containsKey(goldenKey)) {
//            if (abbrMap.get(goldenKey).contains(algoKey))
//                return true;
//        }

        return false;
    }


}
