package com.cse.warana.service;

import java.io.File;

/**
 * Created by Nadeeshaan on 11/7/2014.
 */
public interface CVParserService {

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
    public void parseLines();


    /**
     * Read the pdf
     *
     * @param file
     */
    public void readPdfDocument(File file);


}
