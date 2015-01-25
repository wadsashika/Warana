package com.cse.warana.utility.infoExtractors;

import com.cse.warana.utility.infoHolders.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.StringTokenizer;
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
    public void findProfileInfo(ArrayList<String> lines, Profile profile) {
        int terminatingLine = 0;
        if (lines.size() < 15) {
            terminatingLine = lines.size();
        } else {
            terminatingLine = 15;
        }

        String name = "";
        String email = null;
        boolean chkCtr = false;
        /**
         * Regex to Identify the basic name writing patterns
         */
        Pattern pattern = Pattern.compile("(([A-Z]\\.?\\s?)*([A-Z][a-z]+\\.?\\s?)+([A-Z]\\.?\\s?[a-z]*)*)");
        Matcher matcher = null;
//        for (int a = 0; a < terminatingLine; a++) {
//            matcher = pattern.matcher(lines.get(a));
//            if (matcher.matches() && profile.getName() == null) {
//                for (int b = a + 1; b < terminatingLine; b++) {
//                    String email = getEmail(lines.get(b));
//                    if (email != null) {
//                        LOG.info("Suggested as name: " + matcher.group(0));
//                        profile.setName(matcher.group(0));
//                        profile.setEmail(email);
//                    } else {
//                        getOtherInfo(lines.get(b), profile);
//
//                    }
//                }
//            } else {
//                getOtherInfo(lines.get(a), profile);
//            }
//        }

        for(int a = 0; a< terminatingLine; a++){
            matcher = pattern.matcher(lines.get(a));
            String tmpEmail = "";
            chkCtr = false;
            if (matcher.matches() && name.equals("")){
                name = matcher.group(0);
                chkCtr = true;
            }
            if (!chkCtr && email==null){
                email = getEmail(lines.get(a));
                if (email!=null){
                    chkCtr = true;
                }
            }
            if (!chkCtr){
                getOtherInfo(lines.get(a),profile);
            }

        }

        if (email!=null){
            profile.setEmail(email);
            profile.setName(name);
        }
    }


    /**
     * Get the email address based on the regex
     *
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
                LOG.info(matcher.group(0));
                return matcher.group(0);
            }
        }
        return null;
    }

    public void getOtherInfo(String para, Profile profile) {
        String linkedIn = ".*linkedin.com.*";

        Pattern urlPattern = Pattern.compile("((?<=[^a-zA-Z0-9])(?:https?\\:\\/\\/|[a-zA-Z0-9]{1,}\\.{1}|\\b)(?:\\w{1,}\\.{1}){1,5}(?:com|org|edu|gov|uk|net|ca|de|jp|fr|au|us|ru|ch|it|nl|se|no|es|mil|iq|io|ac|ly|sm){1}(?:\\/[a-zA-Z0-9]{1,})*)");
        Pattern linkedInPtrn = Pattern.compile(linkedIn);
//        Pattern gitPtrn = Pattern.compile(git);
        Matcher linkedInMtch = null;
        Matcher urlMatcher = urlPattern.matcher(para);

        if (urlMatcher.find()) {
            String url = urlMatcher.group(0);
            StringTokenizer tokenizer = new StringTokenizer(para, " ");

            linkedInMtch = linkedInPtrn.matcher(url);

            if (linkedInMtch.matches()) {
                profile.setLinkedIn(linkedInMtch.group(0));
                LOG.info("LinkedIn: " + linkedInMtch.group(0));
            } else {
                profile.getUrls().add(urlMatcher.group(0));
            }
        }
    }
}
