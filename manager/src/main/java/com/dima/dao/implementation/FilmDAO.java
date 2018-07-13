package com.dima.dao.implementation;

import com.dima.dao.GenericDAO;
import com.dima.dao.row.mappers.FilmMapper;
import com.dima.models.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmDAO implements GenericDAO<Film, Integer> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FilmMapper filmMapper;

    private final String GET_ALL_FILMS = "SELECT * FROM FILM";
    private final String UPDATE_FILM = "UPDATE FILM SET NAME = ?, DURATION = ?, ACTIVE = ? WHERE ID = ?";
    private final String GET_FILM = "SELECT * FROM FILM WHERE ID = ?";
    private final String DELETE_FILM = "DELETE FROM FILM WHERE ID = ?";
    private final String INSERT_FILM = "INSERT INTO FILM (NAME, DURATION, ACTIVE) VALUES (?, ?, ?)";

    @Override
    public List<Film> getAll() {
        return jdbcTemplate.query(GET_ALL_FILMS, filmMapper);
    }

    @Override
    public int update(Film entity) {
        return jdbcTemplate.update(UPDATE_FILM, entity.getName(), entity.getDuration(), entity.isActive());
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
    public int create(Film entity) {
        return jdbcTemplate.update(INSERT_FILM, entity.getName(), entity.getDuration(), entity.isActive());
    }
}
