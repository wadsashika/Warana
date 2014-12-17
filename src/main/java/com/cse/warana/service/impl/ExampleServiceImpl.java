package com.cse.warana.service.impl;

import com.cse.warana.service.ExampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Created by Sashika on Oct 29 0029, 2014.
 */
@Service("exampleService")
public class ExampleServiceImpl implements ExampleService {
    private static final Logger LOG = LoggerFactory.getLogger(ExampleServiceImpl.class);

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String thisIsExample(String example, long id) {
        LOG.info("Accessing example service started");
        return example + ". This is an example. New User Successfully Created ";
    }
}
