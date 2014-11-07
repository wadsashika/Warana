package com.cse.warana.service.impl.cvparserImpl.infoExtractorsImpl;

import com.cse.warana.service.cvparser.infoExtractors.WorkInfoExtract;

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
public class WorkInfoExtractImpl implements WorkInfoExtract {


    public static AbstractSequenceClassifier<CoreLabel> classifier = null;
    public static ArrayList<String> companies = new ArrayList<String>();

    /**
     * Constructor Method
     *
     * @param clf
     */
    public WorkInfoExtractImpl(AbstractSequenceClassifier<CoreLabel> clf) {
        this.classifier = clf;
        /**
         * Load the companies gazeteer list
         */
        populateByFile("input/companyNames", companies);
    }


    @Override
    public void extractWorkInfo(ArrayList<String> lines, ArrayList<Integer> headingLines, ArrayList<String> allHeadings, ArrayList<String> linesCopy) {

        String lineText = "";
        boolean foundCompany = false;

        System.out.println("----Beginning Work Information----");
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
                    if (lineText.contains("at")) {

                        String classifierText = "";
                        classifierText = classifier.classifyWithInlineXML(lineText);

                        if (classifierText.contains("<ORGANIZATION>")) {
                            Pattern pattern = Pattern.compile("<ORGANIZATION>(.*?)</ORGANIZATION>");
                            Matcher matcher = pattern.matcher(classifierText);
                            while (matcher.find()) {
                                System.out.println(matcher.group(1));
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
                                System.out.println(findFullCompanyName(tokens, x));
                                findDuration(lineText, lines.get(b + 1));
                                System.out.println("Found");
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
                        System.out.println();
                        System.out.println("found");
                        linesCopy.remove(lineText);
                    }

                    /**
                     * Check for other properties
                     */
                }
            }
        }
        System.out.println("----Ending Work Information----\n");
    }


    @Override
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


    @Override
    public void findDuration(String line1, String line2) {

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
                    System.out.println("From:" + matcher.group(1));
                    x++;
                } else {
                    System.out.println("To:" + matcher.group(1));
                }
            }
        } else if (classifierText2.contains("<DATE>")) {
            int x = 0;
            Pattern pattern = Pattern.compile("<DATE>(.*?)</DATE>");
            Matcher matcher = pattern.matcher(classifierText2);
            while (matcher.find()) {
                if (x == 0) {
                    System.out.println("From:" + matcher.group(1));
                    x++;
                } else {
                    System.out.println("To:" + matcher.group(1));
                }
            }
        }
    }

    
    @Override
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
