package com.cse.warana.service.impl;

import com.cse.warana.dao.ViewStatDao;
import com.cse.warana.dto.ViewStatDTO;
import com.cse.warana.service.ViewStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<String> getTechnologies() {
        List<String> technologies = null;
        technologies = viewStatDao.getTechnologiesList();

        return technologies;
    }

    @Override
    public List<ViewStatDTO> getAdvancedSearchResults(String[] technologies) {
        List<ViewStatDTO> advSearchResultDTOs = null;
        advSearchResultDTOs = viewStatDao.getAdvSearchResults(technologies);
        return advSearchResultDTOs;
    }

    @Override
    public List<ViewStatDTO> getCompareAllResults(String[] technologies) {
        List<ViewStatDTO> advSearchResultDTOs = null;
        advSearchResultDTOs = viewStatDao.getCompareAllResults(technologies);
        return advSearchResultDTOs;
    }

    @Override
    public List<Map<String,Object>> getTechScoreMap(double id) {

        List<Map<String,Object>> techScoreList = null;
        techScoreList = viewStatDao.getTechnologiesScores(id);
        return techScoreList;
    }
}
