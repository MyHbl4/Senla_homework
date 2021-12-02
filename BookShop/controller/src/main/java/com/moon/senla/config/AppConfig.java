package com.moon.senla.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.moon.senla")
@PropertySource("classpath:app.properties")
public class AppConfig {
}
