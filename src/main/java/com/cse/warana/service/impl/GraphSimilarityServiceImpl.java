package com.cse.warana.service.impl;

import com.cse.warana.service.GraphSimilarityService;
import com.cse.warana.utility.Graph.SimilarityMeasure.Graph;
import com.cse.warana.utility.Graph.SimilarityMeasure.NM_Similarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sashika
 * on Jan 10 0010, 2015.
 */
@Service("graphSimilarityService")
public class GraphSimilarityServiceImpl implements GraphSimilarityService {

    private Logger LOG = LoggerFactory.getLogger(GraphSimilarityServiceImpl.class);

    private Double epsilon = 0.0001;

    @Override
    public Double getSimilarityScore(Integer[][] graphA, Integer[][] graphB) {
        LOG.info("Measuring similarity score started");
        try {
            Graph graphX = new Graph(graphA);
            Graph graphY = new Graph(graphB);
            NM_Similarity similarityMeasure = null;

            if (graphX.getGraphSize() > graphY.getGraphSize()) {
                similarityMeasure = new NM_Similarity(graphX, graphY, epsilon);
                Graph finalGraph = new Graph(similarityMeasure.setConstructedGraph());

                similarityMeasure = new NM_Similarity(graphX, finalGraph, epsilon);

                return similarityMeasure.getGraphSimilarity();
            } else {
                similarityMeasure = new NM_Similarity(graphY, graphX, epsilon);
                Graph finalGraph = new Graph(similarityMeasure.setConstructedGraph());

                similarityMeasure = new NM_Similarity(graphY, finalGraph, epsilon);

                return similarityMeasure.getGraphSimilarity();
            }
        } catch (Exception e) {
            LOG.error("Measuring similarity score error occurred", e);
        }
        return null;
    }

    @Override
    public Integer[][] generateGraph(List<String> skillList) {
        LOG.info("Generating a graph started");
        int graphSize = skillList.size() + 2;
        Integer[][] graph = new Integer[graphSize][graphSize];

        graph[0][1] = 2;

        for (int i = 2; i < graphSize; i++) {
            graph[1][i] = 2;
        }

        for (int i = 0; i < graphSize; i++) {
            for (int j = 0; j < graphSize; j++) {
                if (graph[i][j] == null) {
                    graph[i][j] = 0;
                }
            }
        }
        LOG.info("Generating a graph ended");

        return graph;
    }
}
