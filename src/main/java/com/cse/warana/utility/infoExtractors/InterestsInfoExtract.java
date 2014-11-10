package com.cse.warana.utility.infoExtractors;

import java.util.ArrayList;

/**
 * Created by Nadeeshaan on 11/9/2014.
 */
public class InterestsInfoExtract {
    public void extractInterestsInformation(ArrayList<String> lines, ArrayList<Integer> headingLines, ArrayList<String> allHeadings, ArrayList<String> linesCopy){
        String lineText = "";

        System.out.println("----Beginning Interests Information----");
        for (int a = 0; a < headingLines.size(); a++) {
            for (int b = (headingLines.get(a).intValue() + 1); b < lines.size(); b++) {
                lineText = lines.get(b);
                if (allHeadings.contains(String.valueOf(b))) {
                    break;
                } else {
                    System.out.println(lineText);
                }

            }
        }
        System.out.println("----Ending Interests Information----\n");
    }
}
