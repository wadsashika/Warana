package com.cse.warana.dao.impl;

import com.cse.warana.dao.LoginDao;
import com.cse.warana.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

/**
 * Created by Sashika
 * on Dec 16 0016, 2014.
 */
@Repository("loginDao")
public class LoginDaoImpl extends BaseJPADaoImpl<User> implements LoginDao {
    private static final Logger LOG = LoggerFactory.getLogger(LoginDaoImpl.class);

    @Override
    public User getUser(String username) {
        LOG.info("Retrieving logged in user's detail started");

        String queryString = "SELECT u FROM User u WHERE u.userName = :username";

        TypedQuery<User> query = this.entityManager.createQuery(queryString, User.class);
        query.setParameter("username", username);

        LOG.info("Retrieve user detail successful");
        return query.getSingleResult();
    }
}
