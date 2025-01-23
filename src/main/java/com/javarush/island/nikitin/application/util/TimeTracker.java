package com.javarush.island.nikitin.application.util;

import com.javarush.island.nikitin.application.constants.LogMessagesApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Record and log the operating time of the thread pool
 */
public class TimeTracker {
    private long beginTime;
    private long endTime;
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeTracker.class);


    public void beginTiming() {
        this.beginTime = System.currentTimeMillis();
    }

    public void endTiming() {
        this.endTime = System.currentTimeMillis();
    }

    public void writeTimeLog() {
        long executeTime = endTime - beginTime;
        LOGGER.info(LogMessagesApp.EXECUTE_TIME, executeTime);
    }
}
