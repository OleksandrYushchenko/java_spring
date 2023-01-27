//package com.example.demo.DAO;
//
//import com.example.demo.connector.DbConnector;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.sql.*;
//
//@Component
//public class GameDAO {
//    @Autowired
//    public DbConnector dbConnector;
//    public void DAO(){
//    }
//    public void create(String table, String creationId, String factoryId) {
//        try {
//            Statement statement = dbConnector.getConnection().createStatement();
//            // Create and execute a SELECT SQL statement.
//            String selectSql = "INSERT INTO " + table + "(" + "creationId" + ", " + "factoryId" +")" + " VALUES" + "(" + "'" + creationId + "'" + ", " + "'" + factoryId + "'" +")";
//            System.out.println(selectSql);
//            statement.executeUpdate(selectSql);
//            // Print results from select statement
//            System.out.println("OK");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
