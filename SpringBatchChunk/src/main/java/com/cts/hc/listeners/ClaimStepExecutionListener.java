package com.cts.hc.listeners;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class ClaimStepExecutionListener implements StepExecutionListener{

	public ExitStatus afterStep(StepExecution arg0) {
		System.out.println(arg0.toString());
		return arg0.getExitStatus();
	}

	public void beforeStep(StepExecution arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
