package com.cse.warana.service.impl;

import com.cse.warana.service.ExampleService;
import org.springframework.stereotype.Service;

/**
 * Created by Sashika on Oct 29 0029, 2014.
 */
@Service("exampleService")
public class ExampleServiceImpl implements ExampleService {
    @Override
    public String thisIsExample(String example) {
        return null;
    }
}
