package com.cts.hc.SpringBoot_Batch2.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.hc.workers.MessageWriter;

@Controller
@RequestMapping("/cts")
public class SpringBatchController {
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job processJob1;
	
	public Job getProcessJob() {
		return processJob1;
	}

	public void setProcessJob(Job processJob) {
		this.processJob1 = processJob;
	}

	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

	//@RequestMapping(path="/abcd", method=RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	@RequestMapping(path="/abcd", method=RequestMethod.GET)
	public String get() 
	{
		return "Home";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String get2() 
	{
		return "index";
	}

	@RequestMapping("/invokeJob")
	public String invokeJob(Model model)
	{
		String response = "Failed";
		
		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
				
		
		try
		{
			jobLauncher.run(processJob1, jobParameters);
			model.addAttribute("Data",MessageWriter.messages);
			response = "index";
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		return response;
	}


}
