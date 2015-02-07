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
import java.util.*;

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

        for (int a = 0; a < technologies.size(); a++){
            techList.append(("'" + technologies.get(a).getName() + "'"));
            if (a!=technologies.size()-1){
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
                System.out.println(id+"//////////////////////////////////////////////////////////////");
                return id;
            }
        };

        List<String> techIds=null;
        HashMap<String,String> techMap=new HashMap<>();

        try {
            techIds = getNamedParameterJdbcTemplate().query(query.toString(),mapper);


        }
        catch (NullPointerException e){
            System.out.println("Null Pointer Exception");
        }

        return techIds;
    }

    @Override
    public Map<String, Long> getTechnologyIdMap(List<Technology> technologies) {
        StringBuilder query = new StringBuilder("");
        StringBuilder techList = new StringBuilder("");

        for (int a = 0; a < technologies.size(); a++){
            techList.append(("'" + technologies.get(a).getName() + "'"));
            if (a!=technologies.size()-1){
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
                idNameStr = resultSet.getString("id") +"," + resultSet.getString("technology");
                return idNameStr;
            }
        };

        List<String> techIds=null;

        try {
            techIds = getNamedParameterJdbcTemplate().query(query.toString(),mapper);
            System.out.println(techIds.size());
        }
        catch (NullPointerException e){
            System.out.println("Null Pointer Exception");
        }

        Map<String, Long> techIdMap = new HashMap<String, Long>();

        for (String techId : techIds) {
            String[] idAndName = techId.trim().split(",");
            techIdMap.put(idAndName[1], Long.parseLong(idAndName[0]));
        }


        return techIdMap;
    }
}
