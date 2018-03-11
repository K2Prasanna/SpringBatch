package com.cts.hc.store;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Component;

import com.cts.hc.models.TradeDB;


public class TradeStore {

	private ConcurrentMap<String, TradeDB> stockPrices = new ConcurrentHashMap<String, TradeDB>();
	
		public boolean containsKey(Object key) {
			return stockPrices.containsKey(key);
		}
	
		public TradeDB put(String key, TradeDB value) {
			return stockPrices.put(key, value);
		}
	
		public Collection<TradeDB> values() {
			return stockPrices.values();
		}
	
		public TradeDB get(Object key) {
			return stockPrices.get(key);
		}

}
