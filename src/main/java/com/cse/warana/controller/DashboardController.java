package com.cse.warana.controller;

import com.cse.warana.dto.CompanyTechnologyViewDTO;
import com.cse.warana.service.GetConceptsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DashboardController {
    private static final Logger LOG = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    @Qualifier("getConceptsService")
    private GetConceptsService getConceptsService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView loadDashboard() {
        String DASHBOARD_VIEW = "dashboard";
        LOG.info("Loading Dashboard");
        return new ModelAndView(DASHBOARD_VIEW);
    }

    @RequestMapping(value = "/dashboard/getbarchartdata", method = RequestMethod.POST)
    @ResponseBody
    public String getTechnologyBarchartData() {
        LOG.info("Get Bar Chart data started");
        String returnJson = "";
        List<CompanyTechnologyViewDTO> companyTechnologyViewDTOs = getConceptsService.getCompanyTechnologyWithScore();

        Gson gson = new GsonBuilder().serializeNulls().create();
        returnJson = gson.toJson(companyTechnologyViewDTOs);
        return returnJson;
    }
}
