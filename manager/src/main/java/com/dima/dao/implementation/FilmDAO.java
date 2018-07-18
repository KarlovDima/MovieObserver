package com.dima.dao.implementation;

import com.dima.dao.GenericDAO;
import com.dima.dao.row.mappers.FilmMapper;
import com.dima.models.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
public class FilmDAO implements GenericDAO<Film, Integer> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FilmMapper filmMapper;

    private final String GET_ALL_FILMS = "SELECT * FROM FILM";
    private final String UPDATE_FILM = "UPDATE FILM SET NAME = ?, DURATION = ?, ACTIVE = ?, HOST = ?, PORT= ? WHERE ID = ?";
    private final String GET_FILM = "SELECT * FROM FILM WHERE ID = ?";
    private final String DELETE_FILM = "DELETE FROM FILM WHERE ID = ?";
    private final String INSERT_FILM = "INSERT INTO FILM (NAME, DURATION, ACTIVE, HOST, PORT ) VALUES (?, ?, ?, ?, ?)";

    @Override
    public List<Film> getAll() {
        return jdbcTemplate.query(GET_ALL_FILMS, filmMapper);
    }

    @Override
    public int update(Film entity) {
        return jdbcTemplate.update(UPDATE_FILM, entity.getName(), entity.getDuration(), entity.isActive(), entity.getHost(), entity.getPort());
    }

    @Override
    public Film getEntityById(Integer id) {
        return jdbcTemplate.queryForObject(GET_FILM, new Object[]{id}, filmMapper);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE_FILM, id);
    }

    @Override
    public Film create(Film entity) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT_FILM, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getDuration());
            statement.setBoolean(3, entity.isActive());
            statement.setString(4, entity.getHost());
            statement.setInt(5, entity.getPort());
            return statement;
        }, holder);
        entity.setId(holder.getKey().intValue());

        return entity;
    }
}
