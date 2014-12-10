package com.cse.warana.service.impl;

import com.cse.warana.dao.AnalyzeResumeDao;
import com.cse.warana.dao.ResumesToProcessDao;
import com.cse.warana.dto.ResumesToAnalyseDto;
import com.cse.warana.service.AnalyzeResumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Nadeeshaan on 12/5/2014.
 */

@Service("analyzeResumeService")
public class AnalyzeResumeServiceImpl implements AnalyzeResumeService {

    Logger LOG = LoggerFactory.getLogger(AnalyzeResumeServiceImpl.class);

    @Autowired
    @Qualifier("analyzeResumeDao")
    private AnalyzeResumeDao analyzeResumeDao;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<ResumesToAnalyseDto> getResumesToAnalyze() {
        LOG.info("Getting resumes to analyze");

        List<ResumesToAnalyseDto> resumesToAnalyseDtoList = analyzeResumeDao.getResumesToBeAnalyzed();
        return resumesToAnalyseDtoList;
    }
}
