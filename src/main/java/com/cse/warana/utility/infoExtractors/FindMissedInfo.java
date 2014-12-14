package com.cse.warana.utility.infoExtractors;

import com.cse.warana.utility.infoHolders.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nadeeshaan on 11/7/2014.
 */
public class FindMissedInfo {


    private static Logger LOG = LoggerFactory.getLogger(FindMissedInfo.class);

    /**
     * Assume that the profile information is mentioned at the beginning of the resume
     * and within first 10 - 15 lines
     * Heuristic is we can find the email of a person most of the time around the name
     */
    public void findProfileInfo(ArrayList<String> lines,Profile profile) {
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
                    String email = getEmail(lines.get(b));
                    if (email!=null) {
                        LOG.info("Suggested as name: " + matcher.group(1));
                        profile.setName(matcher.group(1));
                        profile.setEmail(email);
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
    public String getEmail(String para) {
        String tokens[] = para.split(" ");
        String email = "";

        for (int a = 0; a < tokens.length; a++) {
            Pattern pattern = Pattern.compile("^(([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})+([;.](([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})+)*$");
            Matcher matcher = pattern.matcher(tokens[a].trim());

            if (matcher.find()) {
                LOG.info(matcher.group(1));
                return matcher.group(1);
            }
        }
        return null;
    }
}
