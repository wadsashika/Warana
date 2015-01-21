package com.cse.warana.dao;

import com.cse.warana.dto.ViewStatDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by Nadeeshaan on 12/10/2014.
 */
public interface ViewStatDao {

    public List<ViewStatDTO> getViewStatDaoList();

    public List<String> getTechnologiesList();

    public List<ViewStatDTO> getAdvSearchResults(String[] technologies);

    public List<ViewStatDTO> getCompareAllResults(String[] technologies);

    public List<Map<String, Object>> getTechnologiesScores(double id);
}
