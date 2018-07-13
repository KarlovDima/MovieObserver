package com.dima.dao.row.mappers;

import com.dima.models.Film;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FilmMapper implements RowMapper<Film> {
    @Override
    public Film mapRow(ResultSet resultSet, int i) throws SQLException {
        return Film.builder()
                .id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .duration(resultSet.getInt(3))
                .isActive(resultSet.getBoolean(4))
                .build();
    }
}
