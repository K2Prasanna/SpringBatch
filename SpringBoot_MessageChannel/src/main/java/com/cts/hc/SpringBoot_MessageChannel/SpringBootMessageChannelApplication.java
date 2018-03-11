package com.cts.hc.SpringBoot_MessageChannel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Transformers;

@SpringBootApplication(scanBasePackages="com.cts")

public class SpringBootMessageChannelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMessageChannelApplication.class, args);
	}
	

}
