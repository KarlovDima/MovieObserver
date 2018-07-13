package com.dima.dao.implementation;

import com.dima.dao.GenericDAO;
import com.dima.dao.row.mappers.CriticMapper;
import com.dima.models.Critic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CriticDAO implements GenericDAO<Critic, Integer> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CriticMapper criticMapper;

    private final String GET_ALL_CRITICS = "SELECT * FROM CRITIC";
    private final String UPDATE_CRITIC = "UPDATE CRITIC SET NAME = ?, WORK_BEGINNING = ?, WORK_ENDING = ? WHERE ID = ?";
    private final String GET_CRITIC = "SELECT * FROM CRITIC WHERE ID = ?";
    private final String DELETE_CRITIC = "DELETE FROM CRITIC WHERE ID = ?";
    private final String INSERT_CRITIC = "INSERT INTO CRITIC (NAME, WORK_BEGINNING, WORK_ENDING) VALUES (?, ?, ?)";

    @Override
    public List<Critic> getAll() {
        return jdbcTemplate.query(GET_ALL_CRITICS, criticMapper);
    }

    @Override
    public int update(Critic entity) {
        return jdbcTemplate.update(UPDATE_CRITIC, entity.getName(), entity.getWorkBeginning(), entity.getWorkEnding());
    }

    @Override
    public Critic getEntityById(Integer id) {
        return jdbcTemplate.queryForObject(GET_CRITIC, new Object[]{id}, criticMapper);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE_CRITIC, id);
    }

    @Override
    public int create(Critic entity) {
        return jdbcTemplate.update(INSERT_CRITIC, entity.getName(), entity.getWorkBeginning(), entity.getWorkEnding());
    }
}