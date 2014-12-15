package com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.WebCrawler;

import com.cse.warana.utility.infoExtractors.OnlineInfoExtractor;
import com.cse.warana.utility.infoHolders.Profile;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.FileManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Thilina on 11/19/2014.
 */
public class WebCrawler {
    private FirefoxDriver driver;
    private OnlineInfoExtractor profile;
    private String baseUrl;
    FileManager fileManager;
    public static void main(String[] args){
//        Profile1 profile1=new Profile1("dulanga sashika");
        OnlineInfoExtractor profile1 = new OnlineInfoExtractor("dulanga sashika");
//        WebCrawler sel=new WebCrawler(profile1);
//        sel.SavePage("http://wadsashika.wordpress.com/");

//        profile1=new Profile("Thilina premasiri");
//        sel=new WebCrawler(profile1);
//        sel.SavePage("http://gamedevsl.blogspot.com/");

        profile1=new OnlineInfoExtractor("Nisansa Dilushan de Silva");
        WebCrawler sel=new WebCrawler(profile1);
        sel.SavePage("http://solibnis.blogspot.com/");
    }

    public WebCrawler(OnlineInfoExtractor profile) {
        this.profile=profile;
        fileManager=new FileManager();
        FirefoxProfile fp=new FirefoxProfile();
//        firefox_profile = driver.FirefoxProfile()
        fp.setPreference("permissions.default.stylesheet", 2);
        fp.setPreference("permissions.default.image", 2);
        fp.setPreference("dom.ipc.plugins.enabled.libflashplayer.so", false);

        this.driver = new FirefoxDriver(fp);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

//        driver = webdriver.Firefox(firefox_profile=firefox_profile)

    }

    public void SavePage(String url){
        this.baseUrl=url;
        driver=downloadPage(url);
        String source=driver.getPageSource();
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

    public void GetLinks(String source){

        ArrayList<String> allLinks=new ArrayList<String>();
        HashMap<String,Integer> linkMap=new HashMap<String,Integer>();
        linkMap.put(baseUrl,0);

        org.jsoup.nodes.Document doc = Jsoup.parse(source);
        Elements links = doc.select("a[href]");


        // href ...
        for (Element link : links) {
            String str=link.attr("abs:href");
            if(str.contains(baseUrl) ) {
                linkMap.put(str, 0);
            }
        }

        saveToUserDocs(linkMap);
    }

    private void saveToUserDocs(HashMap<String,Integer> linkMap) {
        HashMap<String,String> pageMap=new HashMap<String,String>();
        for (String link : linkMap.keySet()) {
            getContent(pageMap, link);
            if (pageMap.size()>Config.user_max_docs)
                break;
        }

        for (Map.Entry<String, String> entry : pageMap.entrySet()) {

            fileManager.WriteFile(Config.profilesPath+"/"+profile.name,entry);
        }
        driver.quit();

    }

    private void getContent(HashMap<String, String> pageMap, String link) {
        driver=downloadPage(link);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!driver.getCurrentUrl().contains(baseUrl)){             // redirected to another website
            return;
        }
        org.jsoup.nodes.Document doc = Jsoup.parse(driver.getPageSource());
        if (pageMap.get(driver.getTitle())==null) {
            pageMap.put(driver.getTitle(), doc.text());
        }
        else {
            if (pageMap.get(driver.getTitle()).length()<doc.text().length()) {
                pageMap.put(driver.getTitle(), doc.text());
            }
        }
//        driver.close();
    }
}
