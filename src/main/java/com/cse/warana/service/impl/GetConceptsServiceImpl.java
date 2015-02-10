package com.cse.warana.service.impl;

import com.cse.warana.dao.GetConceptsDao;
import com.cse.warana.dto.CompanyTechnologyViewDTO;
import com.cse.warana.service.GetConceptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Anushka on 2015-01-02.
 */
@Service("getConceptsService")
public class GetConceptsServiceImpl implements GetConceptsService {

    @Autowired
    @Qualifier("getConceptsDao")
    private GetConceptsDao getConceptsDao;

    @Override
    public List<String> getConceptsList() {
        List<String> concepts = null;
        concepts = getConceptsDao.getConceptsList();

        return concepts;
    }

    @Override
    public List<String> getCompanyTechnologies() {
        List<String> technologies = null;
        technologies = getConceptsDao.getTechnologyList();

        return technologies;
    }

    @Override
    public List<CompanyTechnologyViewDTO> getCompanyTechnologyWithScore() {
        List<CompanyTechnologyViewDTO> returnList = null;
        returnList = getConceptsDao.getCompanyTechnologiesWithScore();
        return returnList;
    }
}
