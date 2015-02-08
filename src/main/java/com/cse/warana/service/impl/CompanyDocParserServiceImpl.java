package com.cse.warana.service.impl;

import com.cse.warana.service.CompanyDocParserService;
import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.AlgorithmComparotor;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Anushka on 2015-02-05.
 */
@Service("companyDocParser")
public class CompanyDocParserServiceImpl implements CompanyDocParserService {
    private static String[] docLines;
    private static Logger LOG;

    @Override
    public void readCompanyDoc(File file, String path, String outPath) {
        PDFParser parser = null;
        String text = "";
        PDFTextStripper stripper = null;
        PDDocument pdoc = null;
        COSDocument cdoc = null;
        new File(path).mkdirs();
        new File(outPath).mkdirs();
        try {
            parser = new PDFParser(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            parser.parse();
            cdoc = parser.getDocument();
            stripper = new PDFTextStripper();
            pdoc = new PDDocument(cdoc);
            stripper.setStartPage(1);
            stripper.setEndPage(pdoc.getNumberOfPages());
            text = stripper.getText(pdoc);

            docLines = text.split("(\r\n)");
            PrintWriter writer = new PrintWriter(path+"/"+file.getName().split("\\.")[0]+".txt", "UTF-8");
            for (String i:docLines) {
                writer.println(i);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                cdoc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        AlgorithmComparotor algorithmComparotor= new AlgorithmComparotor();
        algorithmComparotor.ExtractTermsBatch(path, outPath);

    }
}
