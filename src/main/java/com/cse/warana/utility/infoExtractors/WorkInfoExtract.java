package com.cse.warana.utility.infoExtractors;

import com.cse.warana.utility.infoHolders.Work;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nadeeshaan on 11/7/2014.
 */
public class WorkInfoExtract {

    public static AbstractSequenceClassifier<CoreLabel> classifier = null;
    public static ArrayList<String> companies = new ArrayList<String>();
    private static Logger LOG = LoggerFactory.getLogger(AchievementsInfoExtract.class);

    /**
     * Constructor Method
     *
     * @param clf
     */
    public WorkInfoExtract(AbstractSequenceClassifier<CoreLabel> clf) {
        this.classifier = clf;
        /**
         * Load the companies gazeteer list
         */
        populateByFile("F:\\Accademic\\Semister 7\\Final_Year_Project\\Project Implementation\\Implementation_2\\Warana\\src\\main\\resources\\gazeteerLists\\companyNames", companies);
    }


    /**
     * Extract the Work Information
     *
     * @param lines
     * @param headingLines
     * @param allHeadings
     * @param linesCopy
     */
    public void extractWorkInfo(ArrayList<String> lines, ArrayList<Integer> headingLines, ArrayList<String> allHeadings, ArrayList<String> linesCopy, ArrayList<Work> worksList) {

        String lineText = "";
        String companyName = "";
        boolean foundCompany = false;
        boolean newCompany = false;
        Work work = null;

        LOG.info("----Beginning Work Information----");
        for (int a = 0; a < headingLines.size(); a++) {
            for (int b = (headingLines.get(a).intValue() + 1); b < lines.size(); b++) {
                lineText = lines.get(b);
                if (allHeadings.contains(String.valueOf(b))) {
                    break;
                } else {
                    /**
                     * Stanford classifier can correctly identify the organization names when it is mentioned as follows
                     * ex: Trainee Software Engineer at WSO2 Lanka (Pvt) Ltd
                     * Need to mention the "at" before the company name
                     */

                    if (newCompany && work != null) {
                        worksList.add(work);
                        newCompany = false;
                    }
                    else

                    if (lineText.contains("at")) {

                        String classifierText = "";
                        classifierText = classifier.classifyWithInlineXML(lineText);

                        if (classifierText.contains("<ORGANIZATION>")) {
                            work = new Work();
                            newCompany = true;
                            Pattern pattern = Pattern.compile("<ORGANIZATION>(.*?)</ORGANIZATION>");
                            Matcher matcher = pattern.matcher(classifierText);
                            while (matcher.find()) {
                                companyName = matcher.group(1);
                                work.setCompanyName(companyName);
                                LOG.info(companyName);
                            }
                        }
                    }

                    /**
                     * When the company name is mentioned without "at" before the company name
                     * These are identified by checking for the company descriptors such as [(Pvt) Ltd, Inc, Corp, etc..]
                     * or Looking at the gazeteer list for the companies
                     */

                    // Check in the Companies Gazeteer list
                    else {
                        String tokens[] = lineText.split(" ");
                        for (int x = 0; x < tokens.length; x++) {
                            if (companies.contains(tokens[x].toLowerCase())) {
                                work = new Work();
                                newCompany = true;
                                companyName = findFullCompanyName(tokens, x);
                                LOG.info(companyName);

                                work.setCompanyName(companyName);

                                findDuration(lineText, lines.get(b + 1), work);
                                LOG.info("Found");
                                linesCopy.remove(lineText);
                                foundCompany = true;
                            }
                        }
                    }

                    /**
                     * TODO need to include the other company descriptors
                     * Check for the common properties such as
                     * (pvt) Ltd, Inc., etc
                     */
                    Pattern pattern = Pattern.compile("(.*[(]pvt[)] ltd.*)|(.*inc[.].*)");
                    Matcher matcher = pattern.matcher(lineText.toLowerCase());

                    if (!foundCompany && matcher.matches()) {
                        LOG.info("");
                        LOG.info("found");
                        LOG.info(lineText);
                        if (work == null){
                            work = new Work();
                        }
                        findDuration(lineText, lines.get(b + 1), work);

                        linesCopy.remove(lineText);
                    }

                    /**
                     * Check for other properties
                     */
                }
            }
        }
        LOG.info("----Ending Work Information----\n");
    }


    /**
     * Find the full name of the company
     *
     * @param lineTokens
     * @param start
     * @return
     */
    public String findFullCompanyName(String[] lineTokens, int start) {
        String name = "";
        for (int a = start; a < lineTokens.length; a++) {
            if (lineTokens[a].toLowerCase().contains("pvt") || lineTokens[a].toLowerCase().contains("ltd.")) {
                break;
            }
            name += " " + lineTokens[a];
        }
        return name;
    }


    /**
     * Find the work time period of a particular company
     *
     * @param line1
     * @param line2
     */
    public void findDuration(String line1, String line2, Work work) {

        String classifierText = "";
        String classifierText2 = "";

        classifierText = classifier.classifyWithInlineXML(line1);
        classifierText2 = classifier.classifyWithInlineXML(line2);
        if (classifierText.contains("<DATE>")) {
            int x = 0;
            Pattern pattern = Pattern.compile("<DATE>(.*?)</DATE>");
            Matcher matcher = pattern.matcher(classifierText);
            while (matcher.find()) {
                if (x == 0) {
                    LOG.info("From:" + matcher.group(1));
                    work.setFrom(matcher.group(1));
                    x++;
                } else {
                    LOG.info("To:" + matcher.group(1));
                    work.setTo(matcher.group(1));
                }
            }
        } else if (classifierText2.contains("<DATE>")) {
            int x = 0;
            Pattern pattern = Pattern.compile("<DATE>(.*?)</DATE>");
            Matcher matcher = pattern.matcher(classifierText2);
            while (matcher.find()) {
                if (x == 0) {
                    LOG.info("From:" + matcher.group(1));
                    work.setFrom(matcher.group(1));
                    x++;
                } else {
                    LOG.info("To:" + matcher.group(1));
                    work.setTo(matcher.group(1));
                }
            }
        }
    }


    /**
     * Load the Gazeteer list
     *
     * @param path
     * @param list
     */
    public void populateByFile(String path, ArrayList<String> list) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();
            while (line != null) {
                list.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
