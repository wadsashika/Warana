package com.cse.warana.service;

import com.cse.warana.service.impl.CVParserServiceImpl;
import com.cse.warana.utility.infoHolders.Candidate;

import java.io.File;

/**
 * Created by Nadeeshaan on 11/23/2014.
 */
public interface CandidateProfileGeneratorService {

    /**
     * This methods is responsible for extracting the cv Information via the CVs
     * @param cvParser
     */
    public void extractCVInformation(CVParserService cvParser, File resume);

    /**
     * Extract the candidate information from online profiles
     */
    public void extractOnlineProfileInformation(Candidate candidate);

    public Candidate generateCandidateProfile(Candidate candidate);
}
