package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {
    //
    @Override
    public Connection createConnection() {
        //
        Properties properties = loadConnectionProperties("h2database.properties");
        try {
            Class.forName(properties.getProperty("jdbc_driver"));
            String dbUrl = properties.getProperty("db_url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            return DriverManager.getConnection(dbUrl, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties loadConnectionProperties(String propertiesFile) {
        //
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(propertiesFile)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to loan connection properties");
        }
        return properties;
    }
}

