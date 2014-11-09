package com.cse.warana.service.cvparser.infoExtractors;

import java.util.ArrayList;

/**
 * Created by Nadeeshaan on 11/7/2014.
 */
public interface ProjectInfoExtraction {

    /**
     * Extract the project information
     *
     * @param lines
     * @param headingLines
     * @param allHeadings
     * @param linesCopy
     * @throws java.io.IOException
     */
    public void extractProjectInfo(ArrayList<String> lines, ArrayList<Integer> headingLines, ArrayList<String> allHeadings, ArrayList<String> linesCopy, ArrayList<String> candidateTechnologies);


    /**
     * TODO check what if the technologies are included in the next line also as an example
     * Technologies :
     * C#, Java, etc..
     *
     * @param lineText
     * @return
     */
    public boolean checkForTechnologiesDescription(String lineText,ArrayList<String> candidateTechnologies);


    /**
     * Read the Gazeteer List from the file and load it to the memory
     * @param path
     * @param list
     */
    public void populateByFile(String path, ArrayList<String> list);
}
