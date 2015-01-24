package com.superluli.spg.app;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@EnableAutoConfiguration
@Configuration
/*
 * tightly(type safe) bind properties and yaml file to a pojo, and properties won't be merged into
 * environment, and can use "merge" flag to choose if overwrite if same key found from high level
 * property source(CL, default file, etc)
 */
@EnableConfigurationProperties
/*
 * inject properties into org.springframework.core.env.Environment, can be accessed via @Value
 * later, or via Environment bean,
 * shortcomings : this doesn't work for yaml files, and since all properties merge to environment,
 * values will be missing if key duplicates in multiple properties files
 */
//@PropertySource("classpath:nested.properties")
public class ApplicationContext {

}
