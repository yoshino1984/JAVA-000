package com.yoshino.lesson2.problem6;

import com.yoshino.lesson2.problem6.model.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JdbcCrudDemo {

    public static void main(String[] args) {
        String id11 = "11";
        String id12 = "12";

        // insert
        UserRole userRole1 = UserRole.builder().id(id11).roleId("1").userId("1").build();
        UserRole userRole2 = UserRole.builder().id(id12).roleId("2").userId("2").build();
        insert(HikariDataSourceHolder.getConnection(), Arrays.asList(userRole1, userRole2));

        // query
        System.out.println(listAll(HikariDataSourceHolder.getConnection()));

        // update
        userRole1.setRoleId("new1");
        userRole2.setRoleId("new2");
        update(HikariDataSourceHolder.getConnection(), Arrays.asList(userRole1, userRole2));

        System.out.println(listAll(HikariDataSourceHolder.getConnection()));

        // delete
        delete(HikariDataSourceHolder.getConnection(), Arrays.asList(id11, id12));

    }

    private static void insert(Connection conn, List<UserRole> userRoles) {
        String sql = "INSERT INTO user_role (ID, USER_ID, ROLE_ID) VALUES (?,?,?);";
        PreparedStatement pstmt;
        try {
            // 事务，关闭自动提交
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            for (UserRole userRole : userRoles) {
                try {
                    pstmt.setString(1, userRole.getId());
                    pstmt.setString(2, userRole.getUserId());
                    pstmt.setString(3, userRole.getRoleId());
                    pstmt.addBatch();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            int[] ret = pstmt.executeBatch();
            System.out.println(Arrays.toString(ret));
            pstmt.close();
            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void delete(Connection conn, List<String> ids) {
        String sql = "delete from user_role where id = ?";

        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            for (String id : ids) {
                pstmt.setString(1, id);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            conn.commit();
            pstmt.close();
            conn.setAutoCommit(true);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<UserRole> listAll(Connection conn) {
        String sql = "select ID as id, USER_ID as userId, ROLE_ID as roleId from user_role";

        PreparedStatement pstmt;
        List<UserRole> result = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                UserRole userRole = new UserRole();
                userRole.setId(rs.getString(1));
                userRole.setUserId(rs.getString(2));
                userRole.setRoleId(rs.getString(3));
                result.add(userRole);
            }
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private static void update(Connection conn, List<UserRole> userRoles) {
        String sql = "update user_role set USER_ID = ?, ROLE_ID = ? where ID = ?";
        PreparedStatement pstmt;
        try {
            // 事务，关闭自动提交
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            for (UserRole userRole : userRoles) {
                try {
                    pstmt.setString(1, userRole.getUserId());
                    pstmt.setString(2, userRole.getRoleId());
                    pstmt.setString(3, userRole.getId());
                    pstmt.addBatch();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            int[] ret = pstmt.executeBatch();
            System.out.println(Arrays.toString(ret));
            pstmt.close();
            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static Connection getConnection() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/nicefish";
        String username = "root";
        String password = "root1234";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
