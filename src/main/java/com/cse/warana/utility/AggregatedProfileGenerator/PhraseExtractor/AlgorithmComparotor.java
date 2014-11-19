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
//    private String skillsPath=Config.skillsPath;
//    private String normalizedSkillsPath=Config.normalizedSkillsPath;
    private FileManager fileManager;

    public AlgorithmComparotor() {
            fileManager =new FileManager();
    }

    public static void main(String[] args){
        AlgorithmComparotor comparotor=new AlgorithmComparotor();
        comparotor.Compare(Config.skillsOutputPath,Config.normalizedSkillsPath,Config.aggregatedSkillsPath);
        comparotor.Compare(Config.profilesOutputPath, Config.normalizedProfilesPath, Config.aggregatedProfilesPath);
//        comparotor.Compare(Config);
        comparotor.AggregateAllSkills();
//        System.out.println(Integer.MIN_VALUE);

    }
    public void Compare(String rootPath,String normalizedFilesPath,String aggregatedFilesPath){
        File skillDirectories=new File(rootPath);
        String[] directoryNames=skillDirectories.list();
        for (String name : directoryNames) {
//            if (name.contains("_out")){
                NormalizeFiles(rootPath + "/" + name,normalizedFilesPath);
//                fileManager.Normalize();
//            }
        }
        skillDirectories=new File(normalizedFilesPath);
        directoryNames=skillDirectories.list();
        for (String name : directoryNames) {
//            if (name.contains("_out")){
                CompareTerms(normalizedFilesPath+"/"+name,aggregatedFilesPath);
//            }
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

    public void CompareTerms(String path, String aggregatedDestination){
        File root=new File(path);
        HashMap<String,Double> termsMap=new HashMap<String, Double>();
//        termsMap.put("000","");
//        for (File file : root.listFiles()) {
//            fileManager.Normalize(file);
//        }
//        root=new File("src/com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker/Skills/Normalized");
        ArrayList<File> fileList=new ArrayList<File>();
//        fileList.add(new File(path+"/"+Config.averageCorpusTF));
        fileList.add(new File(path+"/"+Config.c_value));
        fileList.add(new File(path+"/"+Config.IBMglossEx));
//        fileList.add(new File(path+"/"+Config.RIDF));
//        fileList.add(new File(path+"/"+Config.simpleTF));
        fileList.add(new File(path+"/"+Config.termex));
//        fileList.add(new File(path+"/"+Config.TFIDF));
        fileList.add(new File(path+"/"+Config.weirdness));
        for (File file : fileList) {
            aggregateTerms(termsMap, file);
        }
        for (File file : fileList) {
            aggregateValues(termsMap, file);
        }
        termsMap= (HashMap<String, Double>) fileManager.NormalizeMap(termsMap);
//        for (File file : root.listFiles()) {
//            aggregateTerms(termsMap, file);
//        }
//        for (File file : root.listFiles()) {
//            aggregateValues(termsMap, file);
//        }
        for (Map.Entry<String, Double> entry : termsMap.entrySet()) {
            System.out.println(entry.getKey()+" , "+entry.getValue());
        }
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
