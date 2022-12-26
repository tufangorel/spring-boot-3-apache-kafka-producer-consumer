package com.kafka.producer.api.config.datasource;

import com.zaxxer.hikari.HikariPoolMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class HikariCPStatisticsBackgroundThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(HikariCPStatisticsBackgroundThread.class);

    private HikariPoolMXBean poolProxy;

    public HikariCPStatisticsBackgroundThread( String hikariConnectionPoolName ) {

        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName poolAccessor = new ObjectName("com.zaxxer.hikari:type=Pool (" + hikariConnectionPoolName + ")");
            poolProxy = JMX.newMXBeanProxy(mBeanServer, poolAccessor, HikariPoolMXBean.class);
            logger.debug("HikariPoolMXBean created!");
        } catch (MalformedObjectNameException e) {
            throw new RuntimeException("Pool " + hikariConnectionPoolName + " could not be found", e);
        }
    }

    @Override
    public void run() {

        while( true ) {
            logger.info("Starting Connection Pool status monitor");
            logger.info("hikariPool getActiveConnections : " + poolProxy.getActiveConnections());
            logger.info("hikariPool getTotalConnections : " + poolProxy.getTotalConnections());
            logger.info("hikariPool getIdleConnections : " + poolProxy.getIdleConnections());
            logger.info("hikariPool getThreadsAwaitingConnection : " + poolProxy.getThreadsAwaitingConnection());
            logger.info("End Connection Pool status monitor");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {

                logger.error("ERROR in Hikari Connection Pool status monitor");
                while (poolProxy.getActiveConnections() > 0 ) {
                    poolProxy.softEvictConnections();
                    // sleep a little
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        logger.error("ERROR in Hikari Connection softEvictConnections : "+ex.getMessage());
                    }
                }
            }
        }

    }

}