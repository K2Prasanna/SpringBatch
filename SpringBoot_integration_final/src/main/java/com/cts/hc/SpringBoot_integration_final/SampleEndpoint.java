package com.cts.hc.SpringBoot_integration_final;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.util.StreamUtils;

@MessageEndpoint
public class SampleEndpoint {

	private final SampleService sampleService;

	public SampleEndpoint(SampleService sampleService) {
		this.sampleService = sampleService;
	}

	@ServiceActivator
	public String hello(File input) throws Exception {
		FileInputStream in = new FileInputStream(input);
		String name = new String(StreamUtils.copyToByteArray(in));
		System.out.println(name);
		in.close();
		return this.sampleService.getHelloMessage(name);
	}

}