package com.cse.warana.dao.impl;

import com.cse.warana.dao.AnalyzedResultsDao;
import com.cse.warana.dto.*;
import com.cse.warana.utility.infoHolders.Candidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nadeeshaan on 12/10/2014.
 */

@Repository("analyzedResults")
public class AnalyzedResultsDaoImpl extends BaseJDBCDaoImpl implements AnalyzedResultsDao {

    Logger LOG = LoggerFactory.getLogger(AnalyzedResultsDaoImpl.class);

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<AnalyticResultsDTO> getAnalyzedResults() {

        List<AnalyticResultsDTO> returnList = null;

        StringBuilder query = new StringBuilder("");
        query.append("SELECT id,name,score \n");
        query.append("FROM candidate \n");
        query.append("WHERE score <> -1");

        RowMapper<AnalyticResultsDTO> mapper = new RowMapper<AnalyticResultsDTO>() {
            @Override
            public AnalyticResultsDTO mapRow(ResultSet resultSet, int i) throws SQLException {
                AnalyticResultsDTO analyticResultsDTO = new AnalyticResultsDTO();

                analyticResultsDTO.setId(resultSet.getLong("id"));
                analyticResultsDTO.setName(resultSet.getString("name"));
                analyticResultsDTO.setScore(resultSet.getString("score"));

                return analyticResultsDTO;
            }
        };

        returnList = getNamedParameterJdbcTemplate().query(query.toString(),mapper);

        return returnList;
    }

    @Override
    public List<CandidateDTO> getCandidateProfileData(long id) {

        List<CandidateDTO> returnList = null;
        StringBuilder query = new StringBuilder("");

        query.append("SELECT score,pic_path,name,address,email,gender,marital_status\n");
        query.append("FROM candidate\n");
        query.append("WHERE id = :id");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);

        RowMapper<CandidateDTO> mapper = new RowMapper<CandidateDTO>() {
            @Override
            public CandidateDTO mapRow(ResultSet resultSet, int i) throws SQLException {
                CandidateDTO candidateDTO = new CandidateDTO();

                candidateDTO.setScore(resultSet.getDouble("score"));
                candidateDTO.setPic_path(resultSet.getString("pic_path"));
                candidateDTO.setName(resultSet.getString("name"));
                candidateDTO.setAddress(resultSet.getString("address"));
                candidateDTO.setEmail(resultSet.getString("email"));
                candidateDTO.setGender(resultSet.getString("gender"));
                candidateDTO.setMarital_status(resultSet.getString("marital_status"));

                return candidateDTO;
            }
        };

        returnList = getNamedParameterJdbcTemplate().query(query.toString(),paramMap,mapper);

        return returnList;
    }

    @Override
    public List<AchievementDTO> getCandidateAchievementsData(long id) {
        List<AchievementDTO> returnList = null;
        StringBuilder query = new StringBuilder("");

        query.append("SELECT description\n");
        query.append("FROM awards_and_achievements\n");
        query.append("WHERE candidate_id = :candidate_id");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("candidate_id", id);

        RowMapper<AchievementDTO> mapper = new RowMapper<AchievementDTO>() {
            @Override
            public AchievementDTO mapRow(ResultSet resultSet, int i) throws SQLException {
                AchievementDTO achievementDTO = new AchievementDTO();

                achievementDTO.setDescription(resultSet.getString("description"));
                achievementDTO.setDate("");

                return achievementDTO;
            }
        };

        returnList = getNamedParameterJdbcTemplate().query(query.toString(),paramMap,mapper);

        return returnList;
    }

    @Override
    public List<EducationDTO> getCandidateEducationData(long id) {
        List<EducationDTO> returnList = null;
        StringBuilder query = new StringBuilder("");

        query.append("SELECT institution_name,duration,specialization,grade\n");
        query.append("FROM educational_details\n");
        query.append("WHERE candidate_id = :candidate_id");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("candidate_id", id);

        RowMapper<EducationDTO> mapper = new RowMapper<EducationDTO>() {
            @Override
            public EducationDTO mapRow(ResultSet resultSet, int i) throws SQLException {
                EducationDTO educationDTO = new EducationDTO();

                educationDTO.setInstitution_name(resultSet.getString("institution_name"));
                educationDTO.setDuration(resultSet.getString("duration"));
                educationDTO.setSpecialization(resultSet.getString("specialization"));
                educationDTO.setGrade(resultSet.getString("grade"));

                return educationDTO;
            }
        };

        returnList = getNamedParameterJdbcTemplate().query(query.toString(),paramMap,mapper);

        return returnList;
    }

    @Override
    public List<ProjectDTO> getCandidateProjectData(long id) {
        List<ProjectDTO> returnList = null;
        StringBuilder query = new StringBuilder("");

        query.append("SELECT proj_name,description\n");
        query.append("FROM project_details\n");
        query.append("WHERE candidate_id = :candidate_id");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("candidate_id", id);

        RowMapper<ProjectDTO> mapper = new RowMapper<ProjectDTO>() {
            @Override
            public ProjectDTO mapRow(ResultSet resultSet, int i) throws SQLException {
                ProjectDTO projectDTO = new ProjectDTO();

                projectDTO.setProj_name(resultSet.getString("proj_name"));
                projectDTO.setDescription(resultSet.getString("description"));

                return projectDTO;
            }
        };

        returnList = getNamedParameterJdbcTemplate().query(query.toString(),paramMap,mapper);

        return returnList;
    }

    @Override
    public List<PublicationsDTO> getCandidatePublicationsData(long id) {
        List<PublicationsDTO> returnList = null;
        StringBuilder query = new StringBuilder("");

        query.append("SELECT name,description\n");
        query.append("FROM publications\n");
        query.append("WHERE candidate_id = :candidate_id");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("candidate_id", id);

        RowMapper<PublicationsDTO> mapper = new RowMapper<PublicationsDTO>() {
            @Override
            public PublicationsDTO mapRow(ResultSet resultSet, int i) throws SQLException {
                PublicationsDTO publicationsDTO = new PublicationsDTO();

                publicationsDTO.setName(resultSet.getString("name"));
                publicationsDTO.setDescription(resultSet.getString("description"));

                return publicationsDTO;
            }
        };

        returnList = getNamedParameterJdbcTemplate().query(query.toString(),paramMap,mapper);

        return returnList;
    }

    @Override
    public List<WorkExpDTO> getCandidateWorkExperienceData(long id) {
        List<WorkExpDTO> returnList = null;
        StringBuilder query = new StringBuilder("");

        query.append("SELECT company_name,post,duration_from,duration_to\n");
        query.append("FROM work_experience\n");
        query.append("WHERE candidate_id = :candidate_id");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("candidate_id", id);

        RowMapper<WorkExpDTO> mapper = new RowMapper<WorkExpDTO>() {
            @Override
            public WorkExpDTO mapRow(ResultSet resultSet, int i) throws SQLException {
                WorkExpDTO workExpDTO = new WorkExpDTO();

                workExpDTO.setCompany_name(resultSet.getString("company_name"));
                workExpDTO.setPost(resultSet.getString("post"));
                workExpDTO.setFrom(resultSet.getString("duration_from"));
                workExpDTO.setTo(resultSet.getString("duration_to"));

                return workExpDTO;
            }
        };

        returnList = getNamedParameterJdbcTemplate().query(query.toString(),paramMap,mapper);

        return returnList;
    }


}
