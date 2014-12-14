package com.cse.warana.service.impl;

import com.cse.warana.dao.StoreCandidateDao;
import com.cse.warana.dao.impl.*;
import com.cse.warana.model.*;
import com.cse.warana.service.StoreProcessedResumeService;
import com.cse.warana.utility.infoHolders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.sql.Ref;
import java.util.ArrayList;

/**
 * Created by Nadeeshaan on 12/13/2014.
 */

@Service("storeProcessedResume")
public class StoreProcessedResumeServiceImpl implements StoreProcessedResumeService {

    @Autowired
    @Qualifier("storeCandidate")
    private StoreCandidateDaoImpl storeCandidateDao;

    @Autowired
    @Qualifier("storeEducational")
    private StoreEducationDaoImpl storeEducationDao;

    @Autowired
    @Qualifier("storeProject")
    private StoreProjectDaoImpl storeProjectDao;

    @Autowired
    @Qualifier("storeAchievement")
    private StoreAchievementDaoImpl storeAchievementDao;

    @Autowired
    @Qualifier("storeReferee")
    private StoreRefereeDaoImpl storeRefereeDao;

    @Autowired
    @Qualifier("storeWork")
    private StoreWorkDaoImpl storeWorkDao;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public long storeCandidateTableData(Candidate candidate) {
        Profile profile = candidate.getProfile();
        CandidateTbl candidateTbl = new CandidateTbl();

        candidateTbl.setName(profile.getName());
        candidateTbl.setEmail(profile.getEmail());
        candidateTbl.setAddress(profile.getAddress());
        candidateTbl.setGender(profile.getGender());

        storeCandidateDao.persistEntity(candidateTbl);
        storeCandidateDao.flush();
        return candidateTbl.getCandidateId();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void storeEducationalTableData(Candidate candidate, long candidateId) {
        ArrayList<Education> educationArrayList = candidate.getEducationsList();

        for (int a = 0; a < educationArrayList.size(); a++){
            EducationalTbl educationalTbl = new EducationalTbl();
            educationalTbl.setCandidate_id(candidateId);
            educationalTbl.setInstitution_name(educationArrayList.get(a).getInstitution());

            storeEducationDao.saveEntity(educationalTbl);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void storeProjectTableData(Candidate candidate, long candidateId){

        ArrayList<Project> projectArrayList = candidate.getProjectsLists();
        for (int a = 0; a<projectArrayList.size(); a++){
            ProjectDetailsTbl projectDetailsTbl = new ProjectDetailsTbl();
            projectDetailsTbl.setCandidate_id(candidateId);
            projectDetailsTbl.setDescription(projectArrayList.get(a).getDescription());
            projectDetailsTbl.setProj_name(projectArrayList.get(a).getName());

            storeProjectDao.saveEntity(projectDetailsTbl);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void storeWorkTableData(Candidate candidate, long candidateId) {
        ArrayList<Work> workArrayList = new ArrayList<>();
        workArrayList = candidate.getWorksList();

        for (int a = 0; a<workArrayList.size(); a++){
            Work work = workArrayList.get(a);
            WorkExperienceTbl workExperienceTbl = new WorkExperienceTbl();
            workExperienceTbl.setCandidate_id(candidateId);
            workExperienceTbl.setCompany_name(work.getCompanyName());
            workExperienceTbl.setDuration_from(work.getFrom());
            workExperienceTbl.setDuration_to(work.getTo());

            storeWorkDao.saveEntity(workExperienceTbl);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void storeAchievementTableData(Candidate candidate, long candidateId) {
        ArrayList<Achievement> achievements = new ArrayList<>();
        achievements = candidate.getAchievementsList();

        for (int a = 0; a<achievements.size(); a++){
            Achievement achievement = achievements.get(a);
            AchievementTbl achievementTbl = new AchievementTbl();

            achievementTbl.setCandidate_id(candidateId);
//            achievementTbl.setName(achievement.getAchievement());
            achievementTbl.setDescription(achievement.getAchievement());

            storeAchievementDao.saveEntity(achievementTbl);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void storeRefereeTableData(Candidate candidate, long candidateId) {
        ArrayList<Referee> referees = new ArrayList<>();
        referees = candidate.getRefereesList();

        for (int a = 0; a<referees.size(); a++){
            Referee referee = referees.get(a);
            RefereeTbl refereeTbl = new RefereeTbl();

            refereeTbl.setCandidate_id(candidateId);
            refereeTbl.setName(referee.getName());
            refereeTbl.setTitle(referee.getDescription());
            refereeTbl.setEmail(referee.getEmail());
            refereeTbl.setPhone(referee.getPhone());

            storeAchievementDao.saveEntity(refereeTbl);
        }
    }
}
