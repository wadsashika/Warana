package com.cse.warana.service.impl;

import com.cse.warana.dao.SignupDao;
import com.cse.warana.dto.UserSignupDTO;
import com.cse.warana.model.User;
import com.cse.warana.service.SignupService;
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
@Service("signupService")
public class SignupServiceImpl implements SignupService {
    private static final Logger LOG = LoggerFactory.getLogger(SignupServiceImpl.class);

    @Autowired
    @Qualifier("signupDao")
    private SignupDao signupDao;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User registerUser(UserSignupDTO userSignupDTO) {
        LOG.info("Save new user in the database");
        User user = new User();

        user.setFirstName(userSignupDTO.getFirstName());
        user.setLastName(userSignupDTO.getLastName());
        user.setEmail(userSignupDTO.getEmail());
        user.setUserName(userSignupDTO.getEmail());
        user.setPassword(userSignupDTO.getPassword());
        user.setImgPath("/");
        user.setPrivilegeLvl(1);

        user = signupDao.saveEntity(user);

        return user;
    }
}
