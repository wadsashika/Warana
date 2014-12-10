package com.cse.warana.dao.impl;

import com.cse.warana.dao.ResumesToProcessDao;
import com.cse.warana.dto.ResumesToProcessDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Created by Nadeeshaan on 12/4/2014.
 */

@Repository("resumesToProcessDao")
public class ResumesToProcessDaoImpl extends BaseJDBCDaoImpl implements ResumesToProcessDao {
    private Logger LOG = LoggerFactory.getLogger(ResumesToProcessDaoImpl.class);

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ResumesToProcessDto> getUploadedResumes(String status) {
        List<ResumesToProcessDto> returnDtoList = null;
        StringBuilder query = new StringBuilder("");
        query.append("SELECT id,file_name \n");
        query.append("FROM uploaded_resumes \n");
        query.append("WHERE status = :status");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("status", status);

        RowMapper<ResumesToProcessDto> mapper = new RowMapper<ResumesToProcessDto>() {
            @Override
            public ResumesToProcessDto mapRow(ResultSet resultSet, int i) throws SQLException {
                ResumesToProcessDto resumesToProcessDto = new ResumesToProcessDto();

                resumesToProcessDto.setId(resultSet.getLong("id"));
                resumesToProcessDto.setFileName(resultSet.getString("file_name"));

                return resumesToProcessDto;
            }
        };

        returnDtoList = getNamedParameterJdbcTemplate().query(query.toString(),paramMap,mapper);

        return returnDtoList;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public int updateResumeStatus(String fileName, String status) {
        int returnVal = 0;

        StringBuilder query = new StringBuilder("");
        query.append("UPDATE uploaded_resumes \n");
        query.append("SET status = :status \n");
        query.append("WHERE file_name = :fileName");

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("status",status);
        paramMap.put("fileName",fileName);

        returnVal = getNamedParameterJdbcTemplate().update(query.toString(),paramMap);
        return returnVal;
    }
}
