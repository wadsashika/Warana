package com.cse.warana.dao;

import com.cse.warana.utility.infoHolders.Technology;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nadeeshaan on 1/2/2015.
 */
public interface GetTechnologyIdDao {
    public List<String> getCurrentTechnologyIds(ArrayList<Technology> technologies);
}
