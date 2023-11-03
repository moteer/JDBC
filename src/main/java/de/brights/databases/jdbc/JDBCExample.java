package de.brights.databases.jdbc;

import de.brights.databases.jdbc.entity.City;
import de.brights.databases.jdbc.entity.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<City> findCitiesByCountry(String countryName) {
        List<City> cities = new ArrayList<>();
        try {
            String sql = "SELECT * FROM city WHERE CountryCode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, countryName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String country = resultSet.getString("CountryCode");
                String district = resultSet.getString("district");
                String population = resultSet.getString("population");

                cities.add(new City(id, name, country, district, population));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
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

        System.out.println("All Countries:");
        main.printAllCountries();

        System.out.println("\nAll Cities:");
        main.printAllCities();

        System.out.println("\nCities in a Given Country (e.g., 'USA'):");
        main.findCitiesByCountry("USA");

        System.out.println("\nCountries for a continent (e.g., 'Europe'):");
        List<Country> countries = main.getCountryBy("Europe");
        System.out.println(countries);

        main.closeConnection();
    }

    private static List<Country> getCountryBy(String continent) {
        Map<String, Country> countries = new HashMap<>();

        try {
            String sql = "SELECT * FROM country JOIN city ON country.code = city.countryCode WHERE country.continent = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, continent);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String countryName = resultSet.getString("country.name");
                String cont = resultSet.getString("country.continent");
                String region = resultSet.getString("country.region");

                int id = resultSet.getInt("city.ID");
                String name = resultSet.getString("city.Name");
                String code = resultSet.getString("city.CountryCode");
                String district = resultSet.getString("city.district");
                String population = resultSet.getString("city.population");

                countries.putIfAbsent(countryName, new Country(countryName, cont, region));
                Country country = countries.get(countryName);
                country.getCities().add(new City(id, name, countryName, district, population));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(countries.values());

    }
}
