package com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor;

import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.FileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Thilina on 11/14/2014.
 */
public class AlgorithmComparotor {
    private FileManager fileManager;
    private PhraseAnalyzer phraseAnalyzer;

    public AlgorithmComparotor() {

        fileManager =new FileManager();
        phraseAnalyzer =new PhraseAnalyzer();
    }

    public static void main(String[] args){
        AlgorithmComparotor comparotor=new AlgorithmComparotor();
//        comparotor.ExtractTerms(Config.skillsPath,Config.skillsOutputPath);
//        comparotor.Compare(Config.skillsOutputPath,Config.normalizedSkillsPath,Config.aggregatedSkillsPath, Config.abbreviationsSkillsPath);
//        comparotor.Compare(Config.profilesOutputPath, Config.normalizedProfilesPath, Config.aggregatedProfilesPath);
//        comparotor.Compare(Config);
        comparotor.AggregateAllSkills();
//        System.out.println(Integer.MIN_VALUE);

    }
    public void Compare(String rootPath,String normalizedFilesPath,String aggregatedFilesPath, String abbrFilesPath){
//        System.out.println("Comparing "+rootPath);
        File directories=new File(rootPath);
        String[] directoryNames=directories.list();
        for (String name : directoryNames) {
//            if (name.contains("_out")){
                NormalizeFiles(rootPath + "/" + name,normalizedFilesPath);
//                fileManager.Normalize();
//            }
        }
        directories=new File(normalizedFilesPath);
        directoryNames=directories.list();
        for (String name : directoryNames) {
                CompareTerms(normalizedFilesPath+"/"+name,aggregatedFilesPath,abbrFilesPath+"/"+name+".csv");
        }

    }

    private void NormalizeFiles(String path,String destPath) {
        File root=new File(path);
        HashMap<String,String> termsMap=new HashMap<String, String>();
//        termsMap.put("000","");
        for (File file : root.listFiles()) {
            fileManager.Normalize(file,destPath);
        }
    }

    public void CompareTerms(String path, String aggregatedDestination, String abbrPath){
        File root=new File(path);
        File abbrFile=new File(abbrPath);
        HashMap<String,Double> termsMap=new HashMap<String, Double>();
//        termsMap.put("000","");
//        for (File file : root.listFiles()) {
//            fileManager.Normalize(file);
//        }
//        root=new File("src/com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker/Skills/Normalized");
        ArrayList<File> fileList=new ArrayList<File>();
        if(Config.enable_averageCorpusTF)
        fileList.add(new File(path+"/"+Config.averageCorpusTF));

        if (Config.enable_c_value)
        fileList.add(new File(path+"/"+Config.c_value));

        if (Config.enable_IBMglossEx)
        fileList.add(new File(path+"/"+Config.IBMglossEx));

        if (Config.enable_RIDF)
        fileList.add(new File(path+"/"+Config.RIDF));

        if (Config.enable_simpleTF)
        fileList.add(new File(path+"/"+Config.simpleTF));

        if (Config.enable_termex)
        fileList.add(new File(path+"/"+Config.termex));

        if (Config.enable_TFIDF)
        fileList.add(new File(path+"/"+Config.TFIDF));

        if (Config.enable_weirdness)
        fileList.add(new File(path+"/"+Config.weirdness));



        for (File file : fileList) {
            aggregateTerms(termsMap, file);
        }
        for (File file : fileList) {
            aggregateValues(termsMap, file);
        }
        termsMap= (HashMap<String, Double>) fileManager.NormalizeMap(termsMap);

        aggregateAbbreviations(termsMap,abbrFile);

//        for (Map.Entry<String, Double> entry : termsMap.entrySet()) {
//            System.out.println(entry.getKey()+" , "+entry.getValue());
//        }
        termsMap= (HashMap<String, Double>) fileManager.SortByComparator(termsMap);
        writeFile(root.getName(),termsMap,aggregatedDestination);
    }

    private void aggregateTerms(HashMap<String, Double> termsMap, File file) {
//        termsMap.put("000",termsMap.get("000")+","+file.getName());
        try {
            Scanner sc=new Scanner(file);
            String line;
            while (sc.hasNextLine()){
                line=sc.nextLine();
                String s=line.split(",")[0].split("\\|")[0].toLowerCase();
                termsMap.put(s,0.0);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void aggregateAbbreviations(HashMap<String, Double> termsMap, File abbrFile) {
//        termsMap.put("000",termsMap.get("000")+","+file.getName());
        HashMap<String, String> abbrMap= fileManager.FileToStrStrMap(abbrFile);
        try {

            for (Map.Entry<String, Double> entry : termsMap.entrySet()) {
                if (abbrMap.get(entry.getKey())!=null){
                    if(termsMap.containsKey(abbrMap.get(entry.getKey()))) {
                        entry.setValue(entry.getValue() + termsMap.get(abbrMap.get(entry.getKey())));
                        termsMap.put(abbrMap.get(entry.getKey()), entry.getValue());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void aggregateValues(HashMap<String, Double> termsMap, File file) {
//        termsMap.put("000",termsMap.get("000")+","+file.getName());
        HashMap<String,String> fileMap=new HashMap<String, String>();
        try {
            Scanner sc=new Scanner(file);
            String line;
            while (sc.hasNextLine()){
                line=sc.nextLine();
                String s=line.split(",")[0].split("\\|")[0].toLowerCase();
                fileMap.put(s,line.split(",")[1]);
            }
            for (Map.Entry<String, Double> entry : termsMap.entrySet()) {
                System.out.println(entry.getKey()+" :   "+entry.getValue().toString());
                Double val;
                if (fileMap.get(entry.getKey())!=null)
                    val = Double.parseDouble(fileMap.get(entry.getKey()));
                else
                    val=0.0;
//                if (val==null) {
//                    val = "0";
//                }else {
//                    val="1";
//                }
                termsMap.put(entry.getKey(),entry.getValue()+val);
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void AggregateAllSkills(){
        File file=new File(Config.aggregatedSkillsPath);
        HashMap<String,Double> termsMap=new HashMap<String, Double>();
        for (File f : file.listFiles()) {
            aggregateTerms(termsMap,f);
        }
        for (File f : file.listFiles()) {
            aggregateValues(termsMap,f);
        }
        fileManager.NormalizeMap(termsMap);
        termsMap= (HashMap<String, Double>) fileManager.SortByComparator(termsMap);
        writeFile(file.getName(),termsMap,Config.aggregatedAllDocsPath);

    }

    public void ExtractTerms(String rootPath,String destPath){
        File root=new File(rootPath);
        for (String s : root.list()) {
            phraseAnalyzer.RecognizeTerms(rootPath+"/"+s,destPath+"/"+s);
        }
    }

    public void ExtractAbbreviations(String rootPath,String destPath){
        File root=new File(rootPath);
        ExtractAbbrev extractAbbrev=new ExtractAbbrev();
        File file = new File(destPath);
        if (!file.exists())
            file.mkdirs();
        for (String s : root.list()) {
            extractAbbrev.Extract(rootPath+"/"+s,destPath);
        }
    }



    private void writeFile(String name,HashMap<String,Double> map, String destinationPath){
//        boolean b = new File(filePath+"/"+name).mkdirs();

        boolean f = new File(destinationPath).mkdirs();
        File file=new File(destinationPath+name+".csv");
        // creates the file
        try {
            if(!file.exists())
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                writer.write(entry.getKey()+","+entry.getValue().toString()+"\n");
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
