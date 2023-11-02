package org.example;

import java.sql.*;

public class Main {


    private static Connection connection;
    public static void main(String[] args) {


        // JDBC URL, username, and password of the local database
        String url = "jdbc:mysql://localhost:3306/world";
        String user = "root";
        String password = "";

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection(url, user, password);

            // Perform database operations here
            String sql = "SELECT * FROM country";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {
                String code = resultSet.getString("code");
                String name = resultSet.getString("Name");
                String region = resultSet.getString("region");
                System.out.println(String.format("%s | %s | %s", code, name, region));
                // Process the retrieved data
            }

            // Don't forget to close the connection when you're done
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}