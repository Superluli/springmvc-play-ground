package com.superluli.spg.app;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
/*
 * tightly(type safe) bind properties and yaml file to a pojo
 */
@EnableConfigurationProperties
/*
 * inject properties into org.springframework.core.env.Environment, can be accessed via @Value
 * later, or via Environment bean,
 * shortcomings : this doesn't work for yaml files, and since all properties merge to environment,
 * values will be missing if key duplicates in multiple properties files
 */
// @PropertySource("classpath:application2.properties")
public class ApplicationConfig {
    

}
