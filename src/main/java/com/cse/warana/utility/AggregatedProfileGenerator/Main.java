package com.cse.warana.utility.AggregatedProfileGenerator;

import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.PhraseAnalyzer;
import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Profile.Profile;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        Pattern findUrl = Pattern.compile("\\bhttp.*?\\.pdf\\b");
//        Matcher matcher = findUrl.matcher("\n" +
//                " Organizer: <PERSON>Dayata Kirula</PERSON> 2011 <ORGANIZATION>CSE</ORGANIZATION>’s <ORGANIZATION>Exhibition Stall</ORGANIZATION>. ");
//        while (matcher.find()) {
//            System.out.println(matcher.group());
//        }

//        String input = "one Organizer: <PERSON>Dayata Kirula</PERSON> 2011 <ORGANIZATION>CSE</ORGANIZATION>’s <ORGANIZATION>Exhibition Stall</ORGANIZATION>.  two;";
//        Pattern p = Pattern.compile("(?<=\\bPERSON>\\b).*?(?=\\b</PERSON\\b)");
//        Matcher m = p.matcher(input);
//        List<String> matches = new ArrayList<String>();
//        while (m.find()) {
//            matches.add(m.group());
//        }
//        System.out.println(matches.toString());
//        PDF_Reader rd=new PDF_Reader();
//        rd.SaveTxt("Res/CV.pdf","Res/CV.txt");
//
//        NER_Extractor ner=new NER_Extractor();
//        ner.ExtractNames();
        //============== extracting linkedIn profile info

//        com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Google g=new com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Google();
//        com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.LinkedInExtractor linkedIn=new com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.LinkedInExtractor();
//        Scanner sc=new Scanner(System.in);
//        String name =sc.nextLine();
////        String link=g.FindOnLinkedIn(name);             // insert name here
//        new GitHubExtractor("69e07dde89a8a0a6713f810cfd4c461f04f47e85").Find(name);
//        System.out.println(link);
//        linkedIn.Extract(link);
        new Main().CallName();
    }

    public void CallName() {
        System.out.print("Enter name: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        PhraseAnalyzer phraseAnalyzer =new PhraseAnalyzer();
        Profile pr = new Profile(name, phraseAnalyzer);
//        System.out.println(pr.pic_url);
//        LinkedInExtractor linkedIn = new LinkedInExtractor();
//        GoogleScholarExtractor gscholar = new GoogleScholarExtractor();
//        GitHubExtractor github = new GitHubExtractor("69e07dde89a8a0a6713f810cfd4c461f04f47e85");
//
//        linkedIn.Extract(name, pr);
//        gscholar.Extract(name, pr);
//        github.Extract(name, pr);
//        String link=g.FindOnLinkedIn(name);             // insert name here
//        new GitHubExtractor("69e07dde89a8a0a6713f810cfd4c461f04f47e85").Find(name);

        CallName();

    }
}
