package com.cse.warana.service.impl;

import com.cse.warana.dao.AnalyzedResultsDao;
import com.cse.warana.dto.AnalyticResultsDTO;
import com.cse.warana.dto.CandidateDTO;
import com.cse.warana.dto.ResumesToAnalyseDto;
import com.cse.warana.service.AnalyzedResultsService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nadeeshaan on 12/10/2014.
 */
@Service("analyzedResultsService")
public class AnalyzedResultsServiceImpl implements AnalyzedResultsService {

    @Autowired
    @Qualifier("analyzedResults")
    private AnalyzedResultsDao analyzedResultsDao;
    Logger LOG = LoggerFactory.getLogger(AnalyzedResultsServiceImpl.class);

    @Override
    public List<AnalyticResultsDTO> getAnalyzedResults() {
        LOG.info("Getting resumes to analyze");

        List<AnalyticResultsDTO> analyticResultsDTOs = analyzedResultsDao.getAnalyzedResults();
        return analyticResultsDTOs;

    }

    @Override
    public String getCandidateData(long id) {

        Map<String,String> candidateData = new HashMap<>();
        Gson gson = new Gson();
        candidateData.put("profile",gson.toJson(analyzedResultsDao.getCandidateProfileData(id).get(0)));
        candidateData.put("achievement",gson.toJson(analyzedResultsDao.getCandidateAchievementsData(id)));
        candidateData.put("education",gson.toJson(analyzedResultsDao.getCandidateEducationData(id)));
        candidateData.put("projects",gson.toJson(analyzedResultsDao.getCandidateProjectData(id)));
        candidateData.put("publications",gson.toJson(analyzedResultsDao.getCandidatePublicationsData(id)));
        candidateData.put("workexp",gson.toJson(analyzedResultsDao.getCandidateWorkExperienceData(id)));

        String candidateJson = gson.toJson(candidateData);

        return candidateJson;
    }


}
