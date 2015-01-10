package com.cse.warana.utility.Graph.SimilarityMeasure;

/**
 * Created by Sashika on 8/26/2014.
 */
public class Main {

    public static void main(String[] args) {
        try {
            Integer[][] graphASource = new Integer[][]{{0, 2, 0, 0, 0, 0},
                    {0, 0, 0, 2, 2, 0},
                    {0, 0, 0, 2, 0, 0},
                    {0, 0, 0, 0, 2, 0},
                    {0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0}};
            /*Integer[][] graphBSource = new Integer[][]{{0, 2, 0, 0, 0, 0},
                    {0, 0, 0, 2, 0, 0},
                    {0, 0, 0, 2, 0, 0},
                    {0, 0, 0, 0, 2, 0},
                    {0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0}};*/

            Integer[][] graphBSource = new Integer[][]{{0, 2, 0},
                    {0, 0, 2},
                    {0, 0, 0}};

            Integer[][] graphCSource = new Integer[][]{{0, 2, 2, 2, 2, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0}};

            /*Integer[][] graphDSource = new Integer[][]{{0, 2, 2, 1, 2, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0}};*/

            Integer[][] graphDSource = new Integer[][]{{0, 2, 2, 2, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 2, 2, 2, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0}};

            Integer[][] graphESource = new Integer[][]{{0, 2, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 2, 0, 0, 0},
                    {0, 0, 2, 0, 0},
                    {2, 0, 0, 2, 0}};

            Integer[][] graphFSource = new Integer[][]{{0, 2, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 2, 0, 0, 0},
                    {0, 0, 2, 0, 0},
                    {0, 0, 0, 2, 0}};

            Integer[][] graphGSource = new Integer[][]{{0, 2, 0, 2, 0},
                    {0, 0, 2, 0, 0},
                    {2, 0, 0, 2, 0},
                    {0, 2, 0, 0, 0},
                    {2, 0, 2, 2, 0}};

            Integer[][] graphHSource = new Integer[][]{{0, 2, 0, 2, 0},
                    {0, 0, 2, 0, 0},
                    {0, 0, 0, 2, 0},
                    {0, 2, 0, 0, 0},
                    {2, 0, 2, 2, 0}};

            Graph graphA = new Graph(graphASource);
            Graph graphB = new Graph(graphBSource);
            Graph graphC = new Graph(graphCSource);
            Graph graphD = new Graph(graphDSource);
            Graph graphE = new Graph(graphESource);
            Graph graphF = new Graph(graphFSource);
            Graph graphG = new Graph(graphGSource);
            Graph graphH = new Graph(graphHSource);

            NM_Similarity similarityMeasure = new NM_Similarity(graphA, graphB, 0.0001);
            Graph finalGraph = new Graph(similarityMeasure.setConstructedGraph());

            similarityMeasure = new NM_Similarity(graphA, finalGraph, 0.0001);

            System.out.println("\nTwo graphs have " + similarityMeasure.getGraphSimilarity() + "% of similarity");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
