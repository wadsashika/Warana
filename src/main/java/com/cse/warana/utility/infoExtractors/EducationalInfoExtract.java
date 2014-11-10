package com.cse.warana.utility.infoExtractors;

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
public class EducationalInfoExtract {


    /**
     * Extract educational information
     *
     * @param lines
     * @param headingLines
     * @param allHeadings
     * @param linesCopy
     */
    public void extractEduInformation(ArrayList<String> lines, ArrayList<Integer> headingLines, ArrayList<String> allHeadings, ArrayList<String> linesCopy) {

        BufferedReader br = null;
        String indexWord = "";
        try {
            br = new BufferedReader(new FileReader("input/eduIndex.txt"));
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


        System.out.println("----Beginning Educational Information----");
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
                            System.out.println(lineText + "--------THIS IS EDUCATIONAL INFORMATION");
                            // Remove the line from the array list.
                            linesCopy.remove(lineText);
                        }
                    }
                }
            }
        }
        System.out.println("----Ending Educational Information----\n");
    }
}
