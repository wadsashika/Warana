package com.cse.warana.utility.AggregatedProfileGenerator.jate.core.feature.indexer;

import com.cse.warana.utility.AggregatedProfileGenerator.jate.JATEException;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.core.npextractor.CandidateTermExtractor;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.model.Corpus;

/**
 * Build an instance of GlobalIndex
 */
public interface GlobalIndexBuilder {

    public GlobalIndex build(Corpus c, CandidateTermExtractor extractor) throws JATEException;
}
