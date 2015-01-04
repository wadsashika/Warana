package com.cse.warana.utility.Graph.techonolgyExtractors;

import com.cse.warana.utility.Graph.extraction.ConceptBurn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/**
 * Created by Anushka on 2015-01-03.
 */
public class ConceptExtractor {
    private Hashtable<String, Boolean> wordlist;
    private ConceptBurn cb;
    private List<String> gatheredList;

    //    public static void main(String a[]) throws IOException {
//        System.out.println(new ConceptExtractor().getAvailablity("my name is computer interaction designing"));
//    }
    public ConceptExtractor() throws IOException {
        cb = new ConceptBurn();
        wordlist = cb.getConcepts();
        gatheredList = new ArrayList<String>();
    }

    public List<String> getAvailablity(String word) {
        for (String key : wordlist.keySet()) {
            if (word.contains(key)) {
                gatheredList.add(key);
            }
        }
        return gatheredList;
    }
}
