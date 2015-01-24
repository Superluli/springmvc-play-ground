package com.superluli.spg.app;

import org.springframework.boot.SpringApplication;

public class Application {

    public static void main(String[] args) {
	SpringApplication app = new SpringApplication(ApplicationContext.class);
	app.setShowBanner(false);
	app.run(args);
    }
}
