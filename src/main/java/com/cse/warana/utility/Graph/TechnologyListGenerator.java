package com.cse.warana.utility.Graph;

import com.cse.warana.utility.Graph.techonolgyExtractors.ConceptExtractor;
import com.cse.warana.utility.Graph.techonolgyExtractors.TechnologyExtractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anushka on 2015-01-03.
 */
public class TechnologyListGenerator {
    private List<String> addedList;
    private TechnologyExtractor tx;
    private ConceptExtractor cx;

    public TechnologyListGenerator() throws IOException {
        tx = new TechnologyExtractor();
        cx = new ConceptExtractor();
        addedList = new ArrayList<String>();
    }

    public static void main(String at[]) throws IOException{
        List<String> ss= new ArrayList<String>();
        ss.add("computer science\\/engineering or equivalent");
        ss.add("java");
        ss.add("amicable personality");
        ss.add("java or c\\/c");
        System.out.println(new TechnologyListGenerator().inti(ss));
    }

    public List<String> inti(List<String> conceptList) throws IOException {

        for (String i : conceptList) {
            List<String> w1 = tx.getAvailablity(i);
            List<String> w2 = cx.getAvailablity(i);
            if (w1 != null || w2 != null) {
                listCreator(w1, w2);
            }
        }
        addedList.add("Technologies");
        return addedList;
    }

    private void listCreator(List<String> word1, List<String> word2) {
        if (word1 != null) {
            for (String i : word1) {
                if (!addedList.contains(i)) {
                    addedList.add(i);
                }
            }
        }

        if (word2 != null) {
            for (String i : word2) {
                if (!addedList.contains(i)) {
                    addedList.add(i);
                }
            }
        }
    }
}
