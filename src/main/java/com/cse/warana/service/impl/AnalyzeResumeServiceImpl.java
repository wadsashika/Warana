package com.cse.warana.service.impl;

import com.cse.warana.dao.AnalyzeResumeDao;
import com.cse.warana.dao.GetTechnologyIdDao;
import com.cse.warana.dao.StoreCandidateDao;
import com.cse.warana.dao.StoreCandidateTechnologyDao;
import com.cse.warana.dto.ResumesToAnalyseDto;
import com.cse.warana.model.CandidateTbl;
import com.cse.warana.model.TechnologyCandidateTbl;
import com.cse.warana.service.AnalyzeResumeService;
import com.cse.warana.service.GetConceptsService;
import com.cse.warana.service.GraphSimilarityService;
import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.AlgorithmComparotor;
import com.cse.warana.utility.AggregatedProfileGenerator.ProfileMaker.Skills.SkillAnalyzer;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;
import com.cse.warana.utility.infoHolders.Technology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Nadeeshaan on 12/5/2014.
 */

@Service("analyzeResumeService")
public class AnalyzeResumeServiceImpl implements AnalyzeResumeService {

    Logger LOG = LoggerFactory.getLogger(AnalyzeResumeServiceImpl.class);

    @Autowired
    @Qualifier("analyzeResumeDao")
    private AnalyzeResumeDao analyzeResumeDao;

    @Autowired
    @Qualifier("storeCandidate")
    private StoreCandidateDao storeCandidateDao;

    @Autowired
    @Qualifier("graphSimilarityService")
    private GraphSimilarityService graphSimilarityService;

    @Autowired
    @Qualifier("getConceptsService")
    private GetConceptsService getConceptsService;

    @Autowired
    @Qualifier("getTechnologyIdDao")
    private GetTechnologyIdDao getTechnologyIdDao;

    @Autowired
    @Qualifier("storeCandidateTechnology")
    private StoreCandidateTechnologyDao storeCandidateTechnologyDao;

    @Value("${warana.resources.root}")
    private String root;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<ResumesToAnalyseDto> getResumesToAnalyze() {
        LOG.info("Getting resumes to analyze");

        List<ResumesToAnalyseDto> resumesToAnalyseDtoList = analyzeResumeDao.getResumesToBeAnalyzed();
        return resumesToAnalyseDtoList;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean analyzeSelectedListOfCandidates(String[] idList) {

        double[] scoreList = new double[idList.length];


        LOG.info("Processing userdocs to get skill scores");
        Config.initialize(root);
        SkillAnalyzer skillAnalyzer = new SkillAnalyzer();
        AlgorithmComparotor comparotor = new AlgorithmComparotor();
        comparotor.AggregateAllSkills();

        for (int i = 0; i < idList.length; i++) {
            Map<String, Double> sortSkills = skillAnalyzer.SortSkills(Long.parseLong(idList[i]));
            System.out.println(idList[i]+"**************************");
            List<Technology> techListStr = new ArrayList<Technology>();

            for (String s : sortSkills.keySet()) {
                Technology technology = new Technology();
                technology.setName(s.split("\\.")[0].trim());

                techListStr.add(technology);
            }

            Map<String, Long> idTechnologyMap = getTechnologyIdDao.getTechnologyIdMap(techListStr);

            for (String tech : idTechnologyMap.keySet()) {
                TechnologyCandidateTbl technologyCandidateTbl = new TechnologyCandidateTbl();

                technologyCandidateTbl.setCandidate_id(Long.parseLong(idList[i]));
                technologyCandidateTbl.setTechnology_id(idTechnologyMap.get(tech));
                technologyCandidateTbl.setPercentage(sortSkills.get(tech));
                System.out.println(technologyCandidateTbl.getCandidate_id()+"-"+technologyCandidateTbl.getTechnology_id()+"-"+technologyCandidateTbl.getPercentage());
                storeCandidateTechnologyDao.saveEntity(technologyCandidateTbl);
            }
        }

        LOG.info("Matching the Similarity of the candidates");

        List<String> companyTechnologyList = getConceptsService.getCompanyTechnologies();
        Integer[][] companyGraph = graphSimilarityService.generateGraph(companyTechnologyList);
        for (int i = 0; i < companyGraph.length; i++) {
            for (int j = 0; j < companyGraph.length; j++) {
                System.out.print(companyGraph[i][j] + ", ");
            }
            System.out.println();
        }

        for (int i = 0; i < idList.length; i++) {

            List<String> candidateTechnologyList = analyzeResumeDao.getTechnologyListOfCandidate(Long.parseLong(idList[i]));
            Integer[][] candidateGraph = graphSimilarityService.generateGraph(candidateTechnologyList);

            for (int k = 0; k < candidateGraph.length; k++) {
                for (int j = 0; j < candidateGraph.length; j++) {
                    System.out.print(candidateGraph[k][j] + ", ");
                }
                System.out.println();
            }

            scoreList[i] = graphSimilarityService.getSimilarityScore(companyGraph, candidateGraph);
            System.out.println(scoreList[i]);
        }

        for (int a = 0; a < idList.length; a++) {
            CandidateTbl candidateTbl = storeCandidateDao.getEntity(CandidateTbl.class, new Long(idList[a]));
            candidateTbl.setScore(scoreList[a]);
        }

        return true;
    }
}