package com.ashfaq.example.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/log")
public class LogController {

	//	Web layers : POST /log/level?packageName=org.springframework.web&level=DEBUG
	//	JPA (Repository/Database Layer)POST /log/level?packageName=org.hibernate.SQL&level=DEBUG
	//	SQL queries generated by Hibernate:
	//	POST /log/level?packageName=org.hibernate.SQL&level=DEBUG
	//			SQL query parameter bindings:
	//			POST /log/level?packageName=org.hibernate.type&level=TRACE
	//					 Spring Data JPA repository operations:
	//	POST /log/level?packageName=org.springframework.data.jpa&level=DEBUG

	// this can targer the whole class or the the whole package
	@PostMapping("/level")
	public String changeLogLevel(@RequestParam String packageName, @RequestParam String level) {
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		Logger logger = loggerContext.getLogger(packageName);

		// Set the log level based on the API request
		logger.setLevel(Level.toLevel(level.toUpperCase()));

		return "Log level for " + packageName + " changed to " + level;
	}

}
