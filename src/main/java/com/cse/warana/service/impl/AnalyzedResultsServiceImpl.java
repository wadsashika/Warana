package com.cse.warana.service.impl;

import com.cse.warana.dao.AnalyzedResultsDao;
import com.cse.warana.dto.AnalyticResultsDTO;
import com.cse.warana.dto.ResumesToAnalyseDto;
import com.cse.warana.service.AnalyzedResultsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
