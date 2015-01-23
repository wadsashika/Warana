package com.cse.warana.utility.infoExtractors;

import com.cse.warana.utility.infoHolders.Referee;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Ref;
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
     *
     * @param clf
     */
    public RefereeInfoExtract(AbstractSequenceClassifier<CoreLabel> clf) {
        this.classifier = clf;
    }


    /**
     * Extracts the refereeInfo
     *
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
        Referee referee = null;
        Pattern titlePattern = Pattern.compile("((dr\\.|prof\\.|ms\\.|miss\\.|mrs\\.|mr\\.|master\\.|rev\\.|atty\\.|hon\\.|pres\\.|gov\\.|coach\\.|ofc\\.|lt\\.|lt col\\.|col\\.|gen\\.|sec\\.|eng\\.)(.*?))");
        Matcher titleMatcher = null;

        /**
         * Get the name of the referee
         */

        LOG.info("----Beginning Referee Information----");
        for (int a = 0; a < headingLines.size(); a++) {
            for (int b = (headingLines.get(a).intValue() + 1); b < lines.size(); b++) {
                lineText = lines.get(b);
                titleMatcher = titlePattern.matcher(lineText.toLowerCase());
                if (allHeadings.contains(String.valueOf(b))) {
                    if (referee != null) {
                        referees.add(referee);
                    }
                    break;
                } else {
                    if (lineText.length() > 10) {
                        personTitle = lineText.substring(0, 10);
                    }

                    classifierText = classifier.classifyWithInlineXML(lineText);


                    /**
                     * TODO use the correct regex here
                     * This is to identify the persons with the unidentified titles from the parser
                     * This is to be used just in case stanford parser cannot identify the titles
                     */
                    if (titleMatcher.matches()) {
                        LOG.info(titleMatcher.group(0));

                        if (referee != null) {
                            referees.add(referee);
                        }

                        referee = new Referee();
                        referee.setName(lineText);
                        identified = true;
                    }

                    //Check if the line contains the person name
                    else if (classifierText.contains("<PERSON>") && classifierText.contains("</PERSON>")) {
                        Pattern pattern = Pattern.compile("<PERSON>(.*?)</PERSON>");
                        Matcher matcher = pattern.matcher(classifierText);
                        while (matcher.find()) {
                            if (referee != null) {
                                if (referee.getEmail() == null) {
                                    break;
                                }
                                referees.add(referee);
                            }
                            referee = new Referee();
                            referee.setName(matcher.group(1));
                            LOG.info(matcher.group(1));
                        }
                        identified = true;
                    }

                    /**
                     * Check if the line contains a name of an organization. Such as a university, a company, etc..
                     * This is because we need to identify the referee's career, etc..
                     */

                    /**
                     * TODO
                     * One person can be part of several organizations. Therefore we need to add a list of titles.
                     * ArrayList of titles and then store them all
                     * Nee to handle the organization types. Inc, etc..
                     */
                    else if (classifierText.contains("<ORGANIZATION>") && classifierText.contains("</ORGANIZATION>")) {
                        Pattern pattern = Pattern.compile("<ORGANIZATION>(.*?)</ORGANIZATION>");
                        Matcher matcher = pattern.matcher(classifierText);
                        while (matcher.find()) {
                            LOG.info(matcher.group(1));
                            if (referee != null) {
                                referee.setDescription(lineText);
                                /**
                                 * TODO
                                 * As the description set the whole line text
                                 * referee.setDescription(lineText);
                                 */
                            }
                        }

                        /**
                         * TODO can this identified = true be removed?
                         */
                    }

                    /**
                     * TODO need some more refinement
                     * Get the phone number of the referee
                     */
                    else if (getPhone(lineText, referee)) {

                    }

                    /**
                     * Get the email of the referee
                     */
                    else if (getEmail(lineText, referee)) {

                    }

                    /**
                     * TODO need implementation (Details such as educational qualifications, job titles)
                     * get the other information of the referee
                     */
                    if (!identified && !lineText.equals("")) {
                    }
                }
            }

            if (referee != null && a == headingLines.size() - 1) {
                referees.add(referee);
            }

        }
        LOG.info("----Ending Referee Information----\n");
    }


    /**
     * Get the email address
     *
     * @param para
     * @return
     */
    public boolean getEmail(String para, Referee referee) {
        // Initialize the regex for identifying the email
        Pattern pattern = Pattern.compile("(\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3})");
        Matcher matcher = pattern.matcher(para);
        boolean status = false;

        if (matcher.find() && referee != null && referee.getEmail() == null) {
            String email = matcher.group(0);
            LOG.info(email);
            referee.setEmail(email);
            status = true;
        }
        return status;
    }


    /**
     * Get the phone number
     *
     * @param para
     * @return
     */
    public boolean getPhone(String para, Referee referee) {
        Pattern pattern = Pattern.compile("(.*phone.* | tel | mobile | office )");
        Matcher matcher = pattern.matcher(para.toLowerCase());

        if (matcher.find()) {
            referee.setPhone(para);
            LOG.info(para);
            return true;
        }
        return false;
    }
}
