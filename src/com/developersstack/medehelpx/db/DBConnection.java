package com.developersstack.medehelpx.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;

    private Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("jdbc:mysql://localhost:3306/medehelpx");
        connection = DriverManager.getConnection("com.mysql.cj.jdbc.Driver",
                "root","1234");

    }
    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        return DBConnection.dbConnection==null?dbConnection= new DBConnection():dbConnection;
    }
    public Connection getConnection(){
        return connection;
    }

}
