package com.djorquab.relational.relational;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJms
@ComponentScan
public class RelationalApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelationalApplication.class, args);
	}

}

