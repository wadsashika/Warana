package com.cse.warana.service.cvparser.infoExtractors;

import java.util.ArrayList;

/**
 * Created by Nadeeshaan on 11/7/2014.
 * This class is responsible for extracting the personal details of the person, such as
 * Gender, Name, email, online profiles, etc..
 */
public interface PersonalInfoExtract {


    /**
     * Extract the personal information from the text, such as name, email, gender, mobile number, etc..
     * If they have been included under an identifiable heading such as, Profile Information which is captured
     * during the section identifying step.
     *
     * @param lines
     * @param headingLines
     * @param allHeadings
     * @param linesCopy
     */
    public void extractPersonalInformation(ArrayList<String> lines, ArrayList<Integer> headingLines, ArrayList<String> allHeadings, ArrayList<String> linesCopy);


    /**
     * Get the name of the person
     *
     * @param para
     * @return
     */
    public boolean getName(String para);


    /**
     * Get the gender of the person
     *
     * @param para
     * @return
     */
    public boolean getGender(String para);


    /**
     * Get the email address of the person
     *
     * @param para
     * @return
     */
    public boolean getEmail(String para);


    /**
     * Get the date of birth of the person
     *
     * @param para
     * @return
     */
    public boolean getDbo(String para);


    /**
     * TODO need some more testing on this method for the possible flaws
     *
     * @param para
     * @return sypeID, LinkedInID, blogUrl, gitID
     */
    public boolean getOtherInfo(String para);
}
