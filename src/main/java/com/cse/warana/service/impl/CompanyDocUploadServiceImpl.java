package com.cse.warana.service.impl;

import com.cse.warana.dao.StoreCompanyDocDao;
import com.cse.warana.model.CompanyDocTbl;
import com.cse.warana.service.CompanyDocUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.ArrayList;

/**
 * Created by Anushka on 2015-02-05.
 */
@Service("companyDocUploadService")
public class CompanyDocUploadServiceImpl implements CompanyDocUploadService {

    @Autowired
    @Qualifier("storeUploadedCompanyDoc")
    private StoreCompanyDocDao storeCompanyDocDao;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void storeDocData(ArrayList<String> fileNames) {

        for (int a = 0; a < fileNames.size(); a++) {
            CompanyDocTbl companyDocTbl = new CompanyDocTbl();
            companyDocTbl.setFileName(fileNames.get(a));

//            storeCompanyDocDao.saveEntity(companyDocTbl);
        }
    }
}
