package com.dima.dao.implementation;

import com.dima.dao.ResourceNotFoundException;
import com.dima.dao.repositories.FilmRepository;
import com.dima.models.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmDAO {
    @Autowired
    private FilmRepository filmRepository;

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public Film createFilm(Film film) {
        return filmRepository.save(film);
    }

    public Film getFilmById(int id) {
        return filmRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));
    }

    public Film updateFilm(Film film) {
        return filmRepository.save(film);
    }

    public ResponseEntity deleteFilm(int id) {
        Film film = filmRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Film", "id", id));
        filmRepository.delete(film);

        return ResponseEntity.ok().build();
    }
}
