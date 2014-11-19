package com.cse.warana.utility.AggregatedProfileGenerator.jate.core.feature;

import com.cse.warana.utility.AggregatedProfileGenerator.jate.JATEException;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.core.feature.indexer.GlobalIndex;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.util.control.Normalizer;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.util.counter.TermFreqCounter;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.util.counter.WordCounter;

/**
 * Feature builder that builds a feature from a GlobalIndex
 *
 * @author <a href="mailto:z.zhang@dcs.shef.ac.uk">Ziqi Zhang</a>
 */



public abstract class AbstractFeatureBuilder {

	public TermFreqCounter _termFreqCounter;// = new NPFreqCounter();
	public Normalizer _normaliser; //= new Lemmatizer();
	public WordCounter _wordCounter;// = new WordCounter();

	/**
	 * Creates an instance
	 *
	 * @param counter1   an instance of term frequency counter
	 * @param counter2   an instance of word counter
	 * @param normaliser an instance of Normalizer, which returns a candidate term to its canonical form
	 */
	public AbstractFeatureBuilder(TermFreqCounter counter1, WordCounter counter2, Normalizer normaliser) {
		_termFreqCounter = counter1;
		_wordCounter = counter2;
		_normaliser = normaliser;
	}

	public abstract AbstractFeature build(GlobalIndex index) throws JATEException;


}
