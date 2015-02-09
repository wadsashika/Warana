package com.cse.warana.service.impl;

import com.cse.warana.dao.GetUploadedDocDao;
import com.cse.warana.service.GetUploadedDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Anushka on 2015-02-08.
 */
@Service("getCompanyDocUploadService")
public class GetUploadedDocServiceImpl implements GetUploadedDocService {

    @Autowired
    @Qualifier("getUploadeDocDao")
    private GetUploadedDocDao getUploadedDocDao;

    @Override
    public List<String> getUploadedDocList() {
        return getUploadedDocDao.getDocumentList();
    }
}
