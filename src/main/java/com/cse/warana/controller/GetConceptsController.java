package com.cse.warana.controller;

import com.cse.warana.model.CompanyTechnology;
import com.cse.warana.service.CompanyTechnologyService;
import com.cse.warana.service.GetConceptsService;
import com.cse.warana.utility.Graph.TechnologyListGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * Created by Anushka on 2015-01-02.
 */
@Controller
public class GetConceptsController {
    private static final Logger LOG = LoggerFactory.getLogger(GetConceptsController.class);

    @Autowired
    @Qualifier("getConceptsService")
    private GetConceptsService getConceptsService;

    @Autowired
    @Qualifier("companytechnologiesService")
    private CompanyTechnologyService companyTechnologyService;

    private TechnologyListGenerator technologyListGenerator;

    @Value("${warana.resources.root}")
    private String root;

    @RequestMapping(value = "/getCompanyTechnologies", method = RequestMethod.POST)
    @ResponseBody
    public String getTechnologyList() throws IOException {
        List<String> technologyList = getConceptsService.getCompanyTechnologies();
        technologyList.add("Technologies");
        Gson gson = new GsonBuilder().serializeNulls().create();
        if(technologyList.isEmpty()) {
            technologyListGenerator = new TechnologyListGenerator(root);
            List<String> conceptsNames = technologyListGenerator.inti(getConceptsService.getConceptsList());
//            companyTechnologyService.storeCompanyTechnologies(conceptsNames);

            return gson.toJson(conceptsNames);
        }
        LOG.info("Loading graph");
        return gson.toJson(technologyList);
    }

}
