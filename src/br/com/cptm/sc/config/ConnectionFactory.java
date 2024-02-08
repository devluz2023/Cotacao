package br.com.cptm.sc.config;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fabiolu
 */

public class ConnectionFactory {

    final private static String url = "jdbc:mysql://localhost:3311/laboratorio_altino?zeroDateTimeBehavior=convertToNull";
    final private static String user = "root";
    private static final String password = "root";
    private MysqlDataSource dataSource;

    public ConnectionFactory() {
        dataSource = new MysqlDataSource();
        dataSource.setUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
    }

    public Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }
   
}
