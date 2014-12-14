package com.cse.warana.controller;

import com.cse.warana.service.CVParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;

/**
 * Created by Nadeeshaan on 11/11/2014.
 */

@Controller
public class CvParserController {

    private static Logger LOG = LoggerFactory.getLogger(ExampleController.class);
    @Autowired(required = true)
    @Qualifier(value = "cvParser")
    public CVParserService cvParserService;

    @RequestMapping(value = "/cvparser", method = RequestMethod.GET)
    public String exampleView(ModelMap modelMap) {
        LOG.info("Example Controller Started");

        cvParserService.readPdfDocument(new File("F:\\Accademic\\Semister 7\\Final_Year_Project\\CareersDay2013_CVs\\CareersDay2013_CVs\\pdfs\\090074J_DARSHANA K.U.G.pdf"));
        cvParserService.initializeHeadingTokens();
        cvParserService.identifyHeadings();
//        cvParserService.parseLines();
        String exampleServiceString = cvParserService.test();
        modelMap.addAttribute("message", exampleServiceString);

        return "cvParser";
    }
}
