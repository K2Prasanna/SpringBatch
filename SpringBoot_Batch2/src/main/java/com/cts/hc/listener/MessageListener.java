package com.cts.hc.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class MessageListener implements JobExecutionListener{

	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Job started");
		
	}

	public void afterJob(JobExecution jobExecution) {
		System.out.println("Job completed");	
		
		
	}

	
}
