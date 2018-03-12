package com.cts.hc.SpringBootSkippableDemo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages="com.cts")
@EnableBatchProcessing
@EnableScheduling // Enables scheduling
public class SpringBootSkippableDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSkippableDemoApplication.class, args);
	}
}
