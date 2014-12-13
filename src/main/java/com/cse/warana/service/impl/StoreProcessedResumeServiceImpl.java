package com.cse.warana.service.impl;

import com.cse.warana.dao.StoreCandidateDao;
import com.cse.warana.dao.impl.StoreCandidateDaoImpl;
import com.cse.warana.model.CandidateTbl;
import com.cse.warana.service.StoreProcessedResumeService;
import com.cse.warana.utility.infoHolders.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Created by Nadeeshaan on 12/13/2014.
 */

@Service("storeProcessedResume")
public class StoreProcessedResumeServiceImpl implements StoreProcessedResumeService {

    @Autowired
    @Qualifier("storeCandidate")
    private StoreCandidateDaoImpl storeCandidateDao;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void storeCandidateTableData() {
        CandidateTbl candidate = new CandidateTbl();

        candidate.setName("Nadeeshaan");
        candidate.setEmail("nsg@gmail.com");
        candidate.setAddress("Galle");
        candidate.setGender("Male");
        candidate.setMarital_status("Single");

        storeCandidateDao.saveEntity(candidate);
    }
}
