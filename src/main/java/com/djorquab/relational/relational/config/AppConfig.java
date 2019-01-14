package com.djorquab.relational.relational.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.djorquab.relational.relational.managers.PropertyManager;

@Configuration
public class AppConfig {
	@Autowired
	private PropertyManager propertyManager;
	
	
}
