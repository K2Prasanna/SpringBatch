package com.cts.hc.workers;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;


public class MessageReader implements ItemReader<String>{
	
	private String[] messages = {"this is my message","abhcsdkj skdjsdhj","therhiusoos","agusyyejmfbfugior","asbhasdgydgsiu"};

	
	private int startsAt = 0;
	
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		String message = null;
		
		if(startsAt < messages.length)
		{
			message = messages[startsAt];
			startsAt++;
			
		}
		else
		{
			startsAt = 0;
		}
		return message;
	}

}
 