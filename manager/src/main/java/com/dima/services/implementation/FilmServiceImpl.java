package com.dima.services.implementation;

import com.dima.dao.DAOFactory;
import com.dima.models.entity.Film;
import com.dima.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private DAOFactory daoFactory;

    @Override
    public Film createFilm(Film film) {
        return daoFactory.getFilmDAO().createFilm(film);
    }

    @Override
    public List<Film> getAllFilms() {
        return daoFactory.getFilmDAO().findAllFilms();
    }

    @Override
    public Film getFilmById(int id) {
        return daoFactory.getFilmDAO().getFilmById(id);
    }

    @Override
    public Film updateFilm(int id, Film film) {
        return daoFactory.getFilmDAO().updateFilm(id, film);
    }

    @Override
    public ResponseEntity<Film> deleteFilm(int id) {
        return daoFactory.getFilmDAO().deleteFilm(id);
    }
}
