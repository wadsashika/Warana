package com.cse.warana.utility.infoExtractors;

import com.cse.warana.utility.infoHolders.Work;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nadeeshaan on 11/7/2014.
 */
public class WorkInfoExtract {

    public static AbstractSequenceClassifier<CoreLabel> classifier = null;
    public static ArrayList<String> companies = new ArrayList<String>();
    public static ArrayList<String> jobPositions = new ArrayList<String>();
    private static Logger LOG = LoggerFactory.getLogger(AchievementsInfoExtract.class);
    private String listPath = "";

    /**
     * Constructor Method
     *
     * @param clf
     */
    public WorkInfoExtract(AbstractSequenceClassifier<CoreLabel> clf, HashMap<String, String> paths) {
        this.classifier = clf;
        /**
         * Load the companies gazeteer list
         */
        listPath = paths.get("root") + paths.get("listPath");
        populateByFile(listPath + File.separator+"companyNames", companies);
        populateByFile(listPath + File.separator+"jobPositionsIndex", jobPositions);
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

                    if (foundCompany && work != null) {
                        worksList.add(work);
                        foundCompany = false;
                    }

                    /**
                     * When the company name is mentioned without "at" before the company name
                     * These are identified by checking for the company descriptors such as [(Pvt) Ltd, Inc, Corp, etc..]
                     * or Looking at the gazeteer list for the companies
                     */

                    // Check in the Companies Gazeteer list
                    for (int x = 0; x < companies.size(); x++) {
                        Pattern pattern = Pattern.compile(".*" + companies.get(x) + ".*");
                        Matcher matcher = pattern.matcher(lineText.toLowerCase());

                        if (matcher.matches()) {
                            work = new Work();
                            companyName = companies.get(x);
                            LOG.info(companyName);

                            String jobPosition = "";
                            String currentLine = lineText;
                            jobPosition = checkJobPositions(b, currentLine, lines);
                            if (jobPosition != null) {
                                work.setCompanyName(companyName);
                                work.setPosition(jobPosition);
                                findDuration(lineText, lines.get(b + 1), work);
                                LOG.info(companyName);
                                linesCopy.remove(lineText);
                                foundCompany = true;
                            }
                        }
                    }

                    if (!foundCompany) {
                        String classifierText = "";
                        classifierText = classifier.classifyWithInlineXML(lineText);

                        if (classifierText.contains("<ORGANIZATION>")) {
                            work = new Work();
                            Pattern pattern = Pattern.compile("<ORGANIZATION>(.*?)</ORGANIZATION>");
                            Matcher matcher = pattern.matcher(classifierText);
                            while (matcher.find()) {
                                String jobPosition = "";
                                String currentLine = classifierText.replaceAll("((<ORGANIZATION>)[^&]*(</ORGANIZATION>))|((<DATE>)[^&]*(</DATE>))", "");
                                companyName = matcher.group(0).replace("<ORGANIZATION>", "").replace("</ORGANIZATION>", "");
                                jobPosition = checkJobPositions(b, currentLine, lines);
                                if (jobPosition != null) {
                                    work.setCompanyName(companyName);
                                    work.setPosition(jobPosition);
                                    LOG.info(companyName);
                                    foundCompany = true;
                                }
                                break;
                            }
                        }
                    }

                    /**
                     * TODO need to include the other company descriptors
                     * Check for the common properties such as
                     * (pvt) Ltd, Inc., etc
                     */
//                    Pattern pattern = Pattern.compile("(.*[(]pvt[)] ltd.*)|(.*inc[.].*)");
//                    Matcher matcher = pattern.matcher(lineText.toLowerCase());
//
//                    if (!foundCompany && matcher.matches()) {
//                        LOG.info("");
//                        LOG.info("found");
//                        LOG.info(lineText);
//                        if (work == null) {
//                            work = new Work();
//                        }
//                        findDuration(lineText, lines.get(b + 1), work);
//
//                        linesCopy.remove(lineText);
//                    }

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

    /**
     * Chek for the job Position of the candidate in a certain company
     *
     * @param lineNum
     */
    public static String checkJobPositions(int lineNum, String currentLine, ArrayList<String> lines) {
        String jobPosition = null;
        String patternString = ".*(";

        for (int a = 0; a < jobPositions.size(); a++) {

            if (a == jobPositions.size() - 1) {
                patternString += jobPositions.get(a) + ").*";
            } else {
                patternString += jobPositions.get(a) + "|";
            }
        }

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher1 = pattern.matcher(currentLine.toLowerCase());
        Matcher matcher2 = pattern.matcher(lines.get(lineNum - 1).toLowerCase());
        Matcher matcher3 = pattern.matcher(lines.get(lineNum + 1).toLowerCase());
        Matcher matcher4 = pattern.matcher(lines.get(lineNum - 2).toLowerCase());
        Matcher matcher5 = pattern.matcher(lines.get(lineNum + 2).toLowerCase());
        if (matcher1.find()) {
            jobPosition = currentLine;
        } else if (matcher2.find()) {
            jobPosition = lines.get(lineNum - 1);
        } else if (matcher3.find()) {
            jobPosition = lines.get(lineNum + 1);
        }else if (matcher4.find()) {
            jobPosition = lines.get(lineNum - 2);
        }else if (matcher5.find()) {
            jobPosition = lines.get(lineNum + 2);
        }

        return jobPosition;
    }


//    public static void main(String[] args){
//        populateByFile("C:\\Warana\\gazeteerLists" + "\\jobPositionsIndex", jobPositions);
//        ArrayList<String> s = new ArrayList();
//        s.add("Hello Dear");
//        s.add("Associate Tech lead");
//        s.add("Senior Software Engineer");
//        System.out.println(checkJobPositions(1, "Associate Tech lead", s));
//    }
}
