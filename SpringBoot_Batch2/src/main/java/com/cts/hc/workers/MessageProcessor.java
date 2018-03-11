package com.cts.hc.workers;

import org.springframework.batch.item.ItemProcessor;

public class MessageProcessor implements ItemProcessor<String, String>{

	public String process(String arg0) throws Exception {
		
		return arg0.toUpperCase();
	}
	
	

}
