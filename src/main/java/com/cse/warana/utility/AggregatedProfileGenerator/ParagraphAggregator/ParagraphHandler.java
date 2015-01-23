package com.cse.warana.utility.AggregatedProfileGenerator.ParagraphAggregator;

/**
 * Created by Thilina on 1/13/2015.
 */

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.IOException;

public class ParagraphHandler {

    public static void main(String[] args) throws IOException,
            ClassNotFoundException {

        // Initialize the tagger
        MaxentTagger tagger = new MaxentTagger(
                "taggers/left3words-distsim-wsj-0-18.tagger");

        // The sample string
        String sample = "This is a sample text";

        // The tagged string
        String tagged = tagger.tagString(sample);

        // Output the result
        System.out.println(tagged);
    }
}
