package com.dima.services;

import com.dima.models.FilmScreening;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FilmScreeningService {
    FilmScreening createFilmScreening(FilmScreening filmScreening);

    List<FilmScreening> getAllFilmScreenings();

    FilmScreening getFilmScreeningById(int id);

    FilmScreening updateFilmScreening(int id, FilmScreening filmScreening);

    ResponseEntity<FilmScreening> deleteFilmScreening(int id);
}
