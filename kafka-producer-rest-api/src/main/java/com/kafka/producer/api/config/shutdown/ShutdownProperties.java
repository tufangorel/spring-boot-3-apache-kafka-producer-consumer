package com.kafka.producer.api.config.shutdown;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(value = "endpoints.shutdown")
public class ShutdownProperties {

    private long timeout = 3000;

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }


}
