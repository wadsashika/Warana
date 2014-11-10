package com.cse.warana.controller;

import com.cse.warana.service.ExampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        String exampleServiceString = exampleService.thisIsExample("Hi all");
        modelMap.addAttribute("message", exampleServiceString);

        return EXAMPLE_VIEW;
    }
}
