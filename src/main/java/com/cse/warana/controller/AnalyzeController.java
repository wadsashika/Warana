package com.cse.warana.controller;

import com.cse.warana.dto.AnalyticResultsDTO;
import com.cse.warana.dto.ResponseDTO;
import com.cse.warana.dto.ResumesToAnalyseDto;
import com.cse.warana.service.AnalyzeResumeService;
import com.cse.warana.service.AnalyzedResultsService;
import com.cse.warana.service.impl.AnalyzeResumeServiceImpl;
import com.cse.warana.utility.infoHolders.Candidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    @Qualifier("analyzedResultsService")
    private AnalyzedResultsService analyzedResultsService;

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

    @RequestMapping(value = "/analyzedresults", method = RequestMethod.GET)
    public ModelAndView analyzedResluts() {
        String AN_RESULTS_VIEW = "analyzed-results";
        LOG.info("loading Analyzed results page");

        List<AnalyticResultsDTO> analyticResultsDTOs = analyzedResultsService.getAnalyzedResults();

        ModelAndView model = new ModelAndView();
        model.setViewName(AN_RESULTS_VIEW);
        model.addObject("results", analyticResultsDTOs);

        return model;
    }

    @RequestMapping(value = "analyze/analyzelist", method = RequestMethod.POST)
    @ResponseBody
    public boolean analyzeSelectedResumes(@RequestBody String[] idList) {
        analyzeResumeService.calculateCandidateTechnologyScore(idList);
        analyzeResumeService.analyzeSelectedListOfCandidates(idList);
        return true;
    }

    @RequestMapping(value = "/analyze/profile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<Map<String, String>> deleteResume(@RequestBody (required = false) long id) {
        LOG.info("Retrieving candidate profile information");

        ResponseDTO<Map<String, String>> response = new ResponseDTO<Map<String, String>>();

        response.setSuccess(true);
        response.setResult(analyzedResultsService.getCandidateData(id));

        return response;
    }

}
