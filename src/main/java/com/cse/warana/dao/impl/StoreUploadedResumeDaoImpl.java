package com.cse.warana.dao.impl;

import com.cse.warana.dao.StoreUploadedResumeDao;
import com.cse.warana.model.UploadedResumesTbl;
import org.springframework.stereotype.Repository;

/**
 * Created by Nadeeshaan on 12/26/2014.
 */

@Repository("storeUploadedResume")
public class StoreUploadedResumeDaoImpl extends BaseJPADaoImpl<UploadedResumesTbl> implements StoreUploadedResumeDao {
}
