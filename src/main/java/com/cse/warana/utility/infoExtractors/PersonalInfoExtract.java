package com.cse.warana.utility.infoExtractors;

import com.cse.warana.utility.infoHolders.Profile;
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
public class PersonalInfoExtract {


    private static Logger LOG = LoggerFactory.getLogger(PersonalInfoExtract.class);
    private ArrayList<String> nameHeaderPatterns = new ArrayList<>();
    private String listpath = "";

    public PersonalInfoExtract(Map<String,String> paths){
        listpath = paths.get("root") + paths.get("listPath");
    }

    /**
     * Extract the personal information from the text, such as name, email, gender, mobile number, etc..
     * If they have been included under an identifiable heading such as, Profile Information which is captured
     * during the section identifying step.
     *
     * @param lines
     * @param headingLines
     * @param allHeadings
     * @param linesCopy
     */
    public void extractPersonalInformation(ArrayList<String> lines, ArrayList<Integer> headingLines, ArrayList<String> allHeadings, ArrayList<String> linesCopy, Profile profile) {

        String lineText = "";

        /**
         * Reading for the name headers from relevant gazetteer list
         */
        BufferedReader br = null;
        String nameHeaderStr = "";
        try {
            br = new BufferedReader(new FileReader(listpath + File.separator + "nameHeaderPatterns"));
            nameHeaderStr = br.readLine();

            while (!nameHeaderStr.equals("end")){
                nameHeaderPatterns.add(nameHeaderStr);
                nameHeaderStr = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOG.info("----Beginning Personal/profile Information----");
        for (int a = 0; a < headingLines.size(); a++) {
            for (int b = (headingLines.get(a).intValue() + 1); b < lines.size(); b++) {
                lineText = lines.get(b);
                if (allHeadings.contains(String.valueOf(b))) {
                    break;
                } else {
                    if (!lineText.equals("")) {

//                        Profile prof = new Profile();

                        if (getName(lineText, profile)) {
                            linesCopy.remove(lineText);

                        } else if (getDbo(lineText, profile)) {
                            linesCopy.remove(lineText);

                        } else if (getEmail(lineText, profile)) {
                            linesCopy.remove(lineText);

                        } else if (getGender(lineText, profile)) {
                            linesCopy.remove(lineText);

                        } else if (getOtherInfo(lineText, profile)) {
                            linesCopy.remove(lineText);

                        }
                    }
                }

            }
        }
        LOG.info("----Ending personal Information----\n");
    }


    /**
     * Get the name of the person
     *
     * @param para
     * @return
     */
    public boolean getName(String para, Profile profile) {

        Pattern nameHeaderPattern = null;
        Matcher nameHeaderMatcher = null;
        boolean status = false;

        for (int a = 0; a < nameHeaderPatterns.size(); a++){
            nameHeaderPattern = Pattern.compile(".*("+nameHeaderPatterns.get(a)+").*");
            nameHeaderMatcher = nameHeaderPattern.matcher(para.toLowerCase());
            if (nameHeaderMatcher.matches()){
                LOG.info(nameHeaderMatcher.group(1));
                profile.setName(para.toLowerCase().replaceAll(nameHeaderMatcher.group(1),""));
                status = true;
                break;
            }
        }

        return status;

//        for (int a = 0; a < tokens.length; a++) {
//            if (tokens[a].equalsIgnoreCase("Name")) {
//                if (a + 2 < tokens.length) {
//                    if (tokens[a + 1].equalsIgnoreCase("with") && tokens[a + 2].equalsIgnoreCase("initials")) {
//                        for (int b = a + 3; b < tokens.length; b++) {
//                            name += tokens[b] + " ";
//                            LOG.info(tokens[b]);
//                        }
//                        LOG.info("");
//
//                        profile.setName(name);
//                        return true;
//                    } else {
//                        for (int b = a + 1; b < tokens.length; b++) {
//                            name += tokens[b] + " ";
//                            LOG.info(tokens[b] + " ");
//                        }
//                        LOG.info("");
//
//                        profile.setName(name.trim());
//                        return true;
//                    }
//                }
//                return false;
//            }
//        }
    }


    /**
     * Get the gender of the person
     *
     * @param para
     * @return
     */
    public boolean getGender(String para, Profile profile) {

        String tokens[] = para.split("\\W");
        String geneder = "";

        for (int a = 0; a < tokens.length; a++) {
            if (tokens[a].equalsIgnoreCase("Gender") || tokens[a].equalsIgnoreCase("Sex")) {
                for (int b = a + 1; b < tokens.length; b++) {
                    geneder += tokens[b];
                    LOG.info(tokens[b]);
                }

                profile.setGender(geneder);
                return true;
            }
        }
        return false;
    }


    /**
     * Get the email address of the person
     *
     * @param para
     * @return
     */
    public boolean getEmail(String para, Profile profile) {

        String tokens[] = para.split(" ");
        String email = "";
        boolean status = false;

        for (int a = 0; a < tokens.length; a++) {

            // Initialize the general email address writing pattern
            Pattern pattern = Pattern.compile("^(([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})+([;.](([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})+)*$");
            Matcher matcher = pattern.matcher(tokens[a].trim());

            if (matcher.find()) {
                profile.setEmail(matcher.group(0));
                status = true;
                LOG.info(matcher.group(0));
                break;
            }
        }
        return status;
    }


    /**
     * Get the date of birth of the person
     *
     * @param para
     * @return
     */
    public boolean getDbo(String para, Profile profile) {

        String paraLower = para.toLowerCase();
        String dob = "";

        if (paraLower.contains("date of birth")) {
            String[] arr = paraLower.split("date of birth");
            dob += arr[1];
            LOG.info(arr[1]);

            return true;
        } else if (paraLower.contains("birth day")) {
            String[] arr = paraLower.split("birth day");
            dob += arr[1];
            LOG.info(arr[1]);
            return true;
        }
        return false;
    }


    /**
     * TODO need some more testing on this method for the possible flaws
     *
     * @param para
     * @return skypeID, LinkedInID, blogUrl, gitID
     */
    public boolean getOtherInfo(String para, Profile profile) {

        String linkedIn = ".*linkedin.com.*";
        String git = ".*github.com.*";
        String tempTk = "";
        boolean foundOther = false;

        Pattern urlPattern = Pattern.compile("((?<=[^a-zA-Z0-9])(?:https?\\:\\/\\/|[a-zA-Z0-9]{1,}\\.{1}|\\b)(?:\\w{1,}\\.{1}){1,5}(?:com|org|edu|gov|uk|net|ca|de|jp|fr|au|us|ru|ch|it|nl|se|no|es|mil|iq|io|ac|ly|sm){1}(?:\\/[a-zA-Z0-9]{1,})*)");
        Pattern linkedInPtrn = Pattern.compile(linkedIn);
        Pattern gitPtrn = Pattern.compile(git);
        Matcher linkedInMtch = null;
        Matcher gitMtch = null;
        Matcher urlMatcher = urlPattern.matcher(para);

        if (urlMatcher.find()){
            String url = urlMatcher.group(0);
            StringTokenizer tokenizer = new StringTokenizer(para, " ");

            linkedInMtch = linkedInPtrn.matcher(url);

            if (linkedInMtch.matches()) {
                profile.setLinkedIn(linkedInMtch.group(0));
                LOG.info("LinkedIn: " + linkedInMtch.group(0));
                foundOther = true;
            } else {
                profile.getUrls().add(urlMatcher.group(0));
                foundOther = true;
            }
        }

        return foundOther;
    }
}
