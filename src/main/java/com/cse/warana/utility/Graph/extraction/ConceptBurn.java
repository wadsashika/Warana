package com.cse.warana.utility.Graph.extraction;

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
public class ConceptBurn extends BurnerClass {
    String[] list = {"web development", "augmented reality", ".Net", "artificial intelligence", "sql", "game development", "ruby", "C", "uml", "object oriented design", "Ajax", "user experience", "XML", "software engineering", "android development", "Entrepreneurship", "project management", "graphic design", "computer science"};

    public ConceptBurn(String root) {
        super(root);
    }

    public Hashtable<String, Boolean> getConcepts() throws IOException {
        String fileName = "conceptfile.txt";
        boolean f = new File(termsFilePath).mkdirs();
        File file = new File(termsFilePath + fileName);
        if (file.exists()) {
            List<String> lines = Files.readAllLines(Paths.get(termsFilePath + fileName),
                    Charset.defaultCharset());
            for (int i = 0; i < lines.size(); i++) {
//                System.out.println(lines.get(i));
                wordlist.put(lines.get(i), true);
            }
        } else {
            Document document = getPage("http://en.wikipedia.org/wiki/Outline_of_computer_science");
            File fout = new File(termsFilePath + fileName);
            fout.createNewFile();
            FileOutputStream fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            Elements exa = document.select("#bodyContent #mw-content-text ul>li a");

            for (int i = 23; i < exa.size(); i++) {
                if ("Data structure".contains(exa.get(i).text())) {
                    break;
                }
                wordlist.put(exa.get(i).text().toLowerCase(), true);
                bw.write(exa.get(i).text().toLowerCase());
                bw.newLine();
            }

            for (int i = 0; i < list.length; i++) {
                wordlist.put(list[i].toLowerCase(), true);
                bw.write(list[i].toLowerCase());
                bw.newLine();
            }
            bw.close();
        }
        return wordlist;
    }
}
