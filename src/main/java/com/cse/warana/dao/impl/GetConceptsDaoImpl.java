package com.cse.warana.dao.impl;

import com.cse.warana.dao.GetConceptsDao;
import com.cse.warana.dto.CompanyTechnologyViewDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Anushka on 2015-01-02.
 */
@Repository("getConceptsDao")
public class GetConceptsDaoImpl extends BaseJDBCDaoImpl implements GetConceptsDao {
    @Override
    public List<String> getConceptsList() {
        List<String> returnList = null;

        StringBuilder query = new StringBuilder("");
        query.append("SELECT conceptName FROM concepts");

        RowMapper<String> mapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String technology = resultSet.getString("conceptName");

                return technology;
            }
        };

        returnList = getNamedParameterJdbcTemplate().query(query.toString(), mapper);

        return returnList;
    }

    @Override
    public List<String> getTechnologyList() {
        List<String> returnList = null;

        StringBuilder query = new StringBuilder("");

        query.append("SELECT technology FROM company_technology,technology WHERE company_technology.id = technology.id");


        RowMapper<String> mapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String technology = String.valueOf(resultSet.getString("technology"));
                return technology;
            }
        };

        returnList = getNamedParameterJdbcTemplate().query(query.toString(), mapper);

        return returnList;
    }

    @Override
    public List<CompanyTechnologyViewDTO> getCompanyTechnologiesWithScore() {
        List<CompanyTechnologyViewDTO> returnDtoList = null;
        StringBuilder query = new StringBuilder("");
        query.append("SELECT technology, technology_id, score\n");
        query.append("FROM company_technology, technology\n");
        query.append("WHERE company_technology.technology_id=technology.id");

        RowMapper<CompanyTechnologyViewDTO> mapper = new RowMapper<CompanyTechnologyViewDTO>() {
            @Override
            public CompanyTechnologyViewDTO mapRow(ResultSet resultSet, int i) throws SQLException {
                CompanyTechnologyViewDTO companyTechnologyViewDTO= new CompanyTechnologyViewDTO();

                companyTechnologyViewDTO.setTechnologyID(resultSet.getInt("technology_id"));
                companyTechnologyViewDTO.setScore(resultSet.getFloat("score"));
                companyTechnologyViewDTO.setTechnologyName(resultSet.getString("technology"));

                return companyTechnologyViewDTO;
            }
        };

        returnDtoList = getNamedParameterJdbcTemplate().query(query.toString(), mapper);

        return returnDtoList;
    }
}
