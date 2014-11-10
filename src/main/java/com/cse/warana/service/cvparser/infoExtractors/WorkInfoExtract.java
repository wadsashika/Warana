package com.cse.warana.service.cvparser.infoExtractors;

import java.util.ArrayList;

/**
 * Created by Nadeeshaan on 11/7/2014.
 */
public interface WorkInfoExtract {

    /**
     * TODO GET THE PROJECT INFORMATION UNDER THIS SECTION
     * (Some people include the involved projects under this section too)
     */
    public void extractWorkInfo(ArrayList<String> lines, ArrayList<Integer> headingLines, ArrayList<String> allHeadings, ArrayList<String> linesCopy);


    /**
     * Find the full company name
     *
     * @param lineTokens
     * @param start
     * @return
     */
    public String findFullCompanyName(String[] lineTokens, int start);


    /**
     * Find the Duration within a certain company
     * This is identified by the stanford classifier which enclose the dates within <DATE>..</DATE>
     *
     * @param line1
     * @param line2
     */
    public void findDuration(String line1, String line2);


    /**
     * Load the Gazateer list to the memory
     *
     * @param path
     * @param list
     */
    public void populateByFile(String path, ArrayList<String> list);
}
