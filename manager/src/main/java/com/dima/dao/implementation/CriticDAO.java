package com.dima.dao.implementation;

import com.dima.dao.GenericDAO;
import com.dima.dao.row.mappers.CriticMapper;
import com.dima.models.Critic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
public class CriticDAO implements GenericDAO<Critic, Integer> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CriticMapper criticMapper;

    private final String GET_ALL_CRITICS = "SELECT * FROM CRITIC";
    private final String UPDATE_CRITIC = "UPDATE CRITIC SET NAME = ?, WORK_BEGINNING = ?, WORK_ENDING = ?, HOST = ?, PORT= ? WHERE ID = ?";
    private final String GET_CRITIC = "SELECT * FROM CRITIC WHERE ID = ?";
    private final String DELETE_CRITIC = "DELETE FROM CRITIC WHERE ID = ?";
    private final String INSERT_CRITIC = "INSERT INTO CRITIC (NAME, WORK_BEGINNING, WORK_ENDING, HOST, PORT) VALUES (?, ?, ?, ?, ?)";

    @Override
    public List<Critic> getAll() {
        return jdbcTemplate.query(GET_ALL_CRITICS, criticMapper);
    }

    @Override
    public int update(Critic entity) {
        return jdbcTemplate.update(UPDATE_CRITIC, entity.getName(), entity.getWorkBeginning(), entity.getWorkEnding(), entity.getHost(), entity.getPort());
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
    public Critic create(Critic entity) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT_CRITIC, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getWorkBeginning());
            statement.setString(3, entity.getWorkEnding());
            statement.setString(4, entity.getHost());
            statement.setInt(5, entity.getPort());
            return statement;
        }, holder);
        entity.setId(holder.getKey().intValue());

        return entity;
    }
}