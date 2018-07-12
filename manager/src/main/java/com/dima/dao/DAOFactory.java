package com.dima.dao;

import com.dima.dao.implementation.CriticDAO;
import com.dima.dao.implementation.FilmDAO;
import com.dima.dao.implementation.FilmScreeningDAO;
import com.dima.dao.implementation.ReviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DAOFactory {
    @Autowired
    private CriticDAO criticDAO;

    @Autowired
    private FilmDAO filmDAO;

    @Autowired
    private FilmScreeningDAO filmScreeningDAO;

    @Autowired
    private ReviewDAO reviewDAO;

    public CriticDAO getCriticDAO() {
        return criticDAO;
    }

    public FilmDAO getFilmDAO() {
        return filmDAO;
    }

    public FilmScreeningDAO getFilmScreeningDAO() {
        return filmScreeningDAO;
    }

    public ReviewDAO getReviewDAO() {
        return reviewDAO;
    }
}
