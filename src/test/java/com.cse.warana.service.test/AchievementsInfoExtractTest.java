package com.cse.warana.test.utility.infoExtractors;

import com.cse.warana.utility.infoExtractors.AchievementsInfoExtract;
import com.cse.warana.utility.infoHolders.Achievement;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nadeeshaan on 1/3/2015.
 */
public class AchievementsInfoExtractTest {

    private HashMap<String,String> paths = new HashMap<>();
    private ArrayList<String> lines = new ArrayList<>();
    private ArrayList<Integer> headingLines = new ArrayList<>();
    private ArrayList<String> allHeadings = new ArrayList<>();
    private ArrayList<String> linesCopy = new ArrayList<>();
    private ArrayList<Achievement> achievements = new ArrayList<>();

    public AchievementsInfoExtractTest(){
        paths.put("root", "");
        paths.put("listPath", "/gazeteerLists");

        lines.add("Awards and achievements");
        lines.add("IEEE programming Competition 2012");
        lines.add("IEEE programming Competition 2013");
        lines.add("Other Heading");

        linesCopy.add("Awards and achievements");
        linesCopy.add("IEEE programming Competition 2012");
        linesCopy.add("IEEE programming Competition 2013");
        linesCopy.add("Other Heading");

        headingLines.add(new Integer("0"));

        allHeadings.add("0");
        allHeadings.add("3");

    }

    @Test
    public void extractAchievementInformationTest(){
        AchievementsInfoExtract achievementsInfoExtract = new AchievementsInfoExtract(paths);
        achievementsInfoExtract.extractAchievementInformation(lines,headingLines,allHeadings,linesCopy,achievements);

        Assert.assertEquals(2,achievements.size());
    }

    public static void main(String[] args){
        AchievementsInfoExtractTest achievementsInfoExtractTest = new AchievementsInfoExtractTest();
        achievementsInfoExtractTest.extractAchievementInformationTest();
    }
}
