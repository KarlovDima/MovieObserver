package com.dima.dao.implementation;

import com.dima.dao.GenericDAO;
import com.dima.dao.row.mappers.ReviewMapper;
import com.dima.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
    public int create(Review entity) {
        return jdbcTemplate.update(INSERT_REVIEW, entity.getFilmId(), entity.getCriticId(), entity.getComment());
    }
}

