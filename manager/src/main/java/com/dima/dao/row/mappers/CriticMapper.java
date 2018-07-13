package com.dima.dao.row.mappers;

import com.dima.models.Critic;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CriticMapper implements RowMapper<Critic> {
    @Override
    public Critic mapRow(ResultSet resultSet, int i) throws SQLException {
        return Critic.builder()
                .id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .workBeginning(resultSet.getString(3))
                .workEnding(resultSet.getString(4))
                .build();
    }
}
