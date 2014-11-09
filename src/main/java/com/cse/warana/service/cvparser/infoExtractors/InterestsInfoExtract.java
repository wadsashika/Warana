package com.cse.warana.service.cvparser.infoExtractors;

import java.util.ArrayList;

/**
 * Created by Nadeeshaan on 11/9/2014.
 */
public interface InterestsInfoExtract {

    /**
     * Extract the interests of the candidate
     * @param lines
     * @param headingLines
     * @param allHeadings
     * @param linesCopy
     */
    public void extractInterestsInformation(ArrayList<String> lines, ArrayList<Integer> headingLines, ArrayList<String> allHeadings, ArrayList<String> linesCopy);
}
