package com.cts.hc.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

/**
 * The Class FxMarketPricesStore.
 *
 * @author 
 */
@Configuration
public class TradeStore {
	
	private Map<String, Trade> tradeCopy = new HashMap<String, Trade>();

	public boolean containsKey(Object key) {
		return tradeCopy.containsKey(key);
	}

	public Trade put(String key, Trade value) {
		return tradeCopy.put(key, value);
	}

	public Collection<Trade> values() {
		return tradeCopy.values();
	}

	public Trade get(Object key) {
		return tradeCopy.get(key);
	}

}
