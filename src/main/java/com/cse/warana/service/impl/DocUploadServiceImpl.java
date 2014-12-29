package com.cse.warana.service.impl;

import com.cse.warana.dao.StoreUploadedResumeDao;
import com.cse.warana.dao.impl.StoreUploadedResumeDaoImpl;
import com.cse.warana.model.UploadedResumesTbl;
import com.cse.warana.service.DocUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.ArrayList;

/**
 * Created by Nadeeshaan on 12/26/2014.
 */

@Service("storeUploadedResumeService")
public class DocUploadServiceImpl implements DocUploadService {

    @Autowired
    @Qualifier("storeUploadedResume")
    private StoreUploadedResumeDaoImpl storeUploadedResumeDao;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void storeDocData(ArrayList<String> fileNames) {

        for (int a = 0; a<fileNames.size(); a++){
            UploadedResumesTbl uploadedResumesTbl = new UploadedResumesTbl();
            uploadedResumesTbl.setFileName(fileNames.get(a));
            uploadedResumesTbl.setStatus("NOT PROCESSED");

            storeUploadedResumeDao.saveEntity(uploadedResumesTbl);
        }
    }
}
