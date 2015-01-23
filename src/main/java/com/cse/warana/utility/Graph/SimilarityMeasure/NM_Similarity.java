package com.cse.warana.utility.Graph.SimilarityMeasure;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sashika
 * on 8/26/2014.
 */
public class NM_Similarity {
    private Graph graphA;
    private Graph graphB;
    private List<List<Integer>> inNodeListA;
    private List<List<Integer>> outNodeListA;
    private List<List<Integer>> inNodeListB;
    private List<List<Integer>> outNodeListB;
    private Double[][] nodeSimilarity;
    private Double[][] inNodeSimilarity;
    private Double[][] outNodeSimilarity;
    private Integer[][] constructedGraph;
    private Double epsilon;
    private int graphSizeA;
    private int graphSizeB;
    private List<Integer> duplicateListIn = new ArrayList<Integer>();
    private List<Integer> duplicateListOut = new ArrayList<Integer>();

    public NM_Similarity(Graph graphA, Graph graphB, Double epsilon) {
        try {
            this.graphA = graphA;
            this.graphB = graphB;
            this.epsilon = epsilon;
            this.inNodeListA = graphA.getInDegreeNodeList();
            this.outNodeListA = graphA.getOutDegreeNodeList();
            this.inNodeListB = graphB.getInDegreeNodeList();
            this.outNodeListB = graphB.getOutDegreeNodeList();

            this.graphSizeA = graphA.getGraphSize();
            this.graphSizeB = graphB.getGraphSize();

            this.nodeSimilarity = new Double[graphSizeA][graphSizeB];
            this.inNodeSimilarity = new Double[graphSizeA][graphSizeB];
            this.outNodeSimilarity = new Double[graphSizeA][graphSizeB];
            this.constructedGraph = new Integer[graphSizeA][graphSizeA];

            initializeSimilarityMatrices();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initializeSimilarityMatrices() {
        for (int i = 0; i < graphSizeA; i++) {
            for (int j = 0; j < graphSizeB; j++) {
                double graphAInWeightSum = getWeightSum(inNodeListA.get(i), i, graphA, 0);
                double graphBInWeightSum = getWeightSum(inNodeListB.get(j), j, graphB, 0);
                double graphAOutWeightSum = getWeightSum(outNodeListA.get(i), i, graphA, 1);
                double graphBOutWeightSum = getWeightSum(outNodeListB.get(j), j, graphB, 1);

                Double maxDegree = Math.max(graphAInWeightSum, graphBInWeightSum);
                if (maxDegree != 0) {
                    inNodeSimilarity[i][j] = ((Math.min(graphAInWeightSum, graphBInWeightSum)) / (maxDegree));
                } else {
                    inNodeSimilarity[i][j] = (double) -1;
                }

                maxDegree = Math.max(graphAOutWeightSum, graphBOutWeightSum);
                if (maxDegree != 0) {
                    outNodeSimilarity[i][j] = ((Math.min(graphAOutWeightSum, graphBOutWeightSum)) / (maxDegree));
                } else {
                    outNodeSimilarity[i][j] = (double) -1;
                }
            }
        }

        for (int i = 0; i < graphSizeA; i++) {
            for (int j = 0; j < graphSizeB; j++) {
                if (inNodeSimilarity[i][j] == -1) {
                    nodeSimilarity[i][j] = outNodeSimilarity[i][j];
                } else if (outNodeSimilarity[i][j] == -1) {
                    nodeSimilarity[i][j] = inNodeSimilarity[i][j];
                } else {
                    nodeSimilarity[i][j] = (inNodeSimilarity[i][j] + outNodeSimilarity[i][j]) / 2;
                }
            }
        }
    }

    public void measureSimilarity() {
        double maxDifference = 0.0;
        boolean terminate = false;
        int count = 0;
        while (!terminate) {
            count++;
            maxDifference = 0.0;
            for (int i = 0; i < graphSizeA; i++) {
                for (int j = 0; j < graphSizeB; j++) {
                    //calculate in-degree similarities
                    double similaritySum = 0.0;
                    double graphAInWeightSum = getWeightSum(inNodeListA.get(i), i, graphA, 0);
                    double graphBInWeightSum = getWeightSum(inNodeListB.get(j), j, graphB, 0);
                    double graphAOutWeightSum = getWeightSum(outNodeListA.get(i), i, graphA, 1);
                    double graphBOutWeightSum = getWeightSum(outNodeListB.get(j), j, graphB, 1);

                    double maxDegree = Math.max(graphAInWeightSum, graphBInWeightSum);
                    double minDegree = Math.min(graphAInWeightSum, graphBInWeightSum);

                    if (minDegree == graphAInWeightSum) {
                        similaritySum = enumerationFunction(inNodeListA.get(i), inNodeListB.get(j), i, 0, 0);
                    } else {
                        similaritySum = enumerationFunction(inNodeListB.get(j), inNodeListA.get(i), j, 0, 1);
                    }
                    if (maxDegree == 0.0 && similaritySum == 0.0) {
                        inNodeSimilarity[i][j] = 1.0;
                    } else {
                        inNodeSimilarity[i][j] = similaritySum / maxDegree;
                    }

                    //calculate out-degree similarities
                    similaritySum = 0.0;
                    maxDegree = Math.max(graphAOutWeightSum, graphBOutWeightSum);
                    minDegree = Math.min(graphAOutWeightSum, graphBOutWeightSum);
                    if (minDegree == graphAOutWeightSum) {
                        similaritySum = enumerationFunction(outNodeListA.get(i), outNodeListB.get(j), i, 1, 0);
                    } else {
                        similaritySum = enumerationFunction(outNodeListB.get(j), outNodeListA.get(i), j, 1, 1);
                    }
                    if (maxDegree == 0.0 && similaritySum == 0.0) {
                        outNodeSimilarity[i][j] = 1.0;
                    } else {
                        outNodeSimilarity[i][j] = similaritySum / maxDegree;
                    }

                }
            }

            for (int i = 0; i < graphSizeA; i++) {
                for (int j = 0; j < graphSizeB; j++) {
                    double temp = (inNodeSimilarity[i][j] + outNodeSimilarity[i][j]) / 2;
                    if (Math.abs(nodeSimilarity[i][j] - temp) > maxDifference) {
                        maxDifference = Math.abs(nodeSimilarity[i][j] - temp);
                    }
                    nodeSimilarity[i][j] = temp;
                }
            }

            if (maxDifference < epsilon || count > 100000) {
                terminate = true;
            }
        }
        DecimalFormat f = new DecimalFormat("0.000");

        for (int i = 0; i < graphSizeA; i++) {
            for (int j = 0; j < graphSizeB; j++) {
                nodeSimilarity[i][j] = Double.valueOf(f.format(nodeSimilarity[i][j]));
            }
        }
    }

    public double enumerationFunction(List<Integer> neighborListMin, List<Integer> neighborListMax, int index, int inOut, int graph) {
        double similaritySum = 0.0;
        Map<Integer, Double> valueMap = new HashMap<Integer, Double>();
        if (graph == 0) {
            for (int i = 0; i < neighborListMin.size(); i++) {
                int node = neighborListMin.get(i);
                double max = 0.0;
                int maxIndex = -1;
                for (int j = 0; j < neighborListMax.size(); j++) {
                    int key = neighborListMax.get(j);
                    if (!valueMap.containsKey(key)) {
                        if (max < nodeSimilarity[node][key]) {
                            if (inOut == 0) {
                                max = nodeSimilarity[node][key] * graphA.getGraph()[node][index];
                            } else {
                                max = nodeSimilarity[node][key] * graphA.getGraph()[index][node];
                            }
                            maxIndex = key;
                        }
                    }
                }
                valueMap.put(maxIndex, max);
            }
        } else {
            for (int i = 0; i < neighborListMin.size(); i++) {
                int node = neighborListMin.get(i);
                double max = 0.0;
                int maxIndex = -1;
                for (int j = 0; j < neighborListMax.size(); j++) {
                    int key = neighborListMax.get(j);
                    if (!valueMap.containsKey(key)) {
                        if (max < nodeSimilarity[key][node]) {
                            if (inOut == 0) {
                                max = nodeSimilarity[key][node] * graphB.getGraph()[node][index];
                            } else {
                                max = nodeSimilarity[key][node] * graphB.getGraph()[index][node];
                            }
                            maxIndex = key;
                        }
                    }
                }
                valueMap.put(maxIndex, max);
            }
        }

        for (double value : valueMap.values()) {
            similaritySum += value;
        }
        return similaritySum;
    }

    public double outputEnumerationFunction(List<Integer> neighborListMin, List<Integer> neighborListMax) {
        double similaritySum = 0.0;
        Map<Integer, Double> valueMap = new HashMap<Integer, Double>();

        for (int i = 0; i < neighborListMin.size(); i++) {
            int node = neighborListMin.get(i);
            double max = 0.0;
            int maxIndex = -1;
            for (int j = 0; j < neighborListMax.size(); j++) {
                int key = neighborListMax.get(j);
                if (!valueMap.containsKey(key)) {
                    if (max < nodeSimilarity[key][node]) {
                        max = nodeSimilarity[key][node];
                        maxIndex = key;
                    }
                }
            }
            valueMap.put(maxIndex, max);
        }

        for (double value : valueMap.values()) {
            similaritySum += value;
        }
        return similaritySum;
    }

    public Double getWeightSum(List<Integer> list, int i, Graph graph, int inOrOut) {
        Double sum = 0.0;

        if (inOrOut == 1) {
            //out degree weight sum
            for (Integer item : list) {
                sum += graph.getGraph()[i][item];
            }
        } else {
            //in degree weight sum
            for (Integer item : list) {
                sum += graph.getGraph()[item][i];
            }
        }

        return sum;
    }

    public void extractSubGraph(int indexA, int indexB) {
        Map<Integer, Integer> inNodeMap = getNodeMap(indexA, indexB, 0);
        Map<Integer, Integer> outNodeMap = getNodeMap(indexA, indexB, 1);

        /*Remove in node edges which are already added*/
        duplicateListIn.clear();

        for (int key : inNodeMap.keySet()) {
            if (constructedGraph[inNodeMap.get(key)][indexA] != null) {
                duplicateListIn.add(key);
            }
        }

        for (int i : duplicateListIn) {
            inNodeMap.remove(i);
        }

        /*Remove out node edges which are already added*/
        duplicateListOut.clear();

        for (int key : outNodeMap.keySet()) {
            if (constructedGraph[indexA][outNodeMap.get(key)] != null) {
                duplicateListOut.add(key);
            }
        }

        for (int i : duplicateListOut) {
            outNodeMap.remove(i);
        }

        /*check for termination condition and do the task*/
        if (inNodeMap.isEmpty() && outNodeMap.isEmpty()) {
            return;
        } else {
            for (int value : inNodeMap.values()) {
                if (constructedGraph[value][indexA] == null) {
                    constructedGraph[value][indexA] = 2;
                }
            }

            for (int value : outNodeMap.values()) {
                if (constructedGraph[indexA][value] == null) {
                    constructedGraph[indexA][value] = 2;
                }
            }

            for (int key : inNodeMap.keySet()) {
                extractSubGraph(inNodeMap.get(key), key);
            }

            for (int key : outNodeMap.keySet()) {
                extractSubGraph(outNodeMap.get(key), key);
            }
        }

    }

    public Map<Integer, Integer> getNodeMap(int indexA, int indexB, int inOrOut) {
        Map<Integer, Integer> outPutMap = new HashMap<Integer, Integer>();
        List<Integer> graphANodeList = null;
        List<Integer> graphBNodeList = null;

        if (inOrOut == 0) {
            graphANodeList = graphA.getInDegreeNodeList().get(indexA);
            graphBNodeList = graphB.getInDegreeNodeList().get(indexB);
        } else {
            graphANodeList = graphA.getOutDegreeNodeList().get(indexA);
            graphBNodeList = graphB.getOutDegreeNodeList().get(indexB);
        }

        int a = graphANodeList.size();
        int b = graphBNodeList.size();
        boolean[] isUsed = null;
        if (a >= b) {
            isUsed = new boolean[graphSizeA];
            for (int i = 0; i < b; i++) {
                int node = graphBNodeList.get(i);
                double maxValue = 0.0;
                int maxIndex = -1;
                for (int j = 0; j < a; j++) {
                    int key = graphANodeList.get(j);
                    if (!isUsed[key] && maxValue < nodeSimilarity[key][node]) {
                        maxValue = nodeSimilarity[key][node];
                        maxIndex = key;
                    }
                }
                outPutMap.put(node, maxIndex);
                isUsed[maxIndex] = true;
            }
        } else {
            isUsed = new boolean[graphSizeB];
            for (int i = 0; i < a; i++) {
                int node = graphANodeList.get(i);
                double maxValue = 0.0;
                int maxIndex = -1;
                for (int j = 0; j < b; j++) {
                    int key = graphBNodeList.get(j);
                    if (!isUsed[key] && maxValue < nodeSimilarity[node][key]) {
                        maxValue = nodeSimilarity[node][key];
                        maxIndex = key;
                    }
                }
                outPutMap.put(maxIndex, node);
                isUsed[maxIndex] = true;
            }
        }

        return outPutMap;
    }

    public void setMaximumSimilaritySubGraph() {
        int indexA = -1;
        int indexB = -1;
        double maxValue = 0.0;

        for (int i = 0; i < graphSizeA; i++) {
            for (int j = 0; j < graphSizeB; j++) {
                if (maxValue < nodeSimilarity[i][j]) {
                    maxValue = nodeSimilarity[i][j];
                    indexA = i;
                    indexB = j;
                }
            }
        }
        extractSubGraph(indexA, indexB);
    }

    public void constructLargerGraph() {
        Integer[][] graphAMatrix = graphA.getGraph();
        for (int i = 0; i < graphSizeA; i++) {
            for (int j = 0; j < graphSizeA; j++) {
                if (graphAMatrix[i][j] == 2 && constructedGraph[i][j] == null) {
                    constructedGraph[i][j] = 1;
                } else if (graphAMatrix[i][j] == 0 && constructedGraph[i][j] == null) {
                    constructedGraph[i][j] = 0;
                }
            }
        }
    }

    public Integer[][] setConstructedGraph() {
        measureSimilarity();

        setMaximumSimilaritySubGraph();

        constructLargerGraph();

        return constructedGraph;
    }

    public int getChangedEdgeCount() {
        Integer[][] graphBMatrix = graphB.getGraph();
        int changedEdgeCount = 0;
        for (int i = 0; i < graphSizeB; i++) {
            for (int j = 0; j < graphSizeB; j++) {
                if (graphBMatrix[i][j] == 1) {
                    changedEdgeCount++;
                }
            }
        }

        if (changedEdgeCount == 0) {
            changedEdgeCount = 1;
        }
        return changedEdgeCount;
    }

    public Double getGraphSimilarity() {
        Double finalGraphSimilarity = 0.0;
        DecimalFormat f = new DecimalFormat("0.000");
        measureSimilarity();

        finalGraphSimilarity = outputEnumerationFunction(graphB.getNodeList(), graphA.getNodeList()) / graphSizeB;

        int changedEdgeCount = getChangedEdgeCount();

        finalGraphSimilarity = Double.valueOf(f.format(finalGraphSimilarity * (1.0 / changedEdgeCount) * 100));
        return finalGraphSimilarity;
    }

}
