package com.dima.dao.implementation;

import com.dima.dao.GenericDAO;
import com.dima.dao.row.mappers.ReviewMapper;
import com.dima.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
public class ReviewDAO implements GenericDAO<Review, Integer> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ReviewMapper reviewMapper;

    private final String GET_ALL_REVIEWS = "SELECT * FROM REVIEW";
    private final String UPDATE_REVIEW = "UPDATE REVIEW SET FILM_ID = ?, CRITIC_ID = ?, COMMENT = ? WHERE ID = ?";
    private final String GET_REVIEW = "SELECT * FROM REVIEW WHERE ID = ?";
    private final String DELETE_REVIEW = "DELETE FROM REVIEW WHERE ID = ?";
    private final String INSERT_REVIEW = "INSERT INTO REVIEW (FILM_ID, CRITIC_ID, COMMENT) VALUES (?, ?, ?)";

    @Override
    public List<Review> getAll() {
        return jdbcTemplate.query(GET_ALL_REVIEWS, reviewMapper);
    }

    @Override
    public int update(Review entity) {
        return jdbcTemplate.update(UPDATE_REVIEW, entity.getFilmId(), entity.getCriticId(), entity.getComment());
    }

    @Override
    public Review getEntityById(Integer id) {
        return jdbcTemplate.queryForObject(GET_REVIEW, new Object[]{id}, reviewMapper);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE_REVIEW, id);
    }

    @Override
    public Review create(Review entity) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT_REVIEW, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getFilmId());
            statement.setInt(2, entity.getCriticId());
            statement.setString(3, entity.getComment());
            return statement;
        }, holder);
        entity.setId(holder.getKey().intValue());

        return entity;
    }
}

