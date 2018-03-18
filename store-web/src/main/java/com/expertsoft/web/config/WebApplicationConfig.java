package com.expertsoft.web.config;

import com.expertsoft.core.CoreApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@Import(CoreApplication.class)
@EnableWebSecurity
@ImportResource("classpath:config/web-context.xml")
public class WebApplicationConfig {}
