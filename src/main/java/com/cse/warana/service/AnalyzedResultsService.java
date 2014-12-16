package com.cse.warana.service;

import com.cse.warana.dto.AnalyticResultsDTO;

import java.util.List;

/**
 * Created by Nadeeshaan on 12/10/2014.
 */
public interface AnalyzedResultsService {
    public List<AnalyticResultsDTO> getAnalyzedResults();
    public String getCandidateData(long id);
}
