package com.expertsoft.core.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.expertsoft.core.service", "com.expertsoft.core.validator"})
public class ServiceConfig {}
