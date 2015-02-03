package com.cse.warana.controller;

import com.cse.warana.dto.ResponseDTO;
import com.cse.warana.dto.ViewStatDTO;
import com.cse.warana.service.ViewStatService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
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

    @RequestMapping(value = "/viewstat/getresult", method = RequestMethod.POST, headers = {"content-type=application/json"})
    @ResponseBody
    public ResponseDTO<Map<String, String>> getResultList() {

        ResponseDTO<Map<String, String>> response = new ResponseDTO<Map<String, String>>();
        Map<String, String> initData = new HashMap<String, String>();
        List<ViewStatDTO> viewStatDTOList = viewStatService.getStatisticsDtos();
        Gson gson = new GsonBuilder().serializeNulls().create();
        LOG.info("getting data to be displayed");

        initData.put("resultList", gson.toJson(viewStatDTOList));
        response.setSuccess(true);
        response.setResult(initData);

        return response;
    }

    @RequestMapping(value = "/viewstat/gettechnologies", method = RequestMethod.POST, headers = {"content-type=application/json"})
    @ResponseBody
    public ResponseDTO<Map<String, String>> getTechnologyList() {

        ResponseDTO<Map<String, String>> response = new ResponseDTO<Map<String, String>>();
        Map<String, String> initData = new HashMap<String, String>();
        List<String> technologiesList = viewStatService.getTechnologies();
        Gson gson = new GsonBuilder().serializeNulls().create();

        initData.put("technologyList", gson.toJson(technologiesList));

        response.setSuccess(true);
        response.setResult(initData);

        return response;
    }

    @RequestMapping(value = "/viewstat/advsearchresult", method = RequestMethod.POST, headers = {"content-type=application/json"})
    @ResponseBody
    public ResponseDTO<Map<String, String>> getAdvSearchResults(@RequestBody(required = false) String[] technologies) {
        ResponseDTO<Map<String, String>> response = new ResponseDTO<Map<String, String>>();
        Map<String, String> initData = new HashMap<String, String>();
        List<ViewStatDTO> advancedSearchResults = viewStatService.getAdvancedSearchResults(technologies);
        Gson gson = new GsonBuilder().serializeNulls().create();
        LOG.info("getting data to be displayed");

        initData.put("advancedSearchResults", gson.toJson(advancedSearchResults));

        response.setSuccess(true);
        response.setResult(initData);

        return response;
    }

    @RequestMapping(value = "/viewstat/getstat", method = RequestMethod.POST, headers = {"content-type=application/json"})
    @ResponseBody
    public ResponseDTO<Map<String, String>> getTechnologyScoreMap(@RequestBody(required = false) double id) {
        LOG.info("getting data to be displayed");
        ResponseDTO<Map<String, String>> response = new ResponseDTO<Map<String, String>>();
        Map<String, String> initData = new HashMap<String, String>();
        Gson gson = new GsonBuilder().serializeNulls().create();

        initData.put("techScoreList", gson.toJson(viewStatService.getTechScoreMap(id)));

        response.setSuccess(true);
        response.setResult(initData);

        return response;
    }

    @RequestMapping(value = "/viewstat/getcomparestats", method = RequestMethod.POST, headers = {"content-type=application/json"})
    @ResponseBody
    public ResponseDTO<Map<String, String>> getCompareStats(@RequestBody(required = false) String[] technologies) {

        ResponseDTO<Map<String, String>> response = new ResponseDTO<Map<String, String>>();
        Map<String, String> initData = new HashMap<String, String>();
        List<ViewStatDTO> compareAllList = viewStatService.getCompareAllResults(technologies);
        Gson gson = new GsonBuilder().serializeNulls().create();

        initData.put("compareAllList", gson.toJson(compareAllList));

        response.setSuccess(true);
        response.setResult(initData);

        return response;
    }

    @RequestMapping(value = "/viewstat/getspiderwebdata", method = RequestMethod.POST)
    @ResponseBody
    public String getSpiderWebStats(@RequestParam("id") String id) {

        List<Map<String, Object>> spiderwebList = viewStatService.getSpiderWebResults(id);
        Gson gson = new GsonBuilder().serializeNulls().create();

        String jsonResultList = gson.toJson(spiderwebList);

        return jsonResultList;
    }
}
