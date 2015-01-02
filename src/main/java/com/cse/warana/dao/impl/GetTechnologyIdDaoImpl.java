package com.cse.warana.dao.impl;

import com.cse.warana.dao.GetTechnologyIdDao;
import com.cse.warana.dto.ResumesToProcessDto;
import com.cse.warana.utility.infoHolders.Technology;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
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
 * Created by Nadeeshaan on 1/2/2015.
 */

@Repository("getTechnologyIds")
public class GetTechnologyIdDaoImpl extends BaseJDBCDaoImpl implements GetTechnologyIdDao {
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<String> getCurrentTechnologyIds(ArrayList<Technology> technologies) {
        StringBuilder query = new StringBuilder("");
        StringBuilder techList = new StringBuilder("");

        for (int a = 0; a < technologies.size(); a++){
            techList.append(("'" + technologies.get(a).getName() + "'"));
            if (a!=technologies.size()-1){
                techList.append(",");
            }
        }

        query.append("SELECT id \n");
        query.append("FROM technology \n");
        query.append("WHERE technology in (");
        query.append(techList + ")");

        RowMapper<String> mapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String id = "";
                id = resultSet.getString("id");
                return id;
            }
        };

        List<String> techIds = (List<String>)getNamedParameterJdbcTemplate().query(query.toString(),mapper);

        return techIds;
    }
}
