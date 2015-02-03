package com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.WebCrawler;

import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.AlgorithmComparotor;
import com.cse.warana.utility.infoExtractors.OnlineInfoExtractor;
import com.cse.warana.utility.infoHolders.Profile;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.FileManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Thilina on 11/19/2014.
 */

public class WebCrawler {
    private FirefoxDriver driver;
    private Profile profile;
    private String baseUrl;
    FileManager fileManager;

    public static void main(String[] args){
        WebCrawler webCrawler=new WebCrawler();
        webCrawler.ExtractOnlineDocuments("http://www.insightforfuture.blogspot.com/");
    }

    public WebCrawler(Profile profile) {

        this.profile = profile;

        fileManager = new FileManager();
        FirefoxProfile fp = new FirefoxProfile();
//        firefox_profile = driver.FirefoxProfile()
        fp.setPreference("permissions.default.stylesheet", 2);
        fp.setPreference("permissions.default.image", 2);
        fp.setPreference("dom.ipc.plugins.enabled.libflashplayer.so", false);
//        fp.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");

        this.driver = new FirefoxDriver(fp);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

//        driver = webdriver.Firefox(firefox_profile=firefox_profile)

    }

    // constructor for testing without profile
    public WebCrawler() {
        fileManager = new FileManager();
        FirefoxProfile fp = new FirefoxProfile();
//        firefox_profile = driver.FirefoxProfile()
        fp.setPreference("permissions.default.stylesheet", 2);
        fp.setPreference("permissions.default.image", 2);
        fp.setPreference("dom.ipc.plugins.enabled.libflashplayer.so", false);
        fp.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");

        this.driver = new FirefoxDriver(fp);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

//        driver = webdriver.Firefox(firefox_profile=firefox_profile)

    }


    public void SavePage(String url) {
        this.baseUrl = url;
        driver = downloadPage(url);
        String source = driver.getPageSource();
        System.out.println(driver.getTitle());
//        System.out.println(source);
        GetLinks(source);
//        driver.close();
    }

    private FirefoxDriver downloadPage(String url) {
//        try {
//        driver.close();
        driver.get(url);
        int textLength = Jsoup.parse(driver.getPageSource()).text().length();
//        for (int i = 0; i < Config.maxReloadTimes; i++) {
//            if ()
//        }
//        }
//        catch (Exception e){
//            driver=downloadPage(url);
//        }
        return driver;
    }

    public void ExtractOnlineDocuments() {

        if(profile.getUrls().size()>0) {
            for (String url : profile.getUrls()) {
                ExtractWebArticles(url);
            }
            ExtractKeyterms();
        }

//        if(profile.getBlogUrl().length()==0) {
//
//            System.out.println("blog========================================================"+profile.getBlogUrl());
//            profile.setBlogUrl("https://wadsashika.wordpress.com/");            // remove this
//        }
//        System.out.println("blog========================================================"+profile.getBlogUrl());
//        if (profile.getBlogUrl().length() > 0) {
//            String blogUrl = profile.getBlogUrl();
//            if (!blogUrl.contains("http"))
//                profile.setBlogUrl("http://"+blogUrl);
//            this.baseUrl=profile.getBlogUrl();
//            System.out.println("blog========================================================"+baseUrl);
//            driver = downloadPage(baseUrl);
//            GetLinks(driver.getPageSource());
//            ExtractKeyterms();
//        }
    }

    public void ExtractWebArticles(String url){

        if (!url.contains("http"))
            url="http://"+url;

        this.baseUrl=url;
        System.out.println("blog========================================================"+baseUrl);
        driver = downloadPage(baseUrl);
        GetLinks(driver.getPageSource());
    }

    private void ExtractKeyterms() {
        AlgorithmComparotor comparotor=new AlgorithmComparotor();
        comparotor.ExtractTerms(Config.profilesPath+ File.separator+profile.getId(),Config.profilesOutputPath+File.separator+profile.getId());
        comparotor.ExtractAbbreviations(Config.profilesPath + File.separator + profile.getId(), Config.abbreviationsProfilesPath + File.separator + profile.getId());
        comparotor.NormalizeFiles(Config.profilesOutputPath+File.separator+profile.getId(),Config.normalizedProfilesPath);
        comparotor.CompareTerms(Config.normalizedProfilesPath+File.separator+profile.getId(),Config.aggregatedProfilesPath,Config.abbreviationsProfilesPath+File.separator+profile.getId());
    }


    public void ExtractOnlineDocuments(String baseUrl) {
        driver = downloadPage(baseUrl);
        this.baseUrl=baseUrl;
        GetLinks(driver.getPageSource());
    }

    public void GetLinks(String source) {

        HashMap<String, Integer> linkMap = new HashMap<String, Integer>();
        linkMap.put(baseUrl, 0);

        org.jsoup.nodes.Document doc = Jsoup.parse(source);
        Elements links = doc.select("a[href]");


        // href ...
        for (Element link : links) {
            String str = link.attr("abs:href");
            if (str.contains(baseUrl.split("//")[1])) {
                linkMap.put(str,linkMap.size());
            }
        }

        saveToUserDocs(linkMap);
    }

    private void saveToUserDocs(HashMap<String, Integer> linkMap) {
        HashMap<String, String> pageMap = new HashMap<String, String>();
        for (String link : linkMap.keySet()) {
            getContent(pageMap, link);
            if (pageMap.size() > Config.user_max_docs)
                break;
        }

        for (Map.Entry<String, String> entry : pageMap.entrySet()) {

            fileManager.WriteFile(Config.profilesPath + "/" + profile.getId(), entry);
        }
        driver.quit();

    }

    private void getContent(HashMap<String, String> pageMap, String link) {
        driver = downloadPage(link);
        try {
            System.out.println("waiting "+link+" to download");
            Thread.sleep(1000);
            System.out.println("waiting ended");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!driver.getCurrentUrl().contains(baseUrl.split("//")[1])) {             // redirected to another website
            System.out.println("Rejected :"+driver.getCurrentUrl());
            return;                                                                 // split is used because http https issue
        }
        org.jsoup.nodes.Document doc = Jsoup.parse(driver.getPageSource());
        if (pageMap.get(driver.getTitle()) == null) {
            pageMap.put(driver.getTitle(), doc.text());
        } else {
            if (pageMap.get(driver.getTitle()).length() < doc.text().length()) {
                pageMap.put(driver.getTitle(), doc.text());
            }
        }
//        driver.close();
    }
}
