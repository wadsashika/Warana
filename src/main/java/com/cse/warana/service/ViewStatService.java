package com.cse.warana.service;

import com.cse.warana.dto.ViewStatDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by Nadeeshaan on 12/10/2014.
 */
public interface ViewStatService {
    public List<ViewStatDTO> getStatisticsDtos();
    public List<String> getTechnologies();
    public List<ViewStatDTO> getAdvancedSearchResults(String[] technologies);
    public List<Map<String,Object>> getTechScoreMap(double id);
}
