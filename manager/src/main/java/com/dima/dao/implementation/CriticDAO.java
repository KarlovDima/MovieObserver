package com.dima.dao.implementation;

import com.dima.dao.GenericDAO;
import com.dima.models.Critic;
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
public class CriticDAO implements GenericDAO<Critic, Integer> {
    @Autowired
    private DataSource dataSource;

    @Override
    public List<Critic> getAll() {
        List<Critic> criticList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CRITIC");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next())
                criticList.add(Critic.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .workBeginning(resultSet.getString(3))
                        .workEnding(resultSet.getString(4))
                        .build());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return criticList;
    }

    @Override
    public int update(Critic entity) {
        int affectedRowsAmount = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE CRITIC " +
                     "SET NAME = ?, WORK_BEGINNING = ?, WORK_ENDING = ? WHERE ID = ?")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getWorkBeginning());
            preparedStatement.setString(3, entity.getWorkEnding());
            preparedStatement.setInt(4, entity.getId());
            affectedRowsAmount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRowsAmount;
    }

    @Override
    public Critic getEntityById(Integer id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CRITIC WHERE ID = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next())
                    return Critic.builder()
                            .id(resultSet.getInt(1))
                            .name(resultSet.getString(2))
                            .workBeginning(resultSet.getString(3))
                            .workEnding(resultSet.getString(4))
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
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM CRITIC WHERE ID = ?")) {
            preparedStatement.setInt(1, id);
            affectedRowsAmount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRowsAmount;
    }

    @Override
    public int create(Critic entity) {
        int affectedRowsAmount = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CRITIC " +
                     "(NAME, WORK_BEGINNING, WORK_ENDING) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getWorkBeginning());
            preparedStatement.setString(3, entity.getWorkEnding());
            affectedRowsAmount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRowsAmount;
    }
}