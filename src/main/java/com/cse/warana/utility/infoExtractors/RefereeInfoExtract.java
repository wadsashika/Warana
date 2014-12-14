package com.cse.warana.utility.infoExtractors;

import com.cse.warana.utility.infoHolders.Referee;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nadeeshaan on 11/7/2014.
 */
public class RefereeInfoExtract {

    private static Logger LOG = LoggerFactory.getLogger(AchievementsInfoExtract.class);
    public static AbstractSequenceClassifier<CoreLabel> classifier = null;

    /**
     * Constructor
     * @param clf
     */
    public RefereeInfoExtract(AbstractSequenceClassifier<CoreLabel> clf){
        this.classifier = clf;
    }


    /**
     * Extracts the refereeInfo
     * @param lines
     * @param headingLines
     * @param allHeadings
     * @param linesCopy
     */
    public void getRefereeInfo(ArrayList<String> lines, ArrayList<Integer> headingLines, ArrayList<String> allHeadings, ArrayList<String> linesCopy, ArrayList<Referee> referees) {
        String lineText = "";
        String classifierText = "";
        String personTitle = "";
        boolean identified = false;

        /**
         * Get the name of the referee
         */

        LOG.info("----Beginning Referee Information----");
        for (int a = 0; a < headingLines.size(); a++) {
            for (int b = (headingLines.get(a).intValue() + 1); b < lines.size(); b++) {
                lineText = lines.get(b);
                if (allHeadings.contains(String.valueOf(b))) {
                    break;
                } else {
                    if (lineText.length() > 10) {
                        personTitle = lineText.substring(0, 10);
                    }

                    classifierText = classifier.classifyWithInlineXML(lineText);

                    //Check if the line contains the person name
                    if (classifierText.contains("<PERSON>") && classifierText.contains("</PERSON>")) {
                        Pattern pattern = Pattern.compile("<PERSON>(.*?)</PERSON>");
                        Matcher matcher = pattern.matcher(classifierText);
                        while (matcher.find()) {
                            LOG.info(matcher.group(1));
                        }
                        identified = true;
                    }

                    /**
                     * Check if the line contains a name of an organization. Such as a university, a company, etc..
                     * This is because we need to identify the referee's career, etc..
                     */
                    else if (classifierText.contains("<ORGANIZATION>") && classifierText.contains("</ORGANIZATION>")) {
                        Pattern pattern = Pattern.compile("<ORGANIZATION>(.*?)</ORGANIZATION>");
                        Matcher matcher = pattern.matcher(classifierText);
                        while (matcher.find()) {
                            LOG.info(matcher.group(1));
                        }
                        identified = true;
                    }

                    /**
                     * TODO need some more refinement
                     * Get the phone number of the referee
                     */
                    else if (getPhone(lineText)) {

                    }

                    /**
                     * Get the email of the referee
                     */
                    else if (getEmail(lineText)) {

                    }

                    /**
                     * TODO use the correct regex here
                     * This is to identify the persons with the unidentified titles from the parser
                     * This is to be used just in case stanford parser cannot identify the titles
                     */
                    else if (lineText.toLowerCase().contains("dr.") || personTitle.toLowerCase().contains("prof.")) {
                        Pattern pattern = Pattern.compile("((dr|prof)(.*?))");
                        Matcher matcher = pattern.matcher(lineText.toLowerCase());
                        while (matcher.find()) {
                            LOG.info(matcher.group(0));
                        }
                        identified = true;
                    }

                    /**
                     * TODO need implementation (Details such as educational qualifications, job titles)
                     * get the other information of the referee
                     */
                    if (!identified && !lineText.equals("")) {
                    }
                }
            }
        }
        LOG.info("----Ending Referee Information----\n");
    }


    /**
     * Get the email address
     * @param para
     * @return
     */
    public boolean getEmail(String para) {
        // Initialize the regex for identifying the email
        Pattern pattern = Pattern.compile("(\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3})");
        Matcher matcher = pattern.matcher(para);

        while (matcher.find()) {
            String email = matcher.group();
            LOG.info(email);
            return true;
        }
        return false;
    }


    /**
     * Get the phone number
     * @param para
     * @return
     */
    public boolean getPhone(String para) {
        Pattern pattern = Pattern.compile("(.*phone.* | tel | mobile | office )");
        Matcher matcher = pattern.matcher(para.toLowerCase());

        if (matcher.find()) {
            LOG.info(para);
            return true;
        }
        return false;
    }
}
