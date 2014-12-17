package com.cse.warana.dao.impl;

import com.cse.warana.dao.SignupDao;
import com.cse.warana.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Sashika
 * on Dec 16 0016, 2014.
 */
@Repository("signupDao")
public class SignupDaoImpl extends BaseJPADaoImpl<User> implements SignupDao{
}
