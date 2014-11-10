package com.cse.warana.utility.infoExtractors;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nadeeshaan on 11/7/2014.
 */
public class PersonalInfoExtract {


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
    public void extractPersonalInformation(ArrayList<String> lines, ArrayList<Integer> headingLines, ArrayList<String> allHeadings, ArrayList<String> linesCopy) {

        String lineText = "";

        System.out.println("----Beginning Personal/profile Information----");
        for (int a = 0; a < headingLines.size(); a++) {
            for (int b = (headingLines.get(a).intValue() + 1); b < lines.size(); b++) {
                lineText = lines.get(b);
                if (allHeadings.contains(String.valueOf(b))) {
                    break;
                } else {
                    if (!lineText.equals("")) {
                        if (getName(lineText)) {
                            linesCopy.remove(lineText);

                        } else if (getDbo(lineText)) {
                            linesCopy.remove(lineText);

                        } else if (getEmail(lineText)) {
                            linesCopy.remove(lineText);

                        } else if (getGender(lineText)) {
                            linesCopy.remove(lineText);

                        } else if (getOtherInfo(lineText)) {
                            linesCopy.remove(lineText);

                        }
                    }
                }

            }
        }
        System.out.println("----Ending personal Information----\n");
    }


    /**
     * Get the name of the person
     *
     * @param para
     * @return
     */
    public boolean getName(String para) {

        String tokens[] = para.split("\\W");
        String name = "";

        for (int a = 0; a < tokens.length; a++) {
            if (tokens[a].equalsIgnoreCase("Name")) {
                if (a + 2 < tokens.length) {
                    if (tokens[a + 1].equalsIgnoreCase("with") && tokens[a + 2].equalsIgnoreCase("initials")) {
                        for (int b = a + 3; b < tokens.length; b++) {
                            name += tokens[b] + " ";
                            System.out.println(tokens[b]);
                        }
                        System.out.println();
                        return true;
                    } else {
                        for (int b = a + 1; b < tokens.length; b++) {
                            name += tokens[b] + " ";
                            System.out.print(tokens[b] + " ");
                        }
                        System.out.println();
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
    public boolean getGender(String para) {

        String tokens[] = para.split("\\W");
        String geneder = "";

        for (int a = 0; a < tokens.length; a++) {
            if (tokens[a].equalsIgnoreCase("Gender") || tokens[a].equalsIgnoreCase("Sex")) {
                for (int b = a + 1; b < tokens.length; b++) {
                    geneder += tokens[b];
                    System.out.println(tokens[b]);
                }
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
    public boolean getEmail(String para) {

        String tokens[] = para.split(" ");
        String email = "";

        for (int a = 0; a < tokens.length; a++) {

            // Initialize the general email address writing pattern
            Pattern pattern = Pattern.compile("^(([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})+([;.](([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})+)*$");
            Matcher matcher = pattern.matcher(para.trim());

            if (matcher.find()) {
                System.out.println(matcher.group(1));
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
    public boolean getDbo(String para) {

        String paraLower = para.toLowerCase();
        String dob = "";

        if (paraLower.contains("date of birth")) {
            String[] arr = paraLower.split("date of birth");
            dob += arr[1];
            System.out.println(arr[1]);
            return true;
        } else if (paraLower.contains("birth day")) {
            String[] arr = paraLower.split("birth day");
            dob += arr[1];
            System.out.println(arr[1]);
            return true;
        }
        return false;
    }


    /**
     * TODO need some more testing on this method for the possible flaws
     *
     * @param para
     * @return sypeID, LinkedInID, blogUrl, gitID
     */
    public boolean getOtherInfo(String para) {

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
                System.out.println("LinkedIn: " + tempTk);
                foundOther = true;
            } else if (gitMtch.matches()) {
                System.out.println("Git: " + tempTk);
                foundOther = true;
            }
        }
        return foundOther;
    }
}
