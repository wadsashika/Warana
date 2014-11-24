package com.cse.warana.utility.AggregatedProfileGenerator;

import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.PhraseAnalyzer;
import com.cse.warana.utility.infoHolders.Profile;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main().CallName();
    }

    public void CallName() {
        System.out.print("Enter name: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        PhraseAnalyzer phraseAnalyzer =new PhraseAnalyzer();
        Profile pr = new Profile(name, phraseAnalyzer);

        CallName();

    }
}
