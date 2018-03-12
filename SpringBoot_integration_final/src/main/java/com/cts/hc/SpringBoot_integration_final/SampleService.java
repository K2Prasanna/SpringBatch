package com.cts.hc.SpringBoot_integration_final;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan({"com.cts.hc.SpringBoot_integration_final"})
public class SampleService {
    @Autowired 
	private final ServiceProperties configuration;

	public SampleService(ServiceProperties configuration) {
		this.configuration = configuration;
	}

	public String getHelloMessage(String name) {
		return this.configuration.getGreeting() + " " + name;
	}

}
