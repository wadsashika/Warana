package com.cse.warana.service.impl;

import com.cse.warana.dao.LoginDao;
import com.cse.warana.model.User;
import com.cse.warana.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Sashika
 * on Dec 16 0016, 2014.
 */

@Service("loginService")
public class LoginServiceImpl implements LoginService {
    private static final Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    @Qualifier("loginDao")
    private LoginDao loginDao;

    @Override
    public User getUser(String username) {
        LOG.info("Retrieving login user detail");
        return loginDao.getUser(username);
    }
}
