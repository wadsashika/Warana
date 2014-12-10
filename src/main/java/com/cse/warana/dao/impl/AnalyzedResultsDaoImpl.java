package com.cse.warana.dao.impl;

import com.cse.warana.dao.AnalyzedResultsDao;
import com.cse.warana.dto.AnalyticResultsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
}
