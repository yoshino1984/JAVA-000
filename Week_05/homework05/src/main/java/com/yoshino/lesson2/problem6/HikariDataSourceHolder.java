package com.yoshino.lesson2.problem6;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wangxin
 * 2020/11/15 下午6:11
 * @since
 **/
public class HikariDataSourceHolder {

    static volatile HikariDataSource dataSource = null;

    private static HikariDataSource getDataSource() {
        if (dataSource == null) {
            synchronized ( HikariDataSourceHolder.class) {
                if (dataSource == null) {
                    dataSource = new HikariDataSource();
                    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
                    dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/nicefish");
                    dataSource.setUsername("root");
                    dataSource.setPassword("root1234");
                    dataSource.setMinimumIdle(5);
                    dataSource.setMaximumPoolSize(15);
                    dataSource.setAutoCommit(true);
                    dataSource.setIdleTimeout(30000);
                    dataSource.setPoolName("HikariCp1");
                    dataSource.setMaxLifetime(1800000);
                    dataSource.setConnectionTimeout(30000);
                    dataSource.setConnectionTestQuery("select 1");
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = getDataSource().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
}
