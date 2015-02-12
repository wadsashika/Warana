package com.cse.warana.service;

import com.cse.warana.dto.ResumesToAnalyseDto;

import java.util.List;

/**
 * Created by Nadeeshaan on 12/5/2014.
 */
public interface AnalyzeResumeService {

    public List<ResumesToAnalyseDto> getResumesToAnalyze();

    public boolean analyzeSelectedListOfCandidates(String[] idList);

    public boolean calculateCandidateTechnologyScore(String[] idList);
}
