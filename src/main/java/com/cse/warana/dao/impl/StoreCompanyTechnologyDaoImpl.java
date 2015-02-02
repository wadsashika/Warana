package com.cse.warana.dao.impl;

import com.cse.warana.dao.StoreCompanyTechnologyDao;
import com.cse.warana.model.CompanyTechnology;
import org.springframework.stereotype.Repository;

/**
 * Created by Anushka on 2015-02-02.
 */
@Repository("companytechnologyDao")
public class StoreCompanyTechnologyDaoImpl extends BaseJPADaoImpl<CompanyTechnology> implements StoreCompanyTechnologyDao  {
}
