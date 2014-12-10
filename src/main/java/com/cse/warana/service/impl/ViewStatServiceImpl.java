package com.cse.warana.service.impl;

import com.cse.warana.dao.ViewStatDao;
import com.cse.warana.dto.ViewStatDTO;
import com.cse.warana.service.ViewStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nadeeshaan on 12/10/2014.
 */

@Service("viewStatService")
public class ViewStatServiceImpl implements ViewStatService {

    @Autowired
    @Qualifier("viewStatDao")
    private ViewStatDao viewStatDao;

    @Override
    public List<ViewStatDTO> getStatisticsDtos() {

        List<ViewStatDTO> viewStatDTOs = null;
        viewStatDTOs = viewStatDao.getViewStatDaoList();

        return viewStatDTOs;
    }
}
