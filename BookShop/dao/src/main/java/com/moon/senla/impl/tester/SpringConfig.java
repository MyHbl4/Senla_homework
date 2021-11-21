package com.moon.senla.impl.tester;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.moon.senla")
@PropertySource("classpath:musicPlayer.properties")
public class SpringConfig {
}
