package com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor;

import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;

/**
 * Created by Thilina on 12/21/2014.
 */
public class WeightLearner {

    public Double UpdateWeight(double precisionDifference, Double prevWeight) {
        double newWeight= Config.learning_rate*precisionDifference+prevWeight;
        if (newWeight>0){
            return newWeight;
        }
        else {
            return 0.0;
        }
    }
}
