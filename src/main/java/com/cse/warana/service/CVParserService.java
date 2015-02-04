package com.cse.warana.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nadeeshaan on 11/7/2014.
 */
public interface CVParserService {

    public void setPaths(Map<String, String> paths);

    /**
     * In this method, tokens are loaded to the memory(ArrayList)
     * These tokens are used to identify the possible heading lines
     */
    public void initializeHeadingTokens();


    /**
     * This method is used to identify the sections based on the pre defined tokens
     * specific to the sections
     */
    public void identifyHeadings();


    /**
     * This method is used to classify the lines under the headings and extract the
     * required information
     */
    public void parseLines(HashMap<String,Object> infoCategoryTypes);


    /**
     * Read the pdf
     *
     * @param file
     */
    public void readPdfDocument(File file);

    public String test();

    public ArrayList<String> getNewTechnologies();


}
