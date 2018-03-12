package com.quantvalley.batch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

public class FlowDecision implements JobExecutionDecider{
	public static final String COMPLETED = "COMPLETED";
    public static final String FAILED = "FAILED";
	@Override
	public FlowExecutionStatus decide(JobExecution jobex, StepExecution stepEx) {
		
		if(stepEx.getStatus() == BatchStatus.COMPLETED)
		{
			System.out.println("hitting....");
			return FlowExecutionStatus.COMPLETED;
		
		}
	     return FlowExecutionStatus.FAILED;
		
	}

}
