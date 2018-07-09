package com.dima.models;

public class FilmScreening {
    private int id;
    private int filmId;
    private String time;

    public FilmScreening() {
    }

    public FilmScreening(int id, int filmId, String time) {
        this.id = id;
        this.filmId = filmId;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
