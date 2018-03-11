package com.cts.hc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Claim_New {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	private int claimId;	

	private String doc;
	private int amount;
	private String policyHolderName;

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}

	
	public int getClaimId() {
		return claimId;
	}
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}
	public String getDoc() {
		return doc;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getPolicyHolderName() {
		return policyHolderName;
	}
	public void setPolicyHolderName(String policyHolderName) {
		this.policyHolderName = policyHolderName;
	}

	public void setDoc(String doc) {
		//System.out.println(doc);
		this.doc = doc;
	}

	@Override
	public String toString() {
		return "claimId, doc, amount, policyHolder: " + this.claimId + this.doc + this.amount + this.policyHolderName;
	}
}
