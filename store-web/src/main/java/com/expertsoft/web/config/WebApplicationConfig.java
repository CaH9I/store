package com.expertsoft.web.config;

import com.expertsoft.core.CoreApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableCaching
@Import(CoreApplication.class)
public class WebApplicationConfig {}
