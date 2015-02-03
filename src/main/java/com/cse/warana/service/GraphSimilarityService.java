package com.cse.warana.service;

import java.util.List;

/**
 * Created by Sashika
 * on Jan 10 0010, 2015.
 */
public interface GraphSimilarityService {

    public Double getSimilarityScore(Integer[][] graphA, Integer[][] graphB);

    public Integer[][] generateGraph(List<String> skillList);
}
