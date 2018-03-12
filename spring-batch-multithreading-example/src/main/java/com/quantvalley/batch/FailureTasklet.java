package com.quantvalley.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class FailureTasklet implements Tasklet{

	@Override
	public RepeatStatus execute(StepContribution stepc, ChunkContext cc) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Failure Status....."+stepc.getExitStatus());
		return RepeatStatus.FINISHED;
	}

}
