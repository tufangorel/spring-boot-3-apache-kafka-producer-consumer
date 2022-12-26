package com.kafka.producer.api.service.integration.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;


@SpringBootTest( properties = "spring.datasource.type=com.zaxxer.hikari.HikariDataSource")
public class HikariIntegrationTest {

    private final DataSource dataSource;

    @Autowired
    public HikariIntegrationTest(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Test
    public void hikariConnectionPoolIsConfigured() throws SQLException {
        java.sql.Connection connection = dataSource.getConnection();
        Assertions.assertNotNull(connection);
    }
}
