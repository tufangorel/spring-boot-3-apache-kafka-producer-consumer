package com.kafka.consumer.api.config.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;


@Component
public class MonitorHikariDataSourceConfig implements ApplicationListener<ApplicationStartedEvent> {

    private static final Logger log = LoggerFactory.getLogger(MonitorHikariDataSourceConfig.class);

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        String hikariConnectionPoolName = env.getProperty("spring.datasource.poolName");
        HikariCPStatisticsBackgroundThread hikariCPStatisticsBackgroundThread = new HikariCPStatisticsBackgroundThread(hikariConnectionPoolName);
        executor.execute(hikariCPStatisticsBackgroundThread );
    }

}