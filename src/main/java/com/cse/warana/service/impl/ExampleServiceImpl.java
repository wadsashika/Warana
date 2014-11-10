package com.cse.warana.service.impl;

import com.cse.warana.service.ExampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Sashika on Oct 29 0029, 2014.
 */
@Service("exampleService")
public class ExampleServiceImpl implements ExampleService {
    private static final Logger LOG = LoggerFactory.getLogger(ExampleServiceImpl.class);

    @Override
    public String thisIsExample(String example) {
        LOG.info("Accessing example service started");
        return example+". This is an example.";
    }
}
