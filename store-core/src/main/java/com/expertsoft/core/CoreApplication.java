package com.expertsoft.core;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableCaching
@EnableAutoConfiguration
public class CoreApplication {}
