package com.cse.warana.dao.impl;

import com.cse.warana.dao.AnalyzeResumeDao;
import com.cse.warana.dto.ResumesToAnalyseDto;
import com.cse.warana.dto.ResumesToProcessDto;
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
 * Created by Nadeeshaan on 12/5/2014.
 */

@Repository("analyzeResumeDao")
public class AnalyzeResumeDaoImpl extends BaseJDBCDaoImpl implements AnalyzeResumeDao {

    Logger LOG = LoggerFactory.getLogger(AnalyzeResumeDaoImpl.class);


    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ResumesToAnalyseDto> getResumesToBeAnalyzed() {
        List<ResumesToAnalyseDto> returnList = null;

        StringBuilder query = new StringBuilder("");
        query.append("SELECT id,name,email \n");
        query.append("FROM candidate \n");
        query.append("WHERE score = -1");

        RowMapper<ResumesToAnalyseDto> mapper = new RowMapper<ResumesToAnalyseDto>() {
            @Override
            public ResumesToAnalyseDto mapRow(ResultSet resultSet, int i) throws SQLException {
                ResumesToAnalyseDto resumesToAnalyseDto = new ResumesToAnalyseDto();

                resumesToAnalyseDto.setId(resultSet.getLong("id"));
                resumesToAnalyseDto.setName(resultSet.getString("name"));
                resumesToAnalyseDto.setEmail(resultSet.getString("email"));

                return resumesToAnalyseDto;
            }
        };

        returnList = getNamedParameterJdbcTemplate().query(query.toString(),mapper);

        return returnList;
    }
}
