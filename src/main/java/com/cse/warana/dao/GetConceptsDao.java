package com.cse.warana.dao;

import com.cse.warana.dto.CompanyTechnologyViewDTO;

import java.util.List;

/**
 * Created by Anushka on 2015-01-02.
 */
public interface GetConceptsDao {

    public List<String> getConceptsList();

    public List<String> getTechnologyList();

    public List<CompanyTechnologyViewDTO> getCompanyTechnologiesWithScore();
}
