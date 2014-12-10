package com.cse.warana.dao.impl;

import com.cse.warana.dao.ViewStatDao;
import com.cse.warana.dto.ViewStatDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nadeeshaan on 12/10/2014.
 */

@Repository("viewStatDao")
public class ViewStatDaoImpl extends BaseJDBCDaoImpl implements ViewStatDao {
    @Override
    public List<ViewStatDTO> getViewStatDaoList() {

        List<ViewStatDTO> returnDtoList = null;
        StringBuilder query = new StringBuilder("");
        query.append("SELECT name,email,score \n");
        query.append("FROM candidate \n");
        query.append("WHERE score <> -1");

        RowMapper<ViewStatDTO> mapper = new RowMapper<ViewStatDTO>() {
            @Override
            public ViewStatDTO mapRow(ResultSet resultSet, int i) throws SQLException {
                ViewStatDTO viewStatDto = new ViewStatDTO();

                viewStatDto.setName(resultSet.getString("name"));
                viewStatDto.setEmail(resultSet.getString("email"));
                viewStatDto.setScore(resultSet.getString("score"));

                return viewStatDto;
            }
        };

        returnDtoList = getNamedParameterJdbcTemplate().query(query.toString(),mapper);

        return returnDtoList;
    }
}
