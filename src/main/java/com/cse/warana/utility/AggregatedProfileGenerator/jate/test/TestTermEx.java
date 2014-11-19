package com.cse.warana.utility.AggregatedProfileGenerator.jate.test;

import com.cse.warana.utility.AggregatedProfileGenerator.jate.JATEException;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.core.algorithm.AbstractFeatureWrapper;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.core.algorithm.Algorithm;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.core.algorithm.TermExAlgorithm;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.core.algorithm.TermExFeatureWrapper;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.core.feature.*;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.core.feature.indexer.GlobalIndexBuilderMem;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.core.feature.indexer.GlobalIndexMem;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.core.npextractor.CandidateTermExtractor;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.core.npextractor.NounPhraseExtractorOpenNLP;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.core.npextractor.WordExtractor;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.io.ResultWriter2File;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.model.CorpusImpl;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.model.Term;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.util.control.Lemmatizer;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.util.control.StopList;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.util.counter.TermFreqCounter;
import com.cse.warana.utility.AggregatedProfileGenerator.jate.util.counter.WordCounter;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class TestTermEx {

	private Map<Algorithm, AbstractFeatureWrapper> _algregistry = new HashMap<Algorithm, AbstractFeatureWrapper>();
	private static Logger _logger = Logger.getLogger(AlgorithmTester.class);

	public void registerAlgorithm(Algorithm a, AbstractFeatureWrapper f) {
		_algregistry.put(a, f);
	}

	public void execute(GlobalIndexMem index) throws JATEException, IOException {
		_logger.info("Initializing outputter, loading NP mappings...");
		ResultWriter2File writer = new ResultWriter2File(index);
		if (_algregistry.size() == 0) throw new JATEException("No algorithm registered!");
		_logger.info("Running NP recognition...");

		/*.extractNP(c);*/
		for (Map.Entry<Algorithm, AbstractFeatureWrapper> en : _algregistry.entrySet()) {
			_logger.info("Running feature store builder and ATR..." + en.getKey().toString());
			Term[] result = en.getKey().execute(en.getValue());
			writer.output(result, en.getKey().toString() + ".txt");
		}
	}

	public static void main(String[] args) throws IOException, JATEException {

		if (args.length < 2) {
			System.out.println("Usage: java TestTermEx [path_to_corpus] [path_to_reference_corpus_stats] [output_folder]");
		} else {
			System.out.println("Started "+ TestTermEx.class+"at: " + new Date() + "... For detailed progress see log file jate.log.");

			//creates instances of required processors and resources

			//stop word list
			StopList stop = new StopList(true);

			//lemmatiser
			Lemmatizer lemmatizer = new Lemmatizer();

			//noun phrase extractor, which produces candidate terms as noun phrases
			CandidateTermExtractor npextractor = new NounPhraseExtractorOpenNLP(stop, lemmatizer);
			//we also need a word extractor which produces each single word found in the corpus as required by the algorithm
			CandidateTermExtractor wordextractor = new WordExtractor(stop,lemmatizer);

			//counters
			TermFreqCounter npcounter = new TermFreqCounter();
			WordCounter wordcounter = new WordCounter();

			//create global resource index builder, which indexes global resources, such as documents and terms and their
			//relations
			GlobalIndexBuilderMem builder = new GlobalIndexBuilderMem();
			//build the global resource index of noun phrases
			GlobalIndexMem termDocIndex = builder.build(new CorpusImpl(args[0]), npextractor);
			//build the global resource index of words
			GlobalIndexMem wordDocIndex=builder.build(new CorpusImpl(args[0]), wordextractor);

			//build a feature store required by the termex algorithm, using the processors instantiated above
			FeatureDocumentTermFrequency termDocFreq =
					new FeatureBuilderDocumentTermFrequency(npcounter,wordcounter,lemmatizer).build(termDocIndex);
			FeatureCorpusTermFrequency wordFreq =
					new FeatureBuilderCorpusTermFrequency(npcounter,wordcounter,lemmatizer).build(wordDocIndex);
			FeatureRefCorpusTermFrequency bncRef =
					new FeatureBuilderRefCorpusTermFrequency(args[1]).build(null);

			AlgorithmTester tester = new AlgorithmTester();
			tester.registerAlgorithm(new TermExAlgorithm(), new TermExFeatureWrapper(termDocFreq,wordFreq,bncRef));
			tester.execute(termDocIndex, args[2]);
			System.out.println("Ended at: " + new Date());
		}
	}

}
