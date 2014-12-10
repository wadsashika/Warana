package com.cse.warana.dao.impl;

import com.cse.warana.dao.BaseJPADao;
import com.cse.warana.dao.ExampleDao;
import com.cse.warana.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Sashika
 * on Dec 03 0003, 2014.
 */
@Repository("exampleDao")
public class ExampleDaoImpl extends BaseJPADaoImpl<User> implements ExampleDao{
}
