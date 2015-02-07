package com.cse.warana.service.impl;

import com.cse.warana.service.CVParserService;
import com.cse.warana.utility.infoExtractors.*;
import com.cse.warana.utility.infoHolders.*;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Nadeeshaan on 11/7/2014.
 */

@Service("cvParser")
public class CVParserServiceImpl implements CVParserService {

    /**
     * Initialize the global variables
     */
    private static ArrayList<String> EducationalHeadings;
    private static ArrayList<String> ProfileHeadings;
    private static ArrayList<String> WorkHistoryHeadings;
    private static ArrayList<String> AwardsAndAchievementsHeadings;
    private static ArrayList<String> ProjectsHeadings;
    private static ArrayList<String> lines;
    private static ArrayList<String> linesCopy;
    private static ArrayList<String> indexedLines;
    private static ArrayList<String> newTechnologies;

    private static String[] docLines;
    private static HashMap<String, ArrayList<Integer>> sectionMap;

    private static AbstractSequenceClassifier<CoreLabel> classifier;

    Map<String, String> pathsMap = null;

    private static Logger LOG;

    /**
     * Constructor
     */
    public CVParserServiceImpl() {

    }

    public void setPaths(Map<String, String> paths) {

        EducationalHeadings = new ArrayList<String>();
        ProfileHeadings = new ArrayList<String>();
        WorkHistoryHeadings = new ArrayList<String>();
        AwardsAndAchievementsHeadings = new ArrayList<String>();
        ProjectsHeadings = new ArrayList<String>();
        lines = new ArrayList<String>();
        linesCopy = new ArrayList<String>();
        indexedLines = new ArrayList<String>();

        docLines = null;
        sectionMap = new HashMap<String, ArrayList<Integer>>();

        classifier = null;

        LOG = LoggerFactory.getLogger(CVParserServiceImpl.class);

        pathsMap = paths;

        /**
         * Load the 7 class classifier
         * Finds Time, Location, Organization, Person, Money, Percent, Date
         */
        String serializedClassifier = pathsMap.get("root") + pathsMap.get("classifirePath");
        try {
            classifier = CRFClassifier.getClassifier(serializedClassifier);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * In this method, tokens are loaded to the memory(ArrayList)
     * These tokens are used to identify the possible heading lines
     */
    @Override
    public void initializeHeadingTokens() {

        /**
         * TODO INITIALIZE THE ABOVE HEADING LISTS
         */
        String token = "";
        try {
            BufferedReader EduBr = new BufferedReader(new FileReader(pathsMap.get("root") + pathsMap.get("listPath") + File.separator + "eduTokens"));
            BufferedReader ProfsBr = new BufferedReader(new FileReader(pathsMap.get("root") + pathsMap.get("listPath") + File.separator + "profTokens"));
            BufferedReader WrkBr = new BufferedReader(new FileReader(pathsMap.get("root") + pathsMap.get("listPath") + File.separator + "wrkTokens"));
            BufferedReader AwrdBr = new BufferedReader(new FileReader(pathsMap.get("root") + pathsMap.get("listPath") + File.separator + "awardsTokens"));
            BufferedReader ProjBr = new BufferedReader(new FileReader(pathsMap.get("root") + pathsMap.get("listPath") + File.separator + "projTokens"));

            token = EduBr.readLine();

            while (!token.equals("end")) {
                EducationalHeadings.add(token);
                token = EduBr.readLine();
            }

            token = ProfsBr.readLine();

            while (!token.equals("end")) {
                ProfileHeadings.add(token);
                token = ProfsBr.readLine();
            }

            token = WrkBr.readLine();

            while (!token.equals("end")) {
                WorkHistoryHeadings.add(token);
                token = WrkBr.readLine();
            }

            token = AwrdBr.readLine();

            while (!token.equals("end")) {
                AwardsAndAchievementsHeadings.add(token);
                token = AwrdBr.readLine();
            }

            token = ProjBr.readLine();

            while (!token.equals("end")) {
                ProjectsHeadings.add(token);
                token = ProjBr.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method is used to identify the sections based on the pre defined tokens
     * specific to the sections
     */
    @Override
    public void identifyHeadings() {
        String line = "";
        boolean allFalse = false;
        Pattern pattern = null;
        Matcher matcher = null;
        boolean refereesFound = false;

        for (int a = 0; a < lines.size(); a++) {

            line = lines.get(a);
            if (!line.equals("") && !Character.isLetter(line.charAt(line.length()-1))){
                line = line.replace(line.charAt(line.length()-1),' ');
                line = line.trim();
            }

            // Assume that a heading cannot be larger than three words
            /**
             * TODO need to remove the stop words before the processing
             */
            if (line.split(" ").length <= 5) {
                for (int ctr = 0; ctr < EducationalHeadings.size(); ctr++) {

                    // Section is named under EDU_INF
                    pattern = Pattern.compile(".*" + EducationalHeadings.get(ctr) + ".*");
                    matcher = pattern.matcher(line.toLowerCase());
                    if (!refereesFound && matcher.matches() && (line.charAt(0) + "").equals((line.charAt(0) + "").toUpperCase())) {
                        if (sectionMap.containsKey("EDU_INFO")) {
                            if (!(sectionMap.get("EDU_INFO")).contains(new Integer(a))) {
                                (sectionMap.get("EDU_INFO")).add(new Integer(a));
                                linesCopy.remove(line);
                            }
                        } else {
                            ArrayList<Integer> l = new ArrayList<Integer>();
                            l.add(new Integer(a));
                            sectionMap.put("EDU_INFO", l);
                            linesCopy.remove(line);
                        }
                        indexedLines.add(String.valueOf(a));

                        LOG.info(line + "-----Matched to EDUCATIONAL INFO");
                    }
                }

                for (int ctr = 0; ctr < ProfileHeadings.size(); ctr++) {

                    // Section is named under PROF_INF
                    pattern = Pattern.compile(".*" + ProfileHeadings.get(ctr) + ".*");
                    matcher = pattern.matcher(line.toLowerCase());

                    if (!refereesFound && matcher.matches() && (line.charAt(0) + "").equals((line.charAt(0) + "").toUpperCase())) {
                        if (sectionMap.containsKey("PROF_INFO")) {
                            if (!(sectionMap.get("PROF_INFO")).contains(new Integer(a))) {
                                (sectionMap.get("PROF_INFO")).add(new Integer(a));
                                linesCopy.remove(line);
                            }
                        } else {
                            ArrayList<Integer> l = new ArrayList<Integer>();
                            l.add(new Integer(a));
                            sectionMap.put("PROF_INFO", l);
                            linesCopy.remove(line);
                        }
                        indexedLines.add(String.valueOf(a));

                        LOG.info(line + "-----Matched to PROFILE INFO");
                    }
                }

                for (int ctr = 0; ctr < WorkHistoryHeadings.size(); ctr++) {
                    pattern = Pattern.compile(".*" + WorkHistoryHeadings.get(ctr) + ".*");
                    matcher = pattern.matcher(line.toLowerCase());

                    if (!refereesFound && matcher.matches() && (line.charAt(0) + "").equals((line.charAt(0) + "").toUpperCase())/* && !sectionMap.containsKey("EDU_INFO")*/) {
                        if (sectionMap.containsKey("WRK_INFO")) {
                            if (!(sectionMap.get("WRK_INFO")).contains(new Integer(a))) {
                                (sectionMap.get("WRK_INFO")).add(new Integer(a));
                                linesCopy.remove(line);
                            }
                        } else {
                            ArrayList<Integer> l = new ArrayList<Integer>();
                            l.add(new Integer(a));
                            sectionMap.put("WRK_INFO", l);
                            linesCopy.remove(line);
                        }
                        indexedLines.add(String.valueOf(a));

                        LOG.info(line + "-----Matched to Work INFO");
                    }
                }

                for (int ctr = 0; ctr < AwardsAndAchievementsHeadings.size(); ctr++) {
                    pattern = Pattern.compile(".*" + AwardsAndAchievementsHeadings.get(ctr) + ".*");
                    matcher = pattern.matcher(line.toLowerCase());

                    if (!refereesFound && matcher.matches() && (line.charAt(0) + "").equals((line.charAt(0) + "").toUpperCase())/* && !sectionMap.containsKey("EDU_INFO")*/) {
                        if (sectionMap.containsKey("AWRD_INFO")) {
                            if (!(sectionMap.get("AWRD_INFO")).contains(new Integer(a))) {
                                (sectionMap.get("AWRD_INFO")).add(new Integer(a));
                                linesCopy.remove(line);
                            }
                        } else {
                            ArrayList<Integer> l = new ArrayList<Integer>();
                            l.add(new Integer(a));
                            sectionMap.put("AWRD_INFO", l);
                            linesCopy.remove(line);
                        }
                        indexedLines.add(String.valueOf(a));

                        LOG.info(line + "-----Matched to AWARDS INFO");
                    }
                }

                for (int ctr = 0; ctr < ProjectsHeadings.size(); ctr++) {
                    pattern = Pattern.compile(".*" + ProjectsHeadings.get(ctr) + ".*");
                    matcher = pattern.matcher(line.toLowerCase());

                    if (!refereesFound && matcher.matches() && (line.charAt(0) + "").equals((line.charAt(0) + "").toUpperCase()) && Character.isLetter(line.charAt(0))/* && !sectionMap.containsKey("EDU_INFO")*/) {
                        if (sectionMap.containsKey("PROJ_INFO")) {
                            if (!(sectionMap.get("PROJ_INFO")).contains(new Integer(a))) {
                                (sectionMap.get("PROJ_INFO")).add(new Integer(a));
                                linesCopy.remove(line);
                            }
                        } else {
                            ArrayList<Integer> l = new ArrayList<Integer>();
                            l.add(new Integer(a));
                            sectionMap.put("PROJ_INFO", l);
                            linesCopy.remove(line);
                        }
                        indexedLines.add(String.valueOf(a));

                        LOG.info(line + "-----Matched to PROJECTS INFO");
                    }
                }

                /**
                 * Check for the Special interests of the candidate
                 */
                pattern = Pattern.compile(".*interests.*");
                matcher = pattern.matcher(line.toLowerCase());

                if (matcher.matches() && (line.charAt(0) + "").equals((line.charAt(0) + "").toUpperCase()) && !refereesFound) {
                    if (sectionMap.containsKey("INTERESTS_INFO")) {
                        if (!(sectionMap.get("INTERESTS_INFO")).contains(new Integer(a))) {
                            (sectionMap.get("INTERESTS_INFO")).add(new Integer(a));
                            linesCopy.remove(line);
                        }
                    } else {
                        ArrayList<Integer> l = new ArrayList<Integer>();
                        l.add(new Integer(a));
                        sectionMap.put("INTERESTS_INFO", l);
                        linesCopy.remove(line);
                    }
                    indexedLines.add(String.valueOf(a));

                    LOG.info(line + "-----Matched to INTERESTS INFO");
                }
            }

            pattern = Pattern.compile("(.*referee.*|.*reference.*)");
            matcher = pattern.matcher(line.toLowerCase());

            if (line.split(" ").length <= 5 && matcher.matches() && (line.charAt(0) + "").equals((line.charAt(0) + "").toUpperCase())) {
                if (sectionMap.containsKey("REF_INFO")) {
                    if (!(sectionMap.get("REF_INFO")).contains(new Integer(a))) {
                        (sectionMap.get("REF_INFO")).add(new Integer(a));
                        linesCopy.remove(line);
                        refereesFound = true;
                    }
                } else {
                    ArrayList<Integer> l = new ArrayList<Integer>();
                    l.add(new Integer(a));
                    sectionMap.put("REF_INFO", l);
                    linesCopy.remove(line);
                    refereesFound = true;
                }
                indexedLines.add(String.valueOf(a));

                LOG.info(line + "-----Matched to REFEREE INFO");
            }
        }
    }


    /**
     * This method is used to classify the lines under the headings and extract the
     * required information
     */
    @Override
    public void parseLines(HashMap<String, Object> infoCategoryTypes) {
        EducationalInfoExtract eduInfo = new EducationalInfoExtract(pathsMap);
        PersonalInfoExtract perInfo = new PersonalInfoExtract(pathsMap);
        WorkInfoExtract wrkInfo = new WorkInfoExtract(classifier, pathsMap);
        AchievementsInfoExtract achInfo = new AchievementsInfoExtract(pathsMap);
        ProjectInfoExtraction projInfo = new ProjectInfoExtraction(pathsMap);
        RefereeInfoExtract refInfo = new RefereeInfoExtract(classifier);
        FindMissedInfo missedInfo = new FindMissedInfo();
        InterestsInfoExtract interestsInfo = new InterestsInfoExtract();
        ArrayList<String> candidateTechnologies = new ArrayList<String>();

        String previous = "";
        Iterator it = sectionMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            LOG.info(pairs.getKey() + " = " + pairs.getValue());
            if (pairs.getKey().equals("EDU_INFO")) {
                eduInfo.extractEduInformation(lines, (ArrayList<Integer>) pairs.getValue(), indexedLines, linesCopy, (ArrayList<Education>) infoCategoryTypes.get("EDUCATION_LIST"));
            } else if (pairs.getKey().equals("PROF_INFO")) {
                perInfo.extractPersonalInformation(lines, (ArrayList<Integer>) pairs.getValue(), indexedLines, linesCopy, (Profile) infoCategoryTypes.get("PROFILE"));
            } else if (pairs.getKey().equals("WRK_INFO")) {
                wrkInfo.extractWorkInfo(lines, (ArrayList<Integer>) pairs.getValue(), indexedLines, linesCopy, (ArrayList<Work>) infoCategoryTypes.get("WORK_LIST"));
            } else if (pairs.getKey().equals("AWRD_INFO")) {
                achInfo.extractAchievementInformation(lines, (ArrayList<Integer>) pairs.getValue(), indexedLines, linesCopy, (ArrayList<Achievement>) infoCategoryTypes.get("ACHIEVEMENTS_LIST"));
            } else if (pairs.getKey().equals("PROJ_INFO")) {
                projInfo.extractProjectInfo(lines, (ArrayList<Integer>) pairs.getValue(), indexedLines, linesCopy, candidateTechnologies, (ArrayList<Project>) infoCategoryTypes.get("PROJECTS_LIST"), (ArrayList<Technology>) infoCategoryTypes.get("TECHNOLOGIES_LIST"));
            } else if (pairs.getKey().equals("REF_INFO")) {
                refInfo.getRefereeInfo(lines, (ArrayList<Integer>) pairs.getValue(), indexedLines, linesCopy, (ArrayList<Referee>) infoCategoryTypes.get("REFEREE_LIST"));
            } else if (pairs.getKey().equals("INTERESTS_INFO")) {
                interestsInfo.extractInterestsInformation(lines, (ArrayList<Integer>) pairs.getValue(), indexedLines, linesCopy);
            }
        }
        missedInfo.findProfileInfo(linesCopy, (Profile) infoCategoryTypes.get("PROFILE"));

        newTechnologies = projInfo.getNewTechnologies();
        /**
         * String technologies is used to store the technologies of the candidate as a comma separated string
         */
        String technologies = "";
        for (int a = 0; a < candidateTechnologies.size(); a++) {
            technologies += candidateTechnologies.get(a) + ",";
        }
    }


    /**
     * Read the pdf
     *
     * @param file
     */
    @Override
    public void readPdfDocument(File file) {
        PDFParser parser = null;
        String text = "";
        PDFTextStripper stripper = null;
        PDDocument pdoc = null;
        COSDocument cdoc = null;
        try {
            parser = new PDFParser(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            parser.parse();
            cdoc = parser.getDocument();
            stripper = new PDFTextStripper();
            pdoc = new PDDocument(cdoc);
            stripper.setStartPage(1);
            stripper.setEndPage(pdoc.getNumberOfPages());
            text = stripper.getText(pdoc);

            docLines = text.split("(\r\n)");

            for (int a = 0; a < docLines.length; a++) {
                String s = docLines[a];
                if (!s.equals(" ")) {
                    s = s.replaceAll("[^\\w\\s\\@\\_\\.\\,\\(\\)\\:\\!\\#\\$\\%\\&\\*\\+\\-\\=\\/\\\\]", "");
                    lines.add(s.trim());
                    // Keep a copy of the lines in order to remove them and keep track of the missed lines
                    // to extract information form them during the missed info extraction phase
                    linesCopy.add(s.trim());
                }
            }

            for (int a = 0; a < lines.size(); a++) {
                LOG.info(lines.get(a));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                cdoc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String test() {
        return "Hello There";
    }

    @Override
    public ArrayList<String> getNewTechnologies() {
        return newTechnologies;
    }
}
