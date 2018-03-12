package com.cts.hc.listeners;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.cts.hc.model.Trade;
import com.cts.hc.model.TradeStore;


/**
 * The Class JobCompletionNotificationListener
 *
 * @author 
 */
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private static final String HEADER = "stock,time,prices,shares";

	private static final String LINE_DILM = ",";

	@Autowired
	private TradeStore tradeStore;

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.trace("Loading the results into file");
			Path path = Paths.get("prices.csv");
			try (BufferedWriter fileWriter = Files.newBufferedWriter(path)) {
				fileWriter.write(HEADER);
				fileWriter.newLine();
				for (Trade trade : tradeStore.values()) {
					fileWriter.write(new StringBuilder().append(trade.getStock())
							.append(LINE_DILM).append(trade.getTime())
							.append(LINE_DILM).append(trade.getPrice())
							.append(LINE_DILM).append(trade.getShares())
							.toString());
					fileWriter.newLine();
				}
			} catch (Exception e) {
				log.error("Fetal error: error occurred while writing {} file", path.getFileName());
			}
		}
	}
}
