package com.cse.warana.utility.Graph.extraction;

import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.List;


/**
 * Created by Anushka on 2015-01-03.
 */
public class TechnologyBurn extends BurnerClass {
    public Hashtable<String, Boolean> getTechonologies() throws IOException {
        String fileName="languagefile.txt";
        boolean f = new File(Config.termsFilePath).mkdirs();
        File file=new File(Config.termsFilePath+fileName);
        if (file.exists()) {
            List<String> lines = Files.readAllLines(Paths.get(Config.termsFilePath+fileName),
                    Charset.defaultCharset());
            for (int i = 0; i < lines.size(); i++) {
                wordlist.put(lines.get(i), true);
            }
        } else {
            Document document = getPage("http://en.wikipedia.org/wiki/List_of_programming_languages");
            File fout = new File(Config.termsFilePath+fileName);
            fout.createNewFile();
            FileOutputStream fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            Elements exa = document.select("#mw-content-text table.multicol a");
            for (int i = 0; i < exa.size(); i++) {
                wordlist.put(exa.get(i).text().toLowerCase(), true);
                bw.write(exa.get(i).text().toLowerCase());
                bw.newLine();
            }
            bw.close();
        }

        return wordlist;
    }
}
