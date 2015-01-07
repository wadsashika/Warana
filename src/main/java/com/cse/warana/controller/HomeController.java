package com.cse.warana.controller;

import com.cse.warana.dto.ResponseDTO;
import com.cse.warana.dto.UserSignupDTO;
import com.cse.warana.model.User;
import com.cse.warana.service.CryptoService;
import com.cse.warana.service.SignupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Sashika on Nov 12 0012, 2014.
 */

@Controller
public class HomeController {
    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    @Qualifier("signupService")
    private SignupService signupService;

    @Autowired
    @Qualifier("cryptoService")
    private CryptoService cryptoService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView loadHomePage() {
        String HOME_VIEW = "home";
        LOG.info("Loading Home Page");
        return new ModelAndView(HOME_VIEW);
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public ModelAndView loginError(ModelMap modelMap) {
        String HOME_VIEW = "home";
        LOG.info("Logging error occurred");
        modelMap.addAttribute("error", true);
        return new ModelAndView(HOME_VIEW);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, headers = {"content-type=application/json"})
    @ResponseBody
    public ResponseDTO<UserSignupDTO> userSignup(@RequestBody(required = false) UserSignupDTO userSignupDTO) {
        ResponseDTO<UserSignupDTO> response = new ResponseDTO<UserSignupDTO>();
        LOG.debug("New User register started for [{}]", userSignupDTO);

        try {
            String encryptedPassword = cryptoService.encrypt(userSignupDTO.getPassword());
            userSignupDTO.setPassword(encryptedPassword);
            User user = signupService.registerUser(userSignupDTO);
            response.setSuccess(true);
            response.setMessage("User Register Successfully Completed");

            LOG.debug("User register end for [{}]", user.getEmail());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("User Register Failed");

            LOG.error("User register failed ", e);
        }
        LOG.debug("New User register successfully end for [{}]", userSignupDTO);
        return response;
    }
}
