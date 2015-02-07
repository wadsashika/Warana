package com.cse.warana.dao.impl;

import com.cse.warana.dao.StoreCompanyDocDao;
import com.cse.warana.model.CompanyDocTbl;
import org.springframework.stereotype.Repository;

/**
 * Created by Anushka on 2015-02-05.
 */
@Repository("storeUploadedCompanyDoc")
public class StoreCompanyDocDaoImpl extends BaseJPADaoImpl<CompanyDocTbl> implements StoreCompanyDocDao {
}
