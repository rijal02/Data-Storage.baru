/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastorage;

/**
 *
 * @author rizal
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaatabaseStorage implements DataStorage {

    private Connection connection;

    public DaatabaseStorage(String databasePath) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
            createDataTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createDataTable() throws SQLException {
        try ( Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS data (value TEXT)");
        }
    }

    @Override
    public void writeData(String data) {
        try ( PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO data (value) VALUES( ?)")) {
            statement.setString(1, data);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readData() {
        StringBuilder sb = new StringBuilder();
        try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT value FROM data")) {

            while (resultSet.next()) {
                sb.append(resultSet.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
