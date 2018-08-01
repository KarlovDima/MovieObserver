package com.dima.dao.implementation;

import com.dima.dao.ResourceNotFoundException;
import com.dima.dao.repositories.FilmScreeningRepository;
import com.dima.models.entity.FilmScreening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmScreeningDAO {
    @Autowired
    private FilmScreeningRepository filmScreeningRepository;

    public List<FilmScreening> getAllFilmScreenings() {
        return filmScreeningRepository.findAll();
    }

    public FilmScreening createFilmScreening(FilmScreening filmScreening) {
        return filmScreeningRepository.save(filmScreening);
    }

    public FilmScreening getFilmScreeningById(int id) {
        return filmScreeningRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FilmScreening", "id", id));
    }

    public FilmScreening updateFilmScreening(int id, FilmScreening updatedFilmScreening) {
        FilmScreening filmScreening = filmScreeningRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FilmScreening", "id", id));

        filmScreening.setTime(updatedFilmScreening.getTime());
        filmScreening.setFilm(updatedFilmScreening.getFilm());

        return filmScreeningRepository.save(filmScreening);
    }

    public ResponseEntity deleteFilmScreening(int id) {
        FilmScreening filmScreening = filmScreeningRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FilmScreening", "id", id));
        filmScreeningRepository.delete(filmScreening);

        return ResponseEntity.ok().build();
    }
}

