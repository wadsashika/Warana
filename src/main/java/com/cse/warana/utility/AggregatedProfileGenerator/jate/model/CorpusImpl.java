package com.cse.warana.utility.AggregatedProfileGenerator.jate.model;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author <a href="mailto:z.zhang@dcs.shef.ac.uk">Ziqi Zhang</a>
 */


public class CorpusImpl implements Corpus {

	private Set<Document> _docs;

	public CorpusImpl(){
		_docs = new HashSet<Document>();
	}

	public Iterator<Document> iterator(){
		return _docs.iterator();
	}

	public CorpusImpl(String path){
		_docs=new HashSet<Document>();
        System.out.println(path);
        File targetFolder = new File(path);
		File[] files = targetFolder.listFiles();
		for (File f : files) {
			try {
				this.add(new DocumentImpl(f.toURI().toURL()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}//for
	}

   public boolean add(Document document){
	   return _docs.add(document);
   }

   public boolean contains(Document document){
	   return _docs.contains(document);
   }

   public int size() {
      return _docs.size();
   }

   public boolean remove(Document doc){
	   return _docs.remove(doc);
   }

}
