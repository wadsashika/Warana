package com.cse.warana.service.impl;

import com.cse.warana.dao.LoginDao;
import com.cse.warana.dto.ChangePasswordDTO;
import com.cse.warana.dto.UserSignupDTO;
import com.cse.warana.model.User;
import com.cse.warana.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

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

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User updateUser(UserSignupDTO user, String username) {
        User userProfile = loginDao.getUser(username);

        userProfile.setFirstName(user.getFirstName());
        userProfile.setLastName(user.getLastName());
        userProfile.setEmail(user.getEmail());
        userProfile.setUserName(user.getEmail());

        return userProfile;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean changePassword(ChangePasswordDTO changePasswordDTO, String username) {
        User userProfile = loginDao.getUser(username);
        System.out.println(userProfile.getPassword()+"--"+changePasswordDTO.getOldPassword());
        if (userProfile.getPassword().equals(changePasswordDTO.getOldPassword())) {
            userProfile.setPassword(changePasswordDTO.getNewPassword());
            return true;
        } else {
            return false;
        }
    }
}
