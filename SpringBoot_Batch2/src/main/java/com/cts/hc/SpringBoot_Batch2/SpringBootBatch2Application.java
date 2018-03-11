package com.cts.hc.SpringBoot_Batch2;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.cts.hc")
@EnableBatchProcessing
public class SpringBootBatch2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBatch2Application.class, args);
	}
}
