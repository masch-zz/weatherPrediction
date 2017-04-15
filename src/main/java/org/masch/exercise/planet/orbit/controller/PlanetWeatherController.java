package org.masch.exercise.planet.orbit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Controller
public class PlanetWeatherController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello Planet!";
    }

}
