package com.cse.warana.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Nadeeshaan on 2015-02-10.
 */

@Controller
public class HelpController {
    private static final Logger LOG = LoggerFactory.getLogger(HelpController.class);

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public ModelAndView goToHelp(){
        String HELP_VIEW = "help";
        LOG.info("Loading Help Page");
        return new ModelAndView(HELP_VIEW);
    }
}
