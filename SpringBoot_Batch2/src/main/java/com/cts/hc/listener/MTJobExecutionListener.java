package com.cts.hc.listener;



import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.cts.hc.models.TradeDB;
import com.cts.hc.store.TradeStore;



public class MTJobExecutionListener implements JobExecutionListener{
	

	@Autowired
	private TradeStore store;

	

	@Autowired
	private JpaItemWriter<TradeDB> writer;

	@Override
	@Transactional
	public void afterJob(JobExecution arg0) {
		if(arg0.getStatus() == BatchStatus.COMPLETED) {
			List<TradeDB> dblist = new ArrayList<TradeDB>();
			System.out.println(store.toString());
					dblist.addAll(store.values()) ;
			writer.write(dblist);
			
		}
		
	}

	@Override
	public void beforeJob(JobExecution arg0) {
		// TODO Auto-generated method stub
		
	}

}
