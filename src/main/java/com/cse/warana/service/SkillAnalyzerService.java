package com.cse.warana.service;

/**
 * Created by Thilina on 1/22/2015.
 */
public interface SkillAnalyzerService {

    /**
     * Extract keyterms from skill documents
     */
    public void extractSkillKeyterms();


    /**
     * Extract keyterms from candidate documents
     */
    public void extractCandidateKeyterms();


}
