package com.cts.hc.workers;

import org.springframework.batch.item.ItemProcessor;

import com.cts.hc.models.Claim_New;

public class ClaimProcessor implements ItemProcessor<Claim_New, Claim_New>{

	public Claim_New process(Claim_New arg0) throws Exception {
		System.out.println("Processing claim:" + arg0.toString());
		return arg0;
	}


}
