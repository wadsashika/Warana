package com.cse.warana.controller;

import com.cse.warana.dto.ExampleDTO;
import com.cse.warana.dto.ResponseDTO;
import com.cse.warana.service.ExampleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sashika on Oct 30 0030, 2014.
 */
@Controller
public class ExampleController {
    private static final Logger LOG = LoggerFactory.getLogger(ExampleController.class);

    @Autowired(required = true)
    @Qualifier(value = "exampleService")
    public ExampleService exampleService;

    @RequestMapping(value = "/example", method = RequestMethod.GET)
    public String exampleView(ModelMap modelMap) {
        LOG.info("Example Controller Started");

        String EXAMPLE_VIEW = "example";
        String exampleServiceString = exampleService.thisIsExample("Hi all",1);
        modelMap.addAttribute("message", exampleServiceString);

        return EXAMPLE_VIEW;
    }

    @RequestMapping(value = "/exampleAjax", method = RequestMethod.POST, headers = {"content-type=application/json"})
    @ResponseBody
    public ResponseDTO<Map<String, String>> init() {
        LOG.info("Ajax call initiated");

        ResponseDTO<Map<String, String>> response = new ResponseDTO<Map<String, String>>();
        Map<String, String> initData = new HashMap<String,String>();
        Gson gson = new GsonBuilder().create();

        ExampleDTO exampleDTO1 = new ExampleDTO();
        exampleDTO1.setName("Dulanga");
        exampleDTO1.setNumber(6544654);

        ExampleDTO exampleDTO2 = new ExampleDTO();
        exampleDTO1.setName("Sashika");
        exampleDTO1.setNumber(66876543);

        List<ExampleDTO> list = new ArrayList<ExampleDTO>();
        list.add(exampleDTO1);
        list.add(exampleDTO2);

        initData.put("data",gson.toJson(list));

        response.setResult(initData);
        response.setSuccess(true);

        return response;
    }
}
