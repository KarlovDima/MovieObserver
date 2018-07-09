package com.dima.models;

public class Review {
    private int id;
    private int filmId;
    private int criticId;
    private String comment;

    public Review() {
    }

    public Review(int id, int filmId, int criticId, String comment) {
        this.id = id;
        this.filmId = filmId;
        this.criticId = criticId;
        this.comment = comment;
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

    public int getCriticId() {
        return criticId;
    }

    public void setCriticId(int criticId) {
        this.criticId = criticId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
