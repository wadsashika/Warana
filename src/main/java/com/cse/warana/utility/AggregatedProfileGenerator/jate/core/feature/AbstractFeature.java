package com.cse.warana.utility.AggregatedProfileGenerator.jate.core.feature;

import com.cse.warana.utility.AggregatedProfileGenerator.jate.core.feature.indexer.GlobalIndex;

/**
 * Representation of a term recognition feature built on an instance of GlobalIndex
 *
 * @author <a href="mailto:z.zhang@dcs.shef.ac.uk">Ziqi Zhang</a>
 */



public abstract class AbstractFeature {
	public GlobalIndex _index;

	public GlobalIndex getGlobalIndex(){
		return _index;
	}

	public String toString(){
		return this.getClass().toString();
	}
}
