package com.cts.hc.configurations;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cts.hc.listeners.JobCompletionNotificationListener;
import com.cts.hc.model.Trade;
import com.cts.hc.processors.FxMarketEventProcessor;
import com.cts.hc.readers.FxMarketEvent;
import com.cts.hc.readers.FxMarketEventReader;
import com.cts.hc.writers.TradeWriter;




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

	// TradeWriter (Writer)
		@Bean
		public TradeWriter tradeWriter() {
			return new TradeWriter();
		}

	// JobCompletionNotificationListener (File loader)
		@Bean
		public JobExecutionListener jobExecutionListener() {
			return new JobCompletionNotificationListener();
		}
	
	
	@Bean
	public SkipPolicy fileVerificationSkipper() {
		return new FileVerificationSkipper();
	}

	// Configure job step
	@Bean
	public Job fxMarketPricesETLJob() {
		return jobBuilderFactory.get("FxMarket Prices ETL Job").incrementer(new RunIdIncrementer()).listener(jobExecutionListener())
				.flow(etlStep()).end().build();
	}

	@Bean
	public Step etlStep() {
		return stepBuilderFactory.get("Extract -> Transform -> Aggregate -> Load").<FxMarketEvent, Trade> chunk(10000)
				.reader(fxMarketEventReader()).faultTolerant().skipPolicy(fileVerificationSkipper()).processor(fxMarketEventProcessor())
				.writer(tradeWriter())
				.build();
	}

}
