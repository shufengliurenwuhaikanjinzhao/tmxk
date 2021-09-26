package com.springcloud.tmxk.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnaction {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db01");

        String sql = "select * from user";

        Statement statement = connection.createStatement();
        statement.execute(sql);


        statement.close();
        connection.close();

    }
}
