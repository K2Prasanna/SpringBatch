package com.cts.hc.models;

public class Claim {
	private int claimId;
	private String doc;
	private int amount;
	private String policyHolderName;

	
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
