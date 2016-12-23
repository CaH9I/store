package com.expertsoft.core.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Import({DataSourceConfig.class, SecurityConfig.class})
@ComponentScan({"com.expertsoft.core.service", "com.expertsoft.core.validator"})
public class RootConfig {}
