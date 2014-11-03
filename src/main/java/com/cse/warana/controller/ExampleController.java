package com.cse.warana.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Sashika on Oct 30 0030, 2014.
 */
@Controller
public class ExampleController {

    @RequestMapping(value = "/example", method = RequestMethod.GET)
    public ModelAndView exampleView(){
        String EXAMPLE_VIEW = "example";
        return new ModelAndView(EXAMPLE_VIEW);
    }
}
