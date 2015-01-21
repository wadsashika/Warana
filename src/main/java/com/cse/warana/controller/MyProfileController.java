package com.cse.warana.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Sashika
 * on Jan 21 0021, 2015.
 */
@Controller
public class MyProfileController {

    private static final Logger LOG = LoggerFactory.getLogger(MyProfileController.class);

    @RequestMapping(value = "/myprofile", method = RequestMethod.GET)
    public String profileView() {
        LOG.info("Loading user profile");
        String PROFILE_VIEW = "myProfile";

        return PROFILE_VIEW;
    }
}
