package com.cts.hc.workers;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.cts.hc.models.Trade;
import com.cts.hc.models.TradeDB;
import com.cts.hc.store.TradeStore;

public class TradeProcessor  implements ItemProcessor<Trade, TradeDB> {

	@Autowired
	private TradeStore store;

	@Override
	public TradeDB process(Trade arg0) throws Exception {
		
		TradeDB dbobj = store.get(arg0.getStock());
		if(dbobj == null)
		{
			dbobj = new TradeDB();	
			dbobj.setStock(arg0.getStock());
			store.put(arg0.getStock(), dbobj);
			System.out.println("adding to store: "+arg0.getStock());
		}
		dbobj.setTotalprice(dbobj.getTotalprice() + (arg0.getPrice()*arg0.getShares()));
		dbobj.setTotalshares(dbobj.getTotalshares() + arg0.getShares());
		dbobj.setAvgprice(dbobj.getTotalprice()/dbobj.getTotalshares());
		
		return dbobj;
		
	}

}
