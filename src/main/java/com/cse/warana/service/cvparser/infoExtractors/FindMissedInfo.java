package com.cse.warana.service.cvparser.infoExtractors;

import java.util.ArrayList;

/**
 * Created by Nadeeshaan on 11/7/2014.
 */
public interface FindMissedInfo {


    /**
     * Assume that the profile information is mentioned at the beginning of the resume
     * and within first 10 - 15 lines
     * Heuristic is we can find the email of a person most of the time around the name
     */
    public void findProfileInfo(ArrayList<String> lines);


    /**
     * Get the email address based on the regex
     * @param para
     * @return
     */
    public boolean getEmail(String para);
}
