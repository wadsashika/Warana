package com.cse.warana.utility.AggregatedProfileGenerator.jate.core.algorithm;

import com.cse.warana.utility.AggregatedProfileGenerator.jate.JATEException;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.model.Term;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Simple algorithm that ranks terms by their frequency in the corpus
 * </p>
 *
 * @author <a href="mailto:z.zhang@dcs.shef.ac.uk">Ziqi Zhang</a>
 */
public class FrequencyAlgorithm implements Algorithm{

	public Term[] execute(AbstractFeatureWrapper store) throws JATEException {
		if(!(store instanceof FrequencyFeatureWrapper)) throw new JATEException("" +
				"Required: FrequencyFeatureWrapper");
		FrequencyFeatureWrapper fFeatureStore = (FrequencyFeatureWrapper) store;
		Set<Term> result = new HashSet<Term>();

		for(String s: fFeatureStore.getTerms()){
/*			if(tfidfFeatureStore.getTermFreq(s)==0 || tfidfFeatureStore.getDocFreq(s)==0){
				System.out.println("ZERO: "+s+"-tf:"+tfidfFeatureStore.getTermFreq(s)+", df:"+tfidfFeatureStore.getDocFreq(s));
			}*/
			double tf =  (double)fFeatureStore.getTermFreq(s);
			result.add(new Term(s,tf));
		}

		Term[] all  = result.toArray(new Term[0]);
		Arrays.sort(all);
		return all;
	}

	public String toString(){
		return "Simple_term_frequency_ALGORITHM";
	}
}
