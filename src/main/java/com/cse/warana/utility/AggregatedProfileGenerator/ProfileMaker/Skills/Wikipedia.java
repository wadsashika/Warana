package com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Skills;

import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.PhraseAnalyzer;
import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Google;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.NetworkManager;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Thilina on 11/1/2014.
 */
public class Wikipedia {
    private NetworkManager networkManager;
    public String filePath= Config.skillsPath;
    PhraseAnalyzer phraseAnalyzer;
    Google google;
    public Wikipedia() {
        networkManager=new NetworkManager();
        phraseAnalyzer=new PhraseAnalyzer();
        google=new Google();
    }
    public void GetTermsWikiAPI(String searchTerm){
        ArrayList<String> terms=new ArrayList<String>();
        JSONObject json = null;
        searchTerm=searchTerm.replace(' ','-');
        String url="http://en.wikipedia.org/w/api.php?format=json&action=query&titles="+searchTerm;
        String result = networkManager.Get(url);
        try {
            json = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
//            System.out.println(json.getJSONObject("query").getJSONObject("pages").keys());
            Iterator keys = json.getJSONObject("query").getJSONObject("pages").keys();
            while (keys.hasNext()){
                String key=keys.next().toString();
                System.out.println(key);
                if (!key.equals("-1")){
                    String text=tokenizePage(key);
//                    writeFile(searchTerm,text);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return;
    }
    public void GetTermsGoogle(String searchTerm){

        boolean b = new File(filePath+"/"+searchTerm).mkdirs();
        if (b) {
            ArrayList<String> links = google.FindOnWikipedia(searchTerm);
            for (int i = 0; i < links.size(); i++) {
                String link = links.get(i);
                String text = tokenizePage(link);
                if(text.length()>10)
                writeFile(searchTerm, i + "", text);
            }
//            phraseAnalyzer.RecognizeTerms(Config.skillsPath + "/" + searchTerm, Config.skillsOutputPath + "/" + searchTerm );
        }
    }
    private String tokenizePage(String url){
        ArrayList<String> terms=new ArrayList<String>();
//        String url="http://en.wikipedia.org/wiki?curid="+url;
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(0).get();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        Element body = doc != null ? doc.select("div[id=bodyContent]").first() : null;
        System.out.println(url);
//        System.out.println(body.text());

        return body.text();
    }
    private void writeFile(String skillName,String url,String text){
        boolean b = new File(filePath+"/"+skillName).mkdirs();
//        if(b) {
            File file = new File(filePath + "/" + skillName + "/" + url + ".txt");
        if(file.exists())
            return;
            // creates the file
            try {
                file.createNewFile();
                // creates a FileWriter Object
                FileWriter writer = new FileWriter(file);
                // Writes the content to the file
                writer.write(text);
                writer.flush();
                writer.close();
//                new File(filePath + "/" + skillName+"_out").mkdirs();
//                phraseAnalyzer.RecognizeTerms(filePath + "/" + skillName, filePath + "/" + skillName + "_out");

            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    public static void main(String[] args){
//        Wikipedia wiki=new Wikipedia();
//        wiki.GetTermsGoogle("image processing");

        PhraseAnalyzer ph=new PhraseAnalyzer();
        ph.RecognizeTerms("D:\\Projects\\Repositories\\Final Year Project\\SigmaCV finder 2\\src\\com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker\\Skills\\SkillDocs\\image processing","D:\\Projects\\Repositories\\Final Year Project\\SigmaCV finder 2\\src\\com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker\\Skills\\SkillDocs\\image processing_out");
    }

}
