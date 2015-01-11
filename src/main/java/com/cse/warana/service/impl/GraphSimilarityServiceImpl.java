package com.cse.warana.service.impl;

import com.cse.warana.service.GraphSimilarityService;
import com.cse.warana.utility.Graph.SimilarityMeasure.Graph;
import com.cse.warana.utility.Graph.SimilarityMeasure.NM_Similarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Sashika
 * on Jan 10 0010, 2015.
 */
@Service("graphSimilarityService")
public class GraphSimilarityServiceImpl implements GraphSimilarityService {

    private Logger LOG = LoggerFactory.getLogger(GraphSimilarityServiceImpl.class);

    @Value("graph.similarity.epsilon")
    private Double epsilon;

    @Override
    public Double getSimilarityScore(Integer[][] graphA, Integer[][] graphB) {
        LOG.info("Measuring similarity score started");
        try {
            Graph graphX = new Graph(graphA);
            Graph graphY = new Graph(graphB);
            NM_Similarity similarityMeasure = null;

            if (graphX.getGraphSize()>graphY.getGraphSize()){
                similarityMeasure = new NM_Similarity(graphX,graphY,epsilon);
                Graph finalGraph = new Graph(similarityMeasure.setConstructedGraph());

                similarityMeasure = new NM_Similarity(graphX,finalGraph,epsilon);

                return similarityMeasure.getGraphSimilarity();
            }else {
                similarityMeasure = new NM_Similarity(graphY,graphX,epsilon);
                Graph finalGraph = new Graph(similarityMeasure.setConstructedGraph());

                similarityMeasure = new NM_Similarity(graphY,finalGraph,epsilon);

                return similarityMeasure.getGraphSimilarity();
            }
        } catch (Exception e) {
            LOG.error("Measuring similarity score error occurred",e);
        }
        return null;
    }
}
