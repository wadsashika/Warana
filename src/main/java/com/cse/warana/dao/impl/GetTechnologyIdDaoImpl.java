package com.cse.warana.dao.impl;

import com.cse.warana.dao.GetTechnologyIdDao;
import com.cse.warana.utility.infoHolders.Technology;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nadeeshaan on 1/2/2015.
 */

@Repository("getTechnologyIdDao")
public class GetTechnologyIdDaoImpl extends BaseJDBCDaoImpl implements GetTechnologyIdDao {

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<String> getCurrentTechnologyIds(List<Technology> technologies) {
        StringBuilder query = new StringBuilder("");
        StringBuilder techList = new StringBuilder("");

        for (int a = 0; a < technologies.size(); a++) {
            techList.append(("'" + technologies.get(a).getName() + "'"));
            if (a != technologies.size() - 1) {
                techList.append(",");
            }
        }

        query.append("SELECT id \n");
        query.append("FROM technology \n");
        query.append("WHERE technology in (");
        query.append(techList.toString() + ")");

        RowMapper<String> mapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String id = "";
                id = resultSet.getString("id");
                System.out.println(id + "//////////////////////////////////////////////////////////////");
                return id;
            }
        };

        List<String> techIds = null;
        HashMap<String, String> techMap = new HashMap<>();

        try {
            techIds = getNamedParameterJdbcTemplate().query(query.toString(), mapper);


        } catch (NullPointerException e) {
            System.out.println("Null Pointer Exception");
        }

        return techIds;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Map<String, Long> getTechnologyIdMap(List<Technology> technologies) {
        StringBuilder query = new StringBuilder("");
        StringBuilder techList = new StringBuilder("");

        for (int a = 0; a < technologies.size(); a++) {
            techList.append(("'" + technologies.get(a).getName() + "'"));
            if (a != technologies.size() - 1) {
                techList.append(",");
            }
        }
        System.out.println(techList.toString());
        query.append("SELECT id, technology \n");
        query.append("FROM technology \n");
        query.append("WHERE technology in (");
        query.append(techList.toString() + ")");

        RowMapper<String> mapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String idNameStr = "";
                idNameStr = resultSet.getString("id") + "," + resultSet.getString("technology").toLowerCase();
                return idNameStr;
            }
        };

        List<String> techIds = null;

        try {
            techIds = getNamedParameterJdbcTemplate().query(query.toString(), mapper);
            System.out.println(techIds.size());
        } catch (NullPointerException e) {
            System.out.println("Null Pointer Exception");
        }

        Map<String, Long> techIdMap = new HashMap<String, Long>();

        for (String techId : techIds) {
            String[] idAndName = techId.trim().split(",");
            techIdMap.put(idAndName[1], Long.parseLong(idAndName[0]));
        }


        return techIdMap;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Map<Integer, Double> getCompanyTechnologyScoreMap(Long candidateId) {
        StringBuilder query = new StringBuilder("");
        Map<Integer, Double> result = new HashMap<>();

        query.append("SELECT merged.technology_id, merged.score \n");
        query.append("FROM ( \n");
        query.append("SELECT comt.technology_id, comt.score, ct.candidate_id \n");
        query.append("FROM candidate_technology ct \n");
        query.append("INNER JOIN company_technology comt ON ct.technology_id = comt.technology_id \n");
        query.append(") merged \n");
        query.append("WHERE merged.candidate_id = '" + candidateId+"'");

        RowMapper<String> mapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String idNameStr = "";
                idNameStr = resultSet.getString("technology_id") + "," + resultSet.getString("score");
                return idNameStr;
            }
        };

        List<String> resultSet = getNamedParameterJdbcTemplate().query(query.toString(), mapper);

        for (int i = 0; i < resultSet.size(); i++) {
            String[] temp = resultSet.get(i).split(",");
            result.put(Integer.parseInt(temp[0].trim()), Double.parseDouble(temp[1].trim()));
        }

        return result;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Map<Integer, Double> getCandidateTechnologyScoreMap(Long candidateId) {
        StringBuilder query = new StringBuilder("");
        Map<Integer, Double> result = new HashMap<>();

        query.append("SELECT ct.technology_id, ct.percentage \n");
        query.append("FROM candidate_technology ct \n");
        query.append("WHERE ct.candidate_id = '" + candidateId+"'");

        RowMapper<String> mapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String idNameStr = "";
                idNameStr = resultSet.getString("technology_id") + "," + resultSet.getString("percentage");
                return idNameStr;
            }
        };

        List<String> resultSet = getNamedParameterJdbcTemplate().query(query.toString(), mapper);

        for (int i = 0; i < resultSet.size(); i++) {
            String[] temp = resultSet.get(i).split(",");
            result.put(Integer.parseInt(temp[0].trim()), Double.parseDouble(temp[1].trim()));
        }

        return result;
    }
}
