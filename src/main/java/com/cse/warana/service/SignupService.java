package com.cse.warana.service;

import com.cse.warana.dto.UserSignupDTO;
import com.cse.warana.model.User;

/**
 * Created by Sashika
 * on Dec 16 0016, 2014.
 */
public interface SignupService {

    public User registerUser(UserSignupDTO userSignupDTO);
}
