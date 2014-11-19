package com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.InetSocketAddress;
import java.net.Proxy;

import java.io.IOException;
import java.util.ArrayList;

public class Google {

    public String FindOnLinkedIn(String name) {
        String link = "";
        Document doc = null;
        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxyHost", 1234));
            doc = Jsoup.connect("https://www.google.lk/search?q=" + name.replace(' ', '+') + "+linkedin").userAgent("Chrome").timeout(0).get();
//            doc = Jsoup.connect("https://www.google.lk/search?q=linkedin%3A" + name.replace(' ', '+') ).userAgent("Chrome").timeout(0).get();


        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements;
        elements = doc.select("cite");

        int i = 0;
        while (elements.size() > i) {
            link = elements.get(i).text();
            i++;
            System.out.println(link);
            if (!link.contains("/dir/")) {
                break;
            }
//            if (link.contains("lk.linkedin.com/pub/")) {
//                return link;
//            }
        }
//        System.out.println(link);
        if (link.contains("/dir/")) {
            return "";
        }
        if (link.contains("lk.linkedin.com/")) {
            if (!link.contains("https://"))
                link="https://"+link;
            return link;
        }
        return "";
    }

    public String FindOnGoogleScholar(String name) {
        System.out.println("Searching on Google Scholar");
        String link = "";
        Document doc = null;
        try {
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxyHost", 1234));
            doc = Jsoup.connect("https://www.google.lk/search?q=" + name.replace(' ','+') + "+google+scholar").userAgent("Chrome").timeout(0).get();
//            doc = Jsoup.connect("https://www.google.lk/search?q=nisansa+dilushan+google+scholar").userAgent("Chrome").timeout(0).get();


        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements;
        elements = doc.select("cite");

        int i = 0;
        while (elements.size() > i) {
            link = "https://" + elements.get(i).text();
            System.out.println(link);
            i++;
            if (link.contains(".com/citations?user=")) {
                return link;
            }

        }
        return "";
    }

    public static void main(String[] args) {
        new Google().FindOnGoogleScholar("nisansa dilushan");
//        new Google().FindOnWikipedia("image processing");
    }

    public ArrayList<String> FindOnWikipedia(String searchTerm) {
        String link = "";
        Document doc = null;
        try {
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxyHost", 1234));
            doc = Jsoup.connect("https://www.google.lk/search?q=" + searchTerm.replace(' ', '+') + "+software+wikipedia").userAgent("Chrome").timeout(0).get();
//            doc = Jsoup.connect("https://www.google.lk/search?q=linkedin%3A" + name.replace(' ', '+') ).userAgent("Chrome").timeout(0).get();


        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements;
        elements = doc.select("cite");

        int i = 0;
        ArrayList<String> links=new ArrayList<String>();
        for (Element element : elements) {
            if (element.text().contains("en.wikipedia.org")) {
                System.out.println(element.text());
                links.add("http://"+element.text());
                i++;
                if(i>5)
                    break;
            }
        }
        return links;
    }
}
