package com.cse.warana.service.impl;

import com.cse.warana.dao.ResumesToProcessDao;
import com.cse.warana.dto.ResumesToProcessDto;
import com.cse.warana.service.ResumesToProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Nadeeshaan on 12/4/2014.
 */

@Service("resumesToProcessService")
public class ResumesToProcessServiceImpl implements ResumesToProcessService {

    Logger LOG = LoggerFactory.getLogger(ResumesToProcessServiceImpl.class);

    @Autowired
    @Qualifier("resumesToProcessDao")
    private ResumesToProcessDao resumesToProcessDao;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<ResumesToProcessDto> getResumesToProcess(String status) {
        LOG.info("Getting resumes to be processed");

        List<ResumesToProcessDto> resumesToProcessDtoList = resumesToProcessDao.getUploadedResumes(status);

        return resumesToProcessDtoList;
    }
}