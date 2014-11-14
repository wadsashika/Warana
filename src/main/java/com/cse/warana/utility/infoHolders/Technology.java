package com.cse.warana.utility.infoHolders;

import java.util.List;

/**
 * Created by Nadeeshaan on 11/12/2014.
 */
public class Technology {

    private String name;
    private List<String> descriptiveTerms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDescriptiveTerms() {
        return descriptiveTerms;
    }

    public void setDescriptiveTerms(List<String> descriptiveTerms) {
        this.descriptiveTerms = descriptiveTerms;
    }
}
