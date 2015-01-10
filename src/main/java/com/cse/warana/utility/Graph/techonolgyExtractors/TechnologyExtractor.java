package com.cse.warana.utility.Graph.techonolgyExtractors;

import com.cse.warana.utility.Graph.extraction.TechnologyBurn;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
/**
 * Created by Anushka on 2015-01-03.
 */
public class TechnologyExtractor {
    private Hashtable<String, Boolean> wordlist;
    private TechnologyBurn tb;
    private List<String> gatheredList;

    public TechnologyExtractor(String root) throws IOException {
        tb = new TechnologyBurn(root);
        wordlist = tb.getTechonologies();
        gatheredList = new ArrayList<String>();
    }

    public List<String> getAvailablity(String word) {
        String[] strs = word.split("[^\\w']+");
        for (String s : strs) {
            if (wordlist.get(s) != null) {
                gatheredList.add(s);
            }
        }
        return gatheredList;
    }
}
