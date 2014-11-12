package com.cse.warana.utility.infoExtractors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by Nadeeshaan on 11/9/2014.
 */
public class InterestsInfoExtract {

    private static Logger LOG = LoggerFactory.getLogger(InterestsInfoExtract.class);

    public void extractInterestsInformation(ArrayList<String> lines, ArrayList<Integer> headingLines, ArrayList<String> allHeadings, ArrayList<String> linesCopy){
        String lineText = "";

        LOG.info("----Beginning Interests Information----");
        for (int a = 0; a < headingLines.size(); a++) {
            for (int b = (headingLines.get(a).intValue() + 1); b < lines.size(); b++) {
                lineText = lines.get(b);
                if (allHeadings.contains(String.valueOf(b))) {
                    break;
                } else {
                    LOG.info(lineText);
                }

            }
        }
        LOG.info("----Ending Interests Information----\n");
    }
}
