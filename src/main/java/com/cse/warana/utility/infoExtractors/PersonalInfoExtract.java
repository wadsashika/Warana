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
public class PersonalInfoExtract {


    private static Logger LOG = LoggerFactory.getLogger(PersonalInfoExtract.class);

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

        LOG.info("----Beginning Personal/profile Information----");
        for (int a = 0; a < headingLines.size(); a++) {
            for (int b = (headingLines.get(a).intValue() + 1); b < lines.size(); b++) {
                lineText = lines.get(b);
                if (allHeadings.contains(String.valueOf(b))) {
                    break;
                } else {
                    if (!lineText.equals("")) {

                        Profile prof = new Profile();

                        if (getName(lineText, prof)) {
                            linesCopy.remove(lineText);

                        } else if (getDbo(lineText, prof)) {
                            linesCopy.remove(lineText);

                        } else if (getEmail(lineText, prof)) {
                            linesCopy.remove(lineText);

                        } else if (getGender(lineText, prof)) {
                            linesCopy.remove(lineText);

                        } else if (getOtherInfo(lineText, prof)) {
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

        String tokens[] = para.split("\\W");
        String name = "";

        for (int a = 0; a < tokens.length; a++) {
            if (tokens[a].equalsIgnoreCase("Name")) {
                if (a + 2 < tokens.length) {
                    if (tokens[a + 1].equalsIgnoreCase("with") && tokens[a + 2].equalsIgnoreCase("initials")) {
                        for (int b = a + 3; b < tokens.length; b++) {
                            name += tokens[b] + " ";
                            LOG.info(tokens[b]);
                        }
                        LOG.info("");

                        profile.setName(name);
                        return true;
                    } else {
                        for (int b = a + 1; b < tokens.length; b++) {
                            name += tokens[b] + " ";
                            LOG.info(tokens[b] + " ");
                        }
                        LOG.info("");

                        profile.setName(name);
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
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

        for (int a = 0; a < tokens.length; a++) {

            // Initialize the general email address writing pattern
            Pattern pattern = Pattern.compile("^(([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})+([;.](([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})+)*$");
            Matcher matcher = pattern.matcher(para.trim());

            if (matcher.find()) {
                email = matcher.group(1);
                LOG.info(email);

                profile.setEmail(email);
                return true;
            }
        }
        return false;
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

        Pattern linkedInPtrn = Pattern.compile(linkedIn);
        Pattern gitPtrn = Pattern.compile(git);
        Matcher linkedInMtch = null;
        Matcher gitMtch = null;

        StringTokenizer tokenizer = new StringTokenizer(para, " ");

        while (tokenizer.hasMoreElements()) {
            tempTk = (String) tokenizer.nextElement();
            linkedInMtch = linkedInPtrn.matcher(tempTk);
            gitMtch = gitPtrn.matcher(tempTk);

            if (linkedInMtch.matches()) {
                LOG.info("LinkedIn: " + tempTk);
                foundOther = true;
            } else if (gitMtch.matches()) {
                LOG.info("Git: " + tempTk);
                foundOther = true;
            }
        }
        return foundOther;
    }
}
