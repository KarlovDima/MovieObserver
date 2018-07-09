package com.dima.dao.implementation;

import com.dima.dao.DataSourceConnection;
import com.dima.dao.GenericDAO;
import com.dima.models.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO implements GenericDAO<Film, Integer> {
    @Override
    public List<Film> getAll() {
        List<Film> filmList = new ArrayList<>();
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FILM");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while ((resultSet.next())) {
                Film film = new Film();
                film.setId(resultSet.getInt(1));
                film.setName(resultSet.getString(2));
                film.setDuration(resultSet.getInt(3));
                film.setActive(resultSet.getBoolean(4));
                filmList.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmList;
    }

    @Override
    public int update(Film entity) {
        int affectedRowsAmount = 0;
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE FILM " +
                     "SET NAME = ?, DURATION = ?, ACTIVE = ? WHERE ID = ?")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getDuration());
            preparedStatement.setBoolean(3, entity.isActive());
            preparedStatement.setInt(4, entity.getId());
            affectedRowsAmount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRowsAmount;
    }

    @Override
    public Film getEntityById(Integer id) {
        Film film = new Film();
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FILM WHERE ID = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while ((resultSet.next())) {
                    film.setId(resultSet.getInt(1));
                    film.setName(resultSet.getString(2));
                    film.setDuration(resultSet.getInt(3));
                    film.setActive(resultSet.getBoolean(4));
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return film;
    }

    @Override
    public int delete(Integer id) {
        int affectedRowsAmount = 0;
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM FILM WHERE ID = ?")) {
            preparedStatement.setInt(1, id);
            affectedRowsAmount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRowsAmount;
    }

    @Override
    public int create(Film entity) {
        int affectedRowsAmount = 0;
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO FILM " +
                     "(NAME, DURATION, ACTIVE) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getDuration());
            preparedStatement.setBoolean(3, entity.isActive());
            affectedRowsAmount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRowsAmount;
    }
}
