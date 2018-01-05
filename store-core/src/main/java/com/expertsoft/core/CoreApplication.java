package com.expertsoft.core;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan
@EnableAutoConfiguration
@ImportResource("classpath:config/core-context.xml")
public class CoreApplication {}
