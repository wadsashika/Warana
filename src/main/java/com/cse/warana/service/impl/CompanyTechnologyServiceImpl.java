package com.cse.warana.service.impl;

import com.cse.warana.dao.StoreCompanyTechnologyDao;
import com.cse.warana.model.CompanyTechnology;
import com.cse.warana.service.CompanyTechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Anushka on 2015-02-02.
 */
@Service("companytechnologiesService")
public class CompanyTechnologyServiceImpl implements CompanyTechnologyService {

    @Autowired
    @Qualifier("companytechnologyDao")
    private StoreCompanyTechnologyDao companyTechnologyDao;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void storeCompanyTechnologies(List<String> technologies) {
        CompanyTechnology companyTechnology= new CompanyTechnology();
        for (String t: technologies){
            companyTechnology.setTechnology(t);
            companyTechnologyDao.saveEntity(companyTechnology);
        }

    }
}
