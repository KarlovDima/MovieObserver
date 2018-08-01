package com.dima.services.implementation;

import com.dima.dao.DAOFactory;
import com.dima.models.entity.FilmScreening;
import com.dima.services.FilmScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmScreeningServiceImpl implements FilmScreeningService {
    @Autowired
    private DAOFactory daoFactory;

    @Override
    public FilmScreening createFilmScreening(FilmScreening filmScreening) {
        return daoFactory.getFilmScreeningDAO().createFilmScreening(filmScreening);
    }

    @Override
    public List<FilmScreening> getAllFilmScreenings() {
        return daoFactory.getFilmScreeningDAO().getAllFilmScreenings();
    }

    @Override
    public FilmScreening getFilmScreeningById(int id) {
        return daoFactory.getFilmScreeningDAO().getFilmScreeningById(id);
    }

    @Override
    public FilmScreening updateFilmScreening(int id, FilmScreening filmScreening) {
        return daoFactory.getFilmScreeningDAO().updateFilmScreening(id, filmScreening);
    }

    @Override
    public ResponseEntity<FilmScreening> deleteFilmScreening(int id) {
        return daoFactory.getFilmScreeningDAO().deleteFilmScreening(id);
    }
}
