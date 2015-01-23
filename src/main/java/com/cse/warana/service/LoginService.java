package com.cse.warana.service;

import com.cse.warana.dto.ChangePasswordDTO;
import com.cse.warana.dto.UserSignupDTO;
import com.cse.warana.model.User;

/**
 * Created by Sashika
 * on Dec 16 0016, 2014.
 */
public interface LoginService {

    public User getUser(String username);

    public User updateUser(UserSignupDTO user, String username);

    public boolean changePassword(ChangePasswordDTO changePasswordDTO, String username);
}
