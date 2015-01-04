package com.cse.warana.utility.Graph.extraction;

import com.cse.warana.utility.Graph.network.NetworkConnectionManager;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Hashtable;
/**
 * Created by Anushka on 2015-01-03.
 */

public class BurnerClass {
    public NetworkConnectionManager networkConnectionManager;
    protected Hashtable<String, Boolean> wordlist;

    public BurnerClass() {
        networkConnectionManager = new NetworkConnectionManager();
        wordlist = new Hashtable<String, Boolean>();
    }

    public Document getPage(String urlString) throws IOException {
        networkConnectionManager.DomainName(urlString);
        Document mainPage = networkConnectionManager.URLgraber(urlString);
        return mainPage;
    }
}
