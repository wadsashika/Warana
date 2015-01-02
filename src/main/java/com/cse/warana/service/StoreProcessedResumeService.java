package com.cse.warana.service;

import com.cse.warana.utility.infoHolders.Candidate;

import java.util.ArrayList;

/**
 * Created by Nadeeshaan on 12/13/2014.
 */
public interface StoreProcessedResumeService {
    public long storeCandidateTableData(Candidate candidate);
    public void storeEducationalTableData(Candidate candidate, long candidateID);
    public void storeProjectTableData(Candidate candidate, long candidateId);
    public void storeWorkTableData(Candidate candidate, long candidateId);
    public void storeAchievementTableData(Candidate candidate, long candidateId);
    public void storeRefereeTableData(Candidate candidate, long candidateId);
    public void storeNewTechnologies(ArrayList<String> technologies);
    public void storeCandidateTechnologies(Candidate candidate,long id);
}
