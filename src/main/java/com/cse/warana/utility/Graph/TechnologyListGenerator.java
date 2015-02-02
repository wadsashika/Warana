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
    private Context tx;
    private Context cx;

    public TechnologyListGenerator(String root) throws IOException {
        tx = new Context(new TechnologyExtractor(root));
        cx = new Context(new ConceptExtractor(root));
        addedList = new ArrayList<String>();
    }

    public static void main(String at[]) throws IOException {
        List<String> ss = new ArrayList<String>();
        ss.add("computer science & engineering or equivalent");
        ss.add("java");
        ss.add("phy personality");
        ss.add("java or c++ c");
        ss.add("agilent vee sjshjs Java");
        ss.add("automated reasoning uius hshsg ssdjh");
        System.out.println(new TechnologyListGenerator("C:\\Warana").inti(ss));
    }

    public List<String> inti(List<String> conceptList) throws IOException {

        for (String i : conceptList) {
            List<String> w1 = tx.executeStrategy(i);
            if (w1 != null) {
                listCreator(w1);
            }
            List<String> w2 = cx.executeStrategy(i);
            if (w2 != null) {
                listCreator(w2);
            }
        }
        addedList.add("technologies");
        return addedList;
    }

    private void listCreator(List<String> words) {
        if (words != null) {
            for (String i : words) {
                if (!addedList.contains(i)) {
                    addedList.add(i);
                }
            }
        }
    }
}
