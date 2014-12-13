package com.cse.warana.controller;

import com.cse.warana.dto.ResumesToProcessDto;
import com.cse.warana.service.CVParserService;
import com.cse.warana.service.CandidateProfileGeneratorService;
import com.cse.warana.service.ResumesToProcessService;
import com.cse.warana.service.StoreProcessedResumeService;
import com.cse.warana.service.impl.StoreProcessedResumeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nadeeshaan on 11/15/2014.
 */
@Controller
public class ProcessResumeController {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessResumeController.class);

    @Autowired
    @Qualifier("resumesToProcessService")
    private ResumesToProcessService resumesToProcessService;

    @Autowired
    @Qualifier("storeProcessedResume")
    private StoreProcessedResumeService storeProcessedResumeService;

    @Autowired
    @Qualifier("cvParser")
    private CVParserService cvParserService;

    @Autowired
    @Qualifier("generateCandidateProfile")
    private CandidateProfileGeneratorService candidateProfileGeneratorService;

    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public ModelAndView loadProcessView() {

        String PROCESS_VIEW = "process-resume";

        List<ResumesToProcessDto> resumesToProcessDtoList = resumesToProcessService.getResumesToProcess("NOT PROCESSED");

        List<String> filesNames = new ArrayList<String>();

        for (int a = 0; a < resumesToProcessDtoList.size(); a++){
            filesNames.add(resumesToProcessDtoList.get(a).getFileName());
        }

        ModelAndView model = new ModelAndView();
        model.addObject("files",filesNames);
        model.setViewName(PROCESS_VIEW);

        LOG.info("Loading Process Resume Page");

        return model;
    }

    @RequestMapping(value = "/process/delete", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteResume(@RequestParam("fileName") String fileName){

        String baseDirectory = "F:\\Accademic\\Semister 7\\Final_Year_Project\\CareersDay2013_CVs\\CareersDay2013_CVs\\pdfs";
        File resumeFile = new File(baseDirectory + "\\" + fileName);
        boolean status = false;

        resumesToProcessService.uploadedResumeStatusUpdate(fileName,"DELETED");

        if (resumeFile.exists()){
            resumeFile.delete();
            status = true;
        }

        return status;
    }

    @RequestMapping(value = "/process/processlist", method = RequestMethod.POST)
    @ResponseBody
    public boolean processSelectedResumes(@RequestBody String[] fileNames){

        String baseDirectory = "F:\\Accademic\\Semister 7\\Final_Year_Project\\CareersDay2013_CVs\\CareersDay2013_CVs\\pdfs";
        for (int a = 0; a < fileNames.length; a++){
            System.out.println(fileNames[a]);

            candidateProfileGeneratorService.extractCVInformation(cvParserService,new File(baseDirectory+"\\"+fileNames[a]));

        }

        /**
         * TODO Process the Resumes
         */

        storeProcessedResumeService.storeCandidateTableData();


        return true;
    }
}
