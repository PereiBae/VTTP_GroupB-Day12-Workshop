package com.example.day12;

import java.util.Collections;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
public class Day12Application {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(Day12Application.class);

		/* // Default port value
		String port ="3000";

		// Check for command-line option '--port'
		ApplicationArguments cliOpts = new DefaultApplicationArguments(args);
		if (cliOpts.containsOption("port")){
			port = cliOpts.getOptionValues("port").get(0);
		} else{
			// Check for environment variable 'PORT'
			String envPort = System.getenv("PORT");
			if (envPort != null && !envPort.isEmpty()) {
				port = envPort;
			}
		}

		// Set the server port
		app.setDefaultProperties(Collections.singletonMap("server.port", port));

		System.out.printf("Application started on port %s\n",port); */
		app.run(args);

	}

	@Bean
	public CommonsRequestLoggingFilter log(){
		CommonsRequestLoggingFilter logger = new CommonsRequestLoggingFilter();
		logger.setIncludeClientInfo(true);
		logger.setIncludeQueryString(true);
		return logger;
	}

}
