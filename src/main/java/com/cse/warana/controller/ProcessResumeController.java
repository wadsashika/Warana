package com.cse.warana.controller;

import com.cse.warana.dto.ResumesToProcessDto;
import com.cse.warana.service.ResumesToProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
