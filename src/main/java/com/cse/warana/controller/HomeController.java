package com.cse.warana.controller;

import com.sun.javafx.sg.PGShape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Sashika on Nov 12 0012, 2014.
 */

@Controller
public class HomeController {
    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView loadHomePage() {
        String HOME_VIEW = "home";
        LOG.info("Loading Home Page");
        return new ModelAndView(HOME_VIEW);
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public ModelAndView loginError(ModelMap modelMap) {
        String HOME_VIEW = "home";
        LOG.info("Logging error occurred");
        modelMap.addAttribute("error",true);
        return new ModelAndView(HOME_VIEW);
    }
}
