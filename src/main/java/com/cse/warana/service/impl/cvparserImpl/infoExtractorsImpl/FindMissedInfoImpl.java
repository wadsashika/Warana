package com.cse.warana.service.impl.cvparserImpl.infoExtractorsImpl;

import com.cse.warana.service.cvparser.infoExtractors.FindMissedInfo;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nadeeshaan on 11/7/2014.
 */
public class FindMissedInfoImpl implements FindMissedInfo {


    /**
     * Assume that the profile information is mentioned at the beginning of the resume
     * and within first 10 - 15 lines
     * Heuristic is we can find the email of a person most of the time around the name
     */
    @Override
    public void findProfileInfo(ArrayList<String> lines) {
        int terminatingLine = 0;
        if (lines.size() < 15) {
            terminatingLine = lines.size();
        } else {
            terminatingLine = 15;
        }

        /**
         * Regex to Identify the basic name writing patterns
         */
        Pattern pattern = Pattern.compile("^([a-zA-Z\\.*]+(?:\\.)?(?:(?:'| )[a-zA-Z]+(?:\\.)?)*)$");
        Matcher matcher = null;
        for (int a = 0; a < terminatingLine; a++) {
            matcher = pattern.matcher(lines.get(a));
            if (matcher.matches()) {
                for (int b = a + 1; b < terminatingLine; b++) {
                    if (getEmail(lines.get(b))) {
                        System.out.println("Suggested as name: " + matcher.group(1));
                        break;
                    }
                }
            }
        }
    }


    /**
     * Get the email address based on the regex
     * @param para
     * @return
     */
    @Override
    public boolean getEmail(String para) {
        String tokens[] = para.split(" ");
        String email = "";

        for (int a = 0; a < tokens.length; a++) {
            Pattern pattern = Pattern.compile("^(([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})+([;.](([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})+)*$");
            Matcher matcher = pattern.matcher(tokens[a].trim());

            if (matcher.find()) {
                System.out.println(matcher.group(1));
                return true;
            }
        }
        return false;
    }
}
