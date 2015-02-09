package com.cse.warana.dao;

import com.cse.warana.model.CompanyTechnology;

import java.util.List;

/**
 * Created by Anushka on 2015-01-02.
 */
public interface GetConceptsDao {
    public List<String> getConceptsList();
    public List<String> getTechnologyList();
    public List<CompanyTechnology> getCompanyTechnologiesWithScore();
}
