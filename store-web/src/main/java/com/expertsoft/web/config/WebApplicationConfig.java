package com.expertsoft.web.config;

import com.expertsoft.core.CoreApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import(CoreApplication.class)
@ImportResource("classpath:config/web-context.xml")
public class WebApplicationConfig {}
