package com.moon.senla.util;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.LoggerFactory;

public class LoggerProperty {
    static {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator jc = new JoranConfigurator();
        jc.setContext(context);
        context.reset();
        context.putProperty("application-name", "BookShop");
        try {
            jc.doConfigure("BookShop/src/main/resources/logback.xml");
        } catch (JoranException e) {
            e.printStackTrace();
        }
    }
}