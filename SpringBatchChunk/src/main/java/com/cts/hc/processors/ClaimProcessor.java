package com.cts.hc.processors;

import org.springframework.batch.item.ItemProcessor;

import com.cts.hc.models.Claim;

public class ClaimProcessor implements ItemProcessor<Claim, Claim>{

	public Claim process(Claim arg0) throws Exception {
		System.out.println("Processing claim:" + arg0.toString());
		return arg0;
	}


}
