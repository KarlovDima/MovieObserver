package com.dima.services;

import com.dima.models.Film;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FilmService {
    Film createFilm(Film film);

    List<Film> getAllFilms();

    Film getFilmById(int id);

    Film updateFilm(int id, Film film);

    ResponseEntity<Film> deleteFilm(int id);
}
