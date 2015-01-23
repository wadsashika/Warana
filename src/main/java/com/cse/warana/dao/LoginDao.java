package com.cse.warana.dao;

import com.cse.warana.model.User;

/**
 * Created by Sashika
 * on Dec 16 0016, 2014.
 */
public interface LoginDao  extends BaseJPADao<User>{

    public User getUser(String username);
}
