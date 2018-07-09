package com.dima.dao;

import com.dima.dao.implementation.CriticDAO;
import com.dima.dao.implementation.FilmDAO;
import com.dima.dao.implementation.FilmScreeningDAO;
import com.dima.dao.implementation.ReviewDAO;

public class DAOFactory {
    private static CriticDAO criticDAO;
    private static FilmDAO filmDAO;
    private static FilmScreeningDAO filmScreeningDAO;
    private static ReviewDAO reviewDAO;
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static synchronized DAOFactory getInstance() {
        if (daoFactory == null)
            daoFactory = new DAOFactory();
        return daoFactory;
    }

    public CriticDAO getCriticDAO() {
        if (criticDAO == null) {
            criticDAO = new CriticDAO();
        }
        return criticDAO;
    }

    public FilmDAO getFilmDAO() {
        if (filmDAO == null) {
            filmDAO = new FilmDAO();
        }
        return filmDAO;
    }

    public FilmScreeningDAO getFilmScreeningDAO() {
        if (filmScreeningDAO == null) {
            filmScreeningDAO = new FilmScreeningDAO();
        }
        return filmScreeningDAO;
    }

    public ReviewDAO getReviewDAO() {
        if (reviewDAO == null) {
            reviewDAO = new ReviewDAO();
        }
        return reviewDAO;
    }
}
