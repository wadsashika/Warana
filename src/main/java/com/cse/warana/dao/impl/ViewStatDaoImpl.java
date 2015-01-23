package com.cse.warana.dao.impl;

import com.cse.warana.dao.ViewStatDao;
import com.cse.warana.dto.ViewStatDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nadeeshaan on 12/10/2014.
 */

@Repository("viewStatDao")
public class ViewStatDaoImpl extends BaseJDBCDaoImpl implements ViewStatDao {
    @Override
    public List<ViewStatDTO> getViewStatDaoList() {

        List<ViewStatDTO> returnDtoList = null;
        StringBuilder query = new StringBuilder("");
        query.append("SELECT id,name,email,score \n");
        query.append("FROM candidate \n");
        query.append("WHERE score <> 0 \n");
        query.append("ORDER BY score DESC");

        RowMapper<ViewStatDTO> mapper = new RowMapper<ViewStatDTO>() {
            @Override
            public ViewStatDTO mapRow(ResultSet resultSet, int i) throws SQLException {
                ViewStatDTO viewStatDto = new ViewStatDTO();

                viewStatDto.setId(resultSet.getLong("id"));
                viewStatDto.setName(resultSet.getString("name"));
                viewStatDto.setEmail(resultSet.getString("email"));
                viewStatDto.setScore(resultSet.getString("score"));

                return viewStatDto;
            }
        };

        returnDtoList = getNamedParameterJdbcTemplate().query(query.toString(), mapper);

        return returnDtoList;
    }

    @Override
    public List<String> getTechnologiesList() {

        List<String> returnList = null;

        StringBuilder query = new StringBuilder("");
        query.append("SELECT DISTINCT t.technology \n");
        query.append("FROM candidate_technology as ct \n");
        query.append("LEFT JOIN technology as t \n");
        query.append("ON ct.technology_id = t.id\n");

        RowMapper<String> mapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String technology = resultSet.getString("technology");

                return technology;
            }
        };

        returnList = getNamedParameterJdbcTemplate().query(query.toString(), mapper);

        return returnList;
    }

    @Override
    public List<ViewStatDTO> getAdvSearchResults(String[] technologies) {

        List<ViewStatDTO> returnList = null;

        StringBuilder techStr = new StringBuilder("");
        for (int a = 0; a < technologies.length; a++) {
            if (a == technologies.length - 1) {
                techStr.append("\'" + technologies[a] + "\'");
            } else {
                techStr.append("\'" + technologies[a] + "\'" + ",");
            }
        }

        StringBuilder candidates = new StringBuilder("");
        candidates.append("SELECT c.id as id,c.name as name,c.email as email,c.score as score \n");
        candidates.append("FROM candidate as c \n");
        candidates.append("LEFT JOIN candidate_technology ct on c.id=ct.candidate_id \n");
        candidates.append("LEFT JOIN technology as t on ct.technology_id = t.id \n");
        candidates.append("WHERE t.technology in (" + techStr.toString() + ") \n");
        candidates.append("AND c.score <> 0 \n");
        candidates.append("GROUP BY c.id \n");
        candidates.append("ORDER BY c.score DESC \n");

        RowMapper<ViewStatDTO> mapper = new RowMapper<ViewStatDTO>() {
            @Override
            public ViewStatDTO mapRow(ResultSet resultSet, int i) throws SQLException {
                ViewStatDTO viewStatDTO = new ViewStatDTO();
                viewStatDTO.setId(resultSet.getLong("id"));
                viewStatDTO.setName(resultSet.getString("name"));
                viewStatDTO.setEmail(resultSet.getString("email"));
                viewStatDTO.setScore(resultSet.getString("score"));

                return viewStatDTO;
            }
        };

        returnList = getNamedParameterJdbcTemplate().query(candidates.toString(), mapper);
        return returnList;
    }

    @Override
    public List<ViewStatDTO> getCompareAllResults(final String[] technologies) {
        List<ViewStatDTO> returnList = null;

        StringBuilder techStr = new StringBuilder("");
        for (int a = 0; a < technologies.length; a++) {
            if (a == technologies.length - 1) {
                techStr.append("\'" + technologies[a] + "\'");
            } else {
                techStr.append("\'" + technologies[a] + "\'" + ",");
            }
        }

        StringBuilder query = new StringBuilder("");
        StringBuilder subQuery = new StringBuilder("");

        subQuery.append("SELECT t.id,t.technology \n");
        subQuery.append("FROM technology as t \n");
        subQuery.append("WHERE t.technology IN (" + techStr + ")");

        query.append("SELECT c.id as id,c.name as name,GROUP_CONCAT(p.technology) as technologies,GROUP_CONCAT(ct.percentage) as percentages \n");
        query.append("FROM candidate as c \n");
        query.append("JOIN candidate_technology as ct on c.id = ct.candidate_id \n");
        query.append("JOIN (" + subQuery + ")AS p on ct.technology_id = p.id \n");
        query.append("GROUP BY c.id \n");

        RowMapper<ViewStatDTO> mapper = new RowMapper<ViewStatDTO>() {
            @Override
            public ViewStatDTO mapRow(ResultSet resultSet, int i) throws SQLException {
                ViewStatDTO viewStatDTO = new ViewStatDTO();
                viewStatDTO.setId(resultSet.getLong("id"));
                viewStatDTO.setName(resultSet.getString("name"));
                HashMap<String, String> techPercentageMap = new HashMap<>();

                List<String> stTechList = Arrays.asList(resultSet.getString("technologies").split(","));
                List<String> stPerList = Arrays.asList(resultSet.getString("percentages").split(","));

                for (int a = 0; a < technologies.length; a++) {
                    if (stTechList.contains(technologies[a])) {
                        techPercentageMap.put(technologies[a], stPerList.get(stTechList.indexOf(technologies[a])));
                    } else {
                        techPercentageMap.put(technologies[a], "0");
                    }
                }

                viewStatDTO.setTechnologyScoreMap(techPercentageMap);

                return viewStatDTO;
            }
        };

        returnList = getNamedParameterJdbcTemplate().query(query.toString(), mapper);
        return returnList;
    }

    @Override
    public List<Map<String, Object>> getTechnologiesScores(double id) {

        List<Map<String, Object>> returnList = null;
        StringBuilder query = new StringBuilder("");
        query.append("SELECT t.technology as technology,ct.percentage as percentage \n");
        query.append("FROM candidate as c left join candidate_technology as ct \n");
        query.append("on c.id = ct.candidate_id \n");
        query.append("left join technology as t \n");
        query.append("on t.id = ct.technology_id \n");
        query.append("where c.id = :id  \n");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", new Double(id));

        returnList = getNamedParameterJdbcTemplate().queryForList(query.toString(), paramMap);

        return returnList;
    }


}
