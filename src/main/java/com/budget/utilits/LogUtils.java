package com.budget.utilits;

import org.apache.log4j.Logger;

public class LogUtils {

    static {
        logger = Logger.getLogger(LogUtils.class);
    }

    private static final Logger logger;

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(Throwable t) {
        logger.error("ERROR", t);
    }

    public static void fatal(Throwable t) {
        logger.fatal("FATAL", t);
    }
}
