package com.cts.hc.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cts.hc.listener.MessageListener;
import com.cts.hc.workers.MessageProcessor;
import com.cts.hc.workers.MessageReader;
import com.cts.hc.workers.MessageWriter;

//@Configuration
public class MessageConfigurer {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	public JobBuilderFactory getJobBuilderFactory() {
		return jobBuilderFactory;
	}

	public void setJobBuilderFactory(JobBuilderFactory jobBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
	}

	public StepBuilderFactory getStepBuilderFactory() {
		return stepBuilderFactory;
	}

	public void setStepBuilderFactory(StepBuilderFactory stepBuilderFactory) {
		this.stepBuilderFactory = stepBuilderFactory;
	}
	
	@Bean
	public Job processJob()
	{
		return jobBuilderFactory.get("antJob").incrementer(new RunIdIncrementer())
				.listener(new MessageListener()).flow(getStep()).end()
				.build();
	}
	
	@Bean
	public Step getStep()
	{
		return stepBuilderFactory.get("step1").<String, String> chunk(1)
				.reader(new MessageReader())
				.processor(new MessageProcessor())
				.writer(new MessageWriter()).build();
	}
	
	@Bean
	public ResourcelessTransactionManager transactionManager()
	{
		return new ResourcelessTransactionManager();
	}
	

}
