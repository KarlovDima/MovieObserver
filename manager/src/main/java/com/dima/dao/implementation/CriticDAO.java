package com.dima.dao.implementation;

import com.dima.dao.DataSourceConnection;
import com.dima.dao.GenericDAO;
import com.dima.models.Critic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CriticDAO implements GenericDAO<Critic, Integer> {
    @Override
    public List<Critic> getAll() {
        List<Critic> criticList = new ArrayList<>();
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CRITIC");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while ((resultSet.next())) {
                Critic critic = new Critic();
                critic.setId(resultSet.getInt(1));
                critic.setName(resultSet.getString(2));
                critic.setWorkBeginning(resultSet.getString(3));
                critic.setWorkEnding(resultSet.getString(4));
                criticList.add(critic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return criticList;
    }

    @Override
    public int update(Critic entity) {
        int affectedRowsAmount = 0;
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
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
        Critic critic = new Critic();
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CRITIC WHERE ID = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while ((resultSet.next())) {
                    critic.setId(resultSet.getInt(1));
                    critic.setName(resultSet.getString(2));
                    critic.setWorkBeginning(resultSet.getString(3));
                    critic.setWorkEnding(resultSet.getString(4));
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return critic;
    }

    @Override
    public int delete(Integer id) {
        int affectedRowsAmount = 0;
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
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
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
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