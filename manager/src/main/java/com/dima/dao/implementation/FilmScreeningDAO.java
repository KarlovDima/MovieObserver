package com.dima.dao.implementation;

import com.dima.dao.GenericDAO;
import com.dima.models.FilmScreening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource("classpath:database.properties")
public class FilmScreeningDAO implements GenericDAO<FilmScreening, Integer> {
    @Autowired
    private DataSource dataSource;

    @Override
    public List<FilmScreening> getAll() {
        List<FilmScreening> filmScreeningList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FILM_SCREENING");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next())
                filmScreeningList.add(FilmScreening.builder()
                        .id(resultSet.getInt(1))
                        .filmId(resultSet.getInt(2))
                        .time(resultSet.getString(3))
                        .build());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmScreeningList;
    }

    @Override
    public int update(FilmScreening entity) {
        int affectedRowsAmount = 0;
        try (Connection connection = dataSource.getConnection();
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
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FILM_SCREENING WHERE ID = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next())
                    FilmScreening.builder()
                            .id(resultSet.getInt(1))
                            .filmId(resultSet.getInt(2))
                            .time(resultSet.getString(3))
                            .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int delete(Integer id) {
        int affectedRowsAmount = 0;
        try (Connection connection = dataSource.getConnection();
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
        try (Connection connection = dataSource.getConnection();
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

