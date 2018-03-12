package com.cts.hc.writers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.cts.hc.model.TradeStore;
import com.cts.hc.model.Trade;



/**
 * The Class StockPriceAggregator.
 * 
 * @author 
 */
public class TradeWriter implements ItemWriter<Trade> {

	@Autowired
	private TradeStore tradeStore;

	private static final Logger log = LoggerFactory.getLogger(TradeWriter.class);

	@Override
	public void write(List<? extends Trade> trades) throws Exception {
		trades.forEach(t -> {
			
				log.trace("Adding new stock {}", t.getStock());
				tradeStore.put(t.getStock(),t);
			}
		);
	}

}
