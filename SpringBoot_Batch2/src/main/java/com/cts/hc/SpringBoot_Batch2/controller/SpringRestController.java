package com.cts.hc.SpringBoot_Batch2.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hc.workers.MessageWriter;

@RestController
@RequestMapping("/cts2")
public class SpringRestController {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job processJob;
	
	public Job getProcessJob() {
		return processJob;
	}

	public void setProcessJob(Job processJob) {
		this.processJob = processJob;
	}

	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

	@RequestMapping("/invokeJob")
	public String invokeJob(Model model)
	{
		String response = "Failed";
		
		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
				
		
		try
		{
			jobLauncher.run(processJob, jobParameters);
			model.addAttribute("Data",MessageWriter.messages);
			response = "index";
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		return response;
	}

}
