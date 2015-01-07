package com.cse.warana.utility.infoExtractors;

import com.cse.warana.utility.infoHolders.Education;
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
public class EducationalInfoExtract {

    private static Logger LOG = LoggerFactory.getLogger(EducationalInfoExtract.class);
    private String listpath = "";

    public EducationalInfoExtract(HashMap<String,String> paths){
        listpath = paths.get("root") + paths.get("listPath");
    }

    /**
     * Extract educational information
     *
     * TODO find the specialized area, duration
     *
     * @param lines
     * @param headingLines
     * @param allHeadings
     * @param linesCopy
     */

    public void extractEduInformation(ArrayList<String> lines, ArrayList<Integer> headingLines, ArrayList<String> allHeadings, ArrayList<String> linesCopy, ArrayList<Education> educations) {

        BufferedReader br = null;
        String indexWord = "";
        try {
            br = new BufferedReader(new FileReader(listpath + File.separator + "eduIndex"));
            indexWord = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> indexList = new ArrayList<String>();
        Pattern pattern = null;
        Matcher matcher = null;
        String lineText = "";

        /**
         * Read the eduIndex Gazeteer
         */
        while (!indexWord.equals("end")) {
            indexList.add(indexWord);
            try {
                indexWord = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        LOG.info("----Beginning Educational Information----");
        for (int a = 0; a < headingLines.size(); a++) {
            for (int b = (headingLines.get(a).intValue() + 1); b < lines.size(); b++) {
                lineText = lines.get(b);
                if (allHeadings.contains(String.valueOf(b))) {
                    break;
                } else {
                    for (int c = 0; c < indexList.size(); c++) {
                        pattern = Pattern.compile(".*" + indexList.get(c) + ".*");
                        matcher = pattern.matcher(lineText.toLowerCase());

                        if (matcher.matches()) {
                            Education edu = new Education();
                            edu.setInstitution(lineText);
                            LOG.info(lineText + "--------THIS IS EDUCATIONAL INFORMATION");

                            // Add the new education institute object
                            educations.add(edu);

                            // Remove the line from the array list.
                            linesCopy.remove(lineText);
                        }
                    }
                }
            }
        }
        LOG.info("----Ending Educational Information----\n");
    }
}
