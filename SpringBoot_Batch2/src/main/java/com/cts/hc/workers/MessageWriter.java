package com.cts.hc.workers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class MessageWriter implements ItemWriter<String>{

	public static ArrayList<String> messages =  new ArrayList<String>();
	public void write(List<? extends String> arg0) throws Exception {
		for(String msg : arg0)
		{
			
			messages.clear();
			messages.addAll(arg0);
			if(msg != null)
			{
				System.out.println("writer sending message.."+msg);
			}
		}
		
	}

}
