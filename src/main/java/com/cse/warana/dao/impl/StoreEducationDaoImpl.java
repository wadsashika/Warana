package com.cse.warana.dao.impl;

import com.cse.warana.dao.StoreEducationDao;
import com.cse.warana.model.EducationalTbl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by Nadeeshaan on 12/13/2014.
 */

@Repository("storeEducational")
public class StoreEducationDaoImpl extends BaseJPADaoImpl<EducationalTbl> implements StoreEducationDao {
}
