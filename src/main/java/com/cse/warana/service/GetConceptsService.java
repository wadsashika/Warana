package com.cse.warana.service;

import com.cse.warana.dto.CompanyTechnologyViewDTO;

import java.util.List;

/**
 * Created by Anushka on 2015-01-02.
 */
public interface GetConceptsService {

    public List<String> getConceptsList();

    public List<String> getCompanyTechnologies();

    public List<CompanyTechnologyViewDTO> getCompanyTechologyWithScore();
}
