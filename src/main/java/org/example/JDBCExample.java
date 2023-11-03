package org.example;

import java.sql.*;

public class JDBCExample {

    private static Connection connection;

    // Establish a database connection
    public void connect() {

        String url = "jdbc:mysql://localhost:3306/world";
        String user = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Method to execute "SELECT * FROM country"
    public void printAllCountries() {
        try {
            String sql = "SELECT * FROM country";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String continent = resultSet.getString("Continent");
                String region = resultSet.getString("region");
                Integer population = resultSet.getInt("population");
                String countryCode = resultSet.getString("CountryCode");
                System.out.printf("%d | %s | %s | %s | %d | %s %n",
                        id, name, continent, region, population, countryCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to execute "SELECT * FROM city"
    public void printAllCities() {
        try {
            String sql = "SELECT * FROM city";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            printCity(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printCity(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("Name");
            String country = resultSet.getString("CountryCode");
            String district = resultSet.getString("district");
            String population = resultSet.getString("population");
            System.out.printf("%d | %s | %s | %s%n", id, name, country, district, population);
        }
    }

    // Method to find all cities in a given country
    public void findCitiesByCountry(String countryName) {
        try {
            String sql = "SELECT * FROM city WHERE CountryCode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, countryName);
            ResultSet resultSet = preparedStatement.executeQuery();

            printCity(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Close the database connection
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JDBCExample main = new JDBCExample();
        main.connect();

        // Execute the methods
        System.out.println("All Countries:");
        main.printAllCountries();

        System.out.println("\nAll Cities:");
        main.printAllCities();

        System.out.println("\nCities in a Given Country (e.g., 'USA'):");
        main.findCitiesByCountry("USA");

        // Close the connection when done
        main.closeConnection();
    }
}
