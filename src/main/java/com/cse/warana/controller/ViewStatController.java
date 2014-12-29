package com.cse.warana.controller;

import com.cse.warana.dto.ViewStatDTO;
import com.cse.warana.service.ViewStatService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by Nadeeshaan on 11/18/2014.
 */

@Controller
public class ViewStatController {
    private static final Logger LOG = LoggerFactory.getLogger(ViewStatController.class);

    @Autowired
    @Qualifier("viewStatService")
    private ViewStatService viewStatService;

    @RequestMapping(value = "/viewstat", method = RequestMethod.GET)
    public ModelAndView viewStatistic() {

        String STAT_VIEW = "view-stat";
        ModelAndView model = new ModelAndView();
        LOG.info("View Statistic page Loading");
        model.setViewName(STAT_VIEW);

        return model;
    }

    @RequestMapping(value = "/getresult", method = RequestMethod.POST)
    @ResponseBody
    public String getResultList() {

        List<ViewStatDTO> viewStatDTOList = viewStatService.getStatisticsDtos();
        Gson gson = new GsonBuilder().serializeNulls().create();
        LOG.info("getting data to be displayed");

        String jsonResultList = gson.toJson(viewStatDTOList);

        return jsonResultList;
    }

    @RequestMapping(value = "/gettechnologies", method = RequestMethod.POST)
    @ResponseBody
    public String getTechnologyList() {

        List<String> technologiesList = viewStatService.getTechnologies();
        Gson gson = new GsonBuilder().serializeNulls().create();

        return  gson.toJson(technologiesList);
    }

    @RequestMapping(value = "/advsearchresult", method = RequestMethod.POST)
    @ResponseBody
    public String getAdvSearchResults(@RequestParam("technologies") String[] technologies){
        List<ViewStatDTO> advancedSearchResults = viewStatService.getAdvancedSearchResults(technologies);
        Gson gson = new GsonBuilder().serializeNulls().create();
        LOG.info("getting data to be displayed");

        String jsonResultList = gson.toJson(advancedSearchResults);

        return jsonResultList;
    }

    @RequestMapping(value = "/getstat", method = RequestMethod.POST)
    @ResponseBody
    public String getTechnologyScoreMap(@RequestParam("id") double id){
        List<Map<String,Object>> techScoreList = viewStatService.getTechScoreMap(id);
        Gson gson = new GsonBuilder().serializeNulls().create();
        LOG.info("getting data to be displayed");

        String jsonResultList = gson.toJson(techScoreList);

        return jsonResultList;
    }
}
