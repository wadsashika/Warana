package com.cse.warana.controller;

import com.cse.warana.dto.AnalyticResultsDTO;
import com.cse.warana.dto.ResumesToAnalyseDto;
import com.cse.warana.service.AnalyzeResumeService;
import com.cse.warana.service.impl.AnalyzeResumeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nadeeshaan on 11/15/2014.
 */

@Controller
public class AnalyzeController {
    private static final Logger LOG = LoggerFactory.getLogger(AnalyzeController.class);

    @Autowired
    @Qualifier("analyzeResumeService")
    private AnalyzeResumeService analyzeResumeService;

    @RequestMapping(value = "/analyze", method = RequestMethod.GET)
    public ModelAndView analyzeResumes() {
        String ANALYZE_VIEW = "analyze";
        LOG.info("Loading Analyze page");

        List<ResumesToAnalyseDto> resumesToAnalyseDtoList = analyzeResumeService.getResumesToAnalyze();

        ModelAndView model = new ModelAndView();
        model.setViewName(ANALYZE_VIEW);
        model.addObject("resumesToProcess", resumesToAnalyseDtoList);

        return model;
    }

    @RequestMapping(value = "/analyzedresults",method = RequestMethod.GET)
    public ModelAndView analyzedResluts(){
        String AN_RESULTS_VIEW = "analyzed-results";
        LOG.info("loading Analyzed results page");

        AnalyticResultsDTO res1 = new AnalyticResultsDTO();
        res1.setName("Nadeeshaan Gunasinghe");
        res1.setScore("79%");
        res1.setEmail("default");
        AnalyticResultsDTO res2 = new AnalyticResultsDTO();
        res2.setName("Dulanga Sashika");
        res2.setScore("89%");
        res2.setEmail("default");
        AnalyticResultsDTO res3 = new AnalyticResultsDTO();
        res3.setName("Anushka Mahesh");
        res3.setScore("99%");
        res3.setEmail("default");

        List<AnalyticResultsDTO> resultsDTOList = new ArrayList<AnalyticResultsDTO>();
        resultsDTOList.add(res1);
        resultsDTOList.add(res2);
        resultsDTOList.add(res3);


        ModelAndView model = new ModelAndView();
        model.setViewName(AN_RESULTS_VIEW);
        model.addObject("results",resultsDTOList);

        return model;
    }
}
