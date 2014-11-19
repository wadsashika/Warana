package com.cse.warana.controller;

import com.cse.warana.dto.ViewStatDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nadeeshaan on 11/18/2014.
 */

@Controller
public class ViewStatController {
    private static final Logger LOG = LoggerFactory.getLogger(ViewStatController.class);

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
        Gson gson = new GsonBuilder().serializeNulls().create();
        ArrayList<ViewStatDTO> list = new ArrayList<ViewStatDTO>();
        Map<String, String> initData = new HashMap<String, String>();

        LOG.info("data init started");

        ViewStatDTO obj1 = new ViewStatDTO();
        obj1.setName("Nadeeshaan Gunasinghe");
        obj1.setEmail("nsg@gmail.com");
        obj1.setScore("79%");
        list.add(obj1);

        ViewStatDTO obj2 = new ViewStatDTO();
        obj2.setName("Nadeeshaan Gunasinghe2");
        obj2.setEmail("nsg@gmail.com");
        obj2.setScore("79%");
        list.add(obj2);

        ViewStatDTO obj3 = new ViewStatDTO();
        obj3.setName("Nadeeshaan Gunasinghe3");
        obj3.setEmail("nsg@gmail.com");
        obj3.setScore("79%");
        list.add(obj3);

        System.out.println(list.get(1).getName());
        String jsonResultList = gson.toJson(list);
        initData.put("data", gson.toJson(list));

        return jsonResultList;
    }
}
