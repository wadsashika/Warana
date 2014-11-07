package com.cse.warana.service.impl.cvparserImpl.infoExtractorsImpl;

import com.cse.warana.service.cvparser.infoExtractors.AchievementsInfoExtract;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nadeeshaan on 11/7/2014.
 */
public class AchievementsInfoExtractImpl implements AchievementsInfoExtract {


    /**
     * Extract the Achievement information
     * @param lines
     * @param headingLines
     * @param allHeadings
     * @param linesCopy
     */
    @Override
    public void extractAchievementInformation(ArrayList<String> lines, ArrayList<Integer> headingLines, ArrayList<String> allHeadings, ArrayList<String> linesCopy) {

        /**
         * TODO NEED MORE REFINED HEURISTIC FOR THE CHECK
         */

        BufferedReader br = null;
        String indexString = "";

        try {
            br = new BufferedReader(new FileReader("input/achievementsIndex.txt"));
            indexString = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> achievementIndex = new ArrayList<String>();
        Pattern pattern = null;
        Matcher matcher = null;

        while (!indexString.equals("end")) {
            achievementIndex.add(indexString);
            try {
                indexString = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String lineText = "";

        System.out.println("----Beginning Achievements Information----");
        for (int a = 0; a < headingLines.size(); a++) {
            for (int b = (headingLines.get(a).intValue() + 1); b < lines.size(); b++) {
                lineText = lines.get(b);
                if (allHeadings.contains(b)) {
                    break;
                } else {
                    if (!lineText.equals("") && !lineText.equals(" ")) {
                        for (int c = 0; c < achievementIndex.size(); c++) {
                            pattern = Pattern.compile(".*" + achievementIndex.get(c) + ".*");
                            matcher = pattern.matcher(lineText.toLowerCase());

                            if (matcher.matches()) {
                                System.out.println(lineText + "----- THIS IS AN ACHIEVEMENT");
                                // Remove the line from the array list.
                                linesCopy.remove(lineText);
                                break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("----Ending Achievements Information----\n");
    }
}
