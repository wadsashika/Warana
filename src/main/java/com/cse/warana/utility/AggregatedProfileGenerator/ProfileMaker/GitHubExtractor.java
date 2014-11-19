package com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker;

import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Profile.Profile;
import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Profile.Project;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.NetworkManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GitHubExtractor {

    private String token;
    private NetworkManager networkManager;

    public GitHubExtractor(String Accesstoken) {
        this.token = Accesstoken;
        networkManager = new NetworkManager();
    }

    public static void main(String[] args) {
//        new GitHubExtractor("69e07dde89a8a0a6713f810cfd4c461f04f47e85").GetInfo("https://api.github.com/users/wadsashika");
        new GitHubExtractor("69e07dde89a8a0a6713f810cfd4c461f04f47e85").Extract("thilina premasiri", null);
    }

    public void Extract(String name, Profile profile) {
        System.out.println("\nSearching " + name + " on GitHub");
        name = name.replaceAll(" ", "%20");
        String url = "https://api.github.com/search/users?q=" + name + "&order=desc&access_token=" + token;
        String result = networkManager.Get(url);
        try {
            JSONObject json = new JSONObject(result);
            if (json.getInt("total_count") != 0) {
                GetInfo(json.getJSONArray("items").getJSONObject(0).getString("url"), profile);
            } else {
                System.out.println("No GitHub profiles found");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String GetInfo(String profileUrl, Profile profile) {
        String urlString = profileUrl + "/repos?access_token=" + token;
        String info = networkManager.Get(urlString);
        ShowInfo(info, profile);

//        System.out.println();
        return info;
    }

    public void ShowInfo(String jsonString, Profile profile) {
        try {
//            JSONObject json=new JSONObject(jsonString);
            JSONArray ary = new JSONArray(jsonString);
            JSONObject json;

//            System.out.println("==========GitHub Projects=========");
            Project proj;
            System.out.println(jsonString);
            for (int i = 0; i < ary.length(); i++) {
                json = ary.getJSONObject(i);
//                System.out.println(json.get("name") + "-----------");
//                System.out.println("Description : " + json.get("description"));
//                System.out.println("Technology : " + json.get("language") + "\n");

                proj = new Project();
                proj.name = GetNotNull("name",json);
                proj.summary = GetNotNull("description",json);
                proj.technology = GetNotNull("language",json);
                profile.addProject(proj);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String GetNotNull(String key, JSONObject json) {
        try {
            if (json.get(key).toString() != null) {
                return " "+json.get(key).toString();
            }
        } catch (JSONException ex) {
            Logger.getLogger(GitHubExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
