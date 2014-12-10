package com.cse.warana.dao.impl;

import com.cse.warana.dao.UserDao;
import com.cse.warana.dto.CredentialsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sashika
 * on Dec 04 0004, 2014.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseJDBCDaoImpl implements UserDao {
    private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public CredentialsDTO getUser(long id) {
        LOG.info("User retrieve started");
        CredentialsDTO credentialsDTO = null;
        List<CredentialsDTO> credentialsDTOList = Collections.emptyList();

        StringBuilder queryData = new StringBuilder("");
        queryData.append("SELECT u.ID, \n");
        queryData.append("u.FIRST_NAME, \n");
        queryData.append("u.LAST_NAME, \n");
        queryData.append("u.EMAIL \n");
        queryData.append("FROM USER u \n");
        queryData.append("WHERE  u.ID = :userId \n");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", id);

        RowMapper<CredentialsDTO> mapper = new RowMapper<CredentialsDTO>() {
            @Override
            public CredentialsDTO mapRow(ResultSet resultSet, int i) throws SQLException {
                CredentialsDTO credentialsDTO1 = new CredentialsDTO();

                credentialsDTO1.setId(resultSet.getLong("ID"));
                credentialsDTO1.setFirstName(resultSet.getString("FIRST_NAME"));
                credentialsDTO1.setLastName(resultSet.getString("LAST_NAME"));
                credentialsDTO1.setEmail(resultSet.getString("EMAIL"));

                return credentialsDTO1;
            }
        };

        credentialsDTO = getNamedParameterJdbcTemplate().queryForObject(queryData.toString(), paramMap, mapper);
        return credentialsDTO;
    }
}
