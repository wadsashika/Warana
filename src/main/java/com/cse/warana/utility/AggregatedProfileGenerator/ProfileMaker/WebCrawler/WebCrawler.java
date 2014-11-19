package com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.WebCrawler;

import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Profile.Profile;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.model.Document;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.FileManager;
import com.thoughtworks.selenium.Wait;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Thilina on 11/19/2014.
 */
public class WebCrawler {
    private WebDriver driver;
    private Profile profile;
    private String baseUrl;
    FileManager fileManager;
    public static void main(String[] args){
        Profile profile1=new Profile("dulanga sashika");
        WebCrawler sel=new WebCrawler(profile1);
//        sel.SavePage("http://wadsashika.wordpress.com/");

        profile1=new Profile("Thilina premasiri");
        sel=new WebCrawler(profile1);
        sel.SavePage("http://gamedevsl.blogspot.com/");

//        sel.SavePage("http://wadsashika.wordpress.com/feed/");
    }

    public WebCrawler(Profile profile) {
        this.profile=profile;
        this.driver = new FirefoxDriver();
        fileManager=new FileManager();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

    }

    public void SavePage(String url){
        this.baseUrl=url;

        driver.get(url);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String source=driver.getPageSource();
        System.out.println(driver.getTitle());
//        System.out.println(source);
        GetLinks(source);
        driver.close();
    }
    public void GetLinks(String source){

        ArrayList<String> allLinks=new ArrayList<String>();
        HashMap<String,Integer> linkMap=new HashMap<String,Integer>();

        org.jsoup.nodes.Document doc = Jsoup.parse(source);
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");


        // href ...
        for (Element link : links) {
            String str=link.attr("abs:href");
            if(str.contains(baseUrl) ) {
                linkMap.put(str, 0);
            }
        }

        saveToUserDocs(linkMap);

        // img ...
//        for (Element src : media) {
//            if(src.attr("abs:href").contains(baseUrl))
//            System.out.println(src.attr("abs:src"));
//        }
//
//        // js, css, ...
//        for (Element link : imports) {
//            if(link.attr("abs:href").contains(baseUrl))
//            System.out.println(link.attr("abs:href"));
//        }


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


    }

    private void getContent(HashMap<String, String> pageMap, String link) {
        driver.get(link);
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
    }
}
