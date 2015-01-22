package com.cse.warana.controller;

import com.cse.warana.constants.SessionConstants;
import com.cse.warana.dto.ResponseDTO;
import com.cse.warana.service.LoginService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sashika
 * on Jan 21 0021, 2015.
 */
@Controller
public class MyProfileController {

    private static final Logger LOG = LoggerFactory.getLogger(MyProfileController.class);

    @Autowired
    @Qualifier("loginService")
    private LoginService loginService;

    @RequestMapping(value = "/myprofile", method = RequestMethod.GET)
    public String profileView() {
        LOG.info("Loading user profile");
        String PROFILE_VIEW = "myProfile";

        return PROFILE_VIEW;
    }

    @RequestMapping(value = "/myprofile/init", method = RequestMethod.POST,  headers = {"content-type=application/json"})
    @ResponseBody
    public ResponseDTO<Map<String,String>> profileInit (HttpServletRequest request){
        ResponseDTO<Map<String, String>> response = new ResponseDTO<Map<String, String>>();
        Map<String, String> initData = new HashMap<String, String>();
        Gson gson = new GsonBuilder().serializeNulls().create();

        LOG.info("User profile init started");
        HttpSession httpSession = request.getSession();
        String userName = (String) httpSession.getAttribute(SessionConstants.USER_NAME);

        initData.put("userProfile", gson.toJson(loginService.getUser(userName)));

        response.setSuccess(true);
        response.setResult(initData);

        return response;
    }
}
