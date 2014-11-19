package com.cse.warana.utility.AggregatedProfileGenerator.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thilina on 11/19/2014.
 */
public class UtilTest {
    public static void main(String[] args){
        FileManager fileManager=new FileManager();
        HashMap<String,String> map=new HashMap<String,String>();
        map.put("doc1","asdfasdfasdfdf");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            fileManager.WriteFile(Config.profilesPath+"/"+"Thilina",entry);
        }

    }
}
