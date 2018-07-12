package com.dima.dao.implementation;

import com.dima.dao.GenericDAO;
import com.dima.models.Film;
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
public class FilmDAO implements GenericDAO<Film, Integer> {
    @Autowired
    private DataSource dataSource;

    @Override
    public List<Film> getAll() {
        List<Film> filmList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FILM");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next())
                filmList.add(Film.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .duration(resultSet.getInt(3))
                        .isActive(resultSet.getBoolean(4))
                        .build());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmList;
    }

    @Override
    public int update(Film entity) {
        int affectedRowsAmount = 0;
        try (Connection connection = dataSource.getConnection();
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
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FILM WHERE ID = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next())
                    return Film.builder()
                            .id(resultSet.getInt(1))
                            .name(resultSet.getString(2))
                            .duration(resultSet.getInt(3))
                            .isActive(resultSet.getBoolean(4))
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
        try (Connection connection = dataSource.getConnection();
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
