package com.dima.review.managing;

import com.dima.dao.DAOFactory;
import com.dima.models.entity.Film;
import com.dima.models.entity.FilmScreening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class FilmEndingWatcher {
    @Autowired
    private DAOFactory daoFactory;

    @Autowired
    private ReviewSender reviewSender;

    @Scheduled(fixedRate = 10000, initialDelay = 30000)
    private void checkEndedFilms() {
        daoFactory.getFilmDAO().findAllActiveFilms().forEach(film -> {
            FilmScreening filmScreening = getEndedFilmScreening(film);
            if (filmScreening != null) {
                reviewSender.sendReview(film, filmScreening);
            }
        });
    }

    private FilmScreening getEndedFilmScreening(Film film) {
        for (FilmScreening filmScreening : film.getFilmScreenings())
            if (LocalTime.now().isAfter(getEndTime(filmScreening.getTime(), film.getDuration())))
                return filmScreening;
        return null;
    }

    private LocalTime getEndTime(String time, String duration) {
        LocalTime filmStarted = LocalTime.parse(time);
        LocalTime filmDuration = LocalTime.parse(duration);
        return filmStarted.plusHours(filmDuration.getHour()).plusMinutes(filmDuration.getMinute());
    }
}
