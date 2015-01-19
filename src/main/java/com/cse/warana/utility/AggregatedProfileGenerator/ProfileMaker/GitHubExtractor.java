package com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker;

import com.cse.warana.utility.infoHolders.Candidate;
import com.cse.warana.utility.infoHolders.Profile;
import com.cse.warana.utility.infoHolders.Project;
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




    public void Extract(Candidate candidate) {
        Profile candidateProfile=candidate.getProfile();
        Profile profile=new Profile();
        String name=candidateProfile.getName();
        System.out.println("\nSearching " + name + " on GitHub");
        name = name.replaceAll(" ", "%20");
        String url = "https://api.github.com/search/users?q=" + name + "&order=desc&access_token=" + token;
        String result = networkManager.Get(url);
        try {
            JSONObject json = new JSONObject(result);
            if (json.getInt("total_count") != 0) {
                for (int i = 0; i < json.getInt("total_count"); i++) {
                    profile.setName(getName(json.getJSONArray("items").getJSONObject(i).getString("login")));
                    if (candidateProfile.equals(profile)){
                        GetInfo(json.getJSONArray("items").getJSONObject(i).getString("url"), candidate);
                        break;
                    }
                }
            } else {
                System.out.println("No GitHub profiles found");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getName(String login) {
        String result = networkManager.Get("https://api.github.com/users/"+login);
        JSONObject jsonObject=new JSONObject(result);
        return jsonObject.getString("name");
    }

    public String GetInfo(String profileUrl,Candidate candidate) {
        String urlString = profileUrl + "/user?access_token=" + token;

        String profileInfo = networkManager.Get(urlString);

        urlString = profileUrl + "/repos?access_token=" + token;
        String info = networkManager.Get(urlString);
        AddInfo(info, candidate);

//        System.out.println();
        return info;
    }

    public void AddInfo(String jsonString,  Candidate candidate) {
        try {
//            JSONObject json=new JSONObject(jsonString);
            JSONArray ary = new JSONArray(jsonString);
            JSONObject json;

//            System.out.println("==========GitHub Projects=========");
            Project proj;
            System.out.println(jsonString);
            for (int i = 0; i < ary.length(); i++) {
                json = ary.getJSONObject(i);
                System.out.println(json.get("name") + "-----------");
                System.out.println("Description : " + json.get("description"));
                System.out.println("Technology : " + json.get("language") + "\n");

                proj = new Project();
//                proj.name = GetNotNull("name",json);
                proj.setName(GetNotNull("name",json));
//                proj.summary = GetNotNull("description",json);
                proj.setDescription(GetNotNull("description",json));

                /**
                 * TODO set technologies
                 */
//                proj.technology = GetNotNull("language",json);
                candidate.getProjectsLists().add(proj);
//                profile.addProject(proj);
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
