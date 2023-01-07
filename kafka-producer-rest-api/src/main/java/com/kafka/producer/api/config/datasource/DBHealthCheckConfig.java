package com.kafka.producer.api.config.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DBHealthCheckConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    public Health health() {

        Connection connection = null;
        try {
            //Connect to Database
            DriverManager.registerDriver( new org.h2.Driver() );
            connection = DriverManager.getConnection( url, userName, password );
            String QUERY="select 1";
            //Run your query
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
            rs.close();
            stmt.close();
        } catch (SQLException sqlException) {
            return Health.down().build();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Health.up().build();
    }

}
