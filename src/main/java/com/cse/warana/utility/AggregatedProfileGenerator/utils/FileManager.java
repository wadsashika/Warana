package com.cse.warana.utility.AggregatedProfileGenerator.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Thilina on 11/15/2014.
 */
public class FileManager {

    public void Normalize(File file,String destination) {
        try {
            Scanner sc=new Scanner(file);
            String line;
            Double max= Double.valueOf(Integer.MIN_VALUE),min= Double.valueOf(Integer.MAX_VALUE),ratio;
            Map<String,Double> normalizedMap=new HashMap<String, Double>();
            Double val;
            normalizedMap=FileToMap(file);
            while (sc.hasNextLine()){
                line=sc.nextLine();
                String s=line.split(",")[0].split("\\|")[0];
                val=Double.parseDouble(line.split(",")[1]);
                normalizedMap.put(s,val);
                if (val<min)
                    min=val;
                if(val>max)
                    max=val;
            }
            normalizedMap=NormalizeMap((HashMap<String, Double>) normalizedMap);
            normalizedMap= SortByComparator(normalizedMap);
            ratio=100/(max-min);
            new File(destination+"/"+file.getParentFile().getName()).mkdirs();
            File output=new File(destination+"/"+file.getParentFile().getName()+"/"+file.getName());
            FileWriter writer = new FileWriter(output);

            for (Map.Entry<String, Double> entry : normalizedMap.entrySet()) {
//                val=(entry.getValue()-min)*ratio;
                writer.write(entry.getKey()+","+entry.getValue()+"\n");
            }
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Map<String, Double> NormalizeMap(HashMap<String,Double> map){
        Double max= Double.valueOf(Integer.MIN_VALUE),min= Double.valueOf(Integer.MAX_VALUE),ratio;
        for (Double val : map.values()) {
            if (val>max)
                max=val;
            if (val<min)
                min=val;
        }
        if (max-min==0){
            ratio=0.0;
        }
        else {
            ratio=100/(max-min);
        }

        for (Map.Entry<String, Double> entry : map.entrySet()) {
            map.put(entry.getKey(),(entry.getValue()-min)*ratio);
        }
        return map;
    }

    public Map<String, Double> SortByComparator(Map<String, Double> unsortMap) {

        // Convert Map to List
        List<Map.Entry<String, Double>> list =  new LinkedList<Map.Entry<String, Double>>(unsortMap.entrySet());

        // Sort list with comparator, to compare the Map values
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // Convert sorted map back to a Map
        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Iterator<Map.Entry<String, Double>> it = list.iterator(); it.hasNext();) {
            Map.Entry<String, Double> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    public HashMap<String,Double> FileToMap(File file){
        HashMap<String,Double> map=new HashMap<String, Double>();
        try {
            Scanner sc=new Scanner(file);
            while (sc.hasNextLine()){
                String line=sc.nextLine();
                String[] ary= line.split(",");
//                System.out.println(ary[1]);
//                System.out.println(ary[0].split("\\|")[0]);
                map.put(ary[0].split("\\|")[0],Double.parseDouble(ary[1]));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(file.getName()+" not found");
            return map;
        }

        return map;
    }

    public void WriteFile(String fileName, Map<String, Double> dataMap, String destinationPath) {
        boolean f = new File(destinationPath).mkdirs();
        File file=new File(destinationPath+fileName);
        // creates the file
        try {
            if(!file.exists())
                file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (Map.Entry<String, Double> entry : dataMap.entrySet()) {
                writer.write(entry.getKey().replace(".csv","")+","+entry.getValue().toString()+"\n");
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void WriteFile(String destinationPath, Map.Entry<String, String> entry) {
        boolean f = new File(destinationPath).mkdirs();
        System.out.println(entry.getKey().replaceAll("^[.\\\\/:*?\"<>|]?[\\\\/:*?\"<>|]*", "")+".txt");
        File file=new File(destinationPath+"/"+entry.getKey().replaceAll("[^a-zA-Z0-9\\.\\-]", "_")+".txt");
        // creates the file
        try {
            if(!file.exists())
                file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(entry.getKey());
            writer.write(entry.getValue());
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
