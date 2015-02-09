package com.cse.warana.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Nadeeshaan on 11/14/2014.
 */
@Controller
public class DashboardController {
    private static final Logger LOG = LoggerFactory.getLogger(DashboardController.class);

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView loadDashboard() {
        String DASHBOARD_VIEW = "dashboard";
        LOG.info("Loading Dashboard");
        return new ModelAndView(DASHBOARD_VIEW);
    }

    @RequestMapping(value = "/dashboard/getbarchartdata", method = RequestMethod.POST)
    @ResponseBody
    public String getTechnologyBarchartData(){

    }
}
