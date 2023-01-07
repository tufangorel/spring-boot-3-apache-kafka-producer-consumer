package com.kafka.consumer.api.config.application.listener;

import com.kafka.consumer.api.config.datasource.DBHealthCheckConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventListener.class);

    @Autowired
    private DBHealthCheckConfig dbHealthCheckConfig;

    @Autowired
    private Environment environment;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if( dbHealthCheckConfig.health().getStatus() == Status.UP )
            LOGGER.info("Initial Database Connection Check Success");
        else
            LOGGER.info("Initial Database Connection Check Fail");

        String port = environment.getProperty("server.port");
        LOGGER.info("Tomcat started on port : "+port);
    }
}