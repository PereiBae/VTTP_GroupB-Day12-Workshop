package com.example.day12.controllers;

import java.util.logging.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggerController {

    // Create a Logger instance using java.util.logging
    private static final Logger logger = Logger.getLogger(LoggerController.class.getName());

    @GetMapping("/log")
    public String logExample() {
        
        // Use the java.util.logging methods
        logger.info("This is an INFO message");
        logger.warning("This is a WARN message");
        logger.severe("This is a SEVERE/FATAL message");
        logger.log(Level.FINER, "This is a FINER message");

        return "Logging with java.util.logging!";
    }
}