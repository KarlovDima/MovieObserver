package com.dima.dao.implementation;

import com.dima.dao.DataSourceConnection;
import com.dima.dao.GenericDAO;
import com.dima.models.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO implements GenericDAO<Review, Integer> {
    @Override
    public List<Review> getAll() {
        List<Review> reviewList = new ArrayList<>();
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM REVIEW");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while ((resultSet.next())) {
                Review review = new Review();
                review.setId(resultSet.getInt(1));
                review.setFilmId(resultSet.getInt(2));
                review.setCriticId(resultSet.getInt(3));
                review.setComment(resultSet.getString(4));
                reviewList.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewList;
    }

    @Override
    public int update(Review entity) {
        int affectedRowsAmount = 0;
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE REVIEW " +
                     "SET FILM_ID = ?, CRITIC_ID = ?, COMMENT = ? WHERE ID = ?")) {
            preparedStatement.setInt(1, entity.getFilmId());
            preparedStatement.setInt(2, entity.getCriticId());
            preparedStatement.setString(3, entity.getComment());
            preparedStatement.setInt(4, entity.getId());
            affectedRowsAmount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRowsAmount;
    }

    @Override
    public Review getEntityById(Integer id) {
        Review review = new Review();
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM REVIEW WHERE ID = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while ((resultSet.next())) {
                    review.setId(resultSet.getInt(1));
                    review.setFilmId(resultSet.getInt(2));
                    review.setCriticId(resultSet.getInt(3));
                    review.setComment(resultSet.getString(4));
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }

    @Override
    public int delete(Integer id) {
        int affectedRowsAmount = 0;
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM REVIEW WHERE ID = ?")) {
            preparedStatement.setInt(1, id);
            affectedRowsAmount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRowsAmount;
    }

    @Override
    public int create(Review entity) {
        int affectedRowsAmount = 0;
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO REVIEW " +
                     "(FILM_ID, CRITIC_ID, COMMENT) VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, entity.getFilmId());
            preparedStatement.setInt(2, entity.getCriticId());
            preparedStatement.setString(3, entity.getComment());
            affectedRowsAmount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRowsAmount;
    }
}

