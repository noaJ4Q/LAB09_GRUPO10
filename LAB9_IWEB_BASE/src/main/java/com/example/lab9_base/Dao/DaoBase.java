package com.example.lab9_base.Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public abstract class DaoBase {

        public Connection getConection() throws SQLException {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            String username = "root";
            String password = "123456";
            String database = "lab9";
            String url = "jdbc:mysql://localhost:3306/" +database ;

            return DriverManager.getConnection(url, username, password);

        }
    }

