package com.dima.dao.implementation;

import com.dima.dao.GenericDAO;
import com.dima.dao.row.mappers.FilmScreeningMapper;
import com.dima.models.FilmScreening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmScreeningDAO implements GenericDAO<FilmScreening, Integer> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FilmScreeningMapper filmScreeningMapper;

    private final String GET_ALL_FILM_SCREENINGS = "SELECT * FROM FILM_SCREENING";
    private final String UPDATE_FILM_SCREENING = "UPDATE FILM_SCREENING SET FILM_ID = ?, TIME = ? WHERE ID = ?";
    private final String GET_FILM_SCREENING = "SELECT * FROM FILM_SCREENING WHERE ID = ?";
    private final String DELETE_FILM_SCREENING = "DELETE FROM FILM_SCREENING WHERE ID = ?";
    private final String INSERT_FILM_SCREENING = "INSERT INTO FILM_SCREENING (FILM_ID, TIME) VALUES (?, ?)";

    @Override
    public List<FilmScreening> getAll() {
        return jdbcTemplate.query(GET_ALL_FILM_SCREENINGS, filmScreeningMapper);
    }

    @Override
    public int update(FilmScreening entity) {
        return jdbcTemplate.update(UPDATE_FILM_SCREENING, entity.getFilmId(), entity.getTime());
    }

    @Override
    public FilmScreening getEntityById(Integer id) {
        return jdbcTemplate.queryForObject(GET_FILM_SCREENING, new Object[]{id}, filmScreeningMapper);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE_FILM_SCREENING, id);
    }

    @Override
    public int create(FilmScreening entity) {
        return jdbcTemplate.update(INSERT_FILM_SCREENING, entity.getFilmId(), entity.getTime());
    }
}

