package com.cse.warana.service;

import com.cse.warana.dto.AnalyticResultsDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by Nadeeshaan on 12/10/2014.
 */
public interface AnalyzedResultsService {

    public List<AnalyticResultsDTO> getAnalyzedResults();

    public Map<String, String> getCandidateData(long id);
}
