package com.cse.warana.utility.Graph.techonolgyExtractors;

import com.cse.warana.utility.Graph.extraction.ConceptBurn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/**
 * Created by Anushka on 2015-01-03.
 */
public class ConceptExtractor implements BaseExtractor {
    private Hashtable<String, Boolean> wordlist;
    private ConceptBurn cb;
    private List<String> gatheredList;

    //    public static void main(String a[]) throws IOException {
//        System.out.println(new ConceptExtractor().getAvailablity("my name is computer interaction designing"));
//    }
    public ConceptExtractor(String root) throws IOException {
        cb = new ConceptBurn(root);
        wordlist = cb.getConcepts();
        gatheredList = new ArrayList<String>();
    }

    public List<String> getAvailablity(String word) {
        for (String key : wordlist.keySet()) {
            word = word.toLowerCase();
            key = key.toLowerCase();
            if (word.contains(key)) {
                int lastIdx = word.lastIndexOf(key);
                int index = lastIdx + key.length();
                if ((index == word.length() || word.charAt(index) == 32) && (lastIdx == 0 || word.charAt(lastIdx - 1) == 32)) {
                    gatheredList.add(key);
                }
            }
        }
        return gatheredList;
    }
}
