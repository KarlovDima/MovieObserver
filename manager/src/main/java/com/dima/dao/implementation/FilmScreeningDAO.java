package com.dima.dao.implementation;

import com.dima.dao.DataSourceConnection;
import com.dima.dao.GenericDAO;
import com.dima.models.FilmScreening;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmScreeningDAO implements GenericDAO<FilmScreening, Integer> {
    @Override
    public List<FilmScreening> getAll() {
        List<FilmScreening> filmScreeningList = new ArrayList<>();
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FILM_SCREENING");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while ((resultSet.next())) {
                FilmScreening filmScreening = new FilmScreening();
                filmScreening.setId(resultSet.getInt(1));
                filmScreening.setFilmId(resultSet.getInt(2));
                filmScreening.setTime(resultSet.getString(3));
                filmScreeningList.add(filmScreening);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmScreeningList;
    }

    @Override
    public int update(FilmScreening entity) {
        int affectedRowsAmount = 0;
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE FILM_SCREENING " +
                     "SET FILM_ID = ?, TIME = ? WHERE ID = ?")) {
            preparedStatement.setInt(1, entity.getFilmId());
            preparedStatement.setString(2, entity.getTime());
            preparedStatement.setInt(3, entity.getId());
            affectedRowsAmount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRowsAmount;
    }

    @Override
    public FilmScreening getEntityById(Integer id) {
        FilmScreening filmScreening = new FilmScreening();
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FILM_SCREENING WHERE ID = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while ((resultSet.next())) {
                    filmScreening.setId(resultSet.getInt(1));
                    filmScreening.setFilmId(resultSet.getInt(2));
                    filmScreening.setTime(resultSet.getString(3));
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmScreening;
    }

    @Override
    public int delete(Integer id) {
        int affectedRowsAmount = 0;
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM FILM_SCREENING WHERE ID = ?")) {
            preparedStatement.setInt(1, id);
            affectedRowsAmount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRowsAmount;
    }

    @Override
    public int create(FilmScreening entity) {
        int affectedRowsAmount = 0;
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO FILM_SCREENING " +
                     "(FILM_ID, TIME) VALUES (?, ?)")) {
            preparedStatement.setInt(1, entity.getFilmId());
            preparedStatement.setString(2, entity.getTime());
            affectedRowsAmount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRowsAmount;
    }
}

