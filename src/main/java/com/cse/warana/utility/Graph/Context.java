package com.cse.warana.utility.Graph;

import com.cse.warana.utility.Graph.techonolgyExtractors.BaseExtractor;

import java.util.List;

/**
 * Created by Anushka on 2015-01-31.
 */
public class Context {
    private BaseExtractor base;

    public Context(BaseExtractor base) {
        this.base = base;
    }

    public List<String> executeStrategy(String word) {
        return base.getAvailablity(word);
    }
}
