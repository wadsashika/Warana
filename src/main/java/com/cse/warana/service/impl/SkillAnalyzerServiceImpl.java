package com.cse.warana.service.impl;

import com.cse.warana.service.SkillAnalyzerService;
import com.cse.warana.utility.AggregatedProfileGenerator.PhraseExtractor.AlgorithmComparator;
import com.cse.warana.utility.AggregatedProfileGenerator.utils.Config;

/**
 * Created by Thilina on 1/22/2015.
 */
public class SkillAnalyzerServiceImpl implements SkillAnalyzerService {

    @Override
    public void extractSkillKeyterms() {

        AlgorithmComparator comparotor = new AlgorithmComparator();
        comparotor.ExtractTermsBatch(Config.skillsPath, Config.skillsOutputPath);
        comparotor.AggregateAllSkills();
        comparotor.Compare(Config.skillsOutputPath,Config.normalizedSkillsPath,Config.aggregatedSkillsPath, Config.abbreviationsSkillsPath);
    }

    @Override
    public void extractCandidateKeyterms() {

        AlgorithmComparator comparotor = new AlgorithmComparator();
        comparotor.ExtractTermsBatch(Config.profilesPath, Config.profilesOutputPath);
        comparotor.Compare(Config.profilesOutputPath, Config.normalizedProfilesPath, Config.aggregatedProfilesPath,Config.abbreviationsProfilesPath);
    }
}
