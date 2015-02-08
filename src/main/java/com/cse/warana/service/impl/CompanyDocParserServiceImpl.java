package com.cse.warana.service.impl;

import com.cse.warana.model.CompanyTechnology;
import com.cse.warana.service.CompanyDocParserService;
import com.cse.warana.service.CompanyTechnologyService;
import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.AlgorithmComparotor;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Anushka on 2015-02-05.
 */
@Service("companyDocParser")
public class CompanyDocParserServiceImpl implements CompanyDocParserService {
    private static String[] docLines;
    private static Logger LOG;

    @Autowired
    @Qualifier("companytechnologiesService")
    private CompanyTechnologyService companyTechnologyService;

    @Override
    public void readCompanyDoc(File file, String path) {
        PDFParser parser = null;
        String text = "";
        PDFTextStripper stripper = null;
        PDDocument pdoc = null;
        COSDocument cdoc = null;
        new File(path).mkdirs();
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
            PrintWriter writer = new PrintWriter(path + "/" + file.getName().split("\\.")[0] + ".txt", "UTF-8");
            for (String i : docLines) {
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
    }

    @Override
    public void extractDoc(String root,String companyName) {
        AlgorithmComparotor algorithmComparotor = new AlgorithmComparotor();
        Config.initialize(root);
        new File(Config.companyDocsOut+companyName).mkdirs();
        new File(Config.normalizedCompanyDocs).mkdirs();
        algorithmComparotor.ExtractTerms(Config.companyDocs + companyName, Config.companyDocsOut+companyName);
        algorithmComparotor.ExtractAbbreviations(Config.companyDocs + companyName, Config.abbreviationsCompanyPath);
        algorithmComparotor.Compare(Config.companyDocsOut, Config.normalizedCompanyDocs, Config.aggregatedCompanyDocs, Config.abbreviationsCompanyPath);

        try {
            storeTechnology(Config.aggregatedCompanyDocs + "/" + companyName + ".csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void storeTechnology(String aggregatedPath) throws FileNotFoundException {
        File file = new File(aggregatedPath);
        Scanner sc = new Scanner(file);
        String line;
        List<CompanyTechnology> list = new ArrayList<CompanyTechnology>();
        float val;
        String s;
        CompanyTechnology companyTechnology;
        while (sc.hasNextLine()) {
            companyTechnology = new CompanyTechnology();
            line = sc.nextLine();
            s = line.split(",")[0].split("\\|")[0].trim();
            val = Float.parseFloat(line.split(",")[1]);
            if (val > 0) {
                companyTechnology.setStrength(val);
                companyTechnology.setTechnology(s);
                list.add(companyTechnology);
            } else {
                break;
            }
        }
//        companyTechnologyService.storeCompanyTechnologies(list);
    }
}
