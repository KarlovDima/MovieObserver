package com.dima.dao.row.mappers;

import com.dima.models.FilmScreening;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FilmScreeningMapper implements RowMapper<FilmScreening> {
    @Override
    public FilmScreening mapRow(ResultSet resultSet, int i) throws SQLException {
        return FilmScreening.builder()
                .id(resultSet.getInt(1))
                .filmId(resultSet.getInt(2))
                .time(resultSet.getString(3))
                .build();
    }
}
