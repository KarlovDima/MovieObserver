package com.dima.dao.row.mappers;

import com.dima.models.Review;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ReviewMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        return Review.builder()
                .id(resultSet.getInt(1))
                .filmId(resultSet.getInt(2))
                .criticId(resultSet.getInt(3))
                .comment(resultSet.getString(4))
                .build();
    }
}
