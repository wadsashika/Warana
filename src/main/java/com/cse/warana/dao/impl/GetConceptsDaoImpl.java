package com.cse.warana.dao.impl;

import com.cse.warana.dao.GetConceptsDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Anushka on 2015-01-02.
 */
@Repository("getConceptsDao")
public class GetConceptsDaoImpl extends BaseJDBCDaoImpl implements GetConceptsDao{
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

        returnList = getNamedParameterJdbcTemplate().query(query.toString(),mapper);

        return returnList;
    }
}
