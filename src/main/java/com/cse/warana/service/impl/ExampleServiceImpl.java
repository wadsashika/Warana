package com.cse.warana.service.impl;

import com.cse.warana.dao.ExampleDao;
import com.cse.warana.model.User;
import com.cse.warana.service.ExampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Created by Sashika on Oct 29 0029, 2014.
 */
@Service("exampleService")
public class ExampleServiceImpl implements ExampleService {
    private static final Logger LOG = LoggerFactory.getLogger(ExampleServiceImpl.class);

    @Autowired
    @Qualifier("exampleDao")
    private ExampleDao exampleDao;

    @Value("${GAZETEER.LIST.PATH}")
    private static String listPath;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String thisIsExample(String example) {
        LOG.info("Accessing example service started");
        User user = new User();
        user.setUserId(3);
        user.setUserName("test@warana");
        user.setPassword("password");

        exampleDao.saveEntity(user);
        user = exampleDao.getEntity(User.class,1L);
        return example + ". This is an example. New User Successfully Created "+user.getUserName();
    }
}
