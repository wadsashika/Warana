package com.cse.warana.dao.impl;

import com.cse.warana.dao.StoreTechnologyDao;
import com.cse.warana.model.TechnologyTbl;
import org.springframework.stereotype.Repository;

/**
 * Created by Nadeeshaan on 1/2/2015.
 */

@Repository("storeTechnology")
public class StoreTechnologyDaoImpl extends BaseJPADaoImpl<TechnologyTbl> implements StoreTechnologyDao {
}
