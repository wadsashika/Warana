package com.cse.warana.dao.impl;

import com.cse.warana.dao.GetUploadedDocDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Anushka on 2015-02-08.
 */
@Repository("getUploadeDocDao")
public class GetUploadedDocDoaImpl extends BaseJDBCDaoImpl implements GetUploadedDocDao {
    @Override
    public List<String> getDocumentList() {
        List<String> returnList = null;

        StringBuilder query = new StringBuilder("");
        query.append("SELECT file_name FROM uploaded_compny_doc");

        RowMapper<String> mapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String technology = resultSet.getString("file_name");

                return technology;
            }
        };

        returnList = getNamedParameterJdbcTemplate().query(query.toString(), mapper);

        return returnList;
    }
}
