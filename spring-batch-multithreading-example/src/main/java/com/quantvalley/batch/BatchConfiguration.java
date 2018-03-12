package com.quantvalley.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.quantvalley.batch.listener.JobCompletionNotificationListener;
import com.quantvalley.batch.model.FxMarketEvent;
import com.quantvalley.batch.model.FxMarketVolumeStore;
import com.quantvalley.batch.model.Trade;
import com.quantvalley.batch.processor.FxMarketEventProcessor;
import com.quantvalley.batch.reader.FxMarketEventReader;
import com.quantvalley.batch.reader.ReaderTasklet;
import com.quantvalley.batch.writer.Step1Writer;
import com.quantvalley.batch.writer.Step2WriterTasklet;
import com.quantvalley.batch.writer.StockVolumeAggregator;

/**
 * The Class BatchConfiguration.
 * 
 * @author 
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	  
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public FxMarketVolumeStore fxMarketPricesStore() {
		return new FxMarketVolumeStore();
	}

	// FxMarketEventReader (Reader)
	@Bean
	public FxMarketEventReader fxMarketEventReader() {
		return new FxMarketEventReader();
	}

	// FxMarketEventProcessor (Processor)
	@Bean
	public FxMarketEventProcessor fxMarketEventProcessor() {
		return new FxMarketEventProcessor();
	}

	// StockVolumeAggregator (Writer)
	@Bean
	public StockVolumeAggregator stockVolumeAggregator() {
		return new StockVolumeAggregator();
	}

	// JobCompletionNotificationListener (File loader)
	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionNotificationListener();
	}

	// Configure job step
	@Bean
	public Job fxMarketPricesETLJob() {
		
		FlowBuilder<Flow> flowBuilder = new FlowBuilder<Flow>("flow1");

		  Flow flow =  flowBuilder
		    .start(step1())
		    .next(decision())
		    .on(decision().COMPLETED)
		    .to(step2())
		    .from(decision())
		    .on(decision().FAILED)
		    .to(step3())
		    .end();	
		
		
		return jobBuilderFactory.get("FxMarket Volume ETL Job").incrementer(new RunIdIncrementer()).listener(listener())
				.start(flow).end().build();
	}
	
	@Bean
	public TaskExecutor taskExecutor(){
	    SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor("spring_batch");
	    asyncTaskExecutor.setConcurrencyLimit(5);
	    return asyncTaskExecutor;
	}
    
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("Extract").<FxMarketEvent, Trade> chunk(100)
				.reader(fxMarketEventReader()).processor(fxMarketEventProcessor())
				.writer(new Step1Writer())
				.taskExecutor(taskExecutor()).build();

	}
	
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("Aggregate->load")
				.tasklet(new Step2WriterTasklet())
				.taskExecutor(taskExecutor()).build();

	}

	@Bean
	public Step step3() {
		return stepBuilderFactory.get("Failure")
				.tasklet(failureEventProcessor())
				.build();
	}
	
	public FailureTasklet failureEventProcessor()
	{
		return new FailureTasklet();
	}
	
	 @Bean
	 public FlowDecision decision(){
	  return new FlowDecision();
	 }
}
