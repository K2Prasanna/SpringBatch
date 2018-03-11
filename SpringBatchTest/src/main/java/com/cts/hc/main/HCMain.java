package com.cts.hc.main;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HCMain {

	public static void main(String[] args) throws JobExecutionAlreadyRunningException, JobRestartException, 
	JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		String[] springConfig = { "jobConfig.xml" };
		ApplicationContext ac = new ClassPathXmlApplicationContext(springConfig);
		
		JobLauncher jl = (JobLauncher)ac.getBean("jobLauncher");
		Job job = (Job)ac.getBean("claimJob");
		JobExecution je = jl.run(job, new JobParameters());
		
		System.out.println("Spring batch claim status: "+je.getStatus());

	}

}
