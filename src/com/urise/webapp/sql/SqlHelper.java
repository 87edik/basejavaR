package com.urise.webapp.sql;

import com.urise.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
    public void execute(String sql) {
        execute(sql, PreparedStatement::execute);
    }
    public <T> T execute(String sql, SqlExecutor<T> executor){
        try(
                Connection conn = connectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ) {

            return executor.execute(ps);
        } catch (SQLException e) {

            throw ExceptionUtil.convertException(e);
        }
    }
}
