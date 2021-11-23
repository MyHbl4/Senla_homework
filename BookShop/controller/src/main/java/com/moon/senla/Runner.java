package com.moon.senla;

import com.moon.senla.action.util.LoggerProperty;
import com.moon.senla.action.util.SpringConfig;
import com.moon.senla.menu.MenuController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        new LoggerProperty();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        context.getBean(MenuController.class).run();

        LOGGER.info("The program is closed");
    }
}
