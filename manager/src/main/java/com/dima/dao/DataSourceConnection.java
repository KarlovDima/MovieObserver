package com.dima.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceConnection {
    private final String NAME = "java:/comp/env/MOVIE_OBSERVER";
    private static DataSourceConnection instance;
    private Connection connection;

    private DataSourceConnection() {
        try {
            Context envContext = new InitialContext();
            DataSource ds = (DataSource) envContext.lookup(NAME);
            connection = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static synchronized DataSourceConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed())
            instance = new DataSourceConnection();
        return instance;
    }
}
