package com.cse.warana.utility.infoExtractors;

import com.cse.warana.utility.infoHolders.Project;
import com.cse.warana.utility.infoHolders.Technology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nadeeshaan on 11/7/2014.
 */
public class ProjectInfoExtraction {

    public static ArrayList<String> technologies = new ArrayList<String>();
    public static ArrayList<String> techDescriptions = new ArrayList<String>();
    private static ArrayList<String> newTechnologies = new ArrayList<String>();

    private static Logger LOG = LoggerFactory.getLogger(ProjectInfoExtraction.class);
    private String listpath = "";
    private String tecRegex = "(.*?)(";

    public ProjectInfoExtraction(Map<String, String> paths) {
        listpath = paths.get("root") + paths.get("listPath");
        populateByFile(listpath + File.separator + "techDescriptionTokens", techDescriptions);

        for (int a = 0; a < techDescriptions.size(); a++) {
            if (a == techDescriptions.size() - 1) {
                tecRegex += techDescriptions.get(a);
            } else {
                tecRegex += techDescriptions.get(a) + "|";
            }
        }
        tecRegex += ")(.*?)";
    }

    /**
     * Extract the project information
     *
     * @param lines
     * @param headingLines
     * @param allHeadings
     * @param linesCopy
     * @throws java.io.IOException
     */
    public void extractProjectInfo(ArrayList<String> lines, ArrayList<Integer> headingLines, ArrayList<String> allHeadings, ArrayList<String> linesCopy, ArrayList<String> candidateTechnologies, ArrayList<Project> projects, ArrayList<Technology> techs) {
        /**
         * TODO NEED TO CHECK FOR THE TECHNOLOGIES
         * using the selected word phrase matching
         * mobile application
         * android application, management system, open source contribution, etc..
         */

        BufferedReader br = null;
        String biWrod = "";
        try {
            br = new BufferedReader(new FileReader(listpath + File.separator + "projBiwordIndex"));
            biWrod = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String lineText = "";
        String[] indexTokens = null;

        ArrayList<String> biWordList = new ArrayList<String>();

        int wordMatchCounter = 0;
        int startProjectLine = -1;
        int endProjectLine = -1;
        boolean technologyFound = false;

        StringTokenizer tokenizer = null;

        Pattern pattern = null;
        Matcher matcher = null;
        Project project = null;
        populateByFile(listpath + File.separator + "technologies", technologies);


        /**
         * Heuristic is:
         * People usually include the project related technologies after or within the project description and
         * the project description is always included after the name of the project
         */
        LOG.info("----Beginning Project Information----");
        for (int a = 0; a < headingLines.size(); a++) {
            startProjectLine = -1;
            endProjectLine = -1;
            for (int b = (headingLines.get(a).intValue() + 1); b < lines.size(); b++) {
                lineText = lines.get(b);
                if (allHeadings.contains(String.valueOf(b))) {
                    if (project != null && technologyFound) {
                        technologyFound = false;
                        String description = "";
                        project.setName(lines.get(startProjectLine));
                        for (int x = startProjectLine+1; x <= endProjectLine; x++) {
                            tokenizer = new StringTokenizer(lines.get(x), " ");
                            if (tokenizer.countTokens() > 2) {
                                description += lines.get(x);
                                LOG.info(lines.get(x));
                            }
                        }
//                        for (int x = startProjectLine; x < endProjectLine; x++) {
//                            tokenizer = new StringTokenizer(lines.get(x), " ");
//                            if (tokenizer.countTokens() > 2) {
//                                if (x == startProjectLine) {
//                                    project.setName(lines.get(x));
//                                } else {
//                                    description += lines.get(x);
//                                }
//                                LOG.info(lines.get(x));
//                            }
//                        }
                        project.setDescription(description);
                        if (project.getName() != null) {
                            projects.add(project);
                            project = null;
                        }

                        startProjectLine = -1;
                    }

                    break;
                } else {

                    // Set the start of the set of lines which is used to describe the project
                    pattern = Pattern.compile(".*(http|ftp|https):\\/\\/([\\w\\-_]+(?:(?:\\.[\\w\\-_]+)+))([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?.*");
                    matcher = pattern.matcher(lineText);

                    if (matcher.matches()) {
                        if (project == null && projects.size() > 0) {
                            projects.get(projects.size() - 1).setUrl(matcher.group(0));
                        } else if (project != null) {
                            project.setUrl(matcher.group(0));
                        }
                    } else {
                        if (startProjectLine == -1) {
                            startProjectLine = b;
                            project = new Project();
                        }
                        if (checkForTechnologiesDescription(lineText, candidateTechnologies, techs, lines)) {
                            technologyFound = true;
                        } else if (technologyFound) {
                            technologyFound = false;
                            String description = "";
                            project.setName(lines.get(startProjectLine));
                            for (int x = startProjectLine+1; x <= endProjectLine; x++) {
                                tokenizer = new StringTokenizer(lines.get(x), " ");
                                if (tokenizer.countTokens() > 2) {
                                    description += lines.get(x);
                                    LOG.info(lines.get(x));
                                }
                            }
                            project.setDescription(description);
                            if (project.getName() != null) {
                                projects.add(project);
                                project = null;
                            }

                            startProjectLine = -1;
                            b -= 1;
                        } else {
                            endProjectLine = b;
                        }
                    }
                }
            }
        }
        LOG.info("----Ending Project Information----\n");
    }


    /**
     * TODO check what if the technologies are included in the next line also as an example
     * Technologies :
     * C#, Java, etc..
     *
     * @param lineText
     * @return
     */
    public boolean checkForTechnologiesDescription(String lineText, ArrayList<String> candidateTechnologies, ArrayList<Technology> techsList, ArrayList<String> lines) {
        String tempText = lineText;
        String tempText2 = lineText;
        String technologyVal = "";
        String temp = "";
        String[] tokens = null;
        StringTokenizer tokenizer = null;

        /**
         * Check whether the line contains the word technologies or technology
         * In order to identify the line which describes the technologies
         */

        /**
         * Newly added
         */

        /**
         * Pattern,
         * Technologies: ....,.....,....,...
         * Technologies: (....,.....,....,...)
         */
        String[] techTokens = tempText.split("(:|-|,)");
        if (tempText.toLowerCase().matches(tecRegex) && techTokens.length >= 2) {
            techTokens[1] = techTokens[1].replaceAll("(\\(|\\)|\\[|\\]|\\{|\\})", "");
            String[] technologiesArray = techTokens[1].split(",");

            for (int x = 0; x < technologiesArray.length; x++) {
                technologyVal = technologiesArray[x].toLowerCase().trim();
                LOG.info("************" + technologyVal);
                if (!candidateTechnologies.contains(technologyVal)) {
                    candidateTechnologies.add(technologyVal);
                    Technology technology = new Technology();
                    technology.setName(technologyVal);
                    techsList.add(technology);
                }
                if (!technologies.contains(technologyVal)) {
                    // Write to the technologies file after lowering the case
                    // Also added to the technologies in order to avoid the duplicate entries entering the file.
                    technologies.add(technologyVal);
                    newTechnologies.add(technologyVal);
                }
            }

            return true;
        }

//        if (lines.get(lines.indexOf(lineText)).matches("(.*?)(\\()") && lines.get(lines.indexOf(lineText) + 1).matches("(.*?)(\\))(.*?)")){
//            String combinedLine = lineText + lines.get(lines.indexOf(lineText) + 1);
//            Pattern pattern = Pattern.compile("(\\(|\\[|\\{)(.*?)(\\)|\\]|\\})");
//            Matcher matcher = pattern.matcher(combinedLine);
//
//            String[] combinedTokenArr = (matcher.group(1)).split(",");
//            for (int y = 0; y < combinedTokenArr.length; y++) {
//                technologyVal = combinedTokenArr[y].toLowerCase().trim();
//                if (!technologies.contains(technologyVal)) {
//                    // Write to the technologies file after lowering the case
//                    // Also added to the technologies in order to avoid the duplicate entries entering the file.
//                    technologies.add(combinedTokenArr[y].toLowerCase().trim());
//                    newTechnologies.add(combinedTokenArr[y].toLowerCase().trim());
//                }
//                if (!candidateTechnologies.contains(technologyVal)) {
//                    candidateTechnologies.add(technologyVal);
//                }
//                LOG.info("**************" + technologyVal);
//            }
//            return true;
//        }

//        if (tempText.toLowerCase().contains("technologies") || tempText.toLowerCase().contains("technology")) {
//
//            String[] technologyArr = tempText.substring(tempText.toLowerCase().indexOf("technologies") + 12).split(",");
//            boolean margin = false;
//
//            if (technologyArr.length > 0) {
//                for (int a = 0; a < technologyArr.length; a++) {
//                    technologyArr[a] = technologyArr[a].replaceAll("[:]", "");
//                    technologyVal = technologyArr[a].toLowerCase().trim();
//                    LOG.info("************" + technologyVal);
//                    if (!candidateTechnologies.contains(technologyVal)) {
//                        candidateTechnologies.add(technologyVal);
//                        Technology technology = new Technology();
//                        technology.setName(technologyVal);
//                        techsList.add(technology);
//                    }
//                    if (!technologies.contains(technologyVal)) {
//                        // Write to the technologies file after lowering the case
//                        // Also added to the technologies in order to avoid the duplicate entries entering the file.
//                        technologies.add(technologyArr[a].toLowerCase().trim());
//                        newTechnologies.add(technologyArr[a].toLowerCase().trim());
//                    }
//                }
//                return true;
//            }
//            return false;
//        }

        /**
         * If the technologies are included within brackets and without above two checks satisfying. As an example
         * module Programming Challenge 2 (Java swing ,Joomla CMS)
         */
        Pattern pattern = Pattern.compile("(.*\\(|\\[|\\{)(.*?)(\\)|\\]|\\}.*)");
        Matcher matcher = pattern.matcher(tempText2);
        if (matcher.matches()) {
            String[] tokenArr = (matcher.group(2)).split(",");
            if (tokenArr.length > 0) {
                for (int x = 0; x < tokenArr.length; x++) {
                    if (technologies.contains(tokenArr[x].toLowerCase().trim())) {
                        for (int y = 0; y < tokenArr.length; y++) {
                            technologyVal = tokenArr[y].toLowerCase().trim();
                            if (!technologies.contains(technologyVal)) {
                                // Write to the technologies file after lowering the case
                                // Also added to the technologies in order to avoid the duplicate entries entering the file.
                                technologies.add(tokenArr[y].toLowerCase().trim());
                                newTechnologies.add(tokenArr[y].toLowerCase().trim());
                            }
                            if (!candidateTechnologies.contains(technologyVal)) {
                                candidateTechnologies.add(technologyVal);
                            }
                            LOG.info("**************" + technologyVal);
                        }
                        return true;
                    }
                }
            }
        }

        // Check the lines which are usually included technologies in the last line
        tokens = tempText2.split(",");
        if (tokens.length >= 2) {
            for (int x = 0; x < tokens.length; x++) {
                if (tokens[x].split(" ").length > 3){
                    return false;
                }
                if (technologies.contains(tokens[x].toLowerCase().trim())) {
                    for (int y = 0; y < tokens.length; y++) {
                        if (!technologies.contains(tokens[y].toLowerCase().trim())) {
                            // Write to the technologies file after lowering the case
                            // Also added to the technologies in order to avoid the duplicate entries entering the file.
                            technologies.add(tokens[y].toLowerCase().trim());
                            newTechnologies.add(tokens[y].toLowerCase().trim());
                            candidateTechnologies.add(technologyVal);
                        }
                        LOG.info("**************" + tokens[y].toLowerCase().trim());
                    }
                    return true;
                }
//                if (x == tokens.length - 1 && (tokens[x].charAt(tokens[x].length() - 1) + "").matches("")) {
//                    tokens[x] = (tokens[x].charAt(tokens[x].length() - 1) + "").replaceAll("\\)", "");
//                }
//                technologies.add(tokens[x].toLowerCase().trim());
//                newTechnologies.add(tokens[x].toLowerCase().trim());
//                candidateTechnologies.add(tokens[x].toLowerCase().trim());
            }
            return true;
        }
        return false;
    }


    /**
     * Read the Gazeteer List from the file and load it to the memory
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
     * Return the newly found technologies
     */
    public static ArrayList<String> getNewTechnologies() {
        return newTechnologies;
    }
}
