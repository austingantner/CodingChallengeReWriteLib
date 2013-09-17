/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austingantner.codingchallengerewritelib;

import java.sql.*;

/**
 *
 * @author austingantner
 */
public class ConnectionManager {
    //programuser BestAchievementSystemNA

    private static final String url = "jdbc:mysql://192.168.1.155:3306/";
    private static final String dbName = "CodingChallengeReWrite";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String userName = "programuser";
    private static final String password = "BestAchievementSystemNA";

    public interface ResultSetHandler {
        void Handle(ResultSet rs)throws SQLException;
    }
    
    //Using a handler means we can force the connection to close at the end
    // of the query function. This also makes it much easier to add connection
    //pooling later with something like c3po becaues the connection never leaves
    //the ConnectionManger class like it would if we returned a ResultSet.
    public static void Query(String queryString, ResultSetHandler handler) {
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url + dbName, userName, password);
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(queryString);
            handler.Handle(res);
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static void executeNonQuery(String queryString) {
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url + dbName, userName, password);
            Statement statement = conn.createStatement();
            statement.executeUpdate(queryString);
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
