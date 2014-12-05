package com.cse.warana.dao;

import com.cse.warana.dto.CredentialsDTO;

/**
 * Created by Sashika
 * on Dec 04 0004, 2014.
 */
public interface UserDao {

    public CredentialsDTO getUser(long id);

}
