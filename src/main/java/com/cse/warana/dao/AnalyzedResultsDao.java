package com.cse.warana.dao;

import com.cse.warana.dto.*;

import java.util.List;

/**
 * Created by Nadeeshaan on 12/10/2014.
 */
public interface AnalyzedResultsDao {
    public List<AnalyticResultsDTO> getAnalyzedResults();
    public List<CandidateDTO> getCandidateProfileData(long id);
    public List<AchievementDTO> getCandidateAchievementsData(long id);
    public List<EducationDTO> getCandidateEducationData(long id);
    public List<ProjectDTO> getCandidateProjectData(long id);
    public List<PublicationsDTO> getCandidatePublicationsData(long id);
    public List<WorkExpDTO> getCandidateWorkExperienceData(long id);

}
