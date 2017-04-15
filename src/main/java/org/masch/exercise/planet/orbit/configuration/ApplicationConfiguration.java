package org.masch.exercise.planet.orbit.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationConfiguration {

    private static final Logger log = LoggerFactory.getLogger(ApplicationConfiguration.class);

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfiguration.class);
    }

}
