package com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker;

import com.cse.warana.utility.infoHolders.Candidate;
import com.cse.warana.utility.infoHolders.Profile;
import com.cse.warana.utility.infoHolders.Publication;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class GoogleScholarExtractor {

    public boolean Extract(String searchName, /*Profile profile*/ Candidate candidate) {

        Google g = new Google();
        String link = g.FindOnGoogleScholar(searchName);
        if (link.equals("")) {
            if (candidate.getPublicationList().isEmpty()) {
                candidate.setPublicationList(null);
            }
//            return profile;
            return true;
        }
        Document doc = null;
        String picUrl, name, title;
        String[] publications;
        try {
            doc = Jsoup.connect(link).timeout(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

// profile pic
        if (/*profile.pic_url.equals("")*/ candidate.getProfile().getPic_url().equals("")) {
            Element pic = doc != null ? doc.select("div[id=gsc_prf_pu]> a> img").first() : null;
            if (pic != null) {
                String ulr_suffix = pic.attr("src");
//                profile.pic_url = "http://scholar.google.com/" + ulr_suffix;
                candidate.getProfile().setPic_url("http://scholar.google.com/" + ulr_suffix);
            }
        }
//        publications
//        System.out.println("=========== Research Papers ===========");
        Elements pub = doc != null ? doc.select("tr.gsc_a_tr") : null;
        Publication pb;
        publications = new String[pub.size()];
        for (int i = 0; i < pub.size(); i++) {
//            System.out.println(pub.get(i).select("td>a.gsc_a_at").text());
//            System.out.println("Authors: " + pub.get(i).select("div.gs_gray").get(0).text());
//            System.out.println("Publisher: " + pub.get(i).select("div.gs_gray").get(1).text());
//            System.out.println("Citations: " + pub.get(i).select("td.gsc_a_c").get(0).text());
//            System.out.println("Year: " + pub.get(i).select("td.gsc_a_y").get(0).text());
//            System.out.println("link: " + "http://scholar.google.com/" + pub.get(i).select("a.gsc_a_at").get(0).attr("href"));

            pb = new Publication();
            pb.name = pub.get(i).select("td>a.gsc_a_at").text();
            pb.authors = pub.get(i).select("div.gs_gray").get(0).text();
            pb.link = "http://scholar.google.com/" + pub.get(i).select("a.gsc_a_at").get(0).attr("href");
            pb.summary = GetSummary(pb.link);
            try {
                pb.citations = Integer.parseInt(("0" + pub.get(i).select("td.gsc_a_c").get(0).text()).trim());
            } catch (NumberFormatException e) {
//                System.out.println("Number format exception in "+(pub.get(i).select("td.gsc_a_c").get(0).text()).trim());
                pb.citations=0;
            }
            pb.year = pub.get(i).select("td.gsc_a_y").get(0).text();
            candidate.getPublicationList().add(pb);
//            profile.addPublication(pb);


//            System.out.println("---------------------------------\n");
        }
//        return profile;
        return true;
    }

    public String GetSummary(String link) {
        String summary = "";
        Document doc;
        try {
            doc = Jsoup.connect(link).timeout(0).get();
            Element sumDiv = doc != null ? doc.select("div[id=gsc_descr]").first() : null;
            if (sumDiv != null) {
                summary = sumDiv.text();
                System.out.println("Summary ::::::::::::::::::" + sumDiv.text()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return summary;
    }
}
