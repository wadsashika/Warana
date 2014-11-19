package com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker;

import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Profile.Profile;
import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Profile.Project;
import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Profile.Publication;
import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Skills.SkillsExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LinkedInExtractor {

    private String link;
    private SkillsExtractor skillsExtractor;

    public LinkedInExtractor() {
        skillsExtractor=new SkillsExtractor();
    }

    public Profile ExtractInformation(String searchName, Profile profile) {

        com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Google g = new com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Google();
        link = g.FindOnLinkedIn(searchName);

        System.out.println(link + "--------------------------");
        if (link.equals("")) {
            profile.setName("not found");
            return profile;
        }
        String picUrl, name, title;
        String[] publications;
        Document doc = null;
        try {
            doc = Jsoup.connect(link).timeout(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
//      profile picture
        Element profile_picture = doc != null ? doc.select("div[id=profile-picture] > img").first() : null;
        System.out.println(profile_picture);
        if (profile_picture != null) {
            profile.pic_url = profile_picture.attr("src");
        } else {
            profile_picture = doc != null ? doc.select("div.profile-picture> a > img").first() : null;
            if (profile_picture != null) {
                profile.pic_url = profile_picture.attr("src");
            }
        }

        System.out.println("pic url " + profile.pic_url);
//        name
        Element nameDiv = doc != null ? doc.select("span.full-name").first() : null;
        System.out.println(nameDiv.text());
        profile.setName(nameDiv.text());
        name = nameDiv.text();
        profile.name = nameDiv.text();

//        title
        Element titleP = doc != null ? doc.select("p").first() : null;
        System.out.println(titleP.text());
        title = titleP.text();
        profile.title = titleP.text();

        //        Education
        Elements eduTr = doc != null ? doc.select("dd.summary-education > ul > li") : null;
        if (eduTr != null) {
            profile.education = "";
        }
        for (int i = 0; i < eduTr.size(); i++) {
            profile.education += eduTr.get(i).text() + "\n";
        }

//        publications
        Publication pb;
        Elements pub = doc != null ? doc.select("div[class=background-publications] > div[class=editable-item section-item] > div > hgroup > h4 ") : null;
        Elements pubSummary = doc != null ? doc.select("div[class=editable-item section-item] > div > p") : null;
        publications = new String[pub.size()];
        for (int i = 0; i < pub.size(); i++) {
            System.out.println(pub.get(i).text());
            publications[i] = pub.get(i).text();

            pb = new Publication();
            pb.name = pub.get(i).text();
            if (pubSummary != null && i < pubSummary.size()) {
                pb.summary = pubSummary.get(i).text();
            }
//            System.out.println("LinkedIn publication =============="+pb.name);
            profile.addPublication(pb);
        }
//projects type 1 documents
        Project project;
        Elements pro = doc != null ? doc.select("ul[class=projects documents] > li >h3 ") : null;
        Elements proSummary = doc != null ? doc.select("ul[class=projects documents] > li >div >p") : null;
//        System.out.println("ksdjhfkjsf------" + proSummary.size());
        if (pro != null) {
            for (int i = 0; i < pro.size(); i++) {
                project = new Project();
                project.name = pro.get(i).text();
                if (i < proSummary.size()) {
                    project.summary = proSummary.get(i).text();
                }
                profile.addProject(project);
            }
        }
//projects type 2 documents
        Project project2;
        Elements pro2 = doc != null ? doc.select("div[id=background-projects] >div[class=editable-item section-item] > div[id^=project] >hgroup>h4") : null;
        Elements proSummary2 = doc != null ? doc.select("div[id=background-projects] >div[class=editable-item section-item] > div[id^=project] > p") : null;
//        System.out.println("ksdjhfkjsf------" + pro2.size()+"--------"+proSummary2.size());
        if (pro != null) {
            for (int i = 0; i < pro2.size(); i++) {
                project2 = new Project();
                project2.name = pro2.get(i).text();
                if (i < proSummary2.size()) {
                    project2.summary = proSummary2.get(i).text();
                }
                profile.addProject(project2);
            }
        }

        //===============================skills

        ExtractSkills();

        return profile;
    }

    public Profile Extract2(String searchName, Profile profile) {

        com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Google g = new com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Google();
        String link = g.FindOnLinkedIn(searchName);
        System.out.println(link);
        if (link == "") {
            profile.setName("not found");
            return profile;
        }
        Document doc = null;
        String picUrl, name, title;
        String[] publications;
        try {
            doc = Jsoup.connect(link).timeout(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
//      profile picture
        Element profile_picture = doc != null ? doc.select("div.profile-picture> a > img").first() : null;
//        System.out.println(profile_picture);
//        System.out.println(profile_picture.attr("src"));
        if (profile_picture != null) {
            profile.pic_url = profile_picture.attr("src");
            System.out.println("pic url " + profile.pic_url);
        }
//        name
        Element nameDiv = doc != null ? doc.select("span.full-name").first() : null;
        System.out.println(nameDiv.text());
        profile.setName(nameDiv.text());
        name = nameDiv.text();
        profile.name = nameDiv.text();

//        title
        Element titleP = doc != null ? doc.select("p").first() : null;
        System.out.println(titleP.text());
        title = titleP.text();
        profile.title = titleP.text();

//        Education
        Elements eduTr = doc != null ? doc.select("tr[id=overview-summary-education] > td> ol > li") : null;
        if (eduTr != null) {
            profile.education = "";
        }
        for (int i = 0; i < eduTr.size(); i++) {
            profile.education += eduTr.get(i).text() + "\n";
        }
//        publications
        Publication pb;
        Elements pub = doc != null ? doc.select("ul[class=publications documents] > li >h3 ") : null;
        publications = new String[pub.size()];
        for (int i = 0; i < pub.size(); i++) {
            System.out.println(pub.get(i).text());
            publications[i] = pub.get(i).text();

            pb = new Publication();
            pb.name = pub.get(i).text();
            profile.addPublication(pb);
        }
        return profile;
    }

    public void ExtractSkills(){
        skillsExtractor.ExtractSkills(link);
    }
}
