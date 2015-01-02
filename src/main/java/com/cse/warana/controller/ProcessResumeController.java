package com.cse.warana.controller;

import com.cse.warana.dto.ResumesToProcessDto;
import com.cse.warana.service.CVParserService;
import com.cse.warana.service.CandidateProfileGeneratorService;
import com.cse.warana.service.ResumesToProcessService;
import com.cse.warana.service.StoreProcessedResumeService;
import com.cse.warana.service.impl.CVParserServiceImpl;
import com.cse.warana.service.impl.CandidateProfileGeneratorServiceImpl;
import com.cse.warana.service.impl.StoreProcessedResumeServiceImpl;
import com.cse.warana.utility.infoHolders.Candidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Value("${warana.resources.root}")
    private String root;

    @Value("${NER.CLASSIFIER.PATH}")
    private String classifirePath;

    @Value("${GAZETEER.LIST.PATH}")
    private String listPath;

    @Value("${UPLOADS.PATH}")
    private String uploadsPath;

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

        String baseDirectory = root + uploadsPath;
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

        HashMap<String,String> paths = new HashMap<>();
        paths.put("root",root);
        paths.put("classifirePath",classifirePath);
        paths.put("listPath",listPath);

        String baseDirectory = root + uploadsPath;
        ArrayList<Candidate> candidateArrayList = new ArrayList<>();
        for (int a = 0; a < fileNames.length; a++){
            System.out.println(fileNames[a]);

            CandidateProfileGeneratorService profileGeneratorService = new CandidateProfileGeneratorServiceImpl();
            CVParserService cvParserService = new CVParserServiceImpl(paths);

            Candidate candidate = new Candidate();
            profileGeneratorService.extractCVInformation(cvParserService,new File(baseDirectory+"\\"+fileNames[a]));
            candidateArrayList.add(profileGeneratorService.generateCandidateProfile(candidate));

            long candidate_id = storeProcessedResumeService.storeCandidateTableData(candidate);

            storeProcessedResumeService.storeEducationalTableData(candidate,candidate_id);
            storeProcessedResumeService.storeAchievementTableData(candidate,candidate_id);
            storeProcessedResumeService.storeProjectTableData(candidate,candidate_id);
            storeProcessedResumeService.storeRefereeTableData(candidate,candidate_id);
            storeProcessedResumeService.storeWorkTableData(candidate,candidate_id);
            storeProcessedResumeService.storeNewTechnologies(cvParserService.getNewTechnologies());
            storeProcessedResumeService.storeCandidateTechnologies(candidate,candidate_id);
            resumesToProcessService.uploadedResumeStatusUpdate(fileNames[a],"PROCESSED");
        }

        return true;
    }
}
