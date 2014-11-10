package com.cse.warana.service.cvparser.infoExtractors;

import java.util.ArrayList;

/**
 * Created by Nadeeshaan on 11/7/2014.
 */
public interface RefereeInfoExtract {
    /**
     * Extract the referee information
     * @param lines
     * @param headingLines
     * @param allHeadings
     * @param linesCopy
     * @throws java.io.IOException
     * @throws ClassNotFoundException
     */
    public void getRefereeInfo(ArrayList<String> lines, ArrayList<Integer> headingLines, ArrayList<String> allHeadings, ArrayList<String> linesCopy);


    /**
     * Get the email based on the general regex
     * @param para
     * @return
     */
    public boolean getEmail(String para);


    /**
     * TODO can add some more refinement for this. Should match almost all patterns(Generic one)
     * Get the phone number of the referee
     * @param para
     * @return
     */
    public boolean getPhone(String para);
}
