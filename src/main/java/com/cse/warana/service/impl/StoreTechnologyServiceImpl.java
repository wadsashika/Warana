package com.cse.warana.service.impl;

import com.cse.warana.dao.StoreTechnologyDao;
import com.cse.warana.model.TechnologyTbl;
import com.cse.warana.service.StoreTechnologyService;
import com.cse.warana.utility.infoHolders.Technology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Thilina on 2/4/2015.
 */

@Service("storeTechnologyService")
public class StoreTechnologyServiceImpl implements StoreTechnologyService {

    private static final Logger LOG = LoggerFactory.getLogger(StoreTechnologyServiceImpl.class);

    @Autowired
    @Qualifier("storeTechnology")
    private StoreTechnologyDao storeTechnologyDao;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void storeTechnologies(List<Technology> technologies) {
        if (technologies != null) {
            for (Technology technology : technologies) {
                TechnologyTbl technologyTbl = new TechnologyTbl();
                technologyTbl.setTechnology(technology.getName());
                technologyTbl.setDescription(technology.getName());
                storeTechnologyDao.saveEntity(technologyTbl);
            }
        }
    }
}
