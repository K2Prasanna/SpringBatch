package com.cts.hc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TradeDB {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

	private String stock;
	private long totalshares;
	private float totalprice;
	private float avgprice;
	
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}

	
	public long getTotalshares() {
		return totalshares;
	}
	public void setTotalshares(long totalshares) {
		this.totalshares = totalshares;
	}
	public float getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public float getAvgprice() {
		return avgprice;
	}
	public void setAvgprice(float avgprice) {
		this.avgprice = avgprice;
	}

	
	

}
