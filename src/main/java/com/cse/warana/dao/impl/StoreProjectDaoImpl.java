package com.cse.warana.dao.impl;

import com.cse.warana.dao.StoreProjectDao;
import com.cse.warana.model.ProjectDetailsTbl;
import org.springframework.stereotype.Repository;

/**
 * Created by Nadeeshaan on 12/14/2014.
 */

@Repository("storeProject")
public class StoreProjectDaoImpl extends BaseJPADaoImpl<ProjectDetailsTbl> implements StoreProjectDao {
}
