package com.cse.warana.service.impl;

import com.cse.warana.service.SkillAnalyzerService;
import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.AlgorithmComparotor;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;

/**
 * Created by Thilina on 1/22/2015.
 */
public class SkillAnalyzerServiceImpl implements SkillAnalyzerService {

    @Override
    public void extractSkillKeyterms() {

        AlgorithmComparotor comparotor = new AlgorithmComparotor();
        comparotor.ExtractTerms(Config.skillsPath,Config.skillsOutputPath);
        comparotor.Compare(Config.skillsOutputPath,Config.normalizedSkillsPath,Config.aggregatedSkillsPath, Config.abbreviationsSkillsPath);
    }

    @Override
    public void extractCandidateKeyterms() {

        AlgorithmComparotor comparotor = new AlgorithmComparotor();
        comparotor.ExtractTerms(Config.profilesPath,Config.profilesOutputPath);
        comparotor.Compare(Config.profilesOutputPath, Config.normalizedProfilesPath, Config.aggregatedProfilesPath,Config.abbreviationsProfilesPath);
    }
}
