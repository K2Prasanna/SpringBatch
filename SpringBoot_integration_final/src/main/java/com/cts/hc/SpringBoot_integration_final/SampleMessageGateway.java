package com.cts.hc.SpringBoot_integration_final;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "outputChannel")
public interface SampleMessageGateway {

	void echo(String message);

}