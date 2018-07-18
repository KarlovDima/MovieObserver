package com.dima.services.implementation;

import com.dima.dao.DAOFactory;
import com.dima.models.Film;
import com.dima.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private DAOFactory daoFactory;

    @Override
    public Film createFilm(Film film) {
        return daoFactory.getFilmDAO().create(film);
    }
}
